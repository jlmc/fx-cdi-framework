package org.xine.fx.inject.core;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.inject.Stereotype;
import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

/**
 * This annotation is used to mark classes as FXML-based custom components.
 */
@Stereotype
@InterceptorBinding
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, FIELD, PARAMETER, TYPE })
public @interface FXMLComponent {

	/** The Constant LOCATION_UNSPECIFIED. */
	public static final String LOCATION_UNSPECIFIED = "$$$LOCATION_UNSPECIFIED$$$";

	/** The Constant RESOURCES_UNSPECIFIED. */
	public static final String RESOURCES_UNSPECIFIED = "$$$RESOURCES_UNSPECIFIED$$$";

	/** The Constant CHARSET_UNSPECIFIED. */
	public static final String CHARSET_UNSPECIFIED = "$$$CHARSET_UNSPECIFIED$$$";

	/**
	 * Sets the location used to resolve relative path attribute values.
	 * 
	 * @return The location used to resolve relative path attribute values.
	 * @see javafx.fxml.FXMLLoader#setLocation(java.net.URL)
	 */
	@Nonbinding
	String location() default LOCATION_UNSPECIFIED;

	/**
	 * Sets the resources used to resolve resource key attribute values.
	 * 
	 * @return The resources used to resolve resource key attribute values.
	 * @see javafx.fxml.FXMLLoader#setResources(java.util.ResourceBundle)
	 */
	@Nonbinding
	String resources() default RESOURCES_UNSPECIFIED;

	/**
	 * Sets the character set used by the configured loader.
	 * 
	 * @return The character set used by the configured loader.
	 * @see javafx.fxml.FXMLLoader#setCharset(java.nio.charset.Charset)
	 */
	@Nonbinding
	String charset() default CHARSET_UNSPECIFIED;

}
