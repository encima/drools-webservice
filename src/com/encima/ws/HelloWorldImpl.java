package com.encima.ws;
 
import javax.jws.WebService;
 
//Service Implementation
@WebService(endpointInterface = "com.encima.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld{
 
	@Override
	public String getHelloWorldAsString(String name) {
		System.out.println("Request made.");
		System.out.println(name);
		return "test";
	}
 
}