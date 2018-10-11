package test.jaxws.series.bottom.up.approach.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.springframework.http.MediaType;

public class RestSoapClient {
	private static SOAPMessage createSoapRequest() throws Exception{
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
		 soapMessage.writeTo(System.out);
		 return soapMessage;
	 }
	 private static void createSoapResponse(SOAPMessage soapResponse) throws Exception  {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		System.out.println("\n----------SOAP Response-----------");
		StreamResult result = new StreamResult(System.out);
		transformer.transform(sourceContent, result);
	 }
	 public static void main(String args[]){
	        try{
			/*SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();
			String url = "http://localhost:8011/ApacheCXF-JAX-WS-Bottom-Up/services/book/BookService?wsdl";
			SOAPMessage soapRequest = createSoapRequest();
			//hit soapRequest to the server to get response
			SOAPMessage soapResponse = soapConnection.call(soapRequest, url);
			createSoapResponse(soapResponse);
			soapConnection.close();*/
	        	
	        	/*<?xml version="1.0" encoding="UTF-8"?>
	        	<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
	        	   <soap:Body>
	        	      <ns2:getBookNameBasedOnISBNResponse xmlns:ns2="http://service.approach.up.bottom.series.jaxws.com/">
	        	         <return>Microbiology</return>
	        	      </ns2:getBookNameBasedOnISBNResponse>
	        	   </soap:Body>
	        	</soap:Envelope>*/
	       
	        	Client client = ClientBuilder.newClient();
	        	WebTarget webTarget 
	        	  = client.target("http://localhost:8011/ApacheCXF-JAX-WS-Bottom-Up/services/book/BookService");
	        	
		}catch (Exception e) {
		     e.printStackTrace();
		}
	 }
}