delegacionSeleccionada='';
var a94=[];
var PRI='';
var PAN='';
var PRD='';
var pro=[];
var proPAN=[];
var proPRI=[];
var proPRD=[];
var proPT=[];
var proPVEM=[];
var proNA=[];
var proMC=[];
var proMo=[];
var proValidos=[];
var proNulos=[];
var proTotal=[];
var proList=[];
var proPart=[];
var ca=new Array();
var nor=new Array();
var val=new Array();
var nul=new Array();
var tot=new Array();
var lis=new Array();
var graf=[];
var objPar=[];

var pan=[];var pri=[];
    var prd=[];var pvem=[];var pt=[];
    var na=[];var mc=[]; var vali=[]; var nulo=[];var tota=[];
    var list=[]; var par=[];var mo=[];
    
    var categoryImgs = {
            'PAN': '<img src="http://portal.iedf.org.mx/resultados2015/LogosPartidos/LogosPartidos_25/pan.png"><img>&nbsp;',
            'PRI':'<img src="http://portal.iedf.org.mx/resultados2015/LogosPartidos/LogosPartidos_25/pri.png"><img>&nbsp;',
            'PRD': '<img src="http://portal.iedf.org.mx/resultados2015/LogosPartidos/LogosPartidos_25/prd.png"><img>&nbsp;',
            'PT':'<img src="http://portal.iedf.org.mx/resultados2015/LogosPartidos/LogosPartidos_25/pt.png"><img>&nbsp;',
            'PVEM':'<img src="http://portal.iedf.org.mx/resultados2015/LogosPartidos/LogosPartidos_25/pvem.png"><img>&nbsp;',
            'NA':'<img src="http://portal.iedf.org.mx/resultados2015/LogosPartidos/LogosPartidos_25/na.png"><img>&nbsp;',
            'MC':'<img src="http://portal.iedf.org.mx/resultados2015/LogosPartidos/LogosPartidos_25/mc.png"><img>&nbsp;',
            'Morena':'<img src="http://portal.iedf.org.mx/resultados2015/LogosPartidos/LogosPartidos_25/morena.png"><img>&nbsp;'
            
         };
         
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
var untiled = new ol.layer.Image
({
        source: new ol.source.ImageWMS
        ({
          ratio: 1,
          url: 'http://localhost:8080/geoserver/cite/wms',
          params: 
          {
			'FORMAT': format,
			'VERSION': '1.1.0',  
			LAYERS: 'cite:DF',
			STYLES: '',
          }
        })
      });
      var tiled = new ol.layer.Tile({
        visible: false,
        source: new ol.source.TileWMS
        ({
          url: 'http://localhost:8080/geoserver/cite/wms',
          params: 
          {	
			'FORMAT': format, 
			'VERSION': '1.1.0',
			tiled: true,
			LAYERS: 'cite:DF',
			STYLES: '',
          }
        })
      });
      
  var map = new ol.Map
  ({
    layers: 
    [
      new ol.layer.Image
      ({
        opacity : 1,
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

    view: new ol.View
    ({
      center: ol.proj.transform([-58.0930, 2.7979], 'EPSG:4326', 'EPSG:3857'),
      zoom: 10
    }),
    target: 'map'
  });

	map.on('singleclick', function(evt) 
	{
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
					setTimeout(
						function()
						{
                                    CVE = $('#nodelist').get(0).children[0].contentDocument.children[0].children[1].children[0].children[1].children[1].children[4].innerHTML;
		                    var Estado = $('#nodelist').get(0).children[0].contentDocument.children[0].children[1].children[0].children[1].children[1].children[5].innerHTML;
		                    Estado = nombreRealDelegacion(Estado);
		                    delegacionSeleccionada = Estado;
		                    obtenerPartidos(CVE);
                                    
                                    
                                    
                                    grafica(CVE);
                                    periodos();
                                    

		                    var newLayerGroup = asignLayerGroup(CVE);
                                    map.setLayerGroup(newLayerGroup);

                                    $('#idEstadoActivo').get(0).innerHTML ="Delegación: "+delegacionSeleccionada+"<br>"+"<p>Diferencia de Votos entre procesos electorales. </p>";
                                    $('#idEstadoActivo').hide();
                                    $('#idEstadoActivo').fadeIn(0);

                                                                     
						}, 
                                                //checar tiempos 
                                                1000
					);
				}
			);

			$('#idIFRAME').attr('src',url);
		}
	});
        
     
