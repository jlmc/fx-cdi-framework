package org.xine.fx.cdi;

import javax.enterprise.inject.spi.CDI;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.util.Builder;

public class CdiFXMLLoader extends FXMLLoader{

	// Some marker strings for unspecified values.
	public static final String LOCATION_UNSPECIFIED = "$$$LOCATION_UNSPECIFIED$$$";
	public static final String RESOURCES_UNSPECIFIED = "$$$RESOURCES_UNSPECIFIED$$$";
	public static final String CHARSET_UNSPECIFIED = "$$$CHARSET_UNSPECIFIED$$$";


	/**
	 * A reference to all the package names that won't work as supposed to when trying
	 * to instantiate beans from within via CDI in conjunction with FXML builders.
	 * <p>This will avoid an exception message caused by a {@link com.sun.javafx.fxml.PropertyNotFoundException}
	 * <em>'Property "${propertyName}" does not exist or is read-only'</em>.</p>
	 * @see com.sun.javafx.fxml.PropertyNotFoundException
	 */
	@SuppressWarnings("restriction")
	private static final String[] CDI_BLACKLIST = new String[] {
			"javafx",
			"java.awt",
			"javax.swing"
	};

	/** A default (non-CDI aware) JavaFX builder factory. */
	private final JavaFXBuilderFactory defaultBuilderFactory;

	CdiFXMLLoader(){
		super();


		// Uses the currently loaded CDI implementation to look up controller classes
		// that have been specified via "fx:controller='...'" in our FXML files.
		this.setControllerFactory(clazz -> CDI.current().select(clazz));

		// Initialises the default JavaFX builder factory to be used for non CDI-aware beans.
		defaultBuilderFactory = new JavaFXBuilderFactory();

		this.setBuilderFactory(clazz -> {

			final Builder<?> defaultBuilder = defaultBuilderFactory.getBuilder(clazz);
			if(defaultBuilder != null) {
				return defaultBuilder;
			}

			// Make sure that we use a "null" builder for components that are 'blacklisted'.
			final String packageName = clazz.getPackage().getName();
			for (final String blacklisted : CDI_BLACKLIST) {
				if (packageName.startsWith(blacklisted)) {
					return null;
				}
			}

			return new CdiFXMLComponentBuilder(clazz);

		});
	}

	@Override
	public String toString() {
		return String.format("[CDI-aware] %s", super.toString());
	}
}
