package views;

import java.util.Arrays;
import java.util.List;

import controllers.AppController;
import controllers.TripController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

/**
 * @author Oussama Fahchouch
 */
public class AddTripView implements View {
	private TripController tripController;
	private Scene scene;
	
	/**
	 * @author Oussama Fahchouch
	 */
	public AddTripView() {
		this.tripController = new TripController();
		this.scene = createView();
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return Scene
	 */
	@Override
	public Scene createView(){
		Pane rootPane = new Pane();
		
		rootPane.getChildren().addAll(createAddTripsPane());	
		
		Scene scene = new Scene(rootPane, (1920/1.5), (1080/1.5));
		
		return scene;
	};
	
	/**
	 * @author Oussama Fahchouch
	 * @return addTripsPane
	 */
	private Node createAddTripsPane() {
		Pane addTripsPane = new Pane();
		
		addTripsPane.setMinSize((1345/1.5), (750/1.5));
		addTripsPane.setTranslateX((450/1.5));
		addTripsPane.setTranslateY((200/1.5));
		addTripsPane.setStyle("-fx-background-color: white; -fx-background-radius: 10px;");
		
		Label headerLabel = new Label("Rit toevoegen");	
		
		headerLabel.setFont(Font.font((24/1.5)));
		headerLabel.setTranslateX((50/1.5));
		headerLabel.setTranslateY((25/1.5));
		
				
		addTripsPane.getChildren().addAll(headerLabel, addTripButtonPane(), inputFieldsAddTripVBox());
		
		return addTripsPane;
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
		
		Image image = new Image("file:src/resources/imgs/road_white.png");
		ImageView imageView = new ImageView(image); 
		
		Button addButton = new Button();
		addButton.setGraphic(imageView);
		
		double r = (1.5/1.5);
		addButton.setShape(new Circle(r));
		addButton.setMinSize((75/1.5)*r, (75/1.5)*r);
		addButton.setMaxSize((75/1.5)*r, (75/1.5)*r);
		addButton.setStyle("-fx-background-color: #2F4051; -fx-font-size: 21px; -fx-text-fill: white;");
		
		addButton.setOnAction(e -> this.tripController.appController.loadView("views.TripOverviewView", "createView"));

		addTripButtonPane.getChildren().addAll(addButton);
		
		return addTripButtonPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return inputFieldsAddTripVBox
	 */
	private Node inputFieldsAddTripVBox() {
		VBox inputFieldsAddTripVBox = new VBox();
		inputFieldsAddTripVBox.setPadding(new Insets((5/1.5), (5/1.5), (5/1.5), (5/1.5)));
		inputFieldsAddTripVBox.setSpacing((10/1.5));
		
		inputFieldsAddTripVBox.setMinSize((900/1.5), (450/1.5));
		inputFieldsAddTripVBox.setTranslateX((300/1.5));
		inputFieldsAddTripVBox.setTranslateY((100/1.5));
		
		HBox inputFieldsHBoxRow1 = new HBox();
		HBox inputFieldsHBoxRow2 = new HBox();
		HBox inputFieldsHBoxRow3 = new HBox();
		HBox inputFieldsHBoxRow4 = new HBox();
		HBox inputFieldsHBoxRow5 = new HBox();
		
		List<HBox> inputFieldsList = Arrays.asList(inputFieldsHBoxRow1, inputFieldsHBoxRow2, inputFieldsHBoxRow3, inputFieldsHBoxRow4, inputFieldsHBoxRow5);
		
		int setTranslateYOfRow = (17);
		int amountOfRows = 5;
		int rowCounter = 0;
		
		for (HBox inputFieldRow: inputFieldsList) {
			rowCounter+= 1;
			inputFieldRow.setPadding(new Insets((5/1.5), (5/1.5), (5/1.5), (5/1.5)));
			inputFieldRow.setSpacing((10/1.5));
			
			inputFieldRow.setMinSize((900/1.5), (50/1.5));
			inputFieldRow.setTranslateX((-100/1.5));
			inputFieldRow.setTranslateY(setTranslateYOfRow);
			
			if (rowCounter == amountOfRows) { inputFieldRow.setTranslateY(setTranslateYOfRow + 17); }
			
			setTranslateYOfRow+= 17;

			inputFieldsAddTripVBox.getChildren().add(inputFieldRow);
		}
		
		Label labelInputFieldsHBoxRow1 = new Label("Project id:");
		TextField textFieldInputFieldsHBoxRow1 = new TextField ();
		textFieldInputFieldsHBoxRow1.setId("projectIdTextField");
		textFieldInputFieldsHBoxRow1.setPromptText("project id..");
		inputFieldsHBoxRow1.getChildren().addAll(labelInputFieldsHBoxRow1, textFieldInputFieldsHBoxRow1);
		
		Label labelInputFieldsHBoxRow2 = new Label("Start locatie:");
		TextField textFieldInputFieldsHBoxRow2 = new TextField ();
		textFieldInputFieldsHBoxRow2.setId("startLocationTextField");
		textFieldInputFieldsHBoxRow2.setPromptText("start locatie..");
		inputFieldsHBoxRow2.getChildren().addAll(labelInputFieldsHBoxRow2, textFieldInputFieldsHBoxRow2);
		
		Label labelInputFieldsHBoxRow3 = new Label("Eind locatie:");
		TextField textFieldInputFieldsHBoxRow3 = new TextField ();
		textFieldInputFieldsHBoxRow3.setId("endLocationTextField");
		textFieldInputFieldsHBoxRow3.setPromptText("eind locatie..");
		inputFieldsHBoxRow3.getChildren().addAll(labelInputFieldsHBoxRow3, textFieldInputFieldsHBoxRow3);
		
		Label labelInputFieldsHBoxRow4 = new Label("Kenteken voertuig:");
		TextField textFieldInputFieldsHBoxRow4 = new TextField ();
		textFieldInputFieldsHBoxRow4.setId("licenseplateTextField");
		textFieldInputFieldsHBoxRow4.setPromptText("kenteken voertuig..");
		inputFieldsHBoxRow4.getChildren().addAll(labelInputFieldsHBoxRow4, textFieldInputFieldsHBoxRow4);
		
		List<Label> labelList = Arrays.asList(labelInputFieldsHBoxRow1, labelInputFieldsHBoxRow2, labelInputFieldsHBoxRow3, labelInputFieldsHBoxRow4);

		for (Label label: labelList) {
			label.setStyle("-fx-font-size: 16px;");
			label.setMinSize((250/1.5), (50/1.5));
		}
		
		List<TextField> textFieldList = Arrays.asList(textFieldInputFieldsHBoxRow1, textFieldInputFieldsHBoxRow2, textFieldInputFieldsHBoxRow3, textFieldInputFieldsHBoxRow4);
		
		for (TextField textField: textFieldList) {
			textField.setPrefWidth((475/1.5));
			textField.setStyle("-fx-font-size: 16px;");
			textField.setTranslateX((125/1.5));
			textField.setTranslateY((10/1.5));
		}
		
		Button createTripButton = new Button("create");
		createTripButton.setStyle("-fx-font-size: 16px; -fx-background-color: #1E71EA; -fx-text-fill: white;");
		createTripButton.setTranslateX((750/1.5));
		createTripButton.setTranslateY((50/1.5));
		
		createTripButton.setOnAction(e -> this.tripController.appController.loadView("views.TripOverviewView", "createView"));
		
		inputFieldsHBoxRow5.getChildren().addAll(createTripButton);
		
		return inputFieldsAddTripVBox;
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
