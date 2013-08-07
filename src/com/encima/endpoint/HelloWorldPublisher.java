package com.encima.endpoint;
 
import java.util.Collection;

import javax.xml.ws.Endpoint;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.definition.KnowledgePackage;
import org.drools.runtime.StatefulKnowledgeSession;

import com.encima.Message;
import com.encima.utils.DBTools;
import com.encima.ws.DroolsImpl;
 
//Endpoint publisher
public class HelloWorldPublisher{
	
	static KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
    static Collection<KnowledgePackage> pkgs;
    static KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
    static StatefulKnowledgeSession ksession;
 
	public static void main(String[] args) {
	   DBTools.loadDBRules("drools", "root", "root", 2, kbuilder);
	   
       if ( kbuilder.hasErrors() ) {
           System.out.println( kbuilder.getErrors().toString() );
           throw new RuntimeException( "Unable to compile drl\"." );
       }
	   
      // get the compiled packages (which are serializable)
       pkgs = kbuilder.getKnowledgePackages();

       // add the packages to a knowledgebase (deploy the knowledge packages).
       kbase.addKnowledgePackages( pkgs );

       ksession = kbase.newStatefulKnowledgeSession();
       System.out.println("Rules loaded from DB");
       
       Message msg = new Message();
       msg.setType("Test");
       ksession.insert(msg);
       
	   System.out.println("Running SOAP Service");
	   DroolsImpl di = new DroolsImpl(ksession);
//	   Endpoint.publish("http://localhost:9999/ws/shutdown", di.shutDown());
	   Endpoint.publish("http://localhost:9999/ws/fire", di);
    }
 
}