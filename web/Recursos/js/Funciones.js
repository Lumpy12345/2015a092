//empieza la verificadion
       var xhttp = new XMLHttpRequest();
       

    xhttp.onreadystatechange=function() {
        if (xhttp.readyState == 4 && xhttp.status == 200) 
        {
            if(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[1].nodeName == "Error")
            {
                // mostramos el error en la interfaz
              window.location.href ="/TT/Login.html"; 
            }else if(window.location=="http://localhost:8080/TT/Login.html")
                 {
                     var Perfil = Perfil=xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[1].innerHTML;
                     window.location.href =Perfil+ "/" + "index.html";
                 }

        }
    }
    var url="http://localhost:8080/TT/VerificarSesion?Clave="+sessionStorage.getItem("SClave");
    console.log(sessionStorage.getItem("SClave") + " <-------");
  xhttp.open("GET", url, true);
  xhttp.send();


function Cerrar_Sesion(){
    var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange=function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) 
    {
         window.location.href ="/TT/Login.html";  
         sessionStorage.clear();
    }
    }
    var url="http://localhost:8080/TT/Logout?Clave="+sessionStorage.getItem("SClave");
    console.log(sessionStorage.getItem("SClave") + " <-------");
  xhttp.open("GET", url, true);
  xhttp.send();
    return false;
}

function eliminarU(correo){
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange=function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) 
    {
        
        if(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].nodeName == "Error")
        {
            // mostramos el error en la interfaz
            console.log(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].innerHTML);
            alert(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].innerHTML);
        }
        else
        {
                window.location.href ="usuarios.html";
         
        }
    }
 
  };
  var url="http://localhost:8080/TT/EliminarUsuario?Clave="+sessionStorage.getItem("SClave")+"&Correo="+correo;
  xhttp.open("GET", url, true);
  xhttp.send();
    return false;
}

function ingresarUsuario(){
   var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange=function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) 
    {
        if(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].nodeName == "Error")
        {
            // mostramos el error en la interfaz
            console.log(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].innerHTML);
            alert(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].innerHTML);
        }
        else
        {
            window.location.href ="usuarios.html";
        }
    }
 
  };

  var url="http://localhost:8080/TT/AgregarUsuario?Clave="+sessionStorage.getItem("SClave")+'&Correo='+$("#Correo").val()+'&Password='+$("#Password").val()+'&Nombre='+$("#Nombre").val()+'&APaterno='+$("#APaterno").val()+'&AMaterno='+$("#AMaterno").val()
                        +'&Perfil='+$("#Perfil").val();
  xhttp.open("GET", url, true);
  xhttp.send();
    return false;
}

function actualizarUsuario(correo){
   var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange=function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) 
    {
        
        if(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].nodeName == "Error")
        {
            // mostramos el error en la interfaz
            console.log(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].innerHTML);
            alert(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].innerHTML);
        }
        else
        {
            window.location.href ="/TT/Admin/usuarios.html";
        }
    }
 
  };
 // console.log(correo);
  var url="http://localhost:8080/TT/ActualizarUsuario?Clave="+sessionStorage.getItem("SClave")+'&Correo='+$("#Correo").val()+'&Password='+$("#Password").val()+'&Nombre='+$("#Nombre").val()+'&APaterno='+$("#APaterno").val()+'&AMaterno='+$("#AMaterno").val()
                        +'&Perfil='+$("#Perfil").val()+'&email='+correo;
  alert(url);
  xhttp.open("GET", url, true);
  xhttp.send();
   return false;
}

function editarU(correo){
   var xhttp = new XMLHttpRequest();

    xhttp.onreadystatechange=function() {
    if (xhttp.readyState == 4 && xhttp.status == 200) 
    {
        
        if(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].nodeName == "Error")
        {
            // mostramos el error en la interfaz
            console.log(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].innerHTML);
            alert(xhttp.responseXML.getElementsByTagName("Sesion")[0].childNodes[2].innerHTML);
        }
        else
        {
            window.location.href ="usuarios.html";
        }
    }
 
  };
  alert("Entro");
  var url="http://localhost:8080/TT/ObtenerUsuario?Correo="+correo;
  xhttp.open("GET", url, true);
  xhttp.send();
    return false;
}


