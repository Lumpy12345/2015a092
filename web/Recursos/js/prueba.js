sendXml();

function sendXml()   {  
        var str = '<?xml version="1.0" encoding="UTF-8"?><foo><bar>Hello World</bar></foo>';
    
      /*  $.ajax({
                url: "http://localhost:8084/TT/RecibeXML"
                , type: "POST"
                ,dataType: "xml"
                , contentType: "text/xml"
                , processData: false
                , data: str
                , success: function (res) {
                    alert("XML: it works!");
                },
                error: function (res) {
                    alert("XML: not working! " + res.statusText);
                }
                });*/
         $.ajax({
                type: "POST",
                url: "http://localhost:8084/TT/RecibeXML",
                dataType: "xml",
                contentType: "application/xml",
                data: '<?xml version="1.0" encoding="UTF-8"?><Category><categoryId>007</categoryId><categoryName>Ajax</categoryName></Category>',
                success: function (res) {
                    alert("XML: it works!");
                },
                error: function (res) {
                    alert("XML: not working! " + res.statusText);
                }
            });
 /*$.ajax({
            type: "POST",
            url: "http://localhost:8084/TT/RecibeXML",
            dataType: "xml",
            contentType: "application/xml",
            data: "<Category><categoryId>007</categoryId><categoryName>Ajax</categoryName></Category>",
            success: function (res) {
                alert("XML: it works!");
            },
            error: function (res) {
                alert("XML: not working! " + res.statusText);
            }
        });*/
     }