function grafica(idEstado)
{
        var i=idEstado;
        //compararParticipacion(i);
        getComparacion();       
                var pieChartContent = document.getElementById('p1');
                pieChartContent.innerHTML = '&nbsp;';
                var pieChartContent = document.getElementById('tituloP');
                pieChartContent.innerHTML = '&nbsp;';
                $('#tituloP').append('<p>Diferencia de Participación Ciudadana entre los procesos electorales</p>')
                $('#p1').append('<canvas id="myChart" width="300" height="450"></canvas>');
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
                                            data: objPar[0],
                                            label: "Año 2000"
                                },
                                {
                                            fillColor: "rgba(152,251,152,0.2)",
                                            strokeColor: "rgba(152,251,152,1)",
                                            pointColor: "rgba(151,187,205,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(151,187,205,1)",
                                            data: objPar[1],
                                            label: "Año 2003"
                                },
                                {
                                            fillColor : "rgba(147,112,219,0.2)",
                                            strokeColor : "rgba(147,112,219,1)",
                                            pointColor : "rgba(320,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(51,187,205,1)",
                                            data: objPar[2],
                                            label: "Año 2006"
                                },
                                {
                                            fillColor: "rgba(255,215,0,0.1)",
                                            strokeColor: "rgba(255,215,0,1)",
                                            pointColor: "rgba(220,220,220,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(220,220,220,1)",
                                            data: objPar[3],
                                            label: "Año 2009"
                                },
                                {
                                            fillColor: "rgba(64,224,208,0.2)",
                                            strokeColor: "rgba(64,224,208,1)",
                                            pointColor: "rgba(64,224,208,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(64,224,208,1)",
                                            data: objPar[4],
                                            label: "Año 2012"
                                },
                                {
                                            fillColor: "rgba(210,105,30,0.1)",
                                            strokeColor: "rgba(210,105,30,1)",
                                            pointColor: "rgba(210,105,30,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(210,105,30,1)",
                                            data: objPar[5],
                                            label: "Año 2015"
                                }
                        ]
                };
                graf.length=0;
                console.log(data);
                var ctx = document.getElementById("myChart").getContext("2d");
                new Chart(ctx).Bar(data, {scaleOverride : true,scaleSteps : 20,scaleStartValue:0,scaleStepWidth:50000});
                //legend(document.getElementById("js-legend"), data);
                
//*****************************************************************************************************************************************
                
              //  compararParticipacion(i);
                
                var piec = document.getElementById('p2');
                piec.innerHTML = '&nbsp;';
                $('#p2').append('<canvas id="myChart2" class="titulo" width="300" height="400"></canvas>');
                
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
                                            fillColor: "rgba(64,224,208,0.2)",
                                            strokeColor: "rgba(64,224,208,1)",
                                            pointColor: "rgba(64,224,208,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(64,224,208,1)",
                                            data: [partic[4]],
                                            label: "Año 2012"
                                },
                                {
                                            fillColor: "rgba(210,105,30,0.1)",
                                            strokeColor: "rgba(210,105,30,1)",
                                            pointColor: "rgba(210,105,30,1)",
                                            pointStrokeColor: "#fff",
                                            pointHighlightFill: "#fff",
                                            pointHighlightStroke: "rgba(210,105,30,1)",
                                            data: [partic[5]],
                                            label: "Año 2015"
                                }
                        ]
                };
                graf.length=0;
                console.log(dataa);
                var ctxx = document.getElementById("myChart2").getContext("2d");
                new Chart(ctxx).Bar(dataa, {scaleOverride : true,scaleSteps : 15,scaleStartValue:0,scaleStepWidth:7});
                legend(document.getElementById("js-legend"), data);
                
//*****************************************************************************************************************************************
        
        partic.lenght=0;
        objPar.length=0;
        val.lenght=0;
        nul.length=0;
        tot.length=0;
        lis.lenght=0;
}
        
function obtenerPartidos(idEstado)
{
	//info.length = 0;

	//obtenerPartidosCallOut(  $('#idSelectAnio').val() );
        compararParticipacion(idEstado);
        datosPronostico(idEstado);
}
function partido(nombre,ima,voto,color){
    this.nombre=nombre;
    this.ima=ima;
    this.voto=voto;
    this.color=color;
}
function tendencia(nombre,voto){
    this.nombre=nombre;
    this.voto=voto;
}
function pronostico(partido,incremento){
    this.partido=partido;
    this.incremento=incremento;
}

function getComparacion(){
   
    for(x=0;x<val.length;x++){
        objPar.push([val[x],nul[x],tot[x],lis[x]]);
    }
}   
function compararParticipacion(idEstado)
{
    $.ajax({  
            type: "GET",  
            async: false,
            url: "http://localhost:8080/TT/CompararParticipacion",  
            data: "Clave=" +idEstado+"&sesion="+sessionStorage.getItem("SClave"),  
            success: function(result)
            {
              
                val=result.Vali.split(",");  
                nul=result.Nul.split(",");     
                tot=result.Total.split(",");     
                lis=result.Lista.split(",");     
                partic=result.Part.split(","); 
            }
        });
}



