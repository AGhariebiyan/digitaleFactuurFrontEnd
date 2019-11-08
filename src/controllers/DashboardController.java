package controllers;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * @author Fifi
 *
 */
public class DashboardController implements Controller {
	
	public void setHeaderPane(Pane headerPane) {
		Controller.appController.setHeaderPane(headerPane);
	}
	
	public void setMenuPane(Pane menuPane) {
		Controller.appController.setMenuPane(menuPane);
	}
}
