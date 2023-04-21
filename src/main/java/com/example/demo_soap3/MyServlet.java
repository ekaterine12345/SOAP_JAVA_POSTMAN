package com.example.demo_soap3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.soap.*;

import java.io.IOException;

@WebServlet(name = "myServlet", value = "/my-servlet")
public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
            SOAPBody soapBody = soapEnvelope.getBody();

            Name company = soapEnvelope.createName("company");
            SOAPElement soapElement = soapBody.addChildElement(company);
            
            SOAPElement soapBodyElem = soapElement.addChildElement("name");
            soapBodyElem.addTextNode("Amazon");

            SOAPElement soapBodyElem1 = soapElement.addChildElement("location");
            soapBodyElem1.addTextNode("USA");

            soapMessage.saveChanges();
            resp.setContentType("text/xml;charset=UTF-8");
            soapMessage.writeTo(resp.getOutputStream());
        } catch (SOAPException e) {
            throw new RuntimeException(e);
        }
    }
}
