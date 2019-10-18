package views;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

public class DashboardView implements View {
	private Scene scene;
	private Border blackBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
	
	/**
	 * @author Oussama Fahchouch
	 */
	public DashboardView() {
		this.scene = createView();
	}
	
	@Override
	public Scene createView() {
		Pane rootPane = new Pane();
		
		rootPane.getChildren().addAll(createdashBoardViewPane(), createHeaderPane());	
		
		Scene scene = new Scene(rootPane, 1920, 1080);
		
		return scene;
	}
	
	private Node createHeaderPane() {
		Pane headerPane = new Pane();
		
		headerPane.setMinSize(1920, 75);
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
	    
	    userHBox.setMinSize(1920, 75);
	    userHBox.setTranslateX(1670);
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
		
		Image image = new Image("file:src/resources/imgs/logoDF.jpg");
		ImageView imageView = new ImageView(image); 
		
		logoPane.setMinSize(300, 75);
		logoPane.setTranslateX(0);
		logoPane.setTranslateY(0);
		
		logoPane.getChildren().addAll(imageView);

		
		return logoPane;
	}

	private Node createdashBoardViewPane() {
		Pane dashBoardViewPane = new Pane();
		
		dashBoardViewPane.setMinSize(300, 1080);
		dashBoardViewPane.setTranslateX(0);
		dashBoardViewPane.setTranslateY(0);

		dashBoardViewPane.setStyle("-fx-background-color: #FFFFFF");
		dashBoardViewPane.getChildren().addAll(createMenuVBox(), createLogoutPane());	
		
		return dashBoardViewPane;
	}
	
	private Node createMenuVBox() {
		VBox menuVBox = new VBox();
		
		menuVBox.setPadding(new Insets(0, 0, 0, 0));
		menuVBox.setSpacing(0);
		menuVBox.setMinSize(300, 75);
		menuVBox.setTranslateX(0);
		menuVBox.setTranslateY(125);
		
		//create dashboard button
		Image dashboardImage = new Image("file:src/resources/imgs/dashboard.png");
        Button dashboardButton = new Button("           Dashboard");
        ImageView imageView = new ImageView(dashboardImage);
        imageView.setTranslateX(0);
        dashboardButton.setGraphic(imageView);
        dashboardButton.setStyle("-fx-background-color: #FFFFFF");
        
        dashboardButton.setMinSize(300, 50);
        dashboardButton.setMaxSize(300, 50);
		
		//create trip button 
		Image tripImage = new Image("file:src/resources/imgs/road.png");
        Button tripButton = new Button("                 Ritten");
        ImageView tripImageView = new ImageView(tripImage);
        tripImageView.setTranslateX(0);
        tripButton.setGraphic(tripImageView);
        tripButton.setStyle("-fx-background-color: #FFFFFF");
        
        tripButton.setMinSize(300, 50);
        tripButton.setMaxSize(300, 50);

		//create vehicle button 
        Image vehicleImage = new Image("file:src/resources/imgs/vehicle.png");
        Button vehicleButton = new Button("0");
        ImageView vehicleImageView = new ImageView(vehicleImage);
        tripImageView.setTranslateX(0);
        vehicleButton.setGraphic(vehicleImageView);
        
        vehicleButton.setMinSize(300, 50);
        vehicleButton.setMaxSize(300, 50);
        vehicleButton.setStyle("-fx-background-color: #FFFFFF");

		//create project button 
        Image projectImage = new Image("file:src/resources/imgs/projects.png");
        Button projectButton = new Button("0");
        ImageView projectImageView = new ImageView(projectImage);
        projectImageView.setTranslateX(0);
        projectButton.setGraphic(projectImageView);
        
        projectButton.setMinSize(300, 50);
        projectButton.setMaxSize(300, 50);
        
        projectButton.setStyle("-fx-background-color: #FFFFFF");
		
		menuVBox.getChildren().addAll(dashboardButton, tripButton, vehicleButton, projectButton);
		
		return menuVBox;
	}
	
	private Node createLogoutPane() {
		Pane logoutPane = new Pane();


		logoutPane.setMinSize(220, 135);
		logoutPane.setBorder(blackBorder);
		logoutPane.setTranslateX(0);
		logoutPane.setTranslateY(945);
		
		return logoutPane;
	}

	@Override
	public void updateView() {		
	}

	@Override
	public void setScene(Scene sceneToSet) {

	}

	@Override
	public Scene getScene() {
		return this.scene;
	}

}
