package views;

import controllers.VehicleController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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

	/**
	 * @author Bram de Jong
	 */
	public VehicleOverviewView() {
		this.vehicleController = new VehicleController();
		this.scene = createView();
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
		
		vehiclesOverviewPane.getChildren().addAll(headerLabel, addVehicleButtonPane(), VehicleOverviewTableView());
		
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
        TableView tableView = new TableView();

        TableColumn<String, VehicleModel> column1 = new TableColumn<>("Kenteken");
        column1.setCellValueFactory(new PropertyValueFactory<>("licenseplate"));
        
        TableColumn<String, VehicleModel> column2 = new TableColumn<>("Auto naam");
        column2.setCellValueFactory(new PropertyValueFactory<>("vehicleName"));
        
        TableColumn<String, VehicleModel> column3 = new TableColumn<>("Auto type");
        column3.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));

		TableColumn<String, VehicleModel> column4 = new TableColumn<>("Aantal ritten");
		column4.setCellValueFactory(new PropertyValueFactory<>("totalTrips"));
        
        TableColumn<String, VehicleModel> column5 = new TableColumn<>("Verwijderen");
        column5.setCellValueFactory(new PropertyValueFactory<>("deleteVehicle"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
		tableView.getColumns().add(column5);

//        for (int i = 0; i < vehicleController.getVehicles().size(); i++){
//            tableView.getItems().add(vehicleController.getVehiclesMadeByUser().get(i));
//        }
        
        tableView.setMinSize((1245/1.5), (450/1.5));
        tableView.setTranslateX((50/1.5));
        tableView.setTranslateY((100/1.5));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableView;
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
