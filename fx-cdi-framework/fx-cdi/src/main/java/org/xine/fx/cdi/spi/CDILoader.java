package org.xine.fx.cdi.spi;

/**
 * This service provider interface can be used to plug in different CDI provider
 * implementations into the {@link com.cathive.fx.cdi.CdiApplication} class.
 */
public interface CDILoader {

    /**
     * Initialises the CDI provider instance.
     * <p>This method will be called upon initialisation of CDI-based JavaFX applications.
     * After this method has been executed successfully we assume that a working CDI provider
     * instance can be obtained via a call to {@link javax.enterprise.inject.spi.CDI#current()}.</p>
     * @throws Exception
     */
    void initialize() throws Exception;

    /**
     * Shuts down the CDI provider instance.
     * @throws Exception
     */
    void shutdown() throws Exception;

}
