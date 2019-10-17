package views;

import controllers.AppController;
import controllers.TripController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import models.TripModel;

/**
 * @author Oussama Fahchouch
 */
public class TripOverviewView implements View {
	private TripController tripController;
	private Scene scene;
		
	/**
	 * @author Oussama Fahchouch
	 */
	public TripOverviewView() {
		this.tripController = new TripController();
		this.scene = createView();
	}

	/**
	 * @author Oussama Fahchouch
	 */
	@Override
	public Scene createView(){
		Pane rootPane = new Pane();
		
		rootPane.getChildren().addAll(loadMenu(), createTripsOverviewPane());	
		
		Scene scene = new Scene(rootPane, (1920/1.5), (1080/1.5));
		
		return scene;
	};
	
	
	/**
	 * @author Oussama Fahchouch
	 * @return tripsOverviewPane
	 */
	private Pane createTripsOverviewPane() {
		Pane tripsOverviewPane = new Pane();

		tripsOverviewPane.setMinSize((1345/1.5), (750/1.5));
		tripsOverviewPane.setTranslateX((450/1.5));
		tripsOverviewPane.setTranslateY((200/1.5));
		tripsOverviewPane.setStyle("-fx-background-color: white; -fx-background-radius: 10px;");
		
		Label headerLabel = new Label("Ritten overzicht");	
		
		headerLabel.setFont(Font.font((24/1.5)));
		headerLabel.setTranslateX((50/1.5));
		headerLabel.setTranslateY((25/1.5));
		
		tripsOverviewPane.getChildren().addAll(headerLabel, addTripButtonPane(), TripOverviewTableView());
		
		return tripsOverviewPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return addTripButtonPane
	 */
	private Pane addTripButtonPane() {
		Pane addTripButtonPane = new Pane();
		
		addTripButtonPane.setMinSize((125/1.5), (125/1.5));
		addTripButtonPane.setTranslateX((1295/1.5));
		addTripButtonPane.setTranslateY((-37.50/1.5));
		
		Button addButton = new Button("+");
		
		double r = (1.5/1.5);
		addButton.setShape(new Circle(r));
		addButton.setMinSize((75/1.5)*r, (75/1.5)*r);
		addButton.setMaxSize((75/1.5)*r, (75/1.5)*r);
		addButton.setStyle("-fx-background-color: #4fb04f; -fx-font-size: 21px; -fx-text-fill: white;");
		
		addButton.setOnAction(e -> this.tripController.appController.loadView("views.AddTripView", "createView"));

		addTripButtonPane.getChildren().addAll(addButton);
				
		return addTripButtonPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return headerLabel
	 */
	private Label tripOverviewTableHeaderLabels(String labelContent) {
		Label headerLabel = new Label(labelContent);	
		
		headerLabel.setFont(Font.font((18/1.5)));
		headerLabel.setTranslateX((5/1.5));
		headerLabel.setTranslateY(0);
		
		return headerLabel;
	}
	
	/**
     * @author Oussama Fahchouch
     * @return tableView
     */
    private TableView TripOverviewTableView() {
        TableView tableView = new TableView();

        TableColumn<String, TripModel> column1 = new TableColumn<>("Start locatie");
        column1.setCellValueFactory(new PropertyValueFactory<>("startLocation"));
        
        TableColumn<String, TripModel> column2 = new TableColumn<>("Eind locatie");
        column2.setCellValueFactory(new PropertyValueFactory<>("endLocation"));
        
        TableColumn<String, TripModel> column3 = new TableColumn<>("Gereden kilometers");
        column3.setCellValueFactory(new PropertyValueFactory<>("drivenKilometers"));
        
        TableColumn<String, TripModel> column4 = new TableColumn<>("Kenteken");
        column4.setCellValueFactory(new PropertyValueFactory<>("licenseplate"));
        
        TableColumn<String, TripModel> column5 = new TableColumn<>("Project");
        column5.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        
        TableColumn<String, TripModel> column6 = new TableColumn<>("Verwijderen");
        column6.setCellValueFactory(new PropertyValueFactory<>("deleteTrip"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        tableView.getColumns().add(column6);

//        for (int i = 0; i < tripController.getTrips().size(); i++){
//            tableView.getItems().add(tripController.getTripsMadeByUser().get(i));
//        }
        
        tableView.setMinSize((1245/1.5), (450/1.5));
        tableView.setTranslateX((50/1.5));
        tableView.setTranslateY((100/1.5));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableView;
    }
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Override
	public void updateView(){}
	
	/**
	 * @author Oussama Fahchouch
	 * @return Scene
	 */
	@Override
	public Scene getScene() {
		return this.scene;
	};
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Override
	public Pane loadMenu() {
		Pane menuPane = new Pane();
		Pane headerPane = new Pane();
		
		menuPane.setMinSize((1920/1.5), (1080/1.5));
		menuPane.setTranslateX(0);
		menuPane.setTranslateY(0);
		
		headerPane.setMinSize((1920/1.5), (75/1.5));
		headerPane.setTranslateX(0);
		headerPane.setTranslateY(0);
		
		headerPane.setStyle("-fx-background-color: #FFFFFF");
		
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
		
		Pane logoPane = new Pane();
		
		Image imageLogo = new Image("file:src/resources/imgs/logo.jpg");
		ImageView imageViewLogo = new ImageView(imageLogo); 
		
		logoPane.setMinSize((300/1.5), (75/1.5));
		logoPane.setTranslateX(0);
		logoPane.setTranslateY(0);
		
		userHBox.getChildren().addAll(imageView, usernameLabel);
		logoPane.getChildren().addAll(imageViewLogo);
		headerPane.getChildren().addAll(logoPane, userHBox);
		
		Pane dashBoardViewPane = new Pane();
		
		dashBoardViewPane.setMinSize((300/1.5), (1080/1.5));
		dashBoardViewPane.setTranslateX(0);
		dashBoardViewPane.setTranslateY(0);

		dashBoardViewPane.setStyle("-fx-background-color: #FFFFFF");
		
		VBox menuVBox = new VBox();
		
		menuVBox.setPadding(new Insets(0, 0, 0, 0));
		menuVBox.setSpacing(0);
		menuVBox.setMinSize((170/1.5), (43/1.5));
		menuVBox.setTranslateX(0);
		menuVBox.setTranslateY((125/1.5));
		
		Image dashboardImage = new Image("file:src/resources/imgs/dashboard.png");
        Button dashboardButton = new Button("           Dashboard");
        ImageView imageViewDashboardImage = new ImageView(dashboardImage);
        imageViewDashboardImage.setTranslateX(0);
        dashboardButton.setGraphic(imageViewDashboardImage);
        dashboardButton.setStyle("-fx-background-color: #FFFFFF");
        
        dashboardButton.setMinSize((300/1.5), (50/1.5));
        dashboardButton.setMaxSize((300/1.5), (50/1.5));
        dashboardButton.setOnAction(e -> AppController.loadView("views.DashboardView", "createView"));
		
		Image tripImage = new Image("file:src/resources/imgs/road.png");
        Button tripButton = new Button("                 Ritten");
        ImageView tripImageView = new ImageView(tripImage);
        tripImageView.setTranslateX(0);
        tripButton.setGraphic(tripImageView);
        tripButton.setStyle("-fx-background-color: #FFFFFF");
        
        tripButton.setMinSize((300/1.5), (50/1.5));
        tripButton.setMaxSize((300/1.5), (50/1.5));
        tripButton.setOnAction(e -> AppController.loadView("views.TripOverviewView", "createView"));
       
        Image vehicleImage = new Image("file:src/resources/imgs/vehicle.png");
        Button vehicleButton = new Button("               Auto's");
        ImageView vehicleImageView = new ImageView(vehicleImage);
        tripImageView.setTranslateX(0);
        vehicleButton.setGraphic(vehicleImageView);
        
        vehicleButton.setMinSize((300/1.5), (50/1.5));
        vehicleButton.setMaxSize((300/1.5), (50/1.5));
        vehicleButton.setStyle("-fx-background-color: #FFFFFF");
        vehicleButton.setOnAction(e -> AppController.loadView("views.DashboardView", "createView"));

        Image projectImage = new Image("file:src/resources/imgs/projects.png");
        Button projectButton = new Button("            Projecten");
        ImageView projectImageView = new ImageView(projectImage);
        projectImageView.setTranslateX(0);
        projectButton.setGraphic(projectImageView);
        
        projectButton.setMinSize((300/1.5), (50/1.5));
        projectButton.setMaxSize((300/1.5), (50/1.5));
        
        projectButton.setStyle("-fx-background-color: #FFFFFF");
        projectButton.setOnAction(e -> AppController.loadView("views.DashboardView", "createView"));
        
        Pane logoutPane = new Pane();

		logoutPane.setMinSize((300/1.5), (135/1.5));
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

		menuVBox.getChildren().addAll(dashboardButton, tripButton, vehicleButton, projectButton);
		dashBoardViewPane.getChildren().addAll(menuVBox, logoutPane);
		menuPane.getChildren().addAll(dashBoardViewPane, headerPane);
	    
		return menuPane;
	}
}
