package views;
import controllers.AppController;
import controllers.Controller;
import controllers.DashboardController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * @author fifi
 */
public class DashboardView implements View {
	

	private DashboardController dashboardController;
	
	/**
	 * @author fifi
	 * @return Scene
	 */
	@Override
	public Scene createView() {
		this.dashboardController = new DashboardController();
		
		Pane rootPane = new Pane();
		Image image = new Image(this.getClass().getResource("/imgs/homepageBackground.png").toExternalForm());
		ImageView imageView = new ImageView(image);
		
		imageView.setTranslateY(25);
		rootPane.getChildren().add(imageView);
		
		Pane totalTripsPane = new Pane();
		totalTripsPane.setStyle("-fx-background-color: #3A4F62");
		
		
		
		totalTripsPane.setMinSize((450/1.5), (350/1.5));
		totalTripsPane.setTranslateX((550/1.5));
		totalTripsPane.setTranslateY((275/1.5));
		Label totalTripsLabel = new Label("Totale ritten");
		totalTripsPane.getChildren().addAll(totalTripsLabel);	
		totalTripsLabel.setTextFill(Color.WHITE);
		totalTripsLabel.setTranslateX(55);
		totalTripsLabel.setTranslateY(25);

		totalTripsLabel.setFont(new Font(30));
		
		Label totalTripsLabelNumber = new Label(this.dashboardController.getStringTotalTripsFromBackEnd());
		totalTripsPane.getChildren().addAll(totalTripsLabelNumber);	
		totalTripsLabelNumber.setTextFill(Color.WHITE);
		totalTripsLabelNumber.setTranslateX(125);
		totalTripsLabelNumber.setTranslateY(75);
		
		totalTripsLabelNumber.setFont(new Font(75));
		
		
		Pane totalProjectsPane = new Pane();
		totalProjectsPane.setStyle("-fx-background-color: #3A4F62");
		totalProjectsPane.setMinSize((450/1.5), (350/1.5));
		totalProjectsPane.setTranslateX((1175/1.5));
		totalProjectsPane.setTranslateY((275/1.5));
		Label totalProjectsLabel = new Label("Totale projecten");
		totalProjectsPane.getChildren().addAll(totalProjectsLabel);	
		totalProjectsLabel.setTextFill(Color.WHITE);
		totalProjectsLabel.setTranslateX(35);
		totalProjectsLabel.setTranslateY(25);

		Label totalProjectsLabelNumber = new Label(this.dashboardController.getStringTotalProjectsFromBackEnd());
		totalProjectsPane.getChildren().addAll(totalProjectsLabelNumber);	
		totalProjectsLabelNumber.setTextFill(Color.WHITE);
		totalProjectsLabelNumber.setTranslateX(125);
		totalProjectsLabelNumber.setTranslateY(75);

		totalProjectsLabelNumber.setFont(new Font(75));
		totalProjectsLabel.setFont(new Font(30));
		
		Pane addTripPane = new Pane();
		addTripPane.setStyle("-fx-background-color: #3A4F62");
		
		addTripPane.setMinSize((1075/1.5), (300/1.5));
		addTripPane.setTranslateX((550/1.5));
		addTripPane.setTranslateY((700/1.5));
		Image addButtonImage = new Image(this.getClass().getResource("/imgs/addButton.png").toExternalForm());
        ImageView addButtonImageView = new ImageView(addButtonImage);

		Button addTripButton = new Button("Ritten Toevoegen", addButtonImageView);
		addButtonImageView.setTranslateX(300);
		addTripButton.setTextFill(Color.WHITE);
		addTripButton.setStyle("-fx-background-color: #3A4F62");
		addTripButton.setMinSize((50),50);
		addTripButton.setTranslateX(125);
		addTripButton.setTranslateY(50);
		addTripButton.setFont(new Font(20));
		addTripButton.setOnAction(e -> AppController.getInstance().loadView("views.TripOverviewView", "createView"));

		addTripPane.getChildren().addAll(addTripButton);	
		
		rootPane.getChildren().addAll(createLeftBoardViewPane(), createHeaderPane(),totalTripsPane,totalProjectsPane,addTripPane);	
		
		
		this.dashboardController.setHeaderPane(createHeaderPane());
		this.dashboardController.setMenuPane(createLeftBoardViewPane());
		
		Scene scene = new Scene(rootPane, (1920/1.5), (1080/1.5));
		//scene = scene;
		return scene;
	}
	
	//creates top screen part with inner components
	/**
	 * @author fifi
	 * @return headerPane
	 */
	private Pane createHeaderPane() {
		Pane headerPane = new Pane();
		
		headerPane.setMinSize((1920/1.5), (75/1.5));
		headerPane.setTranslateX(0);
		headerPane.setTranslateY(0);
		
		headerPane.setStyle("-fx-background-color: #FFFFFF");
		
		headerPane.getChildren().addAll(createLogoPane(), createUserHBox());	

		return headerPane;
	}

	/**
	 * @author fifi
	 * @return userHBox
	 */
	private HBox createUserHBox() {
		HBox userHBox = new HBox();
		userHBox.setPadding(new Insets(15, 12, 15, 12));
	    userHBox.setSpacing(10);
	    
	    userHBox.setMinSize((1920/1.5), (75/1.5));
	    userHBox.setTranslateX((1670/1.5));
	    userHBox.setTranslateY(0);
	    
		Label usernameLabel = new Label(Controller.appController.getCurrentUser().getUsername());
		usernameLabel.setTranslateY(10);
		
		Image image = new Image(this.getClass().getResource("/imgs/user_icon.png").toExternalForm());
		ImageView imageView = new ImageView(image); 
		imageView.setTranslateY(5);
		
	    userHBox.getChildren().addAll(imageView, usernameLabel);
		
		return userHBox;
	}

