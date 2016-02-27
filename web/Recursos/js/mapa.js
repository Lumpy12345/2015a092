var puerto = 8080;

function asignLayerGroup(cve)
{
  return new ol.layer.Group
  (
    {
      layers: 
      [
            new ol.layer.Image
            ({
                  opacity : 0.9,
                  source: new ol.source.ImageWMS
                  ({
                        ratio: 1,
                        url: 'http://localhost:8080/geoserver/cite/wms',
                        params: 
                        {
                              'FORMAT': 'image/png',
                              'VERSION': '1.1.0',  
                              LAYERS: 'cite:DF',
                              STYLES: '',
                              'cql_filter' : "cve_mun = \'" + cve + "\'"
                        }

                  })

            })
            ,
            new ol.layer.Image
            ({
                  opacity : 0.2,
                  source: new ol.source.ImageWMS
                  ({
                        ratio: 1,
                        url: 'http://localhost:' + puerto + '/geoserver/cite/wms',
                        params: 
                        {
                              'FORMAT': 'image/png',
                              'VERSION': '1.1.0',  
                              LAYERS: 'cite:DF',
                              STYLES: '',
                        }

                  })


            })
      ]
    }
  );
}

function nombreRealDelegacion(estado)
{
  if(estado == 'Cuauhtmoc')
    return 'Cuauhtémoc';
  if(estado == 'lvaro Obregn')
    return 'Álvaro Obregón';
  if(estado == 'Benito Jurez')
    return 'Benito Juárez';
  if(estado == 'Coyoacn')
    return 'Coyoacán';
  if(estado == 'Tlhuac')
    return 'Tláhuac';

  return estado;
}




var format = 'image/png';
var untiled = new ol.layer.Image({
        source: new ol.source.ImageWMS({
          ratio: 1,
          url: 'http://localhost:' + puerto + '/geoserver/cite/wms',
          params: {'FORMAT': format,
                   'VERSION': '1.1.0',  
                LAYERS: 'cite:DF',
                STYLES: '',
          }
        })
      });
      var tiled = new ol.layer.Tile({
        visible: false,
        source: new ol.source.TileWMS({
          url: 'http://localhost:' + puerto + '/geoserver/cite/wms',
          params: {'FORMAT': format, 
                   'VERSION': '1.1.0',
                   tiled: true,
                LAYERS: 'cite:DF',
                STYLES: '',
          }
        })
      });
      
  var map = new ol.Map
  (
  {



    layers: 
    [
          new ol.layer.Image
          ({
                opacity : 1,
                source: new ol.source.ImageWMS
                ({
                      ratio: 1,
                      url: 'http://localhost:' + puerto + '/geoserver/cite/wms',
                      params: 
                      {
                            'FORMAT': 'image/png',
                            'VERSION': '1.1.0',  
                            LAYERS: 'cite:DF',
                            STYLES: '',
                      }

                })


          })
    ],

    view: new ol.View({
      center: ol.proj.transform([-58.0930, 2.7979], 'EPSG:4326', 'EPSG:3857'),
      zoom: 9
    }),
    target: 'map'
  }
  );

   map.on('singleclick', function(evt) {
        document.getElementById('nodelist').innerHTML = "Loading... please wait...";
        var view = map.getView();
        var viewResolution = view.getResolution();
        var source = untiled.get('visible') ? untiled.getSource() : tiled.getSource();
        var url = source.getGetFeatureInfoUrl(
          evt.coordinate, viewResolution, view.getProjection(),
          {'INFO_FORMAT': 'text/html', 'FEATURE_COUNT': 50});
        if (url) 
        {

          
           //'<iframe seamless src="' + url + '"></iframe>';
          var IFRAME = '<iframe seamless id="idIFRAME"></iframe>';

          document.getElementById('nodelist').innerHTML = IFRAME;

          $('#nodelist').hide();
            $("#idIFRAME").load
            (         
              function()
              {

                setTimeout
                (
                  function()
                  {
                    var CVE = $('#nodelist').get(0).children[0].contentDocument.children[0].children[1].children[0].children[1].children[1].children[4].innerHTML;
                    var Estado = $('#nodelist').get(0).children[0].contentDocument.children[0].children[1].children[0].children[1].children[1].children[5].innerHTML;
                    console.log(Estado + ":" + CVE);
                    Estado = nombreRealDelegacion(Estado);
                    $('#idEstadoActivo').get(0).innerHTML = Estado;
                    $('#idEstadoActivo').hide();
                    $('#idEstadoActivo').fadeIn(600);

                    var newLayerGroup = asignLayerGroup(CVE);
                    map.setLayerGroup(newLayerGroup);
                  }
                  ,
                  1000
                );
              }
            );
            $('#idIFRAME').attr('src',url);
        }
      });



