package org.xine.fx.apps.eplan.messages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;
import java.util.ResourceBundle;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Before;
import org.junit.Test;

public class MessagesTest {

	@SuppressWarnings("unused")
	private Weld weld;

	private WeldContainer container;

	private Messages messages;

	private ResourceBundle resourceBundle;

	@Before
	public void init(){
		//weld = new Weld();
		//container = weld.initialize();
		this.messages = new Messages();
		this.resourceBundle = this.messages.getMessages();
	}

	//@After
	public void after(){
		//weld.shutdown();
	}

	@SuppressWarnings("unused")
	private <T> T getBean(final Class<T> type) {
		return this.container.instance().select(type).get();
	}

	@Test
	public void testGetMessages() {
		final Messages message = new Messages();
		assertNotNull(message);
	}

	@Test
	public void testGetResourceBundle(){
		final ResourceBundle resourceBundle = this.messages.getMessages();
		assertNotNull(resourceBundle);
	}

	@Test
	public void testGetResourceBundleValue(){
		final String value = this.resourceBundle.getString(Messages.APP_NAME);
		assertNotNull(value);
		assertEquals("E-Plan", value);
	}

	@Test
	public void testGetResourceBundleValuePT(){
		final Locale vmLocale = new Locale("pt", "PT");
		final ResourceBundle resouces = new Messages().getMessages(vmLocale);
		final String value = resouces.getString(Messages.APP_NAME);
		assertNotNull(value);
		assertEquals("Nós Planeamos", value);
	}


}
