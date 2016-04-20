var info = [];
var victoriaDelegaciones = [];
var listCVE = ['002','003','004','005','006','007','008','009','010','011','012','013','014','015','016','017'];
var listNombreDelegacion = ['Azcapotzalco','Coyoacán','Cuajimalpa de Morelos','Gustavo A. Madero','Iztacalco',
							'Iztapalapa','La Magdalena Contreras', 'Milpa Alta','Álvaro Obregón','Tláhuac',
							'Tlalpan','Xochimilco','Benito Juárez','Cuauhtémoc','Miguel Hidalgo','Venustiano Carranza'];
var partidoGanador; 
var delegacionSeleccionada;
var CVE = "";
var anio = "";
var primerIntento = true;
var porcentajeVictoria;

function partido(nombre,color,high,voto,anio)
{
    this.nombre=nombre;
    this.color=color;
    this.high=high;
    this.voto=voto;
    this.anio = anio;
}

function victoriaDelegacion(nombreDelegacion,votos)
{
	this.nombreDelegacion = nombreDelegacion;
	this.votos = votos;
}

$('#idDelegacion').hide();
$('#map').hide();
$('#idTabla1').hide();
$('#idTabla2').hide();
$('#idTxtTabla1').hide();

$('#idPartidos').hide();
$('#partidoGanador').hide();

$('#myChart').hide();
$('#porcentajeVictorias').hide();

/////////////////////////////////////////////////////////////
function obtenerPartidoGanador(lista)
{
	var Partido1 = lista[0];
	var PartidoGanador = lista[1];

	if(parseInt(Partido1.voto) > parseInt(PartidoGanador.voto))
	{
		PartidoGanador = Partido1;
	}

	for(var i = 2 ; i < lista.length ; i++ )
	{
		Partido1 = lista[i];
		
		if(parseInt(Partido1.voto) > parseInt(PartidoGanador.voto))
		{
			PartidoGanador = Partido1;
		}
	}

	return PartidoGanador;
}

function mostrarPartidoGanador()
{
	partidoGanador = obtenerPartidoGanador(info);
	var imgURL = obtenerURLPartido(partidoGanador);

	

	$('#partidoGanador').empty();
	$('#partidoGanador').append('<h1>Partido ganador.</h1>');
	$('#partidoGanador').append('<div><img src=\"'+ imgURL +'\" width="350" height="350" /></div>');
	//$('#partidoGanador').append('<p>' + partidoGanador.nombre + '</p>');
	$('#partidoGanador').append('<p>Votos : ' + partidoGanador.voto + '</p>');

	info = info.sort(function(a, b)
			{
				return parseInt(b.voto) - parseInt(a.voto);
			});
	//$('#partidoGanador').append(info);
	$('#partidoGanador').fadeIn('slow');

}

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

function mostrarTabla1()
{
	$('#idTxtTabla1').show();
	$('#idTabla1').show();
	$('#idTabla1 tbody').empty();

	info = info.sort(function(a, b)
			{
				return parseInt(b.voto) - parseInt(a.voto);
			});

	for(var i = 0 ; i < info.length ; i ++)
	{
		var $row = $('<tr></tr>');

		$row.append('<th scope="row" >'+ (i+1) +'</th>');
		$row.append('<td>'+ info[i].nombre +'</td>');
		$row.append('<td>'+ info[i].voto +'</td>');

		$('#idTabla1 tbody').append($row);
	}
}

function mostrarTabla2()
{
	victoriaDelegaciones.length = 0;
	$('#idTabla2 table tbody').empty();

	for(var i = 0 ; i < listCVE.length ; i++ )
	{
		$.ajax
		({ 
	        type: "GET",  
	        async: false,
	        url: "http://localhost:8080/TT/ObtenerDelegacion",  
	        data: "Clave=" +listCVE[i]+ "&ano=ano" +anio,  
	        success: function(result)
	        {
	        	listaPartidos_v2(result,partidoGanador,i);
	        }
	       	,
		    error: function (xhr, ajaxOptions, thrownError)
		    {
		        alert("Error: "+xhr.status);
		        alert("Error: "+ thrownError);
		    }
	  	});
	}

	victoriaDelegaciones = victoriaDelegaciones.sort(function(a, b)
			{
				return parseInt(b.votos) - parseInt(a.votos);
			});

	for(var j = 0 ; j < victoriaDelegaciones.length ; j++)
	{
		var $row = $('<tr></tr>');

		//$row.append('<th scope="row" >'+ (i+1) +'</th>');
		$row.append('<td>'+ victoriaDelegaciones[j].nombreDelegacion +'</td>');
		$row.append('<td>'+ victoriaDelegaciones[j].votos +'</td>');

		$('#idTabla2 table tbody').append($row);
	}

	$('#idTabla2').show();
}

