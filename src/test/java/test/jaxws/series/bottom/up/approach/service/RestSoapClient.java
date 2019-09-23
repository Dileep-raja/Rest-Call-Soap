package test.jaxws.series.bottom.up.approach.service;

import java.io.ByteArrayOutputStream;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;

public class RestSoapClient {
	
	 public static void main(String args[]){
	        try{	
	        	/*<?xml version="1.0" encoding="UTF-8"?>
	        	<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	        	   <soap:Body>
	        	      <ns2:getBookNameBasedOnISBNResponse xmlns:ns2="http://service.approach.up.bottom.series.jaxws.com/">
	        	         <return>Microbiology</return>
	        	      </ns2:getBookNameBasedOnISBNResponse>
	        	   </soap:Body>
	        	</soap:Envelope>*/
	        	 MessageFactory messageFactory = MessageFactory.newInstance();
	    		 SOAPMessage soapMessage = messageFactory.createMessage();
	    		 SOAPPart soapPart = soapMessage.getSOAPPart();
	       	         SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
	       	         soapEnvelope.addNamespaceDeclaration("ser", "http://service.approach.up.bottom.series.jaxws.com/");
	    		 SOAPBody soapBody = soapEnvelope.getBody();
	    		 SOAPElement soapElement = soapBody.addChildElement("getBookNameBasedOnISBN", "ser");
	    		 SOAPElement element1 = soapElement.addChildElement("arg0");
	    		 element1.addTextNode("ISBN-2134");
	    		 soapMessage.saveChanges();
	    		 System.out.println("----------SOAP Request------------");
	    		 ByteArrayOutputStream baos =  new ByteArrayOutputStream();
	    		 soapMessage.writeTo(baos);
	        	
	        	Response response = ClientBuilder.
	        			newClient()
	        			.target("http://localhost:8080/ApacheCXF-JAX-WS-Bottom-Up/services/book/BookService")
	        			.request()
	        			.accept(MediaType.APPLICATION_XML)
	        			.post(Entity.xml(baos.toByteArray()));
	       
	        System.out.println(response.readEntity(String.class));
	        	
		}catch (Exception e) {
		     e.printStackTrace();
		}
	 }
}