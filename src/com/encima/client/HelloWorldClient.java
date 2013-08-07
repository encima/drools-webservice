package com.encima.client;
 
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.encima.ws.Drools;
import com.encima.ws.HelloWorld;
 
public class HelloWorldClient{
 
	public static void main(String[] args) throws Exception {
 
		URL url = new URL("http://localhost:9999/ws/fire?wsdl");
 
        //1st argument service URI, refer to wsdl document above
		//2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://ws.encima.com/", "DroolsImplService");
 
        Service service = Service.create(url, qname);
 
        Drools hello = service.getPort(Drools.class);
 
        System.out.println(hello.fireRules());
 
    }
 
}