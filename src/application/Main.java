package application;
	
import controllers.AppController;
import javafx.application.Application;
import javafx.stage.Stage;
import views.ProjectOverviewView;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			ProjectOverviewView pView = new ProjectOverviewView();
			AppController.getInstance().loadView(pView, primaryStage);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
