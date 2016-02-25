
function crearSesion(Clave){
    sessionStorage.setItem("SClave",Clave);
}
function Iniciar_Sesion()
{
	var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange=function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) 
    {
        
        if(xhttp.responseXML.getElementsByTagName("login")[0].childNodes[1].nodeName == "Error")
        {
            // mostramos el error en la interfaz
            console.log(xhttp.responseXML.getElementsByTagName("login")[0].childNodes[1].innerHTML);
            alert(xhttp.responseXML.getElementsByTagName("login")[0].childNodes[1].innerHTML);
        }
        else
        {
            var Clave = Perfil=xhttp.responseXML.getElementsByTagName("login")[0].childNodes[1].innerHTML;
            var Perfil = Perfil=xhttp.responseXML.getElementsByTagName("login")[0].childNodes[3].innerHTML;
            
            
            crearSesion(Clave);
            console.log("Clave = " + Clave + " Perfil = " + Perfil);
            
            window.location.href =Perfil+ "/" + "index.html";
         
        }
    }
 
  };
  var correo=$("#Correo").val();
  var pass=$("#Password").val();
  var url="http://localhost:8084/TT/Login?Correo="+correo+"&Password="+pass;
  xhttp.open("GET", url, true);
  xhttp.send();
    return false;
}

