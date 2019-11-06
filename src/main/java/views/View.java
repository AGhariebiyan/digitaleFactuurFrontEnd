package main.java.views;

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

    /**
	 * @author Oussama Fahchouch
	 * @return Scene 
	 */
	Scene getScene();
}
