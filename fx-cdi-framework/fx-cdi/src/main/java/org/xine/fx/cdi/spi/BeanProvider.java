package org.xine.fx.cdi.spi;

/**
 * The Interface BeanProvider.
 */
public interface BeanProvider {

	/**
	 * Gets the bean.
	 *
	 * @param <T> the generic type
	 * @param type the type
	 * @return the bean
	 */
	<T> T getBean(Class<T> type);

}