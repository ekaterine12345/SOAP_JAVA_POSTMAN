package com.example.demo_soap3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.soap.*;

import java.io.IOException;

@WebServlet(name="secondServlet", value = "/second-servlet")
public class SecondServlet extends HelloServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //    super.doPost(req, resp);
        try {
            MessageFactory messageFactory = MessageFactory.newInstance();
            SOAPMessage soapMessage = messageFactory.createMessage();
            SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
            SOAPBody soapBody = soapEnvelope.getBody();

            Name student_name = soapEnvelope.createName("student_name");
            SOAPElement soapElement = soapBody.addChildElement(student_name);

            SOAPElement soapElement1 = soapElement.addChildElement("name");
            soapElement1.addTextNode("Ekaterine");

            SOAPElement soapElement2 = soapElement.addChildElement("lastname");
            soapElement2.addTextNode("Gurgenidze");

            SOAPElement soapElement3 = soapElement.addChildElement("age");
            soapElement3.addTextNode("20");

            soapMessage.saveChanges();
            resp.setContentType("text/xml;charset=UTF-8");
            soapMessage.writeTo(resp.getOutputStream());

        } catch (SOAPException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
       // super.doGet(request, response);
            try{
                MessageFactory messageFactory = MessageFactory.newInstance();
                SOAPMessage soapMessage = messageFactory.createMessage();
                SOAPEnvelope soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
                SOAPBody soapBody = soapEnvelope.getBody();

                SOAPElement students = soapBody.addChildElement("students");

                // Create a student1 element
                SOAPElement student1 = students.addChildElement("student");

                // Add a name element to student1
                SOAPElement name = student1.addChildElement("name");
                name.addTextNode("Ekaterine");

                // Add a lastname element to student1
                SOAPElement lastname = student1.addChildElement("lastname");
                lastname.addTextNode("Gurgenidze");

                // Add an age element to student1
                SOAPElement age = student1.addChildElement("age");
                age.addTextNode("21");


                // Create a student1 element
                SOAPElement student2 = students.addChildElement("student");

                // Add a name element to student1
                SOAPElement name2 = student2.addChildElement("name");
                name2.addTextNode("Ekaterine2");

                // Add a lastname element to student1
                SOAPElement lastname2 = student2.addChildElement("lastname");
                lastname2.addTextNode("Gurgenidze2");

                // Add an age element to student1
                SOAPElement age2 = student2.addChildElement("age2");
                age2.addTextNode("20");

//
//                Name students = soapEnvelope.createName("students");
//                SOAPElement soapElement = soapBody.addChildElement(students);
//
//                SOAPElement student1 = soapElement.addChildElement("student1");
//
//                SOAPElement soapElement1 = student1.addChildElement("first_name");
//                soapElement1.addTextNode("Ekaterine ");
//
//                SOAPElement soapElement2 = student1.addChildElement("last_name");
//                soapElement2.addTextNode("Gurgenidze");
//
//                SOAPElement soapElement3 = student1.addChildElement("age");
//                soapElement3.addTextNode("20");

                soapMessage.saveChanges();
                response.setContentType("text/xml;charset=UTF-8");
                soapMessage.writeTo(response.getOutputStream());
            }
            catch (SOAPException e){
                throw new RuntimeException(e);
            }

    }
}
