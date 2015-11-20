package org.xine.fx.cdi.weld;


import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.xine.fx.cdi.spi.BeanProvider;
import org.xine.fx.cdi.spi.CDILoader;


/**
 * CDI loader implementation based on JBoss WELD.
 */
public class WeldLoader implements CDILoader, BeanProvider{

	/** The logger. */
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	/** The weld. */
	private Weld weld;

	/** The container. */
	private WeldContainer container;

	/* (non-Javadoc)
	 * @see org.xine.fx.cdi.spi.CDILoader#initialize()
	 */
	@Override
	public void initialize() throws Exception {
		logger.log(Level.INFO, "Initializing JBoss WELD...");

		weld = new Weld();
		container = weld.initialize();
	}

	/* (non-Javadoc)
	 * @see org.xine.fx.cdi.spi.CDILoader#shutdown()
	 */
	@Override
	public void shutdown() throws Exception {
		if (weld != null) {
			logger.log(Level.INFO, "Shutting down JBoss WELD...");
			weld.shutdown();
		}
	}

	/* (non-Javadoc)
	 * @see org.xine.fx.cdi.weld.BeanProvider#getBean(java.lang.Class)
	 */
	@Override
	public <T> T getBean(final Class<T> type) {
		return container.instance().select(type).get();
	}

}
