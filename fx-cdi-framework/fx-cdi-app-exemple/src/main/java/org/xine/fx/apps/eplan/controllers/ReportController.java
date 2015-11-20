package org.xine.fx.apps.eplan.controllers;
//DashboardView

import javax.inject.Named;

import org.xine.fx.inject.core.FXMLController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * The Class DashboardController.
 */
@Named
@FXMLController(
		location = "/org/xine/fx/apps/eplan/views/ReportView.fxml",
		resources = "org.xine.fx.apps.eplan.messages.Messages"
		)
public class ReportController extends  ContentController{
	
	@FXML
	private AnchorPane root;

	@Override
	public Node getRootNode() {
		return this.root;
	}
	
	
	public ReportController(){
		setName("Report");
	}

}
