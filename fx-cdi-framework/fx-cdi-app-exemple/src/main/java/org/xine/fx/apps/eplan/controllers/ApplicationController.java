package org.xine.fx.apps.eplan.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.xine.fx.apps.eplan.gui.FxDecorateScene;
import org.xine.fx.apps.eplan.gui.font.FontAwesomeDecorate;
import org.xine.fx.apps.eplan.icons.Icons;
import org.xine.fx.apps.eplan.model.ApplicationModel;
import org.xine.fx.inject.core.FXMLController;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Class ApplicationController.
 */
@Named
@ApplicationScoped
@FXMLController(
		location = "/org/xine/fx/apps/eplan/views/ApplicationView.fxml",
		resources = "org.xine.fx.apps.eplan.messages.Messages"
		)
public class ApplicationController {

	/** The application model. */
	@Inject
	private ApplicationModel applicationModel;

	//@Inject
	//private Messages messages;

	/** The fx decorate scene. */
	private FxDecorateScene fxDecorateScene;

	/** The notification controller. */
	private NotificationController notificationController;

	
	@Inject
	DashboardController dashboardController;
	
	@Inject
	ReportController reportController;
	
	
	/** The resources. */
	@FXML
	private ResourceBundle resources;

	/** The location. */
	@FXML
	private URL location;

	/** The navigation buttons. */
	@FXML
	private HBox navigationButtons;

	/** The eplan logo. */
	@FXML
	private ImageView eplanLogo;

	/** The root. */
	@FXML
	private AnchorPane root;

	/** The x2. */
	@FXML
	private Insets x2;

	/** The navigation background. */
	@FXML
	private Rectangle navigationBackground;

	/** The navigation h box. */
	@FXML
	private HBox navigationHBox;

	/** The avatar. */
	@FXML
	private ImageView avatar;

	/** The x3. */
	@FXML
	private Color x3;

	/** The notifications. */
	@FXML
	private Button notifications;

	/** The content. */
	@FXML
	private AnchorPane content;

	/** The username. */
	@FXML
	private Label username;

	/** The avatar default. */
	@FXML
	private Label avatarDefault;

	
	private final List<ContentController> controllers = new ArrayList<>();

	private ContentController activeController;

	/**
	 * Inits the. This is the {@code @FXML} and {@code @PostConstruct} event.
	 */
	@Inject
	protected void init() {
		/**/
		this.navigationBackground.widthProperty().bind(this.root.widthProperty());

		this.username.textProperty().bind(this.applicationModel.getUser().usernameProperty());
		this.avatar.imageProperty().bind(this.applicationModel.getUser().avatarProperty());


		FontAwesomeDecorate.stylise(this.avatarDefault);

		//
		//this.avatarDefault.setGraphic(Test.getLabel());
		this.avatarDefault.setGraphic(Icons.getIcon(Icons.USER, 35, 35));
		
		
		
		//notification button 
		// onMousePressed="#onNotificationButtonPressed" 
		//onMouseReleased="#onNotificationButtonReleased"
		this.notifications.setOnMousePressed(e -> onNotificationButtonPressed());
		this.notifications.setOnMouseReleased(e -> onNotificationButtonReleased());
		
		
		loadSubControllers();
	}

	private void loadSubControllers() {
		if(this.dashboardController != null ){
			loadSubController(dashboardController);
			loadSubController(reportController);
			
			
			
		}
		activateController(this.controllers.get(0));
	}
	
	
	private void loadSubController(ContentController contentController){
		controllers.add(contentController);
		
		contentController.setApplicationController(this);

        final Button btn = addControllerButton(contentController);
        contentController.setNavigationButton(btn);

        this.content.getChildren().add(contentController.getRootNode());
        contentController.setControllerConstrains();

        contentController.getRootNode().setVisible(false);
	}

	
	
	private Button addControllerButton(ContentController contentController) {
		final Button navButton = new Button(contentController.getName());

        navButton.getStyleClass().add("nav-button");
        navButton.getStyleClass().add("xine-button");
        navButton.setPrefHeight(49);

        navButton.setOnAction(e -> activateController(contentController));

        this.navigationButtons.getChildren().add(navButton);

        return navButton;
	}

	/**
     * Sets the active Controller in the content area.
     * @param contentController
     *            the new active controller.
     */
    private void activateController(final ContentController contentController) {
        activateController(contentController, true);
    }


	/**
	 * Gets the root node.
	 *
	 * @return the root node
	 */
	public Node getRootNode() {
		return this.root;
	}

	/**
	 * Sets the decorate scene.
	 *
	 * @param fxDecorateScene the new decorate scene
	 */
	public void setDecorateScene(FxDecorateScene fxDecorateScene) {
		this.fxDecorateScene = fxDecorateScene;
		
		fxDecorateScene.getController().addMoveNode(this.navigationBackground);
		fxDecorateScene.getController().addMoveNode(this.eplanLogo);
		fxDecorateScene.getController().addMoveNode(this.username);
		fxDecorateScene.getController().addMoveNode(this.avatar);
	}
	
