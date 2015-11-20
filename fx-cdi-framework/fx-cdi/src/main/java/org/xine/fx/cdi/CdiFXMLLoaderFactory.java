package org.xine.fx.cdi;


import static org.xine.fx.cdi.CdiFXMLLoader.CHARSET_UNSPECIFIED;
import static org.xine.fx.cdi.CdiFXMLLoader.LOCATION_UNSPECIFIED;
import static org.xine.fx.cdi.CdiFXMLLoader.RESOURCES_UNSPECIFIED;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.ResourceBundle;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;

import org.xine.fx.inject.core.FXMLLoaderParams;

import javafx.fxml.FXMLLoader;

/**
 * This factory is responsible for the production of CDI-aware {@link javafx.fxml.FXMLLoader} instances.
 *
 * @author Benjamin P. Jung
 */
@ApplicationScoped
class CdiFXMLLoaderFactory {

    /**
     * Create a CDI-aware FXMLLoader.
     * If an annotation of type @FXMLLoaderParams can be found, use it's parameters
     * to configure the FXMLLoader instance that shall be used to perform the loading
     * of the FXML file.
     *
     * @param injectionPoint
     *     Injection point.
     * @return
     *     A new FXMLLoader instance.
     */
    @Produces
    @FXMLLoaderParams
    FXMLLoader createCdiFXMLLoader(final InjectionPoint injectionPoint) {
        final CdiFXMLLoader fxmlLoader = new CdiFXMLLoader();
        final Annotated annotated = injectionPoint.getAnnotated();
        final Class<?> declaringClass = injectionPoint.getMember().getDeclaringClass();
        if (annotated.isAnnotationPresent(FXMLLoaderParams.class)) {
            final FXMLLoaderParams annotation = annotated.getAnnotation(FXMLLoaderParams.class);
            initializeFXMLLoader(fxmlLoader, declaringClass, annotation.location(), annotation.resources(), annotation.charset());
        }
        return fxmlLoader;
    }

    /**
     * Initializes the given FXMLLoader instance using the provided parameters.
     *
     * @param fxmlLoader  never <code>null</code>
     * @param targetClass never <code>null</code>
     * @param location    never <code>null</code>
     * @param resources   never <code>null</code>
     * @param charset     never <code>null</code>
     */
    static void initializeFXMLLoader(final FXMLLoader fxmlLoader, final Class<?> targetClass, final String location, final String resources, final String charset) {

        checkAndSetLocation(fxmlLoader, targetClass, location);
        if (charset != null && !charset.equals(CHARSET_UNSPECIFIED)) {
            fxmlLoader.setCharset(Charset.forName(charset));
        }
        if (resources != null && !resources.equals(RESOURCES_UNSPECIFIED)) {
            fxmlLoader.setResources(ResourceBundle.getBundle(resources));
        }

    }

    /**
     * Checks the location that has been specified (if any) and uses the default
     * class loader to create an URL that points to a FXML file on the classpath.
     *
     * @param fxmlLoader  never <code>null</code>
     * @param targetClass never <code>null</code>
     * @param location    never <code>null</code> and should point to an existing FXML file, an Exception is thrown otherwise
     */
    private static void checkAndSetLocation(final FXMLLoader fxmlLoader, final Class<?> targetClass, final String location) {
        if (location != null && !location.equals(LOCATION_UNSPECIFIED)) {
            final URL locationUrl = targetClass.getResource(location);
            if (locationUrl == null) {
                throw new IllegalArgumentException(String.format("Couldn't find FXML file: \"%s\".", location));
            }
            fxmlLoader.setLocation(locationUrl);
        }
    }

}