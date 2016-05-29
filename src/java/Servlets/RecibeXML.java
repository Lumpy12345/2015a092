/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
//import org.apache.catalina.util.XMLWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;



/**
 *
 * @author Lucia
 */
public class RecibeXML extends HttpServlet {
  

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/xml");
         
         String xml=null;
        byte[] xmlData = new byte[request.getContentLength()];

            //Start reading XML Request as a Stream of Bytes
            InputStream sis = request.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(sis);

            bis.read(xmlData, 0, xmlData.length);

            if (request.getCharacterEncoding() != null) {
                    xml = new String(xmlData, request.getCharacterEncoding());
            } else {
                    xml = new String(xmlData);
            }
            
            System.out.println(xml); 
            
            //Creo archivo
            FileOutputStream fop = null;
		File file;
		

		try {

			file = new File("C:\\Users\\Lucia\\Desktop\\2015a092\\web\\xmlfiles\\p2.xml");
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// get the content in bytes
			byte[] contentInBytes = xml.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

			System.out.println("Done");
                        out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
                        out.println("Valio verga :v");
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
                                out.println("Valio verga :v");
			}
		}
            //************/
            
                        
    }
    
    public static Document loadXMLAsDom(String xml){
	return loadXMLAsDom(new ByteArrayInputStream(xml.getBytes()));
}
public static Document loadXMLAsDom(InputStream inputStream) {
	DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	documentBuilderFactory.setNamespaceAware(true);
	DocumentBuilder documentBuilder = null;
	Document document = null;
	try{
		documentBuilder  = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(new InputSource(inputStream)); //use InputSource here to get better support for encodings
		inputStream.close();
	}
	catch (ParserConfigurationException e){
		System.out.println("loadXMLAsDom got a ParserConfigurationException! "); 
	}
	catch (IOException e){
		System.out.println("loadXMLAsDom got a IOException! ");
	}
	catch (SAXException e){
		System.out.println("loadXMLAsDom got a SAXException! "); 
	}		
	return document;
}
    
    public static Document newDocumentFromInputStream(InputStream in) {
    DocumentBuilderFactory factory = null;
    DocumentBuilder builder = null;
    Document ret = null;

    try {
      factory = DocumentBuilderFactory.newInstance();
      builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }

    try {
      ret = builder.parse(new InputSource(in));
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ret;
  }
    
    public static void copyDocument(Document source, Document target)
  {
    Node node = target.importNode(source.getDocumentElement(), true);

    target.getDocumentElement().appendChild(node);
  }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}