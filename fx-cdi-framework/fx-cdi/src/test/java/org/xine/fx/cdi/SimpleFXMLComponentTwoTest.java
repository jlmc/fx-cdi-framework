package org.xine.fx.cdi;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Test;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimpleFXMLComponentTwoTest {

	@Test
	public void testInit(){
		Application.launch(AppTest.class);
	}

	public static class AppTest extends CdiApplication{

		@Inject
		SimpleFXMLComponent component;


		@Override
		public void start(final Stage stage) throws Exception {
			  assertThat(component, notNullValue());
	            assertThat(component.isHello(), is(true));
	            component.setText("Test");
	            stage.setScene(new Scene(component));
	            stage.show();

	            final SimpleFXMLComponent root = (SimpleFXMLComponent) stage.getScene().getRoot();
	            assertThat(root.getText(), is("Test"));
	            stage.hide();
		}

	}
}
