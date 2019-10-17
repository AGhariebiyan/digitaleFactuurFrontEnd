package views;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import controllers.AppController;
//import controllers.DashboardController;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DashboardView implements View {
	private Scene scene;
	private Border blackBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
	private Stage primaryStage;	
	
	public DashboardView() {
		this.scene = createView();
//		dashboardController = new DashboardController();
	}
	
	@Override
	public Scene createView() {
		Pane rootPane = new Pane();
		
		//creates center components of rootPane
		Pane totalTripsPane = new Pane();
		totalTripsPane.setBorder(blackBorder);
		totalTripsPane.setMinSize((450/1.5), (350/1.5));
		totalTripsPane.setTranslateX((450/1.5));
		totalTripsPane.setTranslateY((125/1.5));
		
		Pane totalProjectsPane = new Pane();
		totalProjectsPane.setBorder(blackBorder);
		totalProjectsPane.setMinSize((450/1.5), (350/1.5));
		totalProjectsPane.setTranslateX((1050/1.5));
		totalProjectsPane.setTranslateY((125/1.5));
		
		Pane addTripPane = new Pane();
		addTripPane.setBorder(blackBorder);
		addTripPane.setMinSize((1050/1.5), (300/1.5));
		addTripPane.setTranslateX((450/1.5));
		addTripPane.setTranslateY((625/1.5));
		
		rootPane.getChildren().addAll(createLeftBoardViewPane(), createHeaderPane(),totalTripsPane,totalProjectsPane,addTripPane);	
		
		Scene scene = new Scene(rootPane, (1920/1.5), (1080/1.5));
		
		return scene;
	}
	
	//creates top screen part with inner components
	private Pane createHeaderPane() {
		Pane headerPane = new Pane();
		
		headerPane.setMinSize((1920/1.5), (75/1.5));
		headerPane.setTranslateX(0);
		headerPane.setTranslateY(0);
		
		headerPane.setStyle("-fx-background-color: #FFFFFF");
		
		headerPane.getChildren().addAll(createLogoPane(), createUserHBox());	

		return headerPane;
	}

	
	private Node createUserHBox() {
		HBox userHBox = new HBox();
		userHBox.setPadding(new Insets(15, 12, 15, 12));
	    userHBox.setSpacing(10);
	    
	    userHBox.setMinSize((1920/1.5), (75/1.5));
	    userHBox.setTranslateX((1670/1.5));
	    userHBox.setTranslateY(0);
	    
		Label usernameLabel = new Label("username");
		usernameLabel.setTranslateY(10);
		
		Image image = new Image("file:src/resources/imgs/user_icon.png");
		ImageView imageView = new ImageView(image); 
		imageView.setTranslateY(5);
		
	    userHBox.getChildren().addAll(imageView, usernameLabel);
		
		return userHBox;
	}


	private Node createLogoPane() {
		Pane logoPane = new Pane();
		
		Image image = new Image("file:src/resources/imgs/logo.jpg");
		ImageView imageView = new ImageView(image); 
		
		logoPane.setMinSize((300/1.5), (75/1.5));
		logoPane.setTranslateX(0);
		logoPane.setTranslateY(0);
		
		logoPane.getChildren().addAll(imageView);

		
		return logoPane;
	}

	private Node createLeftBoardViewPane() {
		Pane dashBoardViewPane = new Pane();
		
		dashBoardViewPane.setMinSize((300/1.5), (1080/1.5));
		dashBoardViewPane.setTranslateX(0);
		dashBoardViewPane.setTranslateY(0);

		dashBoardViewPane.setStyle("-fx-background-color: #FFFFFF");
		dashBoardViewPane.getChildren().addAll(createButtonVBox(), createLogoutPane());	
		
		return dashBoardViewPane;
	}
	
	private Node createButtonVBox() {
		VBox menuVBox = new VBox();
		
		menuVBox.setPadding(new Insets(0, 0, 0, 0));
		menuVBox.setSpacing(0);
		menuVBox.setMinSize((170/1.5), (43/1.5));
		menuVBox.setTranslateX(0);
		menuVBox.setTranslateY((125/1.5));
		
		//create dashboard button
		Image dashboardImage = new Image("file:src/resources/imgs/dashboard.png");
        Button dashboardButton = new Button("           Dashboard");
        ImageView imageView = new ImageView(dashboardImage);
        imageView.setTranslateX(0);
        dashboardButton.setGraphic(imageView);
        dashboardButton.setStyle("-fx-background-color: #FFFFFF");
        
        dashboardButton.setMinSize((300/1.5), (50/1.5));
        dashboardButton.setMaxSize((300/1.5), (50/1.5));
        dashboardButton.setOnAction(e -> AppController.getInstance().loadView("views.DashboardView", "createView"));
		
		//create trip button 
		Image tripImage = new Image("file:src/resources/imgs/road.png");
        Button tripButton = new Button("                 Ritten");
        ImageView tripImageView = new ImageView(tripImage);
        tripImageView.setTranslateX(0);
        tripButton.setGraphic(tripImageView);
        tripButton.setStyle("-fx-background-color: #FFFFFF");
        
        tripButton.setMinSize((300/1.5), (50/1.5));
        tripButton.setMaxSize((300/1.5), (50/1.5));
        tripButton.setOnAction(e -> AppController.getInstance().loadView("views.TripOverviewView", "createView"));
       
		//create vehicle button 
        Image vehicleImage = new Image("file:src/resources/imgs/vehicle.png");
        Button vehicleButton = new Button("               Auto's");
        ImageView vehicleImageView = new ImageView(vehicleImage);
        tripImageView.setTranslateX(0);
        vehicleButton.setGraphic(vehicleImageView);
        
        vehicleButton.setMinSize((300/1.5), (50/1.5));
        vehicleButton.setMaxSize((300/1.5), (50/1.5));
        vehicleButton.setStyle("-fx-background-color: #FFFFFF");
        vehicleButton.setOnAction(e -> AppController.getInstance().loadView("views.DashboardView", "createView"));

		//create project button 
        Image projectImage = new Image("file:src/resources/imgs/projects.png");
        Button projectButton = new Button("            Projecten");
        ImageView projectImageView = new ImageView(projectImage);
        projectImageView.setTranslateX(0);
        projectButton.setGraphic(projectImageView);
        
        projectButton.setMinSize((300/1.5), (50/1.5));
        projectButton.setMaxSize((300/1.5), (50/1.5));
        
        projectButton.setStyle("-fx-background-color: #FFFFFF");
        projectButton.setOnAction(e -> AppController.getInstance().loadView("views.DashboardView", "createView"));

		menuVBox.getChildren().addAll(dashboardButton, tripButton, vehicleButton, projectButton);
		
		return menuVBox;
	}
	
	private Node createLogoutPane() {
		Pane logoutPane = new Pane();

		logoutPane.setMinSize((300/1.5), (135/1.5));
		//logoutPane.setBorder(blackBorder);
		logoutPane.setTranslateX(0);
		logoutPane.setTranslateY((945/1.5));
		Button logoutButton = new Button("            Logout");
		logoutButton.setPrefWidth((220/1.5));
		Image logoutImage = new Image("file:src/resources/imgs/logout.png");
		ImageView logoutImageView = new ImageView(logoutImage);
		logoutImageView.setTranslateX(25);
		logoutButton.setGraphic(logoutImageView);
		logoutPane.getChildren().addAll(logoutButton);	
		logoutButton.setTranslateY((45/1.5));
		logoutButton.setStyle("-fx-background-color: #FFFFFF");
		
		return logoutPane;
	}
	

	@Override
	public void updateView() {		
	}

	@Override
	public Scene getScene() {
		return this.scene;
	}
	
	@Override
	public Pane loadMenu(){
		return null;
	}
	
}
