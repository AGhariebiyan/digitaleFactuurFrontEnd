package views;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * @author Oussama Fahchouch
 */
public interface View {	
	/**
	 * @author Oussama Fahchouch
	 * @return
	 */
	Scene createView();
	
	/**
	 * @author Oussama Fahchouch
	 */
	public void updateView();

    void setScene(Scene sceneToSet);

    /**
	 * @author Oussama Fahchouch
	 * @return Scene 
	 */
	Scene getScene();
}