	/**
	 * @author fifi
	 * @return logoPane
	 */
	private Pane createLogoPane() {
		Pane logoPane = new Pane();
		
		Image image = new Image(this.getClass().getResource("/imgs/logoDF.jpg").toExternalForm());
		ImageView imageView = new ImageView(image); 
		
		logoPane.setMinSize((300/1.5), (75/1.5));
		logoPane.setTranslateX(0);
		logoPane.setTranslateY(0);

		logoPane.getChildren().addAll(imageView);

		
		return logoPane;
	}
	
	/**
	 * @author fifi
	 * @return dashBoardViewPane
	 */
	private Pane createLeftBoardViewPane() {
		Pane dashBoardViewPane = new Pane();
		
		dashBoardViewPane.setMinSize((300/1.5), (1080/1.5));
		dashBoardViewPane.setTranslateX(0);
		dashBoardViewPane.setTranslateY(0);

		dashBoardViewPane.setStyle("-fx-background-color: #FFFFFF");
		dashBoardViewPane.getChildren().addAll(createButtonVBox(), createLogoutPane());	
		
		return dashBoardViewPane;
	}
	
	/**
	 * @author fifi
	 * @return buttonVBox
	 */
	private VBox createButtonVBox() {
		VBox menuVBox = new VBox();
		
		menuVBox.setPadding(new Insets(0, 0, 0, 0));
		menuVBox.setSpacing(0);
		menuVBox.setMinSize((170/1.5), (43/1.5));
		menuVBox.setTranslateX(0);
		menuVBox.setTranslateY((125/1.5));
		
		//create dashboard button
		Image dashboardImage = new Image(this.getClass().getResource("/imgs/dashboard.png").toExternalForm());
        Button dashboardButton = new Button("       Dashboard");
        ImageView imageView = new ImageView(dashboardImage);
        imageView.setTranslateX(15);
        dashboardButton.setGraphic(imageView);
        dashboardButton.setStyle("-fx-background-color: #FFFFFF");
        
        dashboardButton.setMinSize((300/1.5), (50/1.5));
        dashboardButton.setMaxSize((300/1.5), (50/1.5));
        dashboardButton.setOnAction(e -> AppController.getInstance().loadView("views.DashboardView", "createView"));
		
		//create trip button 
		Image tripImage = new Image(this.getClass().getResource("/imgs/road.png").toExternalForm());
        Button tripButton = new Button("Ritten");
        ImageView tripImageView = new ImageView(tripImage);
        tripImageView.setTranslateX(-15);
        tripButton.setGraphic(tripImageView);
        tripButton.setStyle("-fx-background-color: #FFFFFF");
        
        tripButton.setMinSize((300/1.5), (50/1.5));
        tripButton.setMaxSize((300/1.5), (50/1.5));
        tripButton.setOnAction(e -> AppController.getInstance().loadView("views.TripOverviewView", "createView"));
       
		//create vehicle button 
        Image vehicleImage = new Image(this.getClass().getResource("/imgs/vehicle.png").toExternalForm());
        Button vehicleButton = new Button("       Voertuigen");
        ImageView vehicleImageView = new ImageView(vehicleImage);
        vehicleImageView.setTranslateX(15);
        vehicleButton.setGraphic(vehicleImageView);
        
        vehicleButton.setMinSize((300/1.5), (50/1.5));
        vehicleButton.setMaxSize((300/1.5), (50/1.5));
        vehicleButton.setStyle("-fx-background-color: #FFFFFF;");
        vehicleButton.setOnAction(e -> AppController.getInstance().loadView("views.VehicleOverviewView", "createView"));

		//create project button 
        Image projectImage = new Image(this.getClass().getResource("/imgs/projects.png").toExternalForm());
        Button projectButton = new Button("      Projecten");
        ImageView projectImageView = new ImageView(projectImage);
        projectImageView.setTranslateX(10);
        projectButton.setGraphic(projectImageView);
        
        projectButton.setMinSize((300/1.5), (50/1.5));
        projectButton.setMaxSize((300/1.5), (50/1.5));
        
        projectButton.setStyle("-fx-background-color: #FFFFFF;");
        projectButton.setOnAction(e -> AppController.getInstance().loadView("views.ProjectOverviewView", "createView"));
        


		menuVBox.getChildren().addAll(dashboardButton, tripButton, vehicleButton, projectButton);
		
		return menuVBox;
	}
	
	/**
	 * @author fifi
	 * @return logoutPane
	 */
	private Pane createLogoutPane() {
		Pane logoutPane = new Pane();

		logoutPane.setMinSize((300/1.5), (70/1.5));
		logoutPane.setTranslateX(0);
		logoutPane.setTranslateY((1010/1.5));
		logoutPane.setStyle("-fx-background-color: #DFE1E0");
		
		Button logoutButton = new Button("   Uitloggen");
		logoutButton.setPrefWidth((220/1.5));
		
		Image logoutImage = new Image(this.getClass().getResource("/imgs/logout.png").toExternalForm());
		ImageView logoutImageView = new ImageView(logoutImage);
		
		logoutButton.setGraphic(logoutImageView);
		logoutPane.getChildren().addAll(logoutButton);	
		logoutButton.setTranslateX((30/1.5));
		logoutButton.setTranslateY((15/1.5));
		logoutButton.setStyle("-fx-background-color: #DFE1E0");
		logoutButton.setOnAction(e -> AppController.getInstance().loadView("views.LoginView", "createView"));
		
		return logoutPane;
	}
	

}