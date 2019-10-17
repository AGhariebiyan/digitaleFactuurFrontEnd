package views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import controllers.TripController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import models.TripModel;

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
	
	public Pane loadMenu() {
		return null;
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
}
