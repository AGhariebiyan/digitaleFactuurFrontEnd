package views;

import controllers.VehicleController;
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

import java.util.Arrays;
import java.util.List;

/**
 * @author Bram de Jong
 */
public class AddVehicleView implements View {
	private VehicleController vehicleController;
	private Scene scene;

	/**
	 * @author Bram de Jong
	 */
	public AddVehicleView() {
		this.vehicleController = new VehicleController();
		this.scene = createView();
	}
	
	/**
	 * @author Bram de Jong
	 * @return Scene
	 */
	@Override
	public Scene createView(){
		Pane rootPane = new Pane();
		
		rootPane.getChildren().addAll(this.vehicleController.getMenuPane(), this.vehicleController.getHeaderPane(), createAddVehiclesPane());	
		
		Scene scene = new Scene(rootPane, (1920/1.5), (1080/1.5));
		
		return scene;
	};
	
	/**
	 * @author Bram de Jong
	 * @return addVehiclesPane
	 */
	private Node createAddVehiclesPane() {
		Pane addVehiclesPane = new Pane();
		
		addVehiclesPane.setMinSize((1345/1.5), (750/1.5));
		addVehiclesPane.setTranslateX((450/1.5));
		addVehiclesPane.setTranslateY((200/1.5));
		addVehiclesPane.setStyle("-fx-background-color: white; -fx-background-radius: 10px;");
		
		Label headerLabel = new Label("Rit toevoegen");	
		
		headerLabel.setFont(Font.font((24/1.5)));
		headerLabel.setTranslateX((50/1.5));
		headerLabel.setTranslateY((25/1.5));
		
				
		addVehiclesPane.getChildren().addAll(headerLabel, addVehicleButtonPane(), inputFieldsAddVehicleVBox());
		
		return addVehiclesPane;
	}

	/**
	 * @author Bram de Jong
	 * @return addVehicleButtonPane
	 */
	private Pane addVehicleButtonPane() {
		Pane addVehicleButtonPane = new Pane();
		
		addVehicleButtonPane.setMinSize((125/1.5), (125/1.5));
		addVehicleButtonPane.setTranslateX((1295/1.5));
		addVehicleButtonPane.setTranslateY((-37.50/1.5));
		
		Image image = new Image("file:src/resources/imgs/road_white.png");
		ImageView imageView = new ImageView(image); 
		
		Button addButton = new Button();
		addButton.setGraphic(imageView);
		
		double r = (1.5/1.5);
		addButton.setShape(new Circle(r));
		addButton.setMinSize((75/1.5)*r, (75/1.5)*r);
		addButton.setMaxSize((75/1.5)*r, (75/1.5)*r);
		addButton.setStyle("-fx-background-color: #2F4051; -fx-font-size: 21px; -fx-text-fill: white;");
		
		addButton.setOnAction(e -> this.vehicleController.appController.loadView("views.VehicleOverviewView", "createView"));

		addVehicleButtonPane.getChildren().addAll(addButton);
		
		return addVehicleButtonPane;
	}
	
	/**
	 * @author Bram de Jong
	 * @return inputFieldsAddVehicleVBox
	 */
	private Node inputFieldsAddVehicleVBox() {
		VBox inputFieldsAddVehicleVBox = new VBox();
		inputFieldsAddVehicleVBox.setPadding(new Insets((5/1.5), (5/1.5), (5/1.5), (5/1.5)));
		inputFieldsAddVehicleVBox.setSpacing((10/1.5));
		
		inputFieldsAddVehicleVBox.setMinSize((900/1.5), (450/1.5));
		inputFieldsAddVehicleVBox.setTranslateX((300/1.5));
		inputFieldsAddVehicleVBox.setTranslateY((100/1.5));
		
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

			inputFieldsAddVehicleVBox.getChildren().add(inputFieldRow);
		}
		
		Label labelInputFieldsHBoxRow1 = new Label("Kenteken:");
		TextField textFieldInputFieldsHBoxRow1 = new TextField ();
		textFieldInputFieldsHBoxRow1.setId("licenseplateTextField");
		textFieldInputFieldsHBoxRow1.setPromptText("kenteken..");
		inputFieldsHBoxRow1.getChildren().addAll(labelInputFieldsHBoxRow1, textFieldInputFieldsHBoxRow1);
		
		Label labelInputFieldsHBoxRow2 = new Label("Project ID:");
		TextField textFieldInputFieldsHBoxRow2 = new TextField ();
		textFieldInputFieldsHBoxRow2.setId("projectIdTextField");
		textFieldInputFieldsHBoxRow2.setPromptText("project id..");
		inputFieldsHBoxRow2.getChildren().addAll(labelInputFieldsHBoxRow2, textFieldInputFieldsHBoxRow2);
		
		Label labelInputFieldsHBoxRow3 = new Label("Voertuig naam:");
		TextField textFieldInputFieldsHBoxRow3 = new TextField ();
		textFieldInputFieldsHBoxRow3.setId("vehicleNameTextField");
		textFieldInputFieldsHBoxRow3.setPromptText("voertuig naam..");
		inputFieldsHBoxRow3.getChildren().addAll(labelInputFieldsHBoxRow3, textFieldInputFieldsHBoxRow3);
		
		Label labelInputFieldsHBoxRow4 = new Label("Voertuig type:");
		TextField textFieldInputFieldsHBoxRow4 = new TextField ();
		textFieldInputFieldsHBoxRow4.setId("vehicleTypeTextField");
		textFieldInputFieldsHBoxRow4.setPromptText("voertuig type..");
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
		
		Button createVehicleButton = new Button("create");
		createVehicleButton.setStyle("-fx-font-size: 16px; -fx-background-color: #1E71EA; -fx-text-fill: white;");
		createVehicleButton.setTranslateX((750/1.5));
		createVehicleButton.setTranslateY((50/1.5));
		
		createVehicleButton.setOnAction(e -> this.vehicleController.appController.loadView("views.VehicleOverviewView", "createView"));
		
		inputFieldsHBoxRow5.getChildren().addAll(createVehicleButton);
		
		return inputFieldsAddVehicleVBox;
	}

	/**
	 * @author Bram de Jong
	 */
	@Override
	public void updateView(){}

	@Override
	public void setScene(Scene sceneToSet) {

	}

	/**
	 * @author Bram de Jong
	 * @return Scene
	 */
	@Override
	public Scene getScene() {
		return this.scene;
	};
}
