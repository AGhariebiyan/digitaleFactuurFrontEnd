package views;

import controllers.TripController;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import models.TripModel;

/**
 * @author Oussama Fahchouch
 */
public class TripOverviewView implements View {
	private TripModel tripModel;
	private TripController tripController;
	private Scene scene;
	
	/**
	 * @author Oussama Fahchouch
	 */
	public TripOverviewView() {
		this.setScene(this.createView());
	}

	/**
	 * @author Oussama Fahchouch
	 */
	@Override
	public Scene createView(){
		Pane root = new Pane();
		Scene scene = new Scene(root,400,400);
//		scene.getStylesheets().add(getClass().getResource("../resources/application.css").toExternalForm());
		return scene;
	};
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Override
	public void updateView(){}
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Override
	public void setScene(Scene sceneToSet) {	
		this.scene = sceneToSet;
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Override
	public Scene getScene() {
		return this.scene;
	};
}
