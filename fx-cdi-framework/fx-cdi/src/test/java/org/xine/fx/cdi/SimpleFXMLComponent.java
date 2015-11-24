package org.xine.fx.cdi;

import org.xine.fx.inject.core.FXMLComponent;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;

@FXMLComponent(location = "simpleFXMLComponent.fxml")
public class SimpleFXMLComponent extends Button {
	   private final BooleanProperty hello = new SimpleBooleanProperty(this, "hello");

	    public boolean isHello() {
	        return this.hello.get();
	    }

	    public void setHello(final boolean hello) {
	        this.hello.set(hello);
	    }

	    public BooleanProperty helloProperty() {
	        return this.hello;
	    }


}
