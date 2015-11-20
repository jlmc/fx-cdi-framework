package org.xine.fx.cdi;

import javax.enterprise.inject.spi.CDI;

import org.xine.fx.inject.core.FXMLComponentBuilder;

/**
 * Component builder for CDI-managed components.
 */
public class CdiFXMLComponentBuilder<T> extends FXMLComponentBuilder<T> {

    /**
     * Default constructor.
     * @param componentClass
     *         Class to use when constructing new component instances.
     */
    CdiFXMLComponentBuilder(final Class<T> componentClass) {
        super(componentClass);
    }

    @Override
    protected T getInstance(final Class<T> clazz) {
        return CDI.current().select(clazz).get();
    }

    @Override
    public String toString() {
        return String.format("[CDI-aware] %s", super.toString());
    }

}
