package application;
	
import controllers.AppController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import views.AddTripView;
import views.DashboardView;
import views.TripOverviewView;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AppController.getInstance().setPrimaryStage(primaryStage);
			AppController.getInstance().loadView("views.DashboardView", "createView");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}