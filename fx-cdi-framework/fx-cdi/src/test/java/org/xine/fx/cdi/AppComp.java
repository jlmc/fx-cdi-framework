package org.xine.fx.cdi;

import javax.inject.Inject;

import org.xine.fx.inject.core.FXMLComponent;

import javafx.scene.layout.AnchorPane;


@FXMLComponent(location="AppComp.fxml")
public class AppComp extends AnchorPane{
	
	
	@Inject
	MyService service;
	
	
//	   @Inject
//	    public void init() {
//		   System.out.println("Compoments Init...");
//	   }
	   
	@Inject
	protected void init(){
		   System.out.println("post AppComp");
		   
		   if(service != null){
			   service.loggin();
		   }
	   }
}
