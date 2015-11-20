package org.xine.fx.apps.eplan.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * The Class ApplicationModel.
 */
public class ApplicationModel {
	
	/** The user. */
	ObjectProperty<User> user = new SimpleObjectProperty<User>(new User());
	
	/**
	 * User property.
	 *
	 * @return the object property
	 */
	public final ObjectProperty<User> userProperty() {
		return user;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public final org.xine.fx.apps.eplan.model.User getUser() {
		return this.userProperty().get();
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public final void setUser(final org.xine.fx.apps.eplan.model.User user) {
		this.userProperty().set(user);
	}

}