function datosPronostico(cve){
    var cv=cve;
    
    $.ajax({  
            type: "GET",  
            async: false,
            url: "http://localhost:8080/TT/Pronostico",  
            data: "CVE=" +cv+ "&sesion="+sessionStorage.getItem("SClave"),  
            success: function(result)
            {
                
                delegacion=result.Delegacion;
                
                //00-03
                proPAN.push(new pronostico("PAN",result.PAN));
                proPRI.push(new pronostico("PRI",result.PRI));
                proPRD.push(new pronostico("PRD",result.PRD));
                proPT.push(new pronostico("PT",result.PT));
                proPVEM.push(new pronostico("PVEM",result.PVEM));
                proValidos.push(new pronostico("Votos Validos",result.DValidos));
                proNulos.push(new pronostico("Votos Nulos",result.DNulos));
                proTotal.push(new pronostico("Votos Totales",result.DTotal));
                proList.push(new pronostico("Lista Nominal",result.DListaNominal));
                proPart.push(new pronostico("Participacion",result.Participacion));
                
                //03-06
                proPAN.push(new pronostico("PAN",result.PAN2));
                proPRI.push(new pronostico("PRI",result.PRI2));
                proPRD.push(new pronostico("PRD",result.PRD2));
                proPT.push(new pronostico("PT",result.PT2));
                proPVEM.push(new pronostico("PVEM",result.PVEM2));
                proValidos.push(new pronostico("Votos Validos",result.DValidos2));
                proNulos.push(new pronostico("Votos Nulos",result.DNulos2));
                proTotal.push(new pronostico("Votos Totales",result.DTotal2));
                proList.push(new pronostico("Lista Nominal",result.DListaNominal2));
                proPart.push(new pronostico("Participacion",result.Participacion2));
                
                //06-09
                proPAN.push(new pronostico("PAN",result.PAN3));
                proPRI.push(new pronostico("PRI",result.PRI3));
                proPRD.push(new pronostico("PRD",result.PRD3));
                proPT.push(new pronostico("PT",result.PT3));
                proPVEM.push(new pronostico("PVEM",result.PVEM3));
                proNA.push(new pronostico("Nueva Alianza",result.NuevaAlianza3));
               // proMC.push(new pronostico("Movimiento Ciudadano",result.Movimiento3));
                proValidos.push(new pronostico("Votos Validos",result.DValidos3));
                proNulos.push(new pronostico("Votos Nulos",result.DNulos3));
                proTotal.push(new pronostico("Votos Totales",result.DTotal3));
                proList.push(new pronostico("Lista Nominal",result.DListaNominal3));
                proPart.push(new pronostico("Participacion",result.Participacion3));
                
                //09-12
                proPAN.push(new pronostico("PAN",result.PAN4));
                proPRI.push(new pronostico("PRI",result.PRI4));
                proPRD.push(new pronostico("PRD",result.PRD4));
                proPT.push(new pronostico("PT",result.PT4));
                proPVEM.push(new pronostico("PVEM",result.PVEM4));
                 proNA.push(new pronostico("Nueva Alianza",result.NuevaAlianza4));
               // proMC.push(new pronostico("Movimiento Ciudadano",result.Movimiento4));
                proValidos.push(new pronostico("Votos Validos",result.DValidos4));
                proNulos.push(new pronostico("Votos Nulos",result.DNulos4));
                proTotal.push(new pronostico("Votos Totales",result.DTotal4));
                proList.push(new pronostico("Lista Nominal",result.DListaNominal4));
                proPart.push(new pronostico("Participacion",result.Participacion4));
                
                //12-15
                proPAN.push(new pronostico("PAN",result.PAN5));
                proPRI.push(new pronostico("PRI",result.PRI5));
                proPRD.push(new pronostico("PRD",result.PRD5));
                proPT.push(new pronostico("PT",result.PT5));
                proPVEM.push(new pronostico("PVEM",result.PVEM5));
                proNA.push(new pronostico("Nueva Alianza",result.NuevaAlianza5));
                proMC.push(new pronostico("Movimiento Ciudadano",result.Movimiento5));
                proMo.push(new pronostico("Morena",result.Morena));
                proValidos.push(new pronostico("Votos Validos",result.DValidos5));
                proNulos.push(new pronostico("Votos Nulos",result.DNulos5));
                proTotal.push(new pronostico("Votos Totales",result.DTotal5));
                proList.push(new pronostico("Lista Nominal",result.DListaNominal5));
                proPart.push(new pronostico("Participacion",result.Participacion5));
                //2018
                pro.push(new pronostico("PAN",result.PAN6));
                pro.push(new pronostico("PRI",result.PRI6));
                pro.push(new pronostico("PRD",result.PRD6));
                pro.push(new pronostico("PT",result.PT6));
                pro.push(new pronostico("PVEM",result.PVEM6));
                pro.push(new pronostico("NuevaAlianza",result.NuevaAlianza6));
                
                pro.push(new pronostico("MovimientoCiudadano",result.Movimiento6));
                pro.push(new pronostico("MORENA",result.Morena6));
                pro.push(new pronostico("VotosValidos",result.DValidos6));
                pro.push(new pronostico("nulos",result.DNulos6));
                pro.push(new pronostico("total",result.DTotal6));
                pro.push(new pronostico("ListaNominal",result.DListaNominal6));
                pro.push(new pronostico("ParticipacionCiudadana",result.Participacion6));
                
                
            }
        });
}

