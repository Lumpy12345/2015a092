var mousePositionControl = new ol.control.MousePosition({
        coordinateFormat: ol.coordinate.createStringXY(4),
        projection: 'EPSG:4326',
        // comment the following two lines to have the mouse position
        // be placed within the map.
        className: 'custom-mouse-position',
        target: document.getElementById('mouse-position'),
        undefinedHTML: '&nbsp;'
      });

var format = 'image/png';
var untiled = new ol.layer.Image({
        source: new ol.source.ImageWMS({
          ratio: 1,
          url: 'http://localhost:8080/geoserver/cite/wms',
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
          url: 'http://localhost:8080/geoserver/cite/wms',
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


    controls: ol.control.defaults({
          attributionOptions: /** @type {olx.control.AttributionOptions} */ ({
            collapsible: false
          })
        }).extend([mousePositionControl]),


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
                            'cql_filter' : "cve_mun = '005'"
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
                      url: 'http://localhost:8080/geoserver/cite/wms',
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
                    var Estado = $('#nodelist').get(0).children[0].contentDocument.children[0].children[1].children[0].children[1].children[1].children[5].innerHTML;
                    console.log(Estado);
                    $('#idEstadoActivo').get(0).innerHTML = Estado;
                    $('#idEstadoActivo').hide();
                    $('#idEstadoActivo').fadeIn(600);
                  }
                  ,
                  1000
                );
              }
            );
            $('#idIFRAME').attr('src',url);
        }
      });



