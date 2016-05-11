var puerto = 8080;
var delegacion='';
var datos='';var totales='';
//var info=[];
var parti=[];
var ctx ='';
var myNewChart ='';
var graf=[];

 
var partic=new Array();
var CVE;
var Estado;
var año;
//parametro
var par;

$('#generarInforme').hide();

$('#idTabla1').hide();
$('#idTabla2').hide();
$('#idTxtTabla1').hide();

$('#idPartidos').hide();
$('#partidoGanador').hide();

$('#myChart').hide();
$('#porcentajeVictorias').hide();

$(document).ready(function(){
 
	$('.ir-arriba').click(function(){
		$('body, html').animate({
			scrollTop: '0px'
		}, 300);
	});
 
	$(window).scroll(function(){
		if( $(this).scrollTop() > 0 ){
			$('.ir-arriba').slideDown(300);
		} else {
			$('.ir-arriba').slideUp(300);
		}
	});
 
});       


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
        año= $('#año').val();
        par=$('#param').val();
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
                    CVE = $('#nodelist').get(0).children[0].contentDocument.children[0].children[1].children[0].children[1].children[1].children[4].innerHTML;
                    
                    localStorage.setItem("CVE", CVE);
                    localStorage.setItem("par",par);
                   
                        datosDelegacion(CVE,año,par);
                    
                    Estado = $('#nodelist').get(0).children[0].contentDocument.children[0].children[1].children[0].children[1].children[1].children[5].innerHTML;
                    console.log(Estado + ":" + CVE);
                    Estado = nombreRealDelegacion(Estado);
                   
                   $('#idEstadoActivo').get(0).innerHTML ="Delegación: "+delegacion;
                    $('#idEstadoActivo').hide();
                    $('#idEstadoActivo').fadeIn(0);
                   
                  
                    grafica(par,CVE);
                    comparativa(CVE,año);
                    
                    
                   
                    var newLayerGroup = asignLayerGroup(CVE);
                    map.setLayerGroup(newLayerGroup);
                  }
                  ,
                  //checar tiempos 
                  1000
                );
              }
            );
            $('#idIFRAME').attr('src',url);
        }
      });

    //Cambio automatico al cambiar la fecha con partidos politicos  
/***************************************************************************************************************/
    
    document.getElementById('año').addEventListener('change',cambioPartido,false);

      
        function cambioPartido()
             {

                var leano=document.getElementById('año').value;
                 var cv=localStorage.getItem("CVE");
                 var para=localStorage.getItem("par");
                 datosDelegacion(cv,leano,para);
                 grafica(para,cv);
                 comparativa(cv,leano);

             }
/***************************************************************************************************************/
      
      //Cambio automatico al cambiar el paramentro de consulta 
/***************************************************************************************************************/

    document.getElementById('param').addEventListener('change',cambioselect,false);
   
        function cambioselect()
            {
                var paramet=document.getElementById('param').value;

                switch(paramet)
                {
                    case 'partido':
                        console.log("entro a partido parametro: "+paramet);
                        var anoo=document.getElementById('año').value;
                        var cv=localStorage.getItem("CVE");
                       
                        datosDelegacion(cv,anoo,'partido');
                        grafica('partido',cv);
                        
                    break;
                    case 'participacion':
                        console.log("entro a participacion parametro: "+paramet);
                        var cv=localStorage.getItem("CVE");
                        grafica('participacion',cv);
                        
                    break;
                }
            }
/***************************************************************************************************************/