function periodos(){
   /* pan.length=0;pri.length=0;
    prd.length=0;pvem.length=0;pt.length=0;na.length=0;mc.length=0;vali.length=0;
    nulo.length=0;tota.length=0;list.length=0;par.length=0;mo.length=0;*/
              
              datosInc();
              tabla03();
              tabla36();
              tabla69();
              tabla92();
              tabla25();
              p2018();
             
              
        
}

function datosInc(){
    for(x=0;x<proPAN.length;x++)
    {
//                   pan[x]=proPAN[x].incremento;
                     pan.push(new tendencia("PAN",proPAN[x].incremento));
                   console.log("incrementosPAN ("+x+")"+pan[x]);
    }
    proPAN.length=0;
    
    for(x=0;x<proPRI.length;x++)
    {
//                  pri[x]=proPRI[x].incremento;
                    pri.push(new tendencia("PRI",proPRI[x].incremento));
                   console.log("incrementosPRI("+x+")"+pri[x]);
    }
    proPRI.length=0;
    
    for(x=0;x<proPRD.length;x++)
    {
//                  prd[x]=proPRD[x].incremento;
                    prd.push(new tendencia("PRD",proPRD[x].incremento));
                    console.log("incrementosPRD("+x+")"+prd[x]);
    }
    proPRD.length=0;
    
    for(x=0;x<proPT.length;x++)
    {
//                   pt[x]=proPT[x].incremento;
                    pt.push(new tendencia("PT",proPT[x].incremento));
                    console.log("incrementosPT("+x+")"+pt[x]);
    }
    proPT.length=0;
    
    for(x=0;x<proPVEM.length;x++)
    {
//                   pvem[x]=proPVEM[x].incremento;
                   pvem.push(new tendencia("PVEM",proPVEM[x].incremento));
                   console.log("incrementosPVEM("+x+")"+pvem[x]);
    }
    proPVEM.length=0;
    
    for(x=0;x<proNA.length;x++)
    {
//                   na[x]=proNA[x].incremento;
                    na.push(new tendencia("Nueva Alianza",proNA[x].incremento));
                   console.log("incrementosNA("+x+")"+na[x]);
    }
    proNA.length=0;
    
//                   mc[0]=proMC[0].incremento;
                    mc.push(new tendencia("Movimiento Ciudadano",proMC[0].incremento));
                   console.log("movimiento:"+mc[0]);
    proMC.length=0;
   
//                   mo[0]=proMo[0].incremento;
                    mo.push(new tendencia("Morena",proMo[0].incremento));
                   console.log("morena"+mo[0]);
    proMo.length=0;
    
//    for(x=0;x<proValidos.length;x++)
//    {
//                   vali[x]=proValidos[x].incremento;
//                   console.log("incrementosValidos("+x+")"+vali[x]);
//    }
    proValidos.length=0;
    
//    for(x=0;x<proNulos.length;x++)
//    {
//                   nulo[x]=proNulos[x].incremento;
//                   console.log("incrementosNulos("+x+")"+nulo[x]);
//    }
//    proNulos.length=0;
//    
////    for(x=0;x<proTotal.length;x++)
////    {
////                   tota[x]=proTotal[x].incremento;
////                   console.log("incrementosTotal("+x+")"+tota[x]);
////    }
    proTotal.length=0;
    
//    for(x=0;x<proList.length;x++)
//    {
//                   list[x]=proList[x].incremento;
//                   console.log("incrementosLista("+x+")"+list[x]);
//    }
    proList.length=0;
    
//    for(x=0;x<proPart.length;x++)
//    {
//                   par[x]=proPart[x].incremento;
//                   console.log("incrementosPart("+x+")"+prd[x]);
//    }
    proPart.length=0;
    
                   
                  
    
}