function listaPartidos_v2(result, partidoGanador,indexCVE)
{
	var partidos = [];

	if(anio.includes('1994'))
    {
    	partidos.push(new partido("PAN","","",result.PAN,1994));
        partidos.push(new partido("PRI","","",result.PRI,1994));
        partidos.push(new partido("PPS","","",result.PPS,1994));
        partidos.push(new partido("PRD","","",result.PRD,1994));
        partidos.push(new partido("PFCRN","","",result.PFCRN,1994));
        partidos.push(new partido("PARM","","",result.PARM,1994));
        partidos.push(new partido("UNO","","",result.UNO,1994));
        partidos.push(new partido("PT","","",result.PT,1994));
        partidos.push(new partido("PVEM","","",result.PVEM,1994));
    }
    if(anio.includes('2000'))
    {
        partidos.push(new partido("AC","","",result.AC,2000));
        partidos.push(new partido("PRI","","",result.PRI,2000));
        partidos.push(new partido("AM","","",result.AM,2000));
        partidos.push(new partido("PCD","","",result.PCD,2000));
        partidos.push(new partido("PARM","","",result.PARM,2000));
        partidos.push(new partido("DSPPN","","",result.DSPPN,2000));
    }
    if(anio.includes('2006'))
    {
        partidos.push(new partido("PAN","","",result.PAN,2006));
        partidos.push(new partido("Alianza por México","","",result.Alianza,2006));
        partidos.push(new partido("Por el bien de todos","","",result.Porelbien,2006));
        partidos.push(new partido("Nueva Alianza","","",result.NuevaAlianza,2006));
        partidos.push(new partido("Alternativa","","",result.Alternativa,2006));
    }
    if(anio.includes('2012'))
    {
        partidos.push(new partido("PAN","","",result.PAN,2012));
        partidos.push(new partido("PRI","","",result.PRI,2012));
        partidos.push(new partido("PRD","","",result.PRD,2012));
        partidos.push(new partido("PVEM","","",result.PVEM,2012));
        partidos.push(new partido("PT","","",result.PT,2012));
        partidos.push(new partido("Movimiento Ciudadano","","",result.Movimiento,2012));
        partidos.push(new partido("Nueva Alianza","","",result.NuevaAlianza,2012));
        partidos.push(new partido("Coalición PRI-PVEM","","",result.PRIPVEM,2012));
        partidos.push(new partido("Coalición PRD-PT-MC","","",result.PRDPTMC,2012));
        partidos.push(new partido("Coalición PRD-PT","","",result.PRDPT,2012));
        partidos.push(new partido("Coalición PRD-MC","","",result.PRDMC,2012));
        partidos.push(new partido("Coalición PT-MC","","",result.PTMC,2012));
    } 

    partidos = partidos.sort(function(a, b)
			{
				return parseInt(b.voto) - parseInt(a.voto);
			});
    

    if(partidos[0].nombre == partidoGanador.nombre)
    {
    	console.log('Partido[0] : ' + partidos[0].nombre + ' : ' + partidos[0].voto);
    	console.log('indexCVE : ' + indexCVE);
    	console.log('listNombreDelegacion[indexCVE] : ' + listNombreDelegacion[indexCVE]);

    	victoriaDelegaciones.push(new victoriaDelegacion(listNombreDelegacion[indexCVE],partidos[0].voto));
    }


}

function obtenerPartidos()
{
	info.length = 0;

	obtenerPartidosCallOut(  $('#idSelectAnio').val() );
}

function obtenerPartidosCallOut(paramAnio)
{
	anio = paramAnio;

	$.ajax
	({ 
        type: "GET",  
        async: false,
        url: "http://localhost:8080/TT/ObtenerDelegacion",  
        data: "Clave=" +CVE+ "&ano=ano" +paramAnio,  
        success: listaPartidos
       	,
	    error: function (xhr, ajaxOptions, thrownError)
	    {
	        alert("Error: "+xhr.status);
	        alert("Error: "+ thrownError);
	    }
  	}); 
}

function listaPartidos(result)
{
	if(anio.includes('1994'))
    {
    	info.push(new partido("PAN","","",result.PAN,1994));
        info.push(new partido("PRI","","",result.PRI,1994));
        info.push(new partido("PPS","","",result.PPS,1994));
        info.push(new partido("PRD","","",result.PRD,1994));
        info.push(new partido("PFCRN","","",result.PFCRN,1994));
        info.push(new partido("PARM","","",result.PARM,1994));
        info.push(new partido("UNO","","",result.UNO,1994));
        info.push(new partido("PT","","",result.PT,1994));
        info.push(new partido("PVEM","","",result.PVEM,1994));
    }
    if(anio.includes('2000'))
    {
        info.push(new partido("AC","","",result.AC,2000));
        info.push(new partido("PRI","","",result.PRI,2000));
        info.push(new partido("AM","","",result.AM,2000));
        info.push(new partido("PCD","","",result.PCD,2000));
        info.push(new partido("PARM","","",result.PARM,2000));
        info.push(new partido("DSPPN","","",result.DSPPN,2000));
    }
    if(anio.includes('2006'))
    {
        info.push(new partido("PAN","","",result.PAN,2006));
        info.push(new partido("Alianza por México","","",result.Alianza,2006));
        info.push(new partido("Por el bien de todos","","",result.Porelbien,2006));
        info.push(new partido("Nueva Alianza","","",result.NuevaAlianza,2006));
        info.push(new partido("Alternativa","","",result.Alternativa,2006));
    }
    if(anio.includes('2012'))
    {
        info.push(new partido("PAN","","",result.PAN,2012));
        info.push(new partido("PRI","","",result.PRI,2012));
        info.push(new partido("PRD","","",result.PRD,2012));
        info.push(new partido("PVEM","","",result.PVEM,2012));
        info.push(new partido("PT","","",result.PT,2012));
        info.push(new partido("Movimiento Ciudadano","","",result.Movimiento,2012));
        info.push(new partido("Nueva Alianza","","",result.NuevaAlianza,2012));
        info.push(new partido("Coalición PRI-PVEM","","",result.PRIPVEM,2012));
        info.push(new partido("Coalición PRD-PT-MC","","",result.PRDPTMC,2012));
        info.push(new partido("Coalición PRD-PT","","",result.PRDPT,2012));
        info.push(new partido("Coalición PRD-MC","","",result.PRDMC,2012));
        info.push(new partido("Coalición PT-MC","","",result.PTMC,2012));
    } 

    mostrarPartidos();
}

