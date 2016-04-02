var puerto = 8080;
var delegacion='';
var datos='';
var info=[];
var parti=[];
var ctx ='';
var myNewChart ='';
var graf=[];
var ca=new Array();
var nor=new Array();
var val=new Array();
var nul=new Array();
var tot=new Array();
var lis=new Array();
var partic=new Array();
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
      zoom: 10
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
                    
                    if(par!=null)
                    {
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
                                                                        for(x=0;x<info.length;x++)
                                                                        {
                                                                            var y=info[x];
                                                                            console.log(y);
                                                                            datos=datos+"<p align='left'>"+y+"</p>";
                                                                        }
                                                            info.length=0;
                                                            datos=datos+"</div>\n\
                                            </div>\n\
                                        </div>";  
                                /****************************************************************************/
                            }

                            else 

                            if(par.includes('partido'))
                            {

                                        datos="<div id='mostrardatos'>\n\
                                                    <div class='span3 tiny'> \n\
                                                                    <div class='pricing-table-header-tiny'>\n\
                                                                            <h2 align='left'>Delegación:</h2>\n\
                                                                            <h3 align='left'>"+Estado+"</h3>\n\
                                                                     </div>\n\
                                                                      <div class='pricing-table-features'>";
                                                                                for(x=0;x<info.length;x++)
                                                                                {
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
                        
                     
                    
                 /*   $('#idEstadoActivo').get(0).innerHTML =datos;
                    $('#idEstadoActivo').hide();
                    $('#idEstadoActivo').fadeIn(0);
                   */
                  
                    grafica(par,CVE);
                   
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
      
      
   

function grafica(par,idEstado)
{
        var para=par;
        var i=idEstado;
    
        if(para.includes('participacion'))
        {
                compararParticipacion(i);
                
                var pieChartContent = document.getElementById('graficaPartidos');
                pieChartContent.innerHTML = '&nbsp;';
                $('#graficaPartidos').append('<canvas id="myChart" width="800" height="600"></canvas>');
                var data = 
                {
                        labels:["Casillas","Votos No Registrados","Votos Validos","Votos Nulos","Votos Totales","Lista Nominal","Participación Ciudadana"],  
                        datasets:
                        [
                                {
                                            fillColor: "rgba(255,105,180,0.2)",
                                            strokeColor: "rgba(255,105,180,1)",
                                            pointColor: "rgba(220,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(220,220,220,1)",
                                            data: [ca[0],nor[0],val[0],nul[0],tot[0],lis[0],partic[0]],
                                            label: "Año 1994"
                                },
                                {
                                            fillColor: "rgba(152,251,152,0.2)",
                                            strokeColor: "rgba(152,251,152,1)",
                                            pointColor: "rgba(151,187,205,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(151,187,205,1)",
                                            data: [ca[1],nor[1],val[1],nul[1],tot[1],lis[1],partic[1]],
                                            label: "Año 2000"
                                },
                                {
                                            fillColor : "rgba(147,112,219,0.2)",
                                            strokeColor : "rgba(147,112,219,1)",
                                            pointColor : "rgba(320,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(51,187,205,1)",
                                            data: [ca[2],nor[2],val[2],nul[2],tot[2],lis[2],partic[2]],
                                            label: "Año 2006"
                                },
                                {
                                            fillColor: "rgba(255,215,0,0.1)",
                                            strokeColor: "rgba(255,215,0,1)",
                                            pointColor: "rgba(220,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(220,220,220,1)",
                                            data: [ca[3],nor[3],val[3],nul[2],tot[2],lis[3],partic[3]],
                                            label: "Año 2012"
                                }
                        ]
                };
                graf.length=0;
                console.log(data);
                var ctx = document.getElementById("myChart").getContext("2d");
                new Chart(ctx).Line(data);
                legend(document.getElementById("js-legend"), data);
                // 
                legend(document.getElementById("js-legend"), data);
        }
      
        if(para.includes('partido'))
        {
                var myPieChart=null;
                var pieChartContent = document.getElementById('graficaPartidos');
                pieChartContent.innerHTML = '&nbsp;';
                $('#graficaPartidos').append('<canvas id="myChart" width="400" height="400"></canvas>');
                var ctx = document.getElementById("myChart").getContext("2d");
                //*********************************************************************
                var g=[];
                        for(y=1;y<graf.length;y++)
                        {
                            var value=graf[y].voto;
                            var nom=graf[y].nombre;
                            g.push({
                                        label: nom,
                                        value: value
                                    });
                        }
                        graf.length=0;
                        g.forEach(function (e, i)
                             {
                                 e.color = '#'+('00000'+(Math.random()*16777216<<0).toString(16)).substr(-6);
                                 //"hsl(" + (i / g.length * 360) + ", 50%, 50%)";
                                // e.highlight = "hsl(" + (i / g.length * 360) + ", 50%, 70%)";
                            })
                        console.log(g);
                //************************************************************************
                myPieChart = new Chart(ctx).Pie(g,{tooltipTemplate:"<%=label%>: <%=value%>g"});
                legend(document.getElementById("js-legend"), g, myPieChart, "<%=label%>: <%=value%>");
        }
}

function partido(nombre,color,high,voto){
    this.nombre=nombre;
    this.color=color;
    this.high=high;
    this.voto=voto;
}

function ParticipacionC(casillas,noreg,validos,nulos,total,lista,parti,year){
    this.Casilla=casillas;
    this.NoRegistrado=noreg;
    this.Validos=validos;
    this.Nulos=nulos;
    this.Total=total;
    this.Lista=lista;
    this.Participacion=parti;
    this.Year=year;
}

function compararParticipacion(idEstado)
{
    $.ajax({  
            type: "GET",  
            async: false,
            url: "http://localhost:8080/TT/CompararParticipacion",  
            data: "Clave=" +idEstado,  
            success: function(result)
            {
                ca=result.Casilla.split(",");
                nor=result.Nore.split(",");
                val=result.Vali.split(",");  
                nul=result.Nul.split(",");     
                tot=result.Total.split(",");     
                lis=result.Lista.split(",");     
                partic=result.Part.split(","); 
            }
        });
}

function datosDelegacion(idEstado,fecha,par){
    
    
    var p=par;
    console.log(p);
    var f=fecha;
    
    
$.ajax({  
            type: "GET",  
            async: false,
            url: "http://localhost:8080/TT/ObtenerDelegacion",  
            data: "Clave=" +idEstado+ "&ano=ano" +fecha,  
            success: function(result)
            {
                
                        if(p.includes('participacion'))
                        {
                                if(f.includes('1994'))
                                {

                                       info.push("Secciones:"+result.Secciones);
                                       info.push("Casillas:"+result.Casillas);
                                       info.push("Votos No Registrados:"+result.DNoRegistrados);
                                       info.push("Votos validos:"+result.Validos);
                                       info.push("Votos nulos:"+result.DNulos);
                                       info.push("Votos totales:"+result.Total);
                                       info.push("Lista Nominal"+result.Lista);
                                       info.push("Participación Ciudadana:"+result.Participacion);
                                       graf.push(new ParticipacionC(result.Casillas,result.DNoRegistrados,result.Validos,result.DNulos,result.Total,result.Lista,result.Participacion,"Año 1994"));

                                }
                               if(f.includes('2000'))
                               {
                                       info.push("Secciones:"+result.Secciones);
                                       info.push("Casillas:"+result.Casillas);
                                       info.push("Votos No Registrados:"+result.DNoRegistrados);
                                       info.push("Votos validos:"+result.Validos);
                                       info.push("Votos nulos:"+result.DNulos);
                                       info.push("Votos totales:"+result.Total);
                                       info.push("Lista Nominal"+result.Lista);
                                       info.push("Participación Ciudadana:"+result.Participacion);
                                       graf.push(new ParticipacionC(result.Casillas,result.DNoRegistrados,result.Validos,result.DNulos,result.Total,result.Lista,result.Participacion,"Año 2000"));
                               }
                               if(f.includes('2006'))
                               {
                                       info.push("Secciones:"+result.Secciones);
                                       info.push("Casillas:"+result.Casillas);
                                       info.push("Votos No Registrados:"+result.DNoRegistrados);
                                       info.push("Votos validos:"+result.Validos);
                                       info.push("Votos nulos:"+result.DNulos);
                                       info.push("Votos totales:"+result.Total);
                                       info.push("Lista Nominal"+result.Lista);
                                       info.push("Participación Ciudadana:"+result.Participacion);
                                       graf.push(new ParticipacionC(result.Casillas,result.DNoRegistrados,result.Validos,result.DNulos,result.Total,result.Lista,result.Participacion,"Año 2006"));
                               }
                               if(f.includes('2012'))
                               {
                                       info.push("Secciones:"+result.Secciones);
                                       info.push("Casillas:"+result.Casillas);
                                       info.push("Votos No Registrados:"+result.DNoRegistrados);
                                       info.push("Votos nulos:"+result.DNulos);
                                       info.push("Votos totales:"+result.Total);
                                       info.push("Lista Nominal"+result.Lista);
                                       info.push("Participación Ciudadana:"+result.Participacion);
                                       graf.push(new ParticipacionC(result.Casillas,result.DNoRegistrados,result.Validos,result.DNulos,result.Total,result.Lista,result.Participacion,"Año 2012"));
                               }    
                          }
                          else 
                          if(p.includes('partido'))
                          {
                                    if(f.includes('1994'))
                                    {
                                            graf.push(new partido("Votos totales","","",result.Total));

                                            info.push("Secciones:"+result.Secciones);
                                            info.push("Casillas:"+result.Casillas);
                                            info.push("PAN:"+result.PAN);//graf.push("PAN:"+result.PAN);
                                            graf.push(new partido("PAN","014aa6","014aa6",result.PAN));
                                            info.push("PRI:"+result.PRI);//graf.push("PRI:"+result.PRI);
                                            graf.push(new partido("PRI","CC0000","CC0000",result.PRI));
                                            info.push("PPS:"+result.PPS);//graf.push("PPS:"+result.PPS);
                                            graf.push(new partido("PPS","","",result.PPS));
                                            info.push("PRD:"+result.PRD);//graf.push("PRD:"+result.PRD);
                                            graf.push(new partido("PRD","","",result.PRD));
                                            info.push("PFCRN:"+result.PFCRN);//graf.push("PFCRN:"+result.PFCRN);
                                            graf.push(new partido("PFCRN","","",result.PFCRN));
                                            info.push("PARM:"+result.PARM);//graf.push("PARM:"+result.PARM);
                                            graf.push(new partido("PARM","","",result.PARM));
                                            info.push("UNO:"+result.UNO);//graf.push("UNO:"+result.UNO);
                                            graf.push(new partido("UNO","","",result.UNO));
                                            info.push("PT:"+result.PT);//graf.push("PT:"+result.PT);
                                            graf.push(new partido("PT","","",result.PT));
                                            info.push("PVEM:"+result.PVEM);//graf.push("PVEM:"+result.PVEM);
                                            graf.push(new partido("PVEM","","",result.PVEM));
                                            info.push("Votos No Registrados:"+result.DNoRegistrados);//graf.push("Votos No Registrados:"+result.DNoRegistrados);
                                            graf.push(new partido("Votos No Registrados","","",result.DNoRegistrados));
                                            info.push("Votos validos:"+result.Validos);
                                            info.push("Votos nulos:"+result.DNulos);//graf.push("Votos nulos:"+result.DNulos);
                                            graf.push(new partido("Votos nulos","","",result.DNulos));
                                            info.push("Votos totales:"+result.Total);

                                            info.push("Lista Nominal"+result.Lista);
                                            info.push("Participación Ciudadana:"+result.Participacion);


                                    }
                                    if(f.includes('2000'))
                                    {
                                            graf.push(new partido("Votos totales","","",result.Total)); 
                                            info.push("Secciones:"+result.Secciones);
                                            info.push("Casillas:"+result.Casillas);
                                            info.push("AC:"+result.AC);//graf.push("AC:"+result.AC);
                                            graf.push(new partido("AC","","",result.AC));
                                            info.push("PRI:"+result.PRI);//graf.push("PRI:"+result.PRI);
                                            graf.push(new partido("PRI","","",result.PRI));
                                            info.push("AM:"+result.AM);//graf.push("AM:"+result.AM);
                                            graf.push(new partido("AM","","",result.AM));
                                            info.push("PCD:"+result.PCD);//graf.push("PCD:"+result.PCD);
                                            graf.push(new partido("PCD","","",result.PCD));
                                            info.push("PARM"+result.PARM);//graf.push("PARM"+result.PARM);
                                            graf.push(new partido("PARM","","",result.PARM));
                                            info.push("DSPPN:"+result.DSPPN);//graf.push("DSPPN:"+result.DSPPN);
                                            graf.push(new partido("DSPPN","","",result.DSPPN));
                                            info.push("Votos No Registrados:"+result.DNoRegistrados);//graf.push("Votos No Registrados:"+result.DNoRegistrados);
                                            graf.push(new partido("Votos No Registrados","","",result.DNoRegistrados));
                                            info.push("Votos validos:"+result.Validos);
                                            info.push("Votos nulos:"+result.DNulos);//graf.push("Votos nulos:"+result.DNulos);
                                            graf.push(new partido("Votos nulos","","",result.DNulos));
                                            info.push("Votos totales:"+result.Total);

                                            info.push("Lista Nominal"+result.Lista);
                                            info.push("Participación Ciudadana:"+result.Participacion);

                                    }
                                    if(f.includes('2006'))
                                    {
                                            graf.push(new partido("Votos totales","","",result.Total));
                                            info.push("Secciones:"+result.Secciones);
                                            info.push("Casillas:"+result.Casillas);
                                            info.push("PAN:"+result.PAN);//graf.push("PAN:"+result.PAN);
                                            graf.push(new partido("PAN","","",result.PAN));
                                            info.push("Alianza por México:"+result.Alianza);//graf.push("Alianza por México:"+result.Alianza);
                                            graf.push(new partido("Alianza por México","","",result.Alianza));
                                            info.push("Por el bien de todos:"+result.Porelbien);//graf.push("Por el bien de todos:"+result.Porelbien);
                                            graf.push(new partido("Por el bien de todos","","",result.Porelbien));
                                            info.push("Nueva Alianza:"+result.NuevaAlianza);//graf.push("Nueva Alianza:"+result.NuevaAlianza);
                                            graf.push(new partido("Nueva Alianza","","",result.NuevaAlianza));
                                            info.push("Alternativa:"+result.Alternativa);//graf.push("Alternativa:"+result.Alternativa);
                                            graf.push(new partido("Alternativa","","",result.Alternativa));
                                            info.push("Votos No Registrados:"+result.DNoRegistrados);//graf.push("Votos No Registrados:"+result.DNoRegistrados);
                                            graf.push(new partido("Votos No Registrados","","",result.DNoRegistrados));
                                            info.push("Votos validos:"+result.Validos);
                                            info.push("Votos nulos:"+result.DNulos);//graf.push("Votos nulos:"+result.DNulos);
                                            graf.push(new partido("Votos nulos","","",result.DNulos));
                                            info.push("Votos totales:"+result.Total);

                                            info.push("Lista Nominal"+result.Lista);
                                            info.push("Participación Ciudadana:"+result.Participacion);

                                    }
                                    if(f.includes('2012'))
                                    {
                                            graf.push(new partido("Votos totales","","",result.Total));
                                            info.push("Secciones:"+result.Secciones);
                                            info.push("Casillas:"+result.Casillas);
                                            info.push("PAN:"+result.PAN);//graf.push("PAN:"+result.PAN);
                                            graf.push(new partido("PAN","","",result.PAN));
                                            info.push("PRI:"+result.PRI);//graf.push("PRI:"+result.PRI);
                                            graf.push(new partido("PRI","","",result.PRI));
                                            info.push("PRD:"+result.PRD);//graf.push("PRD:"+result.PRD);
                                            graf.push(new partido("PRD","","",result.PRD));
                                            info.push("PVEM:"+result.PVEM);//graf.push("PVEM:"+result.PVEM);
                                            graf.push(new partido("PVEM","","",result.PVEM));
                                            info.push("PT:"+result.PT);//graf.push("PT:"+result.PT);
                                            graf.push(new partido("PT","","",result.PT));
                                            info.push("Movimiento Ciudadano:"+result.Movimiento);//graf.push("Movimiento Ciudadano:"+result.Movimiento);
                                            graf.push(new partido("Movimiento Ciudadano","","",result.Movimiento));
                                            info.push("Nueva Alianza:"+result.NuevaAlianza);//graf.push("Nueva Alianza:"+result.NuevaAlianza);
                                            graf.push(new partido("Nueva Alianza","","",result.NuevaAlianza));
                                            info.push("Coalición PRI-PVEM:"+result.PRIPVEM);//graf.push("Coalición PRI-PVEM:"+result.PRIPVEM);
                                            graf.push(new partido("Coalición PRI-PVEM","","",result.PRIPVEM));
                                            info.push("Coalición PRD-PT-MC:"+result.PRDPTMC);//graf.push("Coalición PRD-PT-MC:"+result.PRDPTMC);
                                            graf.push(new partido("Coalición PRD-PT-MC","","",result.PRDPTMC));
                                            info.push("Coalición PRD-PT:"+result.PRDPT);//graf.push("Coalición PRD-PT:"+result.PRDPT);
                                            graf.push(new partido("Coalición PRD-PT","","",result.PRDPT));
                                            info.push("Coalición PRD-MC:"+result.PRDMC);//graf.push("Coalición PRD-MC:"+result.PRDMC);
                                            graf.push(new partido("Coalición PRD-MC","","",result.PRDMC));
                                            info.push("Coalición PT-MC:"+result.PTMC);//graf.push("Coalición PT-MC:"+result.PTMC);
                                            graf.push(new partido("Coalición PT-MC","","",result.PTMC));
                                            info.push("Votos No Registrados:"+result.DNoRegistrados);//graf.push("Votos No Registrados:"+result.DNoRegistrados);
                                            graf.push(new partido("Votos No Registrados","","",result.DNoRegistrados));
                                            info.push("Votos nulos:"+result.DNulos);//graf.push("Votos nulos:"+result.DNulos);
                                            graf.push(new partido("Votos nulos","","",result.DNulos));
                                            info.push("Votos totales:"+result.Total);

                                            info.push("Lista Nominal"+result.Lista);
                                            info.push("Participación Ciudadana:"+result.Participacion);
                                    } 

                           }
                   
                        
            },
            error: function (xhr, ajaxOptions, thrownError)
            {
                alert("Error: "+xhr.status);
                alert("Error: "+ thrownError);
            }
          }); 


   
}
