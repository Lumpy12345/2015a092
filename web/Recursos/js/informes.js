$.ajax
  ({ 
      type: "GET",  
      async: true,
      url: "http://localhost:8080/TT/ListarXML",  
      data: "sesion=" + sessionStorage.getItem('SClave'),  
      success: llenarSelectFile
      ,
    error: function (xhr, ajaxOptions, thrownError)
    {
        console.log("Error: "+xhr.status);
        console.log("Error: "+ thrownError);
    }
  });
var rootInforme;

function llenarSelectFile(result)
{
  rootInforme = result.firstChild;

  $('#idSelectFile').empty();
  $('#idSelectFile').append('<option>--Ninguno--</option>');

  for(var i = 0 ; i < rootInforme.children.length ; i ++ )
  {
    var option = $('<option>' + rootInforme.children[i].children[1].textContent + '</option>');
    option.attr('Nombre', rootInforme.children[i].children[0].textContent);

    $('#idSelectFile').append(option);    
  }
}

function getInforme()
{
    var str = "";
    $( "#idSelectFile option:selected" ).each(function() 
    {
      if($( this ).get(0).text != '--Ninguno--')
      {
        var fileName = $( this ).attr('Nombre');
        fileName = fileName.replace(/\n/g, '');

        obtenerXML(fileName);
      }
    });
}

function obtenerXML(fileName) 
{
  $.ajax
  ({ 
      type: "GET",  
      async: false,
      url: "http://localhost:8080/TT/ObtenerInfoXML",  
      data: "file=" + fileName,  
      success: getComparativa
      ,
    error: function (xhr, ajaxOptions, thrownError)
    {
        console.log("Error: "+xhr.status);
        console.log("Error: "+ thrownError);
    }
  });

  reRenderizarMapa();
}

function reRenderizarMapa()
{
  $('#map').show();
  var newLayerGroup = asignLayerGroup(cve_mun);
  map.setLayerGroup(newLayerGroup);
  map.updateSize();
}


