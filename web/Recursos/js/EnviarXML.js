function enviarXML(CVE_MUN,Nombre,PartidoGanador,Votos,arrResultados,
					arrVictoriasOtrasDelegaciones,DelegacionesGanadas,
					DelegacionesPerdidas,Porcentaje)
{
	arrResultados = arrResultados.sort(function(a, b)
			{
				return parseInt(b.voto) - parseInt(a.voto);
			});

	arrVictoriasOtrasDelegaciones = arrVictoriasOtrasDelegaciones.sort(function(a, b)
			{
				return parseInt(b.votos) - parseInt(a.votos);
			});

	var $root = $('<root></root>');

	var $CVE_MUN = $('<CVE_MUN></CVE_MUN>');
	$CVE_MUN.append(CVE_MUN);

	$root.append($CVE_MUN);

	var $Nombre = $('<Nombre></Nombre>');
	$Nombre.append(Nombre);

	$root.append($Nombre);

	var $PartidoGanador = $('<PartidoGanador></PartidoGanador>');
	$PartidoGanador.append(PartidoGanador);

	$root.append($PartidoGanador);

	var $Votos = $('<Votos></Votos>');
	$Votos.append(Votos);

	/////////////////////////////////////////////////////////

	var $Tabla1 = $('<Tabla1></Tabla1>');

	for(var fila = 0 ; fila < arrResultados.length ; fila++ )
	{
		var $Fila = $('<tr></tr>');

		var $Nombre = $('<td></td>');
		$Nombre.append(arrResultados[fila].nombre);

		var $Voto = $('<td></td>');
		$Voto.append(arrResultados[fila].voto);

		$Fila.append($Nombre);
		$Fila.append($Voto);

		$Tabla1.append($Fila);
	}

	$root.append($Tabla1);

	/////////////////////////////////////////////////////////

	var $Tabla2 = $('<Tabla2></Tabla2>');

	for(var fila = 0 ; fila < arrVictoriasOtrasDelegaciones.length ; fila++ )
	{
		var $Fila = $('<tr></tr>');

		var $Nombre = $('<td></td>');
		$Nombre.append(arrVictoriasOtrasDelegaciones[fila].nombreDelegacion);

		var $Voto = $('<td></td>');
		$Voto.append(arrVictoriasOtrasDelegaciones[fila].votos);

		$Fila.append($Nombre);
		$Fila.append($Voto);

		$Tabla2.append($Fila);
	}

	$root.append($Tabla2);

	/////////////////////////////////////////////////////////

	var $DelegacionesGanadas = $('<DelegacionesGanadas></DelegacionesGanadas>');
	$DelegacionesGanadas.append(DelegacionesGanadas);

	$root.append($DelegacionesGanadas);

	var $DelegacionesPerdidas = $('<DelegacionesPerdidas></DelegacionesPerdidas>');
	$DelegacionesPerdidas.append(DelegacionesPerdidas);

	$root.append($DelegacionesPerdidas);

	var $Porcentaje = $('<Porcentaje></Porcentaje>');
	$Porcentaje.append(Porcentaje);

	$root.append($Porcentaje);


	console.log($root[0].outerHTML);

	$.ajax({
           url: "http://localhost:8080/TT/RecibeXML", 
           processData: false,
           type: "POST",  // type should be POST
           data: $root[0].outerHTML, // send the string directly
           success: function(response){
             console.log(response);
           },
           error: function(response) {
              console.log(response);
           }
        });

}