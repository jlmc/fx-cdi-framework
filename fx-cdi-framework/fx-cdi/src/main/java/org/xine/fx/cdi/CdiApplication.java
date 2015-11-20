package org.xine.fx.cdi;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.xine.fx.cdi.spi.CDILoader;

import javafx.application.Application;

public abstract class CdiApplication extends Application{


	/** CDI loader to be used during application initialisation. */
	private final CDILoader fxCdiLoader;


	public CdiApplication(){
		super();

		// Sets the JavaFX application instance to be used when injecting an instance of this class.
		JavaFXExtension.setJavaFxApplication(this);

		// Searches for a FxCdiLoader instance.
		final ServiceLoader<CDILoader> serviceLoader = ServiceLoader.load(CDILoader.class);
		final Iterator<CDILoader> loaderIterator = serviceLoader.iterator();
		if (!loaderIterator.hasNext()) {
			throw new IllegalStateException("No CDI Loader implementation for JavaFX could be found on your classpath.");
		}
		final CDILoader loader = loaderIterator.next();
		if (loaderIterator.hasNext()) {
			throw new IllegalStateException("More than one CDI Loader implementation for JavaFX could be found on your classpath.");
		}
		fxCdiLoader = loader;
	}

	@Override
	public void init() throws Exception{
		super.init();
		fxCdiLoader.initialize();
	}

	@Override
	public void stop() throws Exception{
		fxCdiLoader.shutdown();
		JavaFXExtension.setJavaFxApplication(null);
		super.stop();
	}

	@Override
	public String toString() {
		return String.format("[CDI-aware] %s", super.toString());
	}
}
