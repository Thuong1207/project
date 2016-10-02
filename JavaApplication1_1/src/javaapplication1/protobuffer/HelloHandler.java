/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1.protobuffer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import thuongdc.com.example.tutoria.AddressBookProtos;


/**
 *
 * @author thuongdc
 */
public class HelloHandler extends AbstractHandler{

    final String greeting;
    final String body;
    //proto buffer

    public String protobuffer() {
        AddressBookProtos.Person.Builder person1 = AddressBookProtos.Person.newBuilder();
              person1.setId(12).setEmail("dasdasd").setName("fff").addPhone(AddressBookProtos.Person.PhoneNumber.newBuilder().setNumber("555-4321")
                      .setType(AddressBookProtos.Person.PhoneType.HOME)).build();
              person1.setEmail("ooo").build();
              person1.setId(1).build();
              person1.addPhone(AddressBookProtos.Person.PhoneNumber.newBuilder().setNumber("55667768686-4321")
                      .setType(AddressBookProtos.Person.PhoneType.HOME)).build();
              System.err.println(person1.getId());
              System.err.println(person1.getPhone(1));         
    return person1.getName();
}
    
    
    public HelloHandler()
    {                        
        this(proto.protobuff_jetty());
                     
    }
    

    public HelloHandler( String greeting )
    {
        this(greeting, null);
    }

    public HelloHandler( String greeting, String body )
    {
        this.greeting = greeting;
        this.body = proto.protobuff_jetty();
    }

    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException,
                                                      ServletException
    {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();

        out.println("<h1>" + greeting + "</h1>");
        if (body != null)
        {
            out.println("<p style=\"color:red;margin-left:60px;text-align: center;\">"+body);
        }

        baseRequest.setHandled(true);
    }
    
}
