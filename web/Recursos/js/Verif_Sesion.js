/*

Si se accede a una pagina que requiera de datos de sesion cuando estos no existen,
se redirecciona a la pagina Inicio.html

*/

var paginaRedir = "/TT/Login.html";
//////////////////// Funciones

function existsOnDB(sesion)
{
	var solicitud = $.ajax({
 					type : "GET",
 					url : "VerificarSesion",
 					data: "Clave=" + sessionStorage.getItem(IDTSesion),
 					dataType : "xml"
 					});

	solicitud.success(
			function Response(respuesta)
			{
				var Respuesta = respuesta.getElementsByTagName("Respuesta")[0].textContent;
				//console.log(respuesta.getElementsByTagName("Respuesta")[0].textContent);
				if(Respuesta === "1")
				{
					/// La pagina se carga normalmente
				}
				else // si no existe, redireccionar
				{
					sessionStorage.removeItem("IDTSesion");
					sessionStorage.removeItem("Perfil");
					location.href=paginaRedir;
				}
			}
		);
}
////////////////////


var IDTSesion = "Clave";
var sesion = sessionStorage.getItem(IDTSesion);

	if(sesion === null) // si la variable de sesion no existe, redireccionar
	{
		location.href=paginaRedir;
	}
	else // la variable de sesion existe
	{
		existsOnDB(sesion) 
	}
	