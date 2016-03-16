var puerto = 8080;
var delegacion='';var secciones='';var casillas='';
var votos='';var validos=''; var nulos=''; var total=''; var lista=''; var participacion='';
var datos='';
var pan=''; var pri=''; var prd=''; 
var pt='';var pvem=''; var movimiento=''; 
var pps=''; var pfcrn=''; var parm='';
var uno=''; var ac=''; var am='';
var pcd='';var parm=''; var dsppn='';
var alianza=''; var porelbien=''; var nuevaalianza='';
var alternativa=''; var pripvem=''; var prdptmc='';
var prdpt='';var prdmc='';var ptmc='';
var info=[];

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
        var año= $('#año').val();
        var par=$('#param').val();
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
                    datosDelegacion(CVE,año,par);
                    var Estado = $('#nodelist').get(0).children[0].contentDocument.children[0].children[1].children[0].children[1].children[1].children[5].innerHTML;
                    console.log(Estado + ":" + CVE);
                    Estado = nombreRealDelegacion(Estado);
                    
                     if(par!=null){
                           
                            if(par.includes('participacion'))
                            {
/****************************************************************************/
datos="<div id='mostrardatos'>\n\
            <div class='span3 tiny'> \n\
                            <div class='pricing-table-header-tiny'>\n\
                                    <h2 align='left'>Delegación:</h2>\n\
                                    <h3 align='left'>"+Estado+"</h3>\n\
                             </div>\n\
                              <div class='pricing-table-features'>";
                            for(x=0;x<info.length;x++){
                                var y=info[x];
                                console.log(y);
                                datos=datos+"<p align='left'>"+y+"</p>";
                            }
                            info.length=0;
                            datos=datos+"</div>\n\
            </div>\n\
        </div>";  
        
      

/****************************************************************************/
                
                           }else if(par.includes('partido')){
                              
datos="<div id='mostrardatos'>\n\
            <div class='span3 tiny'> \n\
                            <div class='pricing-table-header-tiny'>\n\
                                    <h2 align='left'>Delegación:</h2>\n\
                                    <h3 align='left'>"+Estado+"</h3>\n\
                             </div>\n\
                              <div class='pricing-table-features'>";
                            for(x=0;x<info.length;x++){
                                var y=info[x];
                                console.log(y);
                                datos=datos+"<p align='left'>"+y+"</p>";
                            }
                            info.length=0;
                            datos=datos+"</div>\n\
            </div>\n\
        </div>";  
                              
                               
                             
                           }
                           
                        }
                    
                    $('#idEstadoActivo').get(0).innerHTML =datos;
                  //  $('#idEstadoActivo').get(0).innerHTML ='Parametro: '+par+'Año'+año+' '+ Estado +'<br>Votos:' + votos + '<br>Votos Nulos:' + nulos+ '<br> Votos Totales:'+total+'<br>Lista Nominal:'+lista+'<br>'+participacion;
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


