package org.xine.fx.apps.eplan.utils;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Class ResourceUtils.
 */
public class ResourceUtils {

    /** The Constant RESOURCE_PATH. */
    public static final String RESOURCE_PATH = "org/xine/eplan/desktop/resources/";

    /**
     * Gets the resource as stream.
     * @param path
     *            the path
     * @return the resource as stream
     */
    public static InputStream getResourceAsStream(final String path) {
        InputStream stream = ResourceUtils.class.getResourceAsStream(RESOURCE_PATH + path);
        if (stream == null) {
            // Eclipse path
            final File resource = new File(".\\src\\main\\resources\\" + path);
            if (resource.exists()) {
                try {
                    stream = new BufferedInputStream(new FileInputStream(resource));
                } catch (final IOException ignore) {
                    // Nothing
                }
            }
        }
        return stream;
    }
}