function grafica(par,idEstado)
{
        var para=par;
        var i=idEstado;
    
        if(para.includes('participacion'))
        {
                document.getElementById("año").disabled=true;
                var body = document.getElementById("tabla-par");
                body.innerHTML = '&nbsp;';
                $('#tabla-par').append('<div id="tabla-par" ></div>');
                compararParticipacion(i);
                
                var pieChartContent = document.getElementById('graficaPartidos');
                pieChartContent.innerHTML = '&nbsp;';
                $('#graficaPartidos').append('<canvas id="myChart" width="300" height="450"></canvas>');
                var data = 
                {
                        labels:["Votos Validos","Votos Nulos","Votos Totales","Lista Nominal"],  
                        datasets:
                        [
                                {
                                            fillColor: "rgba(255,105,180,0.2)",
                                            strokeColor: "rgba(255,105,180,1)",
                                            pointColor: "rgba(220,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(220,220,220,1)",
                                            data: [val[0],nul[0],tot[0],lis[0]],
                                            label: "Año 2000"
                                },
                                {
                                            fillColor: "rgba(152,251,152,0.2)",
                                            strokeColor: "rgba(152,251,152,1)",
                                            pointColor: "rgba(151,187,205,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(151,187,205,1)",
                                            data: [val[1],nul[1],tot[1],lis[1]],
                                            label: "Año 2003"
                                },
                                {
                                            fillColor : "rgba(147,112,219,0.2)",
                                            strokeColor : "rgba(147,112,219,1)",
                                            pointColor : "rgba(320,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(51,187,205,1)",
                                            data: [val[2],nul[2],tot[2],lis[2]],
                                            label: "Año 2006"
                                },
                                {
                                            fillColor: "rgba(255,215,0,0.1)",
                                            strokeColor: "rgba(255,215,0,1)",
                                            pointColor: "rgba(220,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(220,220,220,1)",
                                            data: [val[3],nul[3],tot[3],lis[3]],
                                            label: "Año 2009"
                                },
                                {
                                            fillColor: "rgba(255,215,0,0.1)",
                                            strokeColor: "rgba(255,215,0,1)",
                                            pointColor: "rgba(220,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(220,220,220,1)",
                                            data: [val[4],nul[4],tot[4],lis[4]],
                                            label: "Año 2012"
                                },
                                {
                                            fillColor: "rgba(255,215,0,0.1)",
                                            strokeColor: "rgba(255,215,0,1)",
                                            pointColor: "rgba(220,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(220,220,220,1)",
                                            data: [val[5],nul[5],tot[5],lis[5]],
                                            label: "Año 2015"
                                }
                        ]
                };
                graf.length=0;
                console.log(data);
                var ctx = document.getElementById("myChart").getContext("2d");
                new Chart(ctx).Bar(data, {scaleOverride : true,scaleSteps : 20,scaleStartValue:0,scaleStepWidth:50000});
                legend(document.getElementById("js-legend"), data);
                
//*****************************************************************************************************************************************
                
                compararParticipacion(i);
                
                var piec = document.getElementById('g2');
                piec.innerHTML = '&nbsp;';
                $('#g2').append('<canvas id="myChart2" class="titulo" width="290" height="450"></canvas>');
                
                var dataa = 
                {
                        labels:["Participación Ciudadana"],  
                        datasets:
                        [
                                {
                                            fillColor: "rgba(255,105,180,0.2)",
                                            strokeColor: "rgba(255,105,180,1)",
                                            pointColor: "rgba(220,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(220,220,220,1)",
                                            data: [partic[0]],
                                            label: "Año 2000"
                                },
                                {
                                            fillColor: "rgba(152,251,152,0.2)",
                                            strokeColor: "rgba(152,251,152,1)",
                                            pointColor: "rgba(151,187,205,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(151,187,205,1)",
                                            data: [partic[1]],
                                            label: "Año 2003"
                                },
                                {
                                            fillColor : "rgba(147,112,219,0.2)",
                                            strokeColor : "rgba(147,112,219,1)",
                                            pointColor : "rgba(320,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(51,187,205,1)",
                                            data: [partic[2]],
                                            label: "Año 2006"
                                },
                                {
                                            fillColor: "rgba(255,215,0,0.1)",
                                            strokeColor: "rgba(255,215,0,1)",
                                            pointColor: "rgba(220,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(220,220,220,1)",
                                            data: [partic[3]],
                                            label: "Año 2009"
                                },
                                {
                                            fillColor: "rgba(255,215,0,0.1)",
                                            strokeColor: "rgba(255,215,0,1)",
                                            pointColor: "rgba(220,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(220,220,220,1)",
                                            data: [partic[4]],
                                            label: "Año 2012"
                                },
                                {
                                            fillColor: "rgba(255,215,0,0.1)",
                                            strokeColor: "rgba(255,215,0,1)",
                                            pointColor: "rgba(220,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(220,220,220,1)",
                                            data: [partic[5]],
                                            label: "Año 2015"
                                }
                        ]
                };
                graf.length=0;
                console.log(dataa);
                var ctxx = document.getElementById("myChart2").getContext("2d");
                new Chart(ctxx).Bar(dataa, {scaleOverride : true,scaleSteps : 15,scaleStartValue:0,scaleStepWidth:7});
                
                // legend(document.getElementById("js-legend"), data);
                
//*****************************************************************************************************************************************
        }
      
        if(para.includes('partido'))
        {   
               
                document.getElementById("año").disabled=false;
                var myPieChart=null;
                
//                var pieChartContent = document.getElementById('graficaPartidos');
//                pieChartContent.innerHTML = '&nbsp;';
                
                var pieChartContent = document.getElementById('g2');
                pieChartContent.innerHTML = '&nbsp;';
                
                var g=[];
                var co=[];
                        for(y=0;y<graf.length;y++)
                        {
                            var value=graf[y].voto;
                            var nom=graf[y].ima;
                            var c=graf[y].color;
                            g.push([nom,parseInt(value)]);
                          console.log('Value :v ---> ' + value);
                            co.push(c);
                        }
        
                        var URL = 'http://localhost:8080/TT/Recursos/img/';
                        $(function () {
                            $('#myChart').highcharts({
                                chart: {
                                    margin: 80,
                                    
                                    plotBackgroundColor: null,
                                    plotBorderWidth: null,
                                    plotShadow: false,
                                    type: 'pie'
                                   
                                },
                                title: {
                                    text: 'Votacion por Partido'
                                },
                                colors: co.valueOf(),
                                tooltip: {
                                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                                },
                                plotOptions: {
                                    pie: {
                                        slicedOffset: 0,
                                        allowPointSelect: true,
                                        cursor: 'pointer',
                                        size: '100%',
                                        dataLabels: {
                                            enabled: true,
                                            useHTML: true,  
                                          format:  '<img height=40px width=auto src='+URL+'{point.name}.png ><img>: {point.percentage:.1f} %',
                                            style: {
                                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                            }
                                        }
                                    }
                                },
                                series: [{
                                    name: 'Porcentaje: ',
                                    data: g
                                }]
                            });
                                //var chart = $('#prueba').highcharts();
                                //chart.series[0].setData(g);
                        });
    
          
                /*$('#graficaPartidos').append('<canvas id="myChart" width="400" height="400"></canvas>');
                var ctx = document.getElementById("myChart").getContext("2d");
                //*********************************************************************
                var g=[];
                        for(y=0;y<graf.length;y++)
                        {
                            var value=graf[y].voto;
                            var nom=graf[y].nombre;
                            var c=graf[y].color;
                            g.push({
                                        label: nom,
                                        value: value,
                                        color: c
                                    });
                        }
                      // graf.length=0;
                       g.forEach(function (e, i)
                             {
                                 e.color = '#'+('00000'+(Math.random()*16777216<<0).toString(16)).substr(-6);
                                 //"hsl(" + (i / g.length * 360) + ", 50%, 50%)";
                                // e.highlight = "hsl(" + (i / g.length * 360) + ", 50%, 70%)";
                            })
                        console.log(g);
                //************************************************************************
                myPieChart = new Chart(ctx).Pie(g,{tooltipTemplate:"<%=label%>: <%=value%>"});
                legend(document.getElementById("js-legend"), g, myPieChart, "<%=label%>: <%=value%>");*/
                 //pastel();
                tabla_porcentajes();
             
                
        }
        
        
}