function tabla03() {
    var a03=[pan[0].voto,pri[0].voto,prd[0].voto,pt[0].voto,pvem[0].voto];
    var min=Math.min.apply(null, a03);
    var max=Math.max.apply(null, a03);
    var instanciaG;
    var instanciaP;
    switch(max){
        case pan[0].voto:
            instanciaG="PAN";
            break;
            case pri[0].voto:
                instanciaG="PRI";
            break;
            case prd[0].voto:
                instanciaG="PRD";
            break;
            case pt[0].voto:
                instanciaG="PT";
            break;
            case pvem[0].voto:
                instanciaG="PVEM"
            break;
    }
    switch(min){
        case pan[0].voto:
            instanciaP="PAN";
        break;
        case pri[0].voto:
            instanciaP="PRI";
        break;
        case prd[0].voto:
            instanciaP="PRD";
        break;
        case pt[0].voto:
            instanciaP="PT";
        break;
        case pvem[0].voto:
            instanciaP="PVEM"
        break;
    }
    
    var piec = document.getElementById('a03');
    piec.innerHTML = '&nbsp;';
    document.getElementById("a03").innerHTML="El siguiente partido: <b>"+ instanciaG +"</b> obtuvo un porcentaje de: <b>"+max+"%</b> , siendo el partido con el mayor aumento de presencia política en esta delegación, "+
            "en cambio el partido:<b>"+instanciaP+"</b>, obtuvo un porcentaje de: <b>"+min+"%</b> lo que lo ubica como el partido con la mayor pérdida de presencia política en el periodo comprendido del 2000 al 2003.";
    
    
    
   $('#p03').highcharts({
        chart: {
            type: 'column'
        },
        colors: [
        '#008cbf',
        '#fd0000',
        '#ecda00',
        '#fe4451',
        '#95d819'
    ],
        title: {
            text: 'Proceso 2000-2003'
        },
        xAxis: {
            categories: ['PAN', 'PRI', 'PRD', 'PT', 'PVEM'],
             labels: {
                    x: 5,
                    useHTML: true,                        
                    formatter: function () {
                       return categoryImgs[this.value]+'<br><div class="text">' 
                       + this.value +'</div>';
                    }
                }
        },
        yAxis: {
            min: -45,
            max: 50,
            tickInterval: 4,
            lineColor: '#fffff',
            lineWidth: 1,
            title: {
                text: 'Porcentaje'

            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#7c7b77'
            }]
        },
        legend: {
                enabled: false
            },
        credits: {
            enabled: false
        },
        plotOptions: {
             column: {
            colorByPoint: true
        },
            series: {
                pointWidth: 80,
                groupPadding: 0,
                 dataLabels: {
                    enabled: true,
                    useHTML: false,
                    formatter: function () {
                        return this.y+"%";
                    }
                }
            }
        },
        series: [{
            //name: '<img style="width: 50px; height: 50px;" src="http://localhost:8080//TT//Recursos//partidos//PAN.jpg" />',
         //   name: 'PAN',
            data: [pan[0].voto,pri[0].voto,prd[0].voto,pt[0].voto,pvem[0].voto]
        }]
    });
    a03.length=0;
}