function mostrarPartidos()
{
	$('#idSelectPartido').empty();
	$('#idSelectPartido').append('<option>-- Ninguno --</option>');

	info.forEach
	(
		function(partido)
		{
			$('#idSelectPartido').append('<option>' + partido.nombre + '</option>');
		}
	);

	
	$('#idPartidos').show();
}


function doughnutChart()
{
	$('#myChart').show();

	var data = 
	[
	  {
	      value: victoriaDelegaciones.length,
	      color: "#46BFBD",
	      highlight: "#5AD3D1",
	      label: "Delegaciones ganadas"
	  },
	  {
	      value: 16 - victoriaDelegaciones.length,
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

	porcentajeVictoria = ((victoriaDelegaciones.length) * 100) / 16;

	$('#porcentajeVictorias').empty();
	$('#porcentajeVictorias').show();
	$('#porcentajeVictorias').append('<p>' + porcentajeVictoria + '%</p>');
	$('#porcentajeVictorias').append('<p>de éxito en CDMX</p>');

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
		                    obtenerPartidos();

		                    var newLayerGroup = asignLayerGroup(CVE);
                    		map.setLayerGroup(newLayerGroup);

                    		$('#idDelegacion').text(Estado);

                    		mostrarPartidoGanador();
                    		mostrarTabla1();
                    		mostrarTabla2();
                    		doughnutChart();
						},1000
					);
				}
			);

			$('#idIFRAME').attr('src',url);
		}
	});

function obtenerURLPartido(partidoGanador)
{
	var URL = 'http://localhost:8080/TT/Recursos/img/';

	if(partidoGanador.nombre == 'PAN')
		return URL + 'PAN.png' ;
	if(partidoGanador.nombre == 'PRI')
		return URL + 'PRI.jpg' ;
	if(partidoGanador.nombre == 'PPS')
		return URL + 'PPS.gif' ;
	if(partidoGanador.nombre == 'PRD')
		return URL + 'PRD.jpg' ;
	if(partidoGanador.nombre == 'PFCRN')
		return URL + 'PFCRN.png' ;
	if(partidoGanador.nombre == 'PARM')
		return URL + 'PARM.png' ;
	if(partidoGanador.nombre == 'UNO') // falta
		return URL + '' ;
	if(partidoGanador.nombre == 'PT')
		return URL + 'PT.png' ;
	if(partidoGanador.nombre == 'PVEM')
		return URL + 'PVEM.png' ;
	if(partidoGanador.nombre == 'AC') // falta
		return URL + '' ;
	if(partidoGanador.nombre == 'AM') // falta
		return URL + '' ;
	if(partidoGanador.nombre == 'PCD')
		return URL + 'PCD.jpg' ;
	if(partidoGanador.nombre == 'DSPPN') //falta
		return URL + '' ;
	if(partidoGanador.nombre == 'Alianza por México')
		return URL + 'Alianza_por_México.jpg' ;
	if(partidoGanador.nombre == 'Por el bien de todos')
		return URL + 'PorElBienDeTodos.jpg' ;
	if(partidoGanador.nombre == 'Nueva Alianza')
		return URL + 'NuevaAlianza.png' ;
	if(partidoGanador.nombre == 'Alternativa')
		return URL + 'alternativa.jpg' ;
	if(partidoGanador.nombre == 'Movimiento Ciudadano')
		return URL + 'MC.jpeg' ;
	if(partidoGanador.nombre == 'Coalición PRI-PVEM')
		return URL + 'PRI-PVEM.jpg' ;
	if(partidoGanador.nombre == 'Coalición PRD-PT-MC')
		return URL + 'PRD-PT-MC.jpg' ;
	if(partidoGanador.nombre == 'Coalición PRD-PT')
		return URL + 'PRD-PT.jpg' ;
	if(partidoGanador.nombre == 'Coalición PRD-MC')// falta
		return URL + '' ;
	if(partidoGanador.nombre == 'Coalición PT-MC') // falta
		return URL + '' ;

}
