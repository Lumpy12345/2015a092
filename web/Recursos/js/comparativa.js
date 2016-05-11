
var CVE = "";
var anio = "";
var primerIntento = true;


$('#idDelegacion').hide();
$('#generarInforme').hide();
$('#map').hide();
$('#idTabla1').hide();
$('#idTabla2').hide();
$('#idTxtTabla1').hide();

$('#idPartidos').hide();
$('#partidoGanador').hide();

$('#myChart').hide();
$('#porcentajeVictorias').hide();

function mostrarMapa()
{
	if($('#idSelectAnio').val() != '--Ninguno--')
	{
		if(primerIntento)
		{
			$('#idDelegacion').show();
			$('#map').show();
			map.updateSize();
			primerIntento = false;
		}
		else
		{
			if(CVE.length != 0)
				obtenerPartidos();
		}
	}
}

/////////////////////////////////////////////////////////////////////
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
      		                    //obtenerPartidos();
                              $.ajax
                              ({ 
                                  type: "GET",  
                                  async: false,
                                  url: "http://localhost:8080/TT/SComparativa",  
                                  data: "cve=" +CVE+ "&anio=" +$('#idSelectAnio').val(),  
                                  success: getComparativa
                                  ,
                                error: function (xhr, ajaxOptions, thrownError)
                                {
                                    console.log("Error: "+xhr.status);
                                    console.log("Error: "+ thrownError);
                                }
                              });
                              var newLayerGroup = asignLayerGroup(CVE);
                              map.setLayerGroup(newLayerGroup);
      						},1000
      					);
      				}
			     );

			$('#idIFRAME').attr('src',url);

		}
	});


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
  $('#idDelegacion').get(0).textContent = nombre;
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

  var elChart = $('<div id="js-legend" class="chart-legend"></div><canvas id="myChart" width="400" height="400"></canvas><div id="porcentajeVictorias"></div>');

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

  var ctx = $("#myChart").get(0).getContext("2d");
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