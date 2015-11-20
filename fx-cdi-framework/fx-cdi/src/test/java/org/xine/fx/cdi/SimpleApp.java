package org.xine.fx.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import javafx.scene.Scene;
import javafx.stage.Stage;


@Named("app")
@ApplicationScoped
public class SimpleApp extends CdiApplication{
	
	
	public static void main(final String... args){
		SimpleApp.launch(args);
	}
	
	
	@Inject
	private AppComp rootPane;
	
	//@Inject
	//private CompWithController compWithController;

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("ABC");

		final Scene scene = new Scene(rootPane);
		//final Scene scene = new Scene((Parent) compWithController.getRootNode());
		
		//scene.getStylesheets().add(getClass().getResource("ContactsApp.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
