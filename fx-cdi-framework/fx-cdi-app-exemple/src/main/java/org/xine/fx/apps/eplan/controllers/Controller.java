package org.xine.fx.apps.eplan.controllers;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * The Interface Controller.
 */
public interface Controller {

	/**
	 * Gets the root node.
	 *
	 * @return the root node
	 */
	public abstract Node getRootNode();

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name);

	/**
	 * On deactivate.
	 */
	public default void onDeactivate(){}

	/**
	 * On activate.
	 */
	public default void onActivate(){}

	/**
	 * On quit.
	 */
	public default void onQuit(){}

	/**
	 * Sets the controller constrains.
	 */
	public default void setControllerConstrains() {
		AnchorPane.setTopAnchor(getRootNode(), 0d);
		AnchorPane.setLeftAnchor(getRootNode(), 0d);
		AnchorPane.setRightAnchor(getRootNode(), 0d);
		AnchorPane.setBottomAnchor(getRootNode(), 0d);
	}
}
