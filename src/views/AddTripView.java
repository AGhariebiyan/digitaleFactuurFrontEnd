package views;

import controllers.AppController;
import controllers.Controller;
import controllers.TripController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.Arrays;
import java.util.List;

/**
 * @author Oussama Fahchouch
 */
public class AddTripView implements View {
	private TripController tripController;
	private AlertView alertView;
	private WebView webViewStart = new WebView();
	private WebView webViewEnd = new WebView();
	private final WebEngine webEngineStartLoc = webViewStart.getEngine();
	private final WebEngine webEngineEndLoc = webViewEnd.getEngine();
	
	/**
	 * @author Oussama Fahchouch
	 */
	public AddTripView() {
		this.tripController = new TripController();
        this.alertView = new AlertView();
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return Scene
	 */
	@Override
	public Scene createView(){
		Pane rootPane = new Pane();
		
		rootPane.getChildren().addAll(this.tripController.getMenuPane(), this.tripController.getHeaderPane(), createAddTripsPane());	
		
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
	 * @author Mike van Es, Ousama Fahchouch
	 * @return addTripButtonPane
	 */
	private Pane addTripButtonPane() {
		Pane addTripButtonPane = new Pane();
		
		addTripButtonPane.setMinSize((125/1.5), (125/1.5));
		addTripButtonPane.setTranslateX((1295/1.5));
		addTripButtonPane.setTranslateY((-37.50/1.5));
		
		Image image = new Image(this.getClass().getResource("/imgs/road_white.png").toExternalForm());
		ImageView imageView = new ImageView(image); 
		
		Button addButton = new Button();
		addButton.setGraphic(imageView);
		
		double r = (1.5/1.5);
		addButton.setShape(new Circle(r));
		addButton.setMinSize((75/1.5)*r, (75/1.5)*r);
		addButton.setMaxSize((75/1.5)*r, (75/1.5)*r);
		addButton.setStyle("-fx-background-color: #2F4051; -fx-font-size: 21px; -fx-text-fill: white;");
		
		addButton.setOnAction(e -> AppController.loadView("views.TripOverviewView", "createView"));


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
		TextField textFieldInputFieldsHBoxRow1 = new TextField ("0");
        final List<Integer> comboBoxProjectIdItems = tripController.fetchAllUniqueProjectIds();
        ComboBox comboBoxProjectId = new ComboBox();
        VBox vBoxProjectId = new VBox();

        comboBoxProjectId.getItems().addAll(comboBoxProjectIdItems);

        comboBoxProjectId.setOnAction(event -> {
        	textFieldInputFieldsHBoxRow1.setText(comboBoxProjectId.getValue().toString());
        });

        vBoxProjectId.getChildren().addAll(comboBoxProjectId);
        
        comboBoxProjectId.setPadding(new Insets((5/1.5), (5/1.5), (5/1.5), (5/1.5)));

        comboBoxProjectId.setMinSize((475/1.5), (7.5/1.5));
        comboBoxProjectId.setTranslateX((125/1.5));
        comboBoxProjectId.setTranslateY((0/1.5));
        
        textFieldInputFieldsHBoxRow1.setId("projectIdTextField");
        inputFieldsHBoxRow1.getChildren().addAll(labelInputFieldsHBoxRow1, vBoxProjectId);
        
        // Mike van Es heeft dit er later bijgezet
		Label labelInputFieldsHBoxRow2 = new Label("Start locatie:");
		this.webEngineStartLoc.load(this.getClass().getResource("/html/autocomplete.html").toString());
		webViewStart.setMaxHeight(200);
		webViewStart.setMaxWidth(470);
		webViewStart.setTranslateX(75);
		webViewStart.setTranslateY(0);
		labelInputFieldsHBoxRow2.setTranslateY(15);
		inputFieldsHBoxRow2.getChildren().addAll(labelInputFieldsHBoxRow2, webViewStart);

		Label labelInputFieldsHBoxRow3 = new Label("Eind locatie:");
		this.webEngineEndLoc.load(this.getClass().getResource("/html/autocomplete.html").toString());
		webViewEnd.setMaxHeight(200);
		webViewEnd.setMaxWidth(470);
		webViewEnd.setTranslateX(75);
		webViewEnd.setTranslateY(-150);
		labelInputFieldsHBoxRow3.setTranslateY(-135);
		inputFieldsHBoxRow3.getChildren().addAll(labelInputFieldsHBoxRow3, webViewEnd);
		// tot hier loopt de code van Mike
		
		Label labelInputFieldsHBoxRow4 = new Label("Kenteken voertuig:");
		TextField textFieldInputFieldsHBoxRow4 = new TextField ();
        final List<String> comboBoxLicenseplateItems4 = tripController.fetchAllUniqueLicenseplates();
        ComboBox comboBoxLicenseplate = new ComboBox();
        VBox vBoxLicenseplate = new VBox();

        comboBoxLicenseplate.getItems().addAll(comboBoxLicenseplateItems4);

        comboBoxLicenseplate.setOnAction(event -> {
        	textFieldInputFieldsHBoxRow4.setText(comboBoxLicenseplate.getValue().toString());
        });

        vBoxLicenseplate.getChildren().addAll(comboBoxLicenseplate);
        
        comboBoxLicenseplate.setPadding(new Insets((5/1.5), (5/1.5), (5/1.5), (5/1.5)));

        comboBoxLicenseplate.setMinSize((475/1.5), (7.5/1.5));
        comboBoxLicenseplate.setTranslateX((125/1.5));
        comboBoxLicenseplate.setTranslateY((0/1.5));
        
        textFieldInputFieldsHBoxRow4.setId("projectIdTextField");
        inputFieldsHBoxRow4.getChildren().addAll(labelInputFieldsHBoxRow4, vBoxLicenseplate);
		inputFieldsHBoxRow4.setTranslateY(-200);
		
		List<Label> labelList = Arrays.asList(labelInputFieldsHBoxRow1,labelInputFieldsHBoxRow2, labelInputFieldsHBoxRow3, labelInputFieldsHBoxRow4);

		for (Label label: labelList) {
			label.setStyle("-fx-font-size: 16px;");
			label.setMinSize((250/1.5), (50/1.5));
		}
		
		List<TextField> textFieldList = Arrays.asList(textFieldInputFieldsHBoxRow1, textFieldInputFieldsHBoxRow4);

		for (TextField textField: textFieldList) {
			textField.setPrefWidth((475/1.5));
			textField.setStyle("-fx-font-size: 16px;");
			textField.setTranslateX((125/1.5));
			textField.setTranslateY((10/1.5));
		}
		
		Button createTripButton = new Button("create");
		createTripButton.setStyle("-fx-font-size: 16px; -fx-background-color: #1E71EA; -fx-text-fill: white;");
		createTripButton.setTranslateX((750/1.5));
		createTripButton.setTranslateY(-190);
		
		createTripButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			    public void handle(ActionEvent e) {
					//webEngine start & endLoc is gemaakt door Mike van Es
					String startLocStr = (String) webEngineStartLoc.executeScript("getPlace()");
					String endLocStr = (String) webEngineEndLoc.executeScript("getPlace()");
					//tot hier
					
					if(textFieldInputFieldsHBoxRow4.getText().isEmpty() || endLocStr.equals("undefined") || startLocStr.equals("undefined")) {

				        alertView.alert("De rit is niet goed aangegeven. Probeer het opnieuw");
					} else if(!textFieldInputFieldsHBoxRow4.getText().isEmpty() ) {
						AppController.getInstance();
						tripController.addTripForProject(Integer.parseInt(textFieldInputFieldsHBoxRow1.getText()),
								textFieldInputFieldsHBoxRow4.getText(),
								startLocStr,
								endLocStr);
						
						alertView.alert("De rit is toegevoegd. U wordt nu doorverwezen naar het ritten overzicht.");		
		                AppController.loadView("views.TripOverviewView", "createView");
					} else { 						
						tripController.addTripByUser(textFieldInputFieldsHBoxRow4.getText(),
								startLocStr,
								endLocStr);
						
						alertView.alert("De rit is toegevoegd. U wordt nu doorverwezen naar het ritten overzicht.");		
		                AppController.loadView("views.TripOverviewView", "createView");
					}
			     }
			 });
		
		inputFieldsHBoxRow5.getChildren().addAll(createTripButton);
		
		return inputFieldsAddTripVBox;
	}
}