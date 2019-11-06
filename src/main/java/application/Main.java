package main.java.application;
	
import main.java.controllers.AppController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AppController.getInstance().setPrimaryStage(primaryStage);
			AppController.getInstance().loadView("main.java.views.DashboardView", "createView");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
