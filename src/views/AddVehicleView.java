package views;

import controllers.VehicleController;
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

        Label headerLabel = new Label("Voertuig toevoegen");

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

        Image image = new Image(this.getClass().getResource("/imgs/vehicle.png").toExternalForm());
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
        TextField licensePlate = new TextField ();
        licensePlate.setId("licenseplateTextField");
        licensePlate.setPromptText("kenteken..");
        inputFieldsHBoxRow1.getChildren().addAll(labelInputFieldsHBoxRow1, licensePlate);
        
        Label labelInputFieldsHBoxRow2 = new Label("Project ID:");        
        TextField projectId = new TextField ("0");
        final List<Integer> comboBoxProjectIdItems = vehicleController.fetchAllUniqueProjectIds();
        ComboBox comboBoxProjectId = new ComboBox();
        VBox vBoxProjectId = new VBox();

        comboBoxProjectId.getItems().addAll(comboBoxProjectIdItems);

        comboBoxProjectId.setOnAction(event -> {
        	projectId.setText(comboBoxProjectId.getValue().toString());
        });

        vBoxProjectId.getChildren().addAll(comboBoxProjectId);
        
        comboBoxProjectId.setPadding(new Insets((5/1.5), (5/1.5), (5/1.5), (5/1.5)));

        comboBoxProjectId.setMinSize((475/1.5), (7.5/1.5));
        comboBoxProjectId.setTranslateX((125/1.5));
        comboBoxProjectId.setTranslateY((0/1.5));
        
        projectId.setId("projectIdTextField");
        projectId.setPromptText("project id..");
        inputFieldsHBoxRow2.getChildren().addAll(labelInputFieldsHBoxRow2, vBoxProjectId);
        
        Label labelInputFieldsHBoxRow3 = new Label("Voertuig naam:");
        TextField vehicleName = new TextField ();
        vehicleName.setId("vehicleNameTextField");
        vehicleName.setPromptText("voertuig naam..");
        inputFieldsHBoxRow3.getChildren().addAll(labelInputFieldsHBoxRow3, vehicleName);
        
        Label labelInputFieldsHBoxRow4 = new Label("Voertuig type:");
        TextField textFieldVehicleType = new TextField ();
        final String[] comboBoxVehicleTypeItems = {"Hatchback", "Sedan", "Stationwagen", "Coupé", "SUV", "Bestelwagen", "Motor"};
        ComboBox comboBoxVehicleType = new ComboBox();
        VBox vBoxVehicleType = new VBox();

        comboBoxVehicleType.getItems().addAll(comboBoxVehicleTypeItems);

        comboBoxVehicleType.setOnAction(event -> {
            textFieldVehicleType.setText(comboBoxVehicleType.getValue().toString());
        });

        vBoxVehicleType.getChildren().addAll(comboBoxVehicleType);
        
        comboBoxVehicleType.setPadding(new Insets((5/1.5), (5/1.5), (5/1.5), (5/1.5)));

        comboBoxVehicleType.setMinSize((475/1.5), (5/1.5));
        comboBoxVehicleType.setTranslateX((125/1.5));
        comboBoxVehicleType.setTranslateY((0/1.5));
        
        inputFieldsHBoxRow4.getChildren().addAll(labelInputFieldsHBoxRow4, vBoxVehicleType);

        List<Label> labelList = Arrays.asList(labelInputFieldsHBoxRow1, labelInputFieldsHBoxRow2, labelInputFieldsHBoxRow3, labelInputFieldsHBoxRow4);

        for (Label label: labelList) {
            label.setStyle("-fx-font-size: 16px;");
            label.setMinSize((250/1.5), (50/1.5));
        }

        List<TextField> textFieldList = Arrays.asList(licensePlate, projectId, vehicleName);

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

        createVehicleButton.setOnAction(
                e -> this.vehicleController.addVehicle(licensePlate.getText(),Integer.parseInt(projectId.getText()),vehicleName.getText(),textFieldVehicleType.getText()));

        inputFieldsHBoxRow5.getChildren().addAll(createVehicleButton);

        return inputFieldsAddVehicleVBox;
    }

    /**
     * @author Bram de Jong
     */
    @Override
    public void updateView(){}

    /**
     * @author Bram de Jong
     * @return Scene
     */
    @Override
    public Scene getScene() {
        return this.scene;
    };
}