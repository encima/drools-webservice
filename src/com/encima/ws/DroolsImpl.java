package com.encima.ws;

import javax.jws.WebService;

import org.drools.runtime.StatefulKnowledgeSession;

@WebService(endpointInterface = "com.encima.ws.Drools")
public class DroolsImpl {
	
	StatefulKnowledgeSession ksession;
	
	public DroolsImpl(StatefulKnowledgeSession ksession) {
		this.ksession = ksession;
	}
	
	public String fireRules() {
		System.out.println("Firing rules");
		ksession.fireAllRules();
		return "fired";
	}
	
	public String shutDown() {
		try { 
			System.out.println("Killing services");
			return "closed";
		}catch(Exception e){
			
		}finally{
			System.exit(0);
		}
		return "closed1";
	}

}