	/**
	 * Gets the decorate scene.
	 *
	 * @return the decorate scene
	 */
	public FxDecorateScene getDecorateScene() {
		return this.fxDecorateScene;
	}

	/**
	 * On notification button pressed.
	 */
	public void onNotificationButtonPressed() {
		final Stage notif = getNotificationController().getStage();
		if(notif.isShowing()){
			if(!getNotificationController().isAnimating()){
				getNotificationController().hide();
			}
		}
	}

	/**
	 * On notification button released.
	 */
	public void onNotificationButtonReleased(){
		final Stage notif = getNotificationController().getStage();
		if(!notif.isShowing()){
			final Bounds b = this.notifications.localToScene(this.notifications.getLayoutBounds());

			double x = (b.getMinX() + b.getMaxX()) / 2d;
			double y = b.getMaxY();

			x += getDecorateScene().getStage().getX();
			y += getDecorateScene().getStage().getY();

			final Point2D arrow = getNotificationController().getArrowPosition();
			x -= arrow.getX();
			y -= arrow.getY();

			notif.setX(x);
			notif.setY(y);

			getNotificationController().show();
		}
	}

	/**
	 * Gets the notification controller.
	 *
	 * @return the notification controller
	 */
	public NotificationController getNotificationController() {
		return this.notificationController;
	}

	/**
	 * Sets the notification controller.
	 *
	 * @param notificationController
	 *            the new notification controller
	 */
	public void setNotificationController(final NotificationController notificationController) {
		this.notificationController = notificationController;
	}
	
	
	
	
	 /**
     * Sets the active Controller in the content area.
     * @param contentController
     *            the new active controller.
     * @param animated
     *            wether the change of controllers should be animated.
     */
    private void activateController(
    		final ContentController contentController,
            final boolean animated) {
    	
        if (this.activeController == contentController) {
            return;
        }
        
        final int from = this.controllers.indexOf(this.activeController);
        final int to = this.controllers.indexOf(contentController);
        
        final ContentController oldController = this.activeController;
        contentController.getRootNode().setVisible(true);
        
        if (this.activeController != null) {
            this.activeController.getRootNode().setVisible(false);
            this.activeController.onDeactivate();
            this.activeController.getNavigationButton().getStyleClass().remove("selected");
        }
        this.activeController = contentController;
        this.activeController.getNavigationButton().getStyleClass().add("selected");
        
        final int direction = from < to ? -1 : 1;
        if (animated && oldController != null) {
            animateController(contentController, oldController, direction);
        }
        
        this.activeController.onActivate();
    }
    
    /**
     * Animate controller.
     * @param contentController
     *            the content controller
     * @param oldController
     *            the old controller
     * @param direction
     *            the direction
     */
    @SuppressWarnings("boxing")
    private void animateController(
    		final ContentController contentController,
            final ContentController oldController, 
            final int direction) {
    	
        oldController.getRootNode().setVisible(true);
        
        if (this.activeController.getRootNode() instanceof Pane) {
            ((Pane) this.activeController.getRootNode()).setPrefSize(
            		this.content.getWidth(),
                    this.content.getHeight());
        }
        
        if (oldController.getRootNode() instanceof Pane) {
            ((Pane) oldController.getRootNode()).setPrefSize(
            		this.content.getWidth(),
                    this.content.getHeight());
        }
        
        AnchorPane.clearConstraints(this.activeController.getRootNode());
        AnchorPane.clearConstraints(oldController.getRootNode());
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        
        KeyValue kvNew = new KeyValue(
        		this.activeController.getRootNode().layoutXProperty(),
                this.content.getWidth() * -direction);
        
        KeyValue kvNewOpac = new KeyValue(
        		this.activeController.getRootNode().opacityProperty(),
                0.0);
        
        KeyFrame kf = new KeyFrame(Duration.ZERO, kvNew, kvNewOpac);
        timeline.getKeyFrames().add(kf);
        
        final KeyValue kvOld = new KeyValue(
        		oldController.getRootNode().layoutXProperty(),
                this.content.getWidth() * direction);
        
        kvNew = new KeyValue(this.activeController.getRootNode().layoutXProperty(), 0);
        kvNewOpac = new KeyValue(this.activeController.getRootNode().opacityProperty(), 1.0);
        final KeyValue kvOldOpac = new KeyValue(oldController.getRootNode().opacityProperty(), 0.0);
        kf = new KeyFrame(Duration.millis(200), kvOld, kvNew, kvNewOpac, kvOldOpac);
        timeline.getKeyFrames().add(kf);
        timeline.play();

        timeline.setOnFinished(e -> {
            oldController.setControllerConstrains();
            contentController.setControllerConstrains();
            oldController.getRootNode().setVisible(false);
        });
       

    }

}
