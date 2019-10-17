package views;

import controllers.TripController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import models.TripModel;

/**
 * @author Oussama Fahchouch
 */
public class TripOverviewView implements View {
	private TripController tripController;
	private Scene scene;
	
	public Pane loadMenu() {
		return null;
	}
	
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
		
		rootPane.getChildren().addAll(createTripsOverviewPane());	
		
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
		
		Label headerLabel = new Label("Ritten");	
		
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
}
