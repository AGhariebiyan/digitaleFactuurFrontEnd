package views;

import controllers.TripController;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Callback;
import models.TripModel;

/**
 * @author Oussama Fahchouch
 */
public class TripOverviewView implements View {
	private TripController tripController;
	private TableView tableView;
	private AlertView alertView;
		
	/**
	 * @author Oussama Fahchouch
	 */
	public TripOverviewView() {
		this.tripController = new TripController();
        this.alertView = new AlertView();
	}

	/**
	 * @author Oussama Fahchouch
	 */
	@Override
	public Scene createView(){
		Pane rootPane = new Pane();
		
		rootPane.getChildren().addAll(this.tripController.getMenuPane(), this.tripController.getHeaderPane(), createTripsOverviewPane());	
		
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
	 * @author Mike van Es
     * @author Oussama Fahchouch
     * @return tableView
     */
    private TableView TripOverviewTableView() {
        tableView = new TableView();

        TableColumn<String, TripModel> column1 = new TableColumn<>("Start locatie");
        column1.setCellValueFactory(new PropertyValueFactory<>("startLocation"));
        
        TableColumn<String, TripModel> column2 = new TableColumn<>("Eind locatie");
        column2.setCellValueFactory(new PropertyValueFactory<>("endLocation"));
        
        TableColumn<String, TripModel> column4 = new TableColumn<>("Kenteken");
        column4.setCellValueFactory(new PropertyValueFactory<>("licenseplate"));
        
        TableColumn<String, TripModel> column5 = new TableColumn<>("Project");
        column5.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column4);
        tableView.getColumns().add(column5);
        
        addDeleteButtonToTable();
        
        for (TripModel trip : tripController.fetchTrips()) {
        	 tableView.getItems().add(trip);
        }
        
        tableView.setMinSize((1245/1.5), (450/1.5));
        tableView.setTranslateX((50/1.5));
        tableView.setTranslateY((100/1.5));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableView;
    }
	
	
	
	/**
	 * @author Oussama Fahchouch
	 */
	private void addDeleteButtonToTable() {
        TableColumn<Data, Void> colBtn = new TableColumn("Verwijderen");

        Callback<TableColumn<Data, Void>, TableCell<Data, Void>> cellFactory = new Callback<TableColumn<Data, Void>, TableCell<Data, Void>>() {
            @Override
            public TableCell<Data, Void> call(final TableColumn<Data, Void> param) {
                final TableCell<Data, Void> cell = new TableCell<Data, Void>() {
             
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                    		Image image = new Image(this.getClass().getResource("/imgs/bin.png").toExternalForm());
                    		ImageView imageView = new ImageView(image); 
                    		imageView.setTranslateX(65);
                        	setGraphic(imageView);
                        }
                    }
                };
                
                cell.setOnMouseClicked((MouseEvent event) -> {
                    if (event.getButton().equals(MouseButton.PRIMARY)) {
                        int index = tableView.getSelectionModel().getSelectedIndex();
                        TripModel trip = (TripModel) tableView.getItems().get(index);
                        
                        tripController.deleteTrip(trip.getTripId());

                        alertView.alert("Rit " + trip.getTripId() + " is verwijderd.");		

                        tripController.appController.loadView("views.TripOverviewView", "createView");
                    }
                });
                
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        tableView.getColumns().add(colBtn);
    }
}