function tabla_porcentajes(){
           
             // Obtener la referencia del elemento body
            var body = document.getElementById("tabla-par");
            body.innerHTML = '&nbsp;';
            $('#tabla-par').append('<div id="tabla-par" class="table table-striped table-hover table-bordered "></div>');
            // Crea un elemento <table> y un elemento <tbody>
            var tabla   = document.createElement("table");
            var tblBody = document.createElement("tbody");

            // Crea las celdas
            for (var i = 0; i < 2; i++)
            {
                // Crea las hileras de la tabla
                var hilera = document.createElement("tr");

                        for (var j = 0; j < graf.length; j++)
                        {
                            switch(i)
                            {
                                case 0:     
                                            if(j===0)
                                            {
                                                var celda = document.createElement("th");
                                                var d=document.createTextNode("Delegación");
                                                celda.appendChild(d);
                                                hilera.appendChild(celda);
                                            }
                                            else
                                            {
                                                var nom=graf[j-1].ima;
                                                // Crea un elemento <td> y un nodo de texto, haz que el nodo de
                                                // texto sea el contenido de <td>, ubica el elemento <td> al final
                                                // de la hilera de la tabla
                                                var celda = document.createElement("th");
                                                var x = document.createElement("IMG");
                                                x.setAttribute("src", "/TT/Recursos/img/"+nom+".png");
                                                x.setAttribute("width", "60");
                                                x.setAttribute("height", "60");
                                                x.setAttribute("alt", nom);
                                               // var textoCelda = document.createTextNode("celda en la hilera "+i+", columna "+j);
                                                celda.appendChild(x);
                                                hilera.appendChild(celda);
                                            }
                                break;
                                case 1:
                                            
                                             if(j===0)
                                            {
                                                var celda = document.createElement("td");
                                                var d=document.createTextNode(delegacion);
                                                celda.appendChild(d);
                                                hilera.appendChild(celda);
                                            }
                                            else
                                            {
                                                var voto=graf[j-1].voto;
                                              /*  var t='';
                                                t=(voto*100)/totales;
                                                t=t.toFixed(2);*/
                                                // Crea un elemento <td> y un nodo de texto, haz que el nodo de
                                                // texto sea el contenido de <td>, ubica el elemento <td> al final
                                                // de la hilera de la tabla
                                                var celda = document.createElement("td");
                                                var textoCelda = document.createTextNode(voto);
                                                celda.appendChild(textoCelda);
                                                hilera.appendChild(celda);
                                            }
                                break;
                                
                            }
                                
                        }

                // agrega la hilera al final de la tabla (al final del elemento tblbody)
                tblBody.appendChild(hilera);
            }

              // posiciona el <tbody> debajo del elemento <table>
            tabla.appendChild(tblBody);
              // appends <table> into <body>
            body.appendChild(tabla);
              // modifica el atributo "border" de la tabla y lo fija a "2";
            tabla.setAttribute("border", "2");
            graf.length=0;
            totales='';
}




