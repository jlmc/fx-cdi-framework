package org.xine.fx.apps.eplan.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Button;

public abstract class ContentController implements Controller{

	private final StringProperty name = new SimpleStringProperty("<name>");

	private ApplicationController applicationController;

	private Button navigationButton;

	public String getName() {
		return this.name.get();
	}

	@Override
	public void setName(final String name) {
		this.name.set(name);
	}

	public StringProperty nameProperty() {
		return this.name;
	}

	@Override
	public abstract Node getRootNode();

	public final void setApplicationController(final ApplicationController applicationController) {
		if (this.applicationController != null) {
			throw new IllegalStateException("application controller was already set");
		}
		this.applicationController = applicationController;
	}

	/**
	 * Gets the application controller.
	 * @return the application controller
	 */
	protected ApplicationController getApplicationController() {
		return this.applicationController;
	}

	public final void setNavigationButton(final Button activationButton) {
		if (this.navigationButton != null) {
			throw new IllegalStateException("navigation button was already set");
		}
		this.navigationButton = activationButton;
	}

	public Button getNavigationButton() {
		return this.navigationButton;
	}

}
