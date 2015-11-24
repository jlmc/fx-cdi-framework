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
	

	@Override
	public void start(final Stage primaryStage) throws Exception {
		primaryStage.setTitle("ABC");

		final Scene scene = new Scene(this.rootPane);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
