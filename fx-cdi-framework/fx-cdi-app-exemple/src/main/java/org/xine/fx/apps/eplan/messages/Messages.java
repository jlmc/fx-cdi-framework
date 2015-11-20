package org.xine.fx.apps.eplan.messages;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * The Class Messages.
 */
@Named("messages")
@ApplicationScoped
public class Messages {
	
	/** The Constant APP_NAME. */
	public static final String APP_NAME = "app.name";

    /** The Constant APP_TITLE. */
    public static final String APP_TITLE = "app.title";

    /** The Constant APP_DESCRIPTION. */
    public static final String APP_DESCRIPTION = "app.description";

	
	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 */
	@Produces
	ResourceBundle getMessages(){
		 return ResourceBundle.getBundle(Messages.class.getName());
	}
	
	ResourceBundle getMessages(final Locale locale){
		return ResourceBundle.getBundle(Messages.class.getName(), locale);
	}

}
