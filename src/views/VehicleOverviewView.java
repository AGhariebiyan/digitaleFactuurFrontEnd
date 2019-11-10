package views;

import controllers.VehicleController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import models.VehicleModel;

/**
 * @author Bram de Jong
 */
public class VehicleOverviewView implements View {
    private VehicleController vehicleController;
    private Scene scene;
    private TableView tableView = new TableView();

    /**
     * @author Bram de Jong
     */
    public VehicleOverviewView() {
        this.vehicleController = new VehicleController();
//        this.scene = createView();
    }

    /**
     * @author Bram de Jong
     */
    @Override
    public Scene createView(){
        Pane rootPane = new Pane();

        rootPane.getChildren().addAll(this.vehicleController.getMenuPane(), this.vehicleController.getHeaderPane(), createVehiclesOverviewPane());

        Scene scene = new Scene(rootPane, (1920/1.5), (1080/1.5));

        return scene;
    };


    /**
     * @author Bram de Jong
     * @return vehiclesOverviewPane
     */
    private Pane createVehiclesOverviewPane() {
        Pane vehiclesOverviewPane = new Pane();

        vehiclesOverviewPane.setMinSize((1345/1.5), (750/1.5));
        vehiclesOverviewPane.setTranslateX((450/1.5));
        vehiclesOverviewPane.setTranslateY((200/1.5));
        vehiclesOverviewPane.setStyle("-fx-background-color: white; -fx-background-radius: 10px;");

        Label headerLabel = new Label("Voertuigen overzicht");

        headerLabel.setFont(Font.font((24/1.5)));
        headerLabel.setTranslateX((50/1.5));
        headerLabel.setTranslateY((25/1.5));

        vehiclesOverviewPane.getChildren().addAll(headerLabel, addVehicleButtonPane(), deleteVehicleButtonPane(), VehicleOverviewTableView());

        return vehiclesOverviewPane;
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

        Button addButton = new Button("+");

        double r = (1.5/1.5);
        addButton.setShape(new Circle(r));
        addButton.setMinSize((75/1.5)*r, (75/1.5)*r);
        addButton.setMaxSize((75/1.5)*r, (75/1.5)*r);
        addButton.setStyle("-fx-background-color: #4fb04f; -fx-font-size: 21px; -fx-text-fill: white;");
        addButton.setOnAction(e -> this.vehicleController.appController.loadView("views.AddVehicleView", "createView"));
        addVehicleButtonPane.getChildren().addAll(addButton);
        return addVehicleButtonPane;
    }

    /**
     * @author Bram de Jong
     * @return headerLabel
     */
    private Label vehicleOverviewTableHeaderLabels(String labelContent) {
        Label headerLabel = new Label(labelContent);

        headerLabel.setFont(Font.font((18/1.5)));
        headerLabel.setTranslateX((5/1.5));
        headerLabel.setTranslateY(0);

        return headerLabel;
    }

    /**
     * @author Mike van Es
     * @author Bram de Jong
     * @return tableView
     */
    private TableView VehicleOverviewTableView() {
        TableColumn<String, VehicleModel> column1 = new TableColumn<>("Kenteken");
        column1.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));

        TableColumn<String, VehicleModel> column2 = new TableColumn<>("Auto naam");
        column2.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));

        TableColumn<String, VehicleModel> column3 = new TableColumn<>("Auto type");
        column3.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));

        TableColumn<String, VehicleModel> column4 = new TableColumn<>("Aantal ritten");
        column4.setCellValueFactory(new PropertyValueFactory<>("totalTrips"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);



        for (VehicleModel vm: vehicleController.fetchAllVehicles()){
            tableView.getItems().add(vm);
        }

        tableView.setMinSize((1245/1.5), (450/1.5));
        tableView.setTranslateX((50/1.5));
        tableView.setTranslateY((100/1.5));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableView;
    }

    private Pane deleteVehicleButtonPane() {
        Pane deleteVehicleButtonPane = new Pane();

        deleteVehicleButtonPane.setMinSize((125/1.5), (125/1.5));
        deleteVehicleButtonPane.setTranslateX((1295/1.5));
        deleteVehicleButtonPane.setTranslateY((700/1.5));
        
        Image image = new Image(this.getClass().getResource("/imgs/bin.png").toExternalForm());
		ImageView imageView = new ImageView(image); 
		imageView.setTranslateX(0);
        Button addButton = new Button("", imageView);

        double r = (1.5/1.5);
        addButton.setShape(new Circle(r));
        addButton.setMinSize((75/1.5)*r, (75/1.5)*r);
        addButton.setMaxSize((75/1.5)*r, (75/1.5)*r);
        addButton.setStyle("-fx-background-color: #FF0000; -fx-font-size: 21px; -fx-text-fill: white;");
        addButton.setOnAction(e -> {vehicleController.deleteVehicle(tableView.getSelectionModel().getSelectedIndex());
            this.vehicleController.appController.loadView("views.VehicleOverviewView", "createView");});

        deleteVehicleButtonPane.getChildren().addAll(addButton);
        return deleteVehicleButtonPane;
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