function tabla36() {
    var a36=[pan[1].voto,pri[1].voto,prd[1].voto,pt[1].voto,pvem[1].voto];
    var min=Math.min.apply(null, a36);
    var max=Math.max.apply(null, a36);
    var instanciaG;
    var instanciaP;
    switch(max){
        case pan[1].voto:
            instanciaG="PAN";
            break;
            case pri[1].voto:
                instanciaG="PRI";
            break;
            case prd[1].voto:
                instanciaG="PRD";
            break;
            case pt[1].voto:
                instanciaG="PT";
            break;
            case pvem[1].voto:
                instanciaG="PVEM"
            break;
    }
    switch(min){
        case pan[1].voto:
            instanciaP="PAN";
        break;
        case pri[1].voto:
            instanciaP="PRI";
        break;
        case prd[1].voto:
            instanciaP="PRD";
        break;
        case pt[1].voto:
            instanciaP="PT";
        break;
        case pvem[1].voto:
            instanciaP="PVEM"
        break;
    }
    
    var piec = document.getElementById('a36');
    piec.innerHTML = '&nbsp;';
    document.getElementById("a36").innerHTML= "El siguiente partido: <b>"+ instanciaG +"</b> obtuvo un porcentaje de: <b>"+max+"%</b> , siendo el partido con el mayor aumento de presencia política en esta delegación, "+
            "en cambio el partido:<b>"+instanciaP+"</b>, obtuvo un porcentaje de: <b>"+min+"%</b> lo que lo ubica como el partido con la mayor pérdida de presencia política en el periodo comprendido del 2003 al 2006.";
    
    
   $('#p36').highcharts({
        chart: {
            type: 'column'
        },
    colors: [
        '#008cbf',
        '#fd0000',
        '#ecda00',
        '#fe4451',
        '#95d819',
        '#009aa3'
    ],
        title: {
            text: 'Proceso 2003-2006'
        },
        xAxis: {
            categories: ['PAN', 'PRI', 'PRD', 'PT', 'PVEM'],
             labels: {
                    x: 6,
                    useHTML: true,                        
                    formatter: function () {
                       return categoryImgs[this.value]+'<br><div class="text">' 
                       + this.value +'</div>';
                    }
                }
        },
        yAxis: {
           min: -45,
            max: 50,
            tickInterval: 4,
            lineColor: '#fffff',
            lineWidth: 1,
            title: {
                text: 'Porcentaje'

            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#7c7b77'
            }]
        },
        legend: {
                enabled: false
            },
        credits: {
            enabled: false
        },
        plotOptions: {
             column: {
            colorByPoint: true
        },
            series: {
                pointWidth: 80,
                groupPadding: 0,
                 dataLabels: {
                    enabled: true,
                    useHTML: false,
                    formatter: function () {
                        return this.y+"%";
                    }
                }
            }
        },
        series: [{
            //name: '<img style="width: 50px; height: 50px;" src="http://localhost:8080//TT//Recursos//partidos//PAN.jpg" />',
           name: 'Porcentaje',
            data: [pan[1].voto,pri[1].voto,prd[1].voto,pt[1].voto,pvem[1].voto]
        }]
    });
    a36.lenght=0;
}
function tabla69() {
    
    var a69=[pan[2].voto,pri[2].voto,prd[2].voto,pt[2].voto,pvem[2].voto,na[0].voto];
    var min=Math.min.apply(null, a69);
    var max=Math.max.apply(null, a69);
    var instanciaG;
    var instanciaP;
    switch(max){
        case pan[2].voto:
            instanciaG="PAN";
            break;
            case pri[2].voto:
                instanciaG="PRI";
            break;
            case prd[2].voto:
                instanciaG="PRD";
            break;
            case pt[2].voto:
                instanciaG="PT";
            break;
            case pvem[2].voto:
                instanciaG="PVEM"
            break;
            case na[0].voto:
                instanciaG="Nueva Alianza"
            break;
    }
    switch(min){
        case pan[2].voto:
            instanciaP="PAN";
        break;
        case pri[2].voto:
            instanciaP="PRI";
        break;
        case prd[2].voto:
            instanciaP="PRD";
        break;
        case pt[2].voto:
            instanciaP="PT";
        break;
        case pvem[2].voto:
            instanciaP="PVEM"
        break;
        case na[0].voto:
                instanciaP="Nueva Alianza"
            break;
    }
    
    var piec = document.getElementById('a69');
    piec.innerHTML = '&nbsp;';
    document.getElementById("a69").innerHTML="El siguiente partido: <b>"+ instanciaG +"</b> obtuvo un porcentaje de: <b>"+max+"%</b> , siendo el partido con el mayor aumento de presencia política en esta delegación, "+
            "en cambio el partido:<b>"+instanciaP+"</b>, obtuvo un porcentaje de: <b>"+min+"%</b> lo que lo ubica como el partido con la mayor pérdida de presencia política en el periodo comprendido del 2006 al 2009.";
    
    
   $('#p69').highcharts({
        chart: {
            type: 'column'
        },
        colors: [
        '#008cbf',
        '#fd0000',
        '#ecda00',
        '#fe4451',
        '#95d819',
        '#009aa3'
    ],
        title: {
            text: 'Proceso 2006-2009'
        },
        xAxis: {
            categories: ['PAN', 'PRI', 'PRD', 'PT', 'PVEM','NA'],
             labels: {
                    x: 6,
                    useHTML: true,                        
                    formatter: function () {
                        return categoryImgs[this.value]+'<br><div class="text">' 
                       + this.value +'</div>';
                    }
                }
        },
        yAxis: {
            min: -45,
            max: 50,
            tickInterval: 4,
            lineColor: '#fffff',
            lineWidth: 1,
            title: {
                text: 'Porcentaje'

            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#7c7b77'
            }]
        },legend: {
                enabled: false
            },
        credits: {
            enabled: false
        },
         plotOptions: {
             column: {
            colorByPoint: true
        },
        
            series: {
                pointWidth: 80,
                groupPadding: 0,
                 dataLabels: {
                    enabled: true,
                    useHTML: false,
                    formatter: function () {
                       return this.y+"%";
                    }
                }
            }
        },
        series: [{
            //name: '<img style="width: 50px; height: 50px;" src="http://localhost:8080//TT//Recursos//partidos//PAN.jpg" />',
         name: 'Porcentaje',
             data: [pan[2].voto,pri[2].voto,prd[2].voto,pt[2].voto,pvem[2].voto,na[0].voto]
        }]
    });
    a69.lenght=0;
}
function tabla92() {
    var a92=[pan[3].voto,pri[3].voto,prd[3].voto,pt[3].voto,pvem[3].voto,na[1].voto];
    var min=Math.min.apply(null, a92);
    var max=Math.max.apply(null, a92);
    var instanciaG;
    var instanciaP;
    switch(max){
        case pan[3].voto:
            instanciaG="PAN";
            break;
            case pri[3].voto:
                instanciaG="PRI";
            break;
            case prd[3].voto:
                instanciaG="PRD";
            break;
            case pt[3].voto:
                instanciaG="PT";
            break;
            case pvem[3].voto:
                instanciaG="PVEM"
            break;
            case na[1].voto:
                instanciaG="Nueva Alianza"
            break;
    }
    switch(min){
        case pan[3].voto:
            instanciaG="PAN";
            break;
            case pri[3].voto:
                instanciaP="PRI";
            break;
            case prd[3].voto:
                instanciaP="PRD";
            break;
            case pt[3].voto:
                instanciaP="PT";
            break;
            case pvem[3].voto:
                instanciaP="PVEM"
            break;
            case na[1].voto:
                instanciaP="Nueva Alianza"
            break;
    }
    
    var piec = document.getElementById('a92');
    piec.innerHTML = '&nbsp;';
    document.getElementById("a92").innerHTML="El siguiente partido: <b>"+ instanciaG +"</b> obtuvo un porcentaje de: <b>"+max+"%</b> , siendo el partido con el mayor aumento de presencia política en esta delegación, "+
            "en cambio el partido:<b>"+instanciaP+"</b>, obtuvo un porcentaje de: <b>"+min+"%</b> lo que lo ubica como el partido con la mayor pérdida de presencia política en el periodo comprendido del 2009 al 2012.";
    
   $('#p92').highcharts({
        chart: {
            type: 'column'
        },
        colors: [
        '#008cbf',
        '#fd0000',
        '#ecda00',
        '#fe4451',
        '#95d819',
        '#009aa3'
    ],
        title: {
            text: 'Proceso 2009-2012'
        },
        xAxis: {
            categories: ['PAN', 'PRI', 'PRD', 'PT', 'PVEM','NA'],
             labels: {
                    x: 6,
                    useHTML: true,                        
                    formatter: function () {
                        return categoryImgs[this.value]+'<br><div class="text">' 
                       + this.value +'</div>';
                    }
                }
        },
        yAxis: {
            min: -45,
            max: 50,
            tickInterval: 4,
            lineColor: '#fffff',
            lineWidth: 1,
            title: {
                text: 'Porcentaje'

            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#7c7b77'
            }]
        },
        legend: {
                enabled: false
        },
        credits: {
            enabled: false
        },
         plotOptions: {
             column: {
            colorByPoint: true
        },
            series: {
                pointWidth: 80,
                groupPadding: 0,
                 dataLabels: {
                    enabled: true,
                    useHTML: false,
                    formatter: function () {
                       return this.y+"%";
                    }
                }
            }
        },
        series: [{
            //name: '<img style="width: 50px; height: 50px;" src="http://localhost:8080//TT//Recursos//partidos//PAN.jpg" />',
        name: 'Porcentaje',
             data: [pan[3].voto,pri[3].voto,prd[3].voto,pt[3].voto,pvem[3].voto,na[1].voto]
        }]
    });
    a92.legnth=0;
}
function tabla25() {
    var a25=[pan[4].voto,pri[4].voto,prd[4].voto,pt[4].voto,pvem[4].voto,na[2].voto,mc[0].voto,mo[0].voto];
    var min=Math.min.apply(null, a25);
    var max=Math.max.apply(null, a25);
    var instanciaG;
    var instanciaP;
    switch(max){
        case pan[4].voto:
            instanciaG="PAN";
            break;
            case pri[4].voto:
                instanciaG="PRI";
            break;
            case prd[4].voto:
                instanciaG="PRD";
            break;
            case pt[4].voto:
                instanciaG="PT";
            break;
            case pvem[4].voto:
                instanciaG="PVEM"
            break;
            case na[2].voto:
                instanciaG="Nueva Alianza"
            break;
            case mc[0].voto:
                instanciaG="Movimiento Ciudadano"
            break;
            case mo[0].voto:
                instanciaG="Morena"
            break;
    }
    switch(min){
         case pan[4].voto:
            instanciaP="PAN";
            break;
            case pri[4].voto:
                instanciaP="PRI";
            break;
            case prd[4].voto:
                instanciaP="PRD";
            break;
            case pt[4].voto:
                instanciaP="PT";
            break;
            case pvem[4].voto:
                instanciaP="PVEM"
            break;
            case na[2].voto:
                instanciaP="Nueva Alianza"
            break;
            case mc[0].voto:
                instanciaP="Movimiento Ciudadano"
            break;
            case mo[0].voto:
                instanciaP="Morena"
            break;
    }
    
    var piec = document.getElementById('a25');
    piec.innerHTML = '&nbsp;';
    document.getElementById("a25").innerHTML="El siguiente partido: <b>"+ instanciaG +"</b> obtuvo un porcentaje de: <b>"+max+"%</b> , siendo el partido con el mayor aumento de presencia política en esta delegación, "+
            "en cambio el partido:<b>"+instanciaP+"</b>, obtuvo un porcentaje de: <b>"+min+"%</b> lo que lo ubica como el partido con la mayor pérdida de presencia política en el periodo comprendido del 2012 al 2015.";
    
    
   $('#p25').highcharts({
        chart: {
            type: 'column'
        },
        colors: [
        '#008cbf',
        '#fd0000',
        '#ecda00',
        '#fe4451',
        '#95d819',
        '#009aa3',
        '#ffa819',
        '#de500c'
    ],
        title: {
            text: 'Proceso 2012-2015'
        },
        xAxis: {
            categories: ['PAN', 'PRI', 'PRD', 'PT', 'PVEM','NA','MC','Morena'],
             labels: {
                    x: 8,
                    useHTML: true,                        
                    formatter: function () {
                        return categoryImgs[this.value]+'<br><div class="text">' 
                       + this.value +'</div>';
                    }
                }
        },
        yAxis: {
            min: -45,
            max: 50,
            tickInterval: 4,
            lineColor: '#fffff',
            lineWidth: 1,
            title: {
                text: 'Porcentaje'

            },
            plotLines: [{
                value: 1,
                width: 1,
                color: '#7c7b77'
            }]
        },
        legend: {
                enabled: false
        },
        credits: {
            enabled: false
        },
        plotOptions: {
             column: {
            colorByPoint: true
        },
            series: {
                pointWidth: 80,
                groupPadding: 0,
                 dataLabels: {
                    enabled: true,
                    useHTML: false,
                    formatter: function () {
                        return this.y+"%";
                    }
                }
            }
        },
        series: [{
             name: 'Porcentaje',
             data: [pan[4].voto,pri[4].voto,prd[4].voto,pt[4].voto,pvem[4].voto,na[2].voto,mc[0].voto,mo[0].voto]
        }]
    });
    proMo.length=0;
    a25.legnth=0;
}


