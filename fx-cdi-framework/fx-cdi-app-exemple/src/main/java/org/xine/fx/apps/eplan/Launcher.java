package org.xine.fx.apps.eplan;

import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.xine.fx.apps.eplan.controllers.ApplicationController;
import org.xine.fx.apps.eplan.controllers.NotificationController;
import org.xine.fx.apps.eplan.gui.FxDecorateScene;
import org.xine.fx.apps.eplan.icons.Icons;
import org.xine.fx.apps.eplan.messages.Messages;
import org.xine.fx.cdi.CdiApplication;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@Named("app")
@ApplicationScoped
public class Launcher extends CdiApplication{
	
	public static final int MIN_WIDTH = 800;
	public static final int MIN_HEIGHT = 600;

	@Inject
	private ResourceBundle messages;
	
	@Inject
	private ApplicationController applicationController;
	
	@Inject
	private NotificationController notificationController;
	
	//@Inject
	//private ApplicactionComp applicactionComp;
	


	@Override
	public void start(final Stage mainStage) throws Exception {
		loadFonts();

		mainStage.setTitle(messages.getString(Messages.APP_TITLE));
		mainStage.getIcons().add(Icons.getIcon(Icons.E_PLAN));
		
		mainStage.setMinHeight(MIN_HEIGHT);
		mainStage.setMinWidth(MIN_WIDTH);
		mainStage.setWidth(MIN_WIDTH);
		mainStage.setHeight(MIN_HEIGHT);

		
		

		//final Scene scene = new Scene(applicactionComp);
		//final Scene scene = new Scene((Parent) applicationController.getRootNode());

		FxDecorateScene fxDecorateScene = new FxDecorateScene(
				applicationController.getRootNode(), 
				mainStage);
		
		fxDecorateScene.setEdgeSize(5);
		mainStage.setScene(fxDecorateScene);
		this.applicationController.setDecorateScene(fxDecorateScene);
		
		mainStage.show();

	
		fxDecorateScene.getController().centerOnScreen();
		
		//define the notification controller
		
		Stage notificationStage = new Stage(StageStyle.TRANSPARENT);
		notificationStage.setScene( new Scene((Parent) notificationController.getRoot()));
		this.notificationController.setStage(notificationStage);
		this.notificationController.init();
		
		this.applicationController.setNotificationController(notificationController);
	}

	private static void loadFonts() {
		loadFont("/font/awesome/fontawesome-webfont.ttf");
		loadFont("/font/ubuntu/Ubuntu-L.ttf");
	}

	private static void loadFont(final String location) {
		Font.loadFont(Launcher.class.getResourceAsStream(location), 12);
	}

	public static void main(final String[] args ){
		Launcher.launch(args);
	}

}
