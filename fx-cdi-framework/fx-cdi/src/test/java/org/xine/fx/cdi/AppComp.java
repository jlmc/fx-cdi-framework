package org.xine.fx.cdi;

import javax.inject.Inject;

import org.xine.fx.inject.core.FXMLComponent;

import javafx.scene.layout.AnchorPane;


@FXMLComponent(location="AppComp.fxml")
public class AppComp extends AnchorPane{
	
	@Inject
	MyService service;
	   
	@Inject
	protected void init(){
		   System.out.println("post AppComp");
		   
		   if(this.service != null){
			   this.service.loggin();
		   }
	   }
}
