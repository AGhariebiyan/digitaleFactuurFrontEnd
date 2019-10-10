package application;
	
import controllers.AppController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import views.TripOverviewView;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			TripOverviewView tView = new TripOverviewView();
			AppController.getInstance().loadView(tView, primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