function partido(nombre,ima,voto,color){
    this.nombre=nombre;
    this.ima=ima;
    this.voto=voto;
    this.color=color;
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
                
                        
                          if(p.includes('partido'))
                          {
                                    if(f.includes('2000'))
                                    {
                                            delegacion=result.Delegacion;
                                            
                                            graf.push(new partido("Coalición PAN-PVEM","panpvem_ico",result.PANPVEM,"#0000ff"));
                                            graf.push(new partido("PRI","pri_ico",result.PRI,"#008000"));
                                            graf.push(new partido("PRD","prd_ico",result.PRD,"#ffd700"));
                                            graf.push(new partido("PT","pt_ico",result.PT,"#ff0000"));
                                            graf.push(new partido("Convergencia","con_ico",result.Convergencia,"#ffa500"));
                                            graf.push(new partido("PCD","pcd_ico",result.PCD,"#a13990"));
                                            graf.push(new partido("PSN","psn_ico",result.PSN,"#808000"));
                                            graf.push(new partido("PARM","parm_ico",result.PARM,"#bed9c8"));
                                            graf.push(new partido("PAS","pas_ico",result.PAS,"#b22222"));
                                            graf.push(new partido("DS","ds_ico",result.DS,"#bc50a5"));;
                                            graf.push(new partido("Votos nulos","nulos",result.DNulos,"#d7dfe2"));
                                           // graf.push(new partido("Votos totales","totales",result.Total));
                                            //graf.push(new partido("Lista Nominal","Lista",result.Lista));
                                            //graf.push(new partido("Participación Ciudadana","Participacion",result.Participacion));
                                            
                                            totales=result.Total;
                                           


                                    }
                                    if(f.includes('2003'))
                                    {
                                            delegacion=result.Delegacion;
                                            
                                            graf.push(new partido("PAN","pan_ico",result.PAN,"#0000ff"));
                                            graf.push(new partido("PRI","pri_ico",result.PRI,"#008000"));
                                            graf.push(new partido("PRD","prd_ico",result.PRD,"#ffd700"));
                                            graf.push(new partido("PT","pt_ico",result.PT,"#ff0000"));
                                            graf.push(new partido("PVEM","pvem_ico",result.PVEM,"#90ee90"));
                                            graf.push(new partido("Convergencia","con_ico",result.Convergencia,"#ffa500"));
                                            graf.push(new partido("PSN","psn_ico",result.PSN,"#6b0f72"));
                                            graf.push(new partido("PAS","pas_ico",result.PAS,"#e63932"));
                                            graf.push(new partido("MP","mp_ico",result.MP,"#007166"));;
                                            graf.push(new partido("PLM","plm_ico",result.PLM,"#f39524"));
                                            graf.push(new partido("FC","fc_ico",result.FC,"#262c8d"));
                                            graf.push(new partido("Votos nulos","nulos",result.DNulos,"#d7dfe2"));
                                           // graf.push(new partido("Votos totales","votostotales","",result.Total));
                                            //graf.push(new partido("Lista Nominal","Lista",result.Lista));
                                            //graf.push(new partido("Participación Ciudadana","Participacion",result.Participacion));
                                            
                                            totales=result.Total;
                                           


                                    }
                                    if(f.includes('2006'))
                                    {
                                            delegacion=result.Delegacion;
                                            //graf.push(new partido("Votos totales","votosvalidos","",result.Total)); 
                                            graf.push(new partido("PAN","pan_ico",result.PAN,"#0000ff"));
                                            graf.push(new partido("PRIPVEM","pri_pvem_ico",result.PRIPVEM,"#008000"));
                                           graf.push(new partido("Por El Bien De Todos","prd_pt_conv_ico",result.PRDPTConvergencia,"#6d3f3f"));
                                            graf.push(new partido("NuevaAlianza","panal_ico",result.NuevaAlianza,"#40e0d0"));
                                            graf.push(new partido("PASC","psd_ico",result.PASC,"#ff0000"));
                                            graf.push(new partido("PANNuevaAlianza","pan_na_ico",result.PANNuevaAlianza,"#008cff"));
                                            graf.push(new partido("Votos nulos","nulos",result.DNulos,"#d7dfe2"));
                                          //  graf.push(new partido("Votos totales","votostotales","",result.Total)); 
                                           // graf.push(new partido("Lista Nominal","Lista",result.Lista));
                                            //graf.push(new partido("Participación Ciudadana","Participacion",result.Participacion));
                                            totales=result.Total;    
                                            

                                    }
                                    if(f.includes('2009'))
                                    {
                                            delegacion=result.Delegacion;
                                            
                                            graf.push(new partido("PAN","pan_ico",result.PAN,"#0000ff"));
                                            graf.push(new partido("PRI","pri_ico",result.PRI,"#008000"));
                                            graf.push(new partido("PRD","prd_ico",result.PRD,"#ffd700"));
                                            graf.push(new partido("PT","pt_ico",result.PT,"#ff0000"));
                                            graf.push(new partido("PVEM","pvem_ico",result.PVEM,"#90ee90"));
                                            graf.push(new partido("Convergencia","con_ico",result.Convergencia,"#ffa500"));
                                            graf.push(new partido("NuevaAlianza","panal_ico",result.NuevaAlianza,"#40e0d0"));
                                            graf.push(new partido("PSD","psd_ico",result.PSD,"#8b0000"));
//                                            graf.push(new partido("Por El Bien De Todos","prd_pt_conv_ico",result.PRDPTConvergencia,"#6d3f3f"));
//                                            graf.push(new partido("PRDPT","prd_pt_ico",result.PRDPT,"#933c1f"));
//                                            graf.push(new partido("PRDConvergencia","prd_con_ico",result.PRDConvergencia,"#8c5d0c"));
//                                            graf.push(new partido("PTConv","pt_con_ico",result.PTConvergencia,"#c46833"));
                                            graf.push(new partido("Votos Nulos","nulos",result.DNulos,"#d7dfe2"));
                                           // graf.push(new partido("Votos totales","votostotales","",result.Total));
                                           // graf.push(new partido("Lista Nominal","Lista",result.Lista));
                                            //graf.push(new partido("Participación Ciudadana","Participacion",result.Participacion));
                                           
                                            totales=result.Total;    
                                           

                                    }
                                    if(f.includes('2012'))
                                    {
                                            delegacion=result.Delegacion;
                                            
                                            graf.push(new partido("PAN","pan_ico",result.PAN,"#0000ff"));
                                            graf.push(new partido("PRI","pri_ico",result.PRI,"#008000"));
                                            graf.push(new partido("PRD","prd_ico",result.PRD,"#ffd700"));
                                            graf.push(new partido("PT","pt_ico",result.PT,"#ff0000"));
                                            graf.push(new partido("PVEM","pvem_ico",result.PVEM,"#90ee90"));
                                            graf.push(new partido("MovimientoCiudadano","mc_ico",result.Movimiento,"#ffa500"));
                                            graf.push(new partido("NuevaAlianza","panal_ico",result.NuevaAlianza,"#40e0d0"));
                                            graf.push(new partido("PRIPVEM","pri_pvem_ico",result.PRIPVEM,"#008000"));
                                            graf.push(new partido("PRDPTMC","prd_pt_mc_ico",result.PRDPTMovimiento,"#933c1f"));
                                            graf.push(new partido("Votos nulos","nulos",result.DNulos,"#d7dfe2"));
                                          //  graf.push(new partido("Votos totales","votostotales","",result.Total));
                                          //  graf.push(new partido("Lista Nominal","Lista",result.Lista));
                                           // graf.push(new partido("Participación Ciudadana","Participacion",result.Participacion));
                                           
                                            totales=result.Total;
                                          
                                    } 
                                    if(f.includes('2015'))
                                    {       
                                            //graf.push(new partido("","",result.));
                                            delegacion=result.Delegacion;
                                            
                                            graf.push(new partido("pan_ico","pan_ico",result.PAN,"#0000ff"));
                                            graf.push(new partido("pri_pvem_ico","pri_pvem_ico",result.PRIPVEM,"#008000"));
                                            graf.push(new partido("prd_pt_ico","prd_pt_ico",result.PRDPT,"#933c1f"));
                                            graf.push(new partido("prd_pt_panal","prd_pt_panal",result.PRDPTNuevaAlianza,"#90ff00"));
                                            graf.push(new partido("prd_ico","prd_ico",result.PRD,"#ffd700"));
                                            graf.push(new partido("pt_ico","pt_ico",result.PT,"#ff0000"));
                                            graf.push(new partido("mc_ico","mc_ico",result.Movimiento,"#ffa500"));
                                            graf.push(new partido("panal_ico","panal_ico",result.NuevaAlianza,"#40e0d0"));
                                            graf.push(new partido("morena_ico","morena_ico",result.Morena,"#800000"));
                                            graf.push(new partido("ph_ico","ph_ico",result.PH,"#c71585"));
                                            graf.push(new partido("pes_ico","pes_ico",result.PES,"#4b0082"));
                                            //graf.push(new partido("Independientes","Inde",result.Inde));
                                            graf.push(new partido("Votos nulos","nulos",result.DNulos,"#d7dfe2"));
                                          //  graf.push(new partido("Votos totales","votostotales","",result.Total));
                                           // graf.push(new partido("Lista Nominal","Lista",result.Lista));
                                            //graf.push(new partido("Participación Ciudadana","Participacion",result.Participacion));
                                           
                                            totales=result.Total;
                                           
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





//**************************************************************

///////////// =============== /////////////
var respuesta;

var cve_mun;
var nombre;
var partidoganador;
var tabla1 = [];
var tabla2 = [];
var delegacionesganadas;
var delegacionesperdidas;
var porcentaje;
var votos;

var root;

function comparativa(CVE,año)
{
    $.ajax
                              ({ 
                                  type: "GET",  
                                  async: false,
                                  url: "http://localhost:8080/TT/SComparativa",  
                                  data: "cve=" +CVE+ "&anio=" +año,  
                                  success: getComparativa
                                  ,
                                error: function (xhr, ajaxOptions, thrownError)
                                {
                                    console.log("Error: "+xhr.status);
                                    console.log("Error: "+ thrownError);
                                }
                              });
}

function generarInforme()
{
  var rootXML = $('<root></root>');

  var cve_munXML = $('<cve_mun>' + cve_mun + '</cve_mun>');
  rootXML.append(cve_munXML);

  var nombreXML = $('<nombre>' + nombre + '</nombre>');
  rootXML.append(nombreXML);

  var partidoganadorXML = $('<partidoganador>' + partidoganador + '</partidoganador>');
  rootXML.append(partidoganadorXML);

  ///////// tabla1
  var tabla1XML = $('<tabla1></tabla1>');

  for(var j = 0 ; j < tabla1.children.length ; j++)
  {
    var $row = $('<tr></tr>');

      $row.append('<td>'+ tabla1.children[j].children[0].textContent +'</td>');
      $row.append('<td>'+ tabla1.children[j].children[1].textContent +'</td>');

    tabla1XML.append($row);
  }
  rootXML.append(tabla1XML);

  ///////// tabla2
  var tabla2XML = $('<tabla2></tabla2>');

  for(var j = 0 ; j < tabla2.children.length ; j++)
  {
    var $row = $('<tr></tr>');

      $row.append('<td>'+ tabla2.children[j].children[0].textContent +'</td>');
      $row.append('<td>'+ tabla2.children[j].children[1].textContent +'</td>');
      $row.append('<td>'+ tabla2.children[j].children[2].textContent +'</td>');

    tabla2XML.append($row);
  }
  rootXML.append(tabla2XML);

  //////////

  var delegacionesganadasXML = $('<delegacionesganadas>' + delegacionesganadas + '</delegacionesganadas>');
  rootXML.append(delegacionesganadasXML);

  var delegacionesperdidasXML = $('<delegacionesperdidas>' + delegacionesperdidas + '</delegacionesperdidas>');
  rootXML.append(delegacionesperdidasXML);

  var porcentajeXML = $('<porcentaje>' + porcentaje + '</porcentaje>');
  rootXML.append(porcentajeXML);

  var votosXML = $('<votos>' + votos + '</votos>');
  rootXML.append(votosXML);

  console.log(rootXML.get(0).outerHTML);

  $.ajax
  ({ 
      type: "POST",  
      async: true,
      url: "http://localhost:8080/TT/RecibeXML",  
      data: "sesion=" + sessionStorage.getItem('SClave') + "&xml=" + rootXML.get(0).outerHTML ,  
      success: function(result){console.log(result);}
      ,
    error: function (xhr, ajaxOptions, thrownError)
    {
        console.log("Error: "+xhr.status);
        console.log("Error: "+ thrownError);
    }
  });

}

function getComparativa(result)
{
  root = result.firstChild;

  cve_mun = root.children[0].textContent;
  nombre = root.children[1].textContent;
  partidoganador = root.children[2].textContent;

  tabla1 = root.children[3];
  tabla2 = root.children[4];
  delegacionesganadas = root.children[5].textContent;
  delegacionesperdidas = root.children[6].textContent;
  porcentaje = root.children[7].textContent;
  votos = root.children[8].textContent;

  actualizarUI();
}

function actualizarUI()
{
  $('#generarInforme').show();
 // $('#idDelegacion').get(0).textContent = nombre;
  mostrarPartido();
  doughnutChart();
  mostrarTabla1();
  mostrarTabla2();
}

function mostrarPartido()
{
  var imgURL = obtenerURLPartido(partidoganador);
  $('#partidoGanador').empty();
  $('#partidoGanador').append('<h1>Partido ganador.</h1>');
  $('#partidoGanador').append('<div><img src=\"'+ imgURL +'\" width="350" height="350" /></div>');
  //$('#partidoGanador').append('<p>' + partidoGanador.nombre + '</p>');
  $('#partidoGanador').append('<p>Votos : ' + votos + '</p>');
  $('#partidoGanador').show();
}

function doughnutChart()
{

  $('#contenedorChart').empty();

  var elChart = $('<div id="js-legend" class="chart-legend"></div><canvas id="myChart3" width="400" height="400"></canvas><div id="porcentajeVictorias"></div>');

  $('#contenedorChart').append(elChart);
  //$('#myChart').show();

  var data = 
  [
    {
        value: delegacionesganadas,
        color: "#46BFBD",
        highlight: "#5AD3D1",
        label: "Delegaciones ganadas"
    },
    {
        value: delegacionesperdidas,
        color: "#F7464A",
        highlight: "#FF5A5E",
        label: "Delegaciones perdidas"
    }
  ];

  var options = 
  {
    segmentShowStroke : true,
    segmentStrokeColor : "#fff",
    segmentStrokeWidth : 4,
    percentageInnerCutout : 50,
    animationSteps : 100,
    animationEasing : "easeOutBounce",
    animateRotate : true,
    animateScale : false,
    legendTemplate : "<ul class=\"<%=name.toLowerCase()%>-legend\"><% for (var i=0; i<segments.length; i++){%><li><span style=\"background-color:<%=segments[i].fillColor%>\"></span><%if(segments[i].label){%><%=segments[i].label%><%}%></li><%}%></ul>"
  }

  var ctx = $("#myChart3").get(0).getContext("2d");
  var myDoughnutChart = new Chart(ctx).Doughnut(data,options);
  legend(document.getElementById("js-legend"), data, myDoughnutChart, "<%=label%>: <%=value%>");

  $('#porcentajeVictorias').empty();
  $('#porcentajeVictorias').show();
  $('#porcentajeVictorias').append('<p>' + porcentaje + '%</p>');
  $('#porcentajeVictorias').append('<p>de éxito en CDMX</p>');

}

function mostrarTabla1()
{
  $('#idTxtTabla1').show();
  $('#idTabla1').show();
  $('#idTabla1 tbody').empty();

  for(var i = 0 ; i < tabla1.children.length ; i ++)
  {
    var $row = $('<tr></tr>');

    $row.append('<th scope="row" >'+ (i+1) +'</th>');
    $row.append('<td>'+ tabla1.children[i].children[0].textContent +'</td>');
    $row.append('<td>'+ tabla1.children[i].children[1].textContent +'</td>');

    $('#idTabla1 tbody').append($row);
  }
}

function mostrarTabla2()
{
  $('#idTabla2 table tbody').empty();

  for(var j = 0 ; j < tabla2.children.length ; j++)
  {
    var $row = $('<tr></tr>');

      $row.append('<td>'+ tabla2.children[j].children[0].textContent +'</td>');
      $row.append('<td>'+ tabla2.children[j].children[2].textContent +'</td>');

    $('#idTabla2 table tbody').append($row);
  }

  $('#idTabla2').show();
}

function obtenerURLPartido(partidoGanador)
{
	var URL = 'http://localhost:8080/TT/Recursos/img/';

	if(partidoGanador == 'pan')
		return URL + 'PAN.png' ;

	if(partidoGanador == 'pri')
		return URL + 'PRI.jpg' ;

	if(partidoGanador == 'PPS')
		return URL + 'PPS.gif' ;

	if(partidoGanador == 'prd')
		return URL + 'PRD.jpg' ;

	if(partidoGanador == 'PFCRN')
		return URL + 'PFCRN.png' ;

	if(partidoGanador == 'PARM')
		return URL + 'PARM.png' ;

	if(partidoGanador == 'pan_na') // falta
		return URL + 'pan_na.jpg' ;

	if(partidoGanador == 'pt')
		return URL + 'PT.png' ;

	if(partidoGanador == 'pvem')
		return URL + 'PVEM.png' ;

	if(partidoGanador == 'AC') // falta
		return URL + '' ;

	if(partidoGanador == 'AM') // falta
		return URL + '' ;

	if(partidoGanador == 'PCD')
		return URL + 'PCD.jpg' ;

	if(partidoGanador == 'prd_con') //falta
		return URL + 'prd_con.jpg' ;

	if(partidoGanador == 'Alianza por México')
		return URL + 'Alianza_por_México.jpg' ;

	if(partidoGanador == 'prd_pt_con')
		return URL + 'PorElBienDeTodos.jpg' ;

	if(partidoGanador == 'panal')
		return URL + 'NuevaAlianza.png' ;

	if(partidoGanador == 'Alternativa')
		return URL + 'alternativa.jpg' ;

	if(partidoGanador == 'mc')
		return URL + 'MC.jpeg' ;

	if(partidoGanador == 'pri_pvem')
		return URL + 'PRI-PVEM.jpg' ;

	if(partidoGanador == 'prd_pt_mc')
		return URL + 'PRD-PT-MC.jpg' ;

	if(partidoGanador == 'prd_pt')
		return URL + 'PRD-PT.jpg' ;

	if(partidoGanador == 'prd_pt_panal')// falta
		return URL + 'prd_pt_panal.png' ;

	if(partidoGanador == 'morena') // falta
		return URL + 'morena.jpg' ;

}

