package org.xine.fx.inject.core;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 * Use this annotation to configure {@link javafx.fxml.FXMLLoader} instances that
 * shall be injected via CDI {@link javax.inject.Inject @Inject} annotations.
 * <p>Example usage:</p>
 * <pre>
 * {@literal @}javax.inject.Inject
 * {@literal @}com.cathive.fx.inject.core.FXMLLoaderParams(location = "MyFile.fxml")
 * private FXMLLoader fxmlLoader;
 * </pre>
 *
 * @author Benjamin P. Jung
 * @since 1.0.0
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ METHOD, FIELD, PARAMETER, TYPE })
public @interface FXMLLoaderParams {

    public static final String LOCATION_UNSPECIFIED = "$$$LOCATION_UNSPECIFIED$$$";
    public static final String RESOURCES_UNSPECIFIED = "$$$RESOURCES_UNSPECIFIED$$$";
    public static final String CHARSET_UNSPECIFIED = "$$$CHARSET_UNSPECIFIED$$$";

    /**
     * Sets the location used to resolve relative path attribute values.
     * @return
     *         The location used to resolve relative path attribute values.
     * @see javafx.fxml.FXMLLoader#setLocation(java.net.URL)
     */
    @Nonbinding
    String location() default LOCATION_UNSPECIFIED;

    /**
     * Sets the resources used to resolve resource key attribute values.
     * @return
     *         The resources used to resolve resource key attribute values.
     * @see javafx.fxml.FXMLLoader#setResources(java.util.ResourceBundle)
     */
    @Nonbinding
    String resources() default RESOURCES_UNSPECIFIED;

    /**
     * Sets the charset used by the configured loader.
     * @return
     *          The charset used by the configured loader.
     * @see javafx.fxml.FXMLLoader#setCharset(java.nio.charset.Charset)
     */
    @Nonbinding
    String charset() default CHARSET_UNSPECIFIED;

}
