package org.xine.fx.apps.eplan.controllers;

import javax.inject.Named;
import javax.management.Notification;

import org.xine.fx.inject.core.FXMLController;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.WritableValue;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * The Class NotificationController.
 */
@Named
@FXMLController(
		location = "/org/xine/fx/apps/eplan/views/NotificationView.fxml",
		resources = "org.xine.fx.apps.eplan.messages.Messages"
		)
public class NotificationController {

	/** The stage. */
	private Stage stage;
	
	/** The root. */
	@FXML
	private AnchorPane root;

	/** The arrow. */
	@FXML
	private Rectangle arrow;

	/** The title bar. */
	@FXML
	private Rectangle titleBar;

	/** The notification view. */
	@FXML
	private ListView<Notification> notificationList;

	/** The content. */
	@FXML
	private AnchorPane content;

	/** The animating. */
	private   boolean animating = false;

	/** The stage height. */
	private WritableValue<Double> stageHeight;

	/**
	 * Inits the.
	 */
	public void init(){
		stage.focusedProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			if(!newValue){
				Platform.runLater(() -> {
					if(!animating) {
						hide();
					}
				});
			}

		});

		getStage().getScene().setFill(null);

		stageHeight = new WritableValue<Double>() {
			@Override
			public void setValue(final Double value) {
				getStage().setHeight(value);
			}
			@Override
			public Double getValue() {
				return getStage().getHeight();
			}
		};

		arrow.setPickOnBounds(true);
	}

	/**
	 * Gets the stage.
	 *
	 * @return the stage
	 */
	public Stage getStage(){
		return stage;
	}

	/**
	 * Sets the stage.
	 *
	 * @param stage
	 *            the new stage
	 */
	public void setStage(final Stage stage){
		this.stage = stage;
	}

	/**
	 * Gets the arrow position.
	 *
	 * @return the arrow position
	 */
	public Point2D getArrowPosition() {
		//final Bounds b = this.arrow.localToScene(this.arrow.getLayoutBounds());
		final Bounds b = arrow.localToParent(arrow.getLayoutBounds());

		final double y = -1 * arrow.getHeight();
		final double x = (b.getMinX() + b.getMaxX() + arrow.getWidth()) / 2d;
		//final double y = b.getMinY();
		//final double x = (b.getMinX() + b.getMaxX()) / 2d;
		return new Point2D(x, y);
	}

	/**
	 * Show.
	 */
	public void show() {
		if (stage.isShowing() || animating) {
			stage.show();
			return;
		}
		animating = true;
		final double startHeight = titleBar.localToScene(titleBar.layoutBoundsProperty().get()).getMaxY();
		final double finalHeight = 350;

		final Timeline timeline = new Timeline();
		KeyValue titleOpacity = new KeyValue(stage.opacityProperty(), 0.0);
		KeyValue height = new KeyValue(stageHeight, startHeight);
		final KeyFrame zero = new KeyFrame(Duration.ZERO, titleOpacity, height);
		titleOpacity = new KeyValue(stage.opacityProperty(), 1.0);
		height = new KeyValue(stageHeight, startHeight);
		final KeyFrame fadeIn = new KeyFrame(Duration.millis(200), titleOpacity, height);
		height = new KeyValue(stageHeight, finalHeight);
		final KeyFrame slide = new KeyFrame(Duration.millis(400), height);
		timeline.getKeyFrames().addAll(zero, fadeIn, slide);
		timeline.play();
		stage.show();
		timeline.setOnFinished(event -> setAnimating(false));
	}

	/**
	 * Hide.
	 */
	public void hide() {
		if (!stage.isShowing() || animating) {
			stage.hide();
			return;
		}
		animating = true;

		stage.hide();
		stage.show();

		final double startHeight = titleBar.localToScene(titleBar.layoutBoundsProperty().get()).getMaxY();
		final double finalHeight = stage.getHeight();

		final Timeline timeline = new Timeline();
		final KeyValue height = new KeyValue(stageHeight, startHeight);
		final KeyFrame slide = new KeyFrame(Duration.millis(400), height);
		final KeyValue titleOpacity = new KeyValue(stage.opacityProperty(), 0.0);
		final KeyFrame fadeOut = new KeyFrame(Duration.millis(200), titleOpacity);
		timeline.getKeyFrames().addAll(slide, fadeOut);
		timeline.play();
		timeline.setOnFinished(event -> {
			NotificationController.this.stage.hide();
			NotificationController.this.stage.setHeight(finalHeight);
			setAnimating(false);
		}
				);
	}

	/**
	 * Sets the animating.
	 *
	 * @param animating
	 *            the new animating
	 */
	private void setAnimating(final boolean animating) {
		this.animating = animating;
	}

	/**
	 * Checks if is animating.
	 *
	 * @return true, if is animating
	 */
	public boolean isAnimating() {
		return animating;
	}
	
	/**
	 * Gets the root.
	 *
	 * @return the root
	 */
	public Node getRoot(){
		return this.root;
	}
}
