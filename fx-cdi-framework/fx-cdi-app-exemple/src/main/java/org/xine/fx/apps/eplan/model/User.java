package org.xine.fx.apps.eplan.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 * The Class User.
 */
public class User {

    /** The username. */
    private final SimpleStringProperty username = new SimpleStringProperty(null);
    
    /** The logged in. */
    private final SimpleBooleanProperty loggedIn = new SimpleBooleanProperty(false);
    
    /** The avatar. */
    private final SimpleObjectProperty<Image> avatar = new SimpleObjectProperty<Image>(null);
   
    
    /**
     * Username property.
     *
     * @return the simple string property
     */
    public final SimpleStringProperty usernameProperty() {
        return username;
    }
    
    /**
     * Gets the username.
     *
     * @return the username
     */
    public final java.lang.String getUsername() {
        return usernameProperty().get();
    }
    
    /**
     * Sets the username.
     *
     * @param username
     *            the new username
     */
    public final void setUsername(final java.lang.String username) {
        usernameProperty().set(username);
    }
    
    /**
     * Logged in property.
     *
     * @return the simple boolean property
     */
    public final SimpleBooleanProperty loggedInProperty() {
        return loggedIn;
    }
    
    /**
     * Checks if is logged in.
     *
     * @return true, if is logged in
     */
    public final boolean isLoggedIn() {
        return loggedInProperty().get();
    }
    
    /**
     * Sets the logged in.
     *
     * @param loggedIn
     *            the new logged in
     */
    public final void setLoggedIn(final boolean loggedIn) {
        loggedInProperty().set(loggedIn);
    }
    
    /**
     * Avatar property.
     *
     * @return the simple object property
     */
    public final SimpleObjectProperty<Image> avatarProperty() {
        return avatar;
    }
    
    /**
     * Gets the avatar.
     *
     * @return the avatar
     */
    public final javafx.scene.image.Image getAvatar() {
        return avatarProperty().get();
    }
    
    /**
     * Sets the avatar.
     *
     * @param avatar
     *            the new avatar
     */
    public final void setAvatar(final javafx.scene.image.Image avatar) {
        avatarProperty().set(avatar);
    }
}