function p2018(){
           
           console.log();
             // Obtener la referencia del elemento body
            var body = document.getElementById("p18");
            body.innerHTML = '&nbsp;';
            $('#p18').append('<p>Pronostico 2018<br>Por último se muestra una tabla con datos estimados para el siguiente proceso electoral, en este caso, 2018. Estos datos son calculados a partir del análisis previo además de considerar otros factores externos.<p><div id="p18" class="table table-striped table-hover table-bordered "></div>');
            // Crea un elemento <table> y un elemento <tbody>
            var tabla   = document.createElement("table");
            var tblBody = document.createElement("tbody");

            // Crea las celdas
            for (var i = 0; i < 2; i++)
            {
                    // Crea las hileras de la tabla
                var hilera = document.createElement("tr");

                        for (var j = 0; j < pro.length; j++)
                        {
                            switch(i)
                            {
                                case 0:
                                            var nom=pro[j].partido;
                                            console.log("p18:"+nom);
                                            // Crea un elemento <td> y un nodo de texto, haz que el nodo de
                                            // texto sea el contenido de <td>, ubica el elemento <td> al final
                                            // de la hilera de la tabla
                                            var celda = document.createElement("td");
                                            var x = document.createElement("IMG");
                                            x.setAttribute("src", "/TT/Recursos/partidos/"+nom+".jpg");
                                            x.setAttribute("width", "60");
                                            x.setAttribute("height", "60");
                                            x.setAttribute("alt", nom);
                                           // var textoCelda = document.createTextNode("celda en la hilera "+i+", columna "+j);
                                            celda.appendChild(x);
                                            hilera.appendChild(celda);
                                break;
                                case 1:
                                            var voto=pro[j].incremento;
                                           console.log("p18:"+voto);
                                            // Crea un elemento <td> y un nodo de texto, haz que el nodo de
                                            // texto sea el contenido de <td>, ubica el elemento <td> al final
                                            // de la hilera de la tabla
                                            var celda = document.createElement("td");
                                            var textoCelda = document.createTextNode(voto);
                                            celda.appendChild(textoCelda);
                                            hilera.appendChild(celda);
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
            pro.length=0;
            totales='';
            pan.length=0;
            pri.length=0;
            prd.length=0;
            pt.length=0;
            pvem.length=0;
            na.length=0;
            mc.length=0;
            mo.lenght=0;
}