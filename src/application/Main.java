package application;
	
import controllers.AppController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import views.AddTripView;
import views.DashboardView;
import views.LoginView;
import views.TripOverviewView;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
//			TripOverviewView tView = new TripOverviewView();
			AppController appController = AppController.getInstance();
			appController.setPrimaryStage(primaryStage);
			appController.loadView("views.LoginView", "createView");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