function datosDelegacion(idEstado,fecha,par){
    var p=par;
    console.log(p);
    var f=fecha;
$.ajax({  
            type: "GET",  
            url: "http://localhost:8080/TT/ObtenerDelegacion",  
            data: "Clave=" +idEstado+ "&ano=ano" +fecha,  
            success: function(result){
                if(p.includes('participacion')){
                    if(f.includes('1994')){
                
                info.push("Secciones:"+result.Secciones);
              
                info.push("Casillas:"+result.Casillas);
         
                
                
                info.push("Votos No Registrados:"+result.DNoRegistrados);
               
                info.push("Votos validos:"+result.Validos);
                
                info.push("Votos nulos:"+result.DNulos);
              
                info.push("Votos totales:"+result.Total);
                
                info.push("Lista Nominal"+result.Lista);
                
                info.push("Participación Ciudadana:"+result.Participacion);

            }
            if(f.includes('2000')){
               info.push("Secciones:"+result.Secciones);
              
                info.push("Casillas:"+result.Casillas);
                
                
                info.push("Votos No Registrados:"+result.DNoRegistrados);
               
                info.push("Votos validos:"+result.Validos);
                
                info.push("Votos nulos:"+result.DNulos);
              
                info.push("Votos totales:"+result.Total);
                
                info.push("Lista Nominal"+result.Lista);
                
                info.push("Participación Ciudadana:"+result.Participacion);
                
            }
            if(f.includes('2006')){
                info.push("Secciones:"+result.Secciones);
              
                info.push("Casillas:"+result.Casillas);
               
                
                info.push("Votos No Registrados:"+result.DNoRegistrados);
               
                info.push("Votos validos:"+result.Validos);
                
                info.push("Votos nulos:"+result.DNulos);
              
                info.push("Votos totales:"+result.Total);
                
                info.push("Lista Nominal"+result.Lista);
                
                info.push("Participación Ciudadana:"+result.Participacion);
            
            }
            if(f.includes('2012')){
                 info.push("Secciones:"+result.Secciones);
              
                info.push("Casillas:"+result.Casillas);
                
               
                info.push("Votos No Registrados:"+result.DNoRegistrados);
                info.push("Votos nulos:"+result.DNulos);
              
                info.push("Votos totales:"+result.Total);
                
                info.push("Lista Nominal"+result.Lista);
                
                 info.push("Participación Ciudadana:"+result.Participacion);
                
              
               
               
                }    
                }else if(p.includes('partido')){
                    
                    if(f.includes('1994')){
                
                info.push("Secciones:"+result.Secciones);
              
                info.push("Casillas:"+result.Casillas);
         
                info.push("PAN:"+result.PAN);
               
                info.push("PRI:"+result.PRI);
                
                info.push("PPS:"+result.PPS);
                
                info.push("PRD:"+result.PRD);
               
                info.push("PFCRN:"+result.PFCRN);
                
                info.push("PARM:"+result.PARM);
                
                info.push("UNO:"+result.UNO);
                
                info.push("PT:"+result.PT);
                
                info.push("PVEM:"+result.PVEM);
                
                info.push("Votos No Registrados:"+result.DNoRegistrados);
               
                info.push("Votos validos:"+result.Validos);
                
                info.push("Votos nulos:"+result.DNulos);
              
                info.push("Votos totales:"+result.Total);
                
                info.push("Lista Nominal"+result.Lista);
                
                info.push("Participación Ciudadana:"+result.Participacion);

            }
            if(f.includes('2000')){
               info.push("Secciones:"+result.Secciones);
              
                info.push("Casillas:"+result.Casillas);
                info.push("AC:"+result.AC);
               
                info.push("PRI:"+result.PRI);
                
                info.push("AM:"+result.AM);
                
                info.push("PCD:"+result.PCD);
                
                info.push("PARM"+result.PARM);
                
                info.push("DSPPN:"+result.DSPPN);
                
                info.push("Votos No Registrados:"+result.DNoRegistrados);
               
                info.push("Votos validos:"+result.Validos);
                
                info.push("Votos nulos:"+result.DNulos);
              
                info.push("Votos totales:"+result.Total);
                
                info.push("Lista Nominal"+result.Lista);
                
                info.push("Participación Ciudadana:"+result.Participacion);
                
            }
            if(f.includes('2006')){
                info.push("Secciones:"+result.Secciones);
              
                info.push("Casillas:"+result.Casillas);
                info.push("PAN:"+result.PAN);
                
                info.push("Alianza por México:"+result.Alianza);
               
                info.push("Por el bien de todos:"+result.Porelbien);
               
                info.push("Nueva Alianza:"+result.NuevaAlianza);
                
                info.push("Alternativa:"+result.Alternativa);
                
                info.push("Votos No Registrados:"+result.DNoRegistrados);
               
                info.push("Votos validos:"+result.Validos);
                
                info.push("Votos nulos:"+result.DNulos);
              
                info.push("Votos totales:"+result.Total);
                
                info.push("Lista Nominal"+result.Lista);
                
                info.push("Participación Ciudadana:"+result.Participacion);
            
            }
            if(f.includes('2012')){
                 info.push("Secciones:"+result.Secciones);
              
                info.push("Casillas:"+result.Casillas);
                info.push("PAN:"+result.PAN);
               
                info.push("PRI:"+result.PRI);
              
                info.push("PRD:"+result.PRD);
                
                info.push("PVEM:"+result.PVEM);
                
                info.push("PT:"+result.PT);
                
                info.push("Movimiento Ciudadano:"+result.Movimiento);
               
                info.push("Nueva Alianza:"+result.NuevaAlianza);
                
                info.push("Coalición PRI-PVEM:"+result.PRIPVEM);
                
                info.push("Coalición PRD-PT-MC:"+result.PRDPTMC);
                
                info.push("Coalición PRD-PT:"+result.PRDPT);
                
                info.push("Coalición PRD-MC:"+result.PRDMC);
               
                info.push("Coalición PT-MC:"+result.PTMC);
               
                info.push("Votos No Registrados:"+result.DNoRegistrados);
                info.push("Votos nulos:"+result.DNulos);
              
                info.push("Votos totales:"+result.Total);
                
                info.push("Lista Nominal"+result.Lista);
                
                 info.push("Participación Ciudadana:"+result.Participacion);
                
              
               
               
                } 
                    
                }
                   
                        
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert("Error: "+xhr.status);
                alert("Error: "+ thrownError);
            }
          }); 


   
}
