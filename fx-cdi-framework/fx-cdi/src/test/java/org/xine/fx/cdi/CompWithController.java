package org.xine.fx.cdi;

import javax.inject.Inject;

import org.xine.fx.inject.core.FXMLController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

@FXMLController(location="/CompWithController.fxml")
public class CompWithController {

	@Inject
	private MyService myService;

    @FXML
    private AnchorPane root;

    @FXML
    private Button myButton;

    @FXML
    private CheckBox myCheckbox;

    public  Node getRootNode(){
    	return this.root;
    }

	@Inject
    public void init(){
    	System.out.println("post constructor event");
    }

}
