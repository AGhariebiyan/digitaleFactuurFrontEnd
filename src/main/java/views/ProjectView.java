package views;

import controllers.ProjectViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import models.ProjectModel;
import models.TripModel;

import java.util.ArrayList;

public class ProjectView implements View {
    private ProjectViewController projectViewController;
    private Scene scene;
    private ObservableList<ProjectModel> searchData = FXCollections.observableArrayList();
    private TextField searchTextField;


    /**
     * @author Mike van Es
     */
    public ProjectView() {
        this.projectViewController = new ProjectViewController();
    }

    /**
     * @author Mike van Es
     * @return
     */
    @Override
    public Scene createView(){
        Pane rootPane = new Pane();

        rootPane.getChildren().addAll(this.projectViewController.getMenuPane(), this.projectViewController.getHeaderPane(), addTabs());
        rootPane.setId("mainBackground");
        Scene scene = new Scene(rootPane,(1920/1.5), (1080/1.5));
        this.scene = scene;
        this.scene.getStylesheets().add( this.getClass().getResource("/css/application.css").toExternalForm() );
        return scene;
    };

    private TabPane addTabs(){
        // create a tabpane
        TabPane tabpane = new TabPane();



        // create multiple tabs

        ArrayList<TripModel> tripList = projectViewController.getTrips();
//        System.out.println(tripList);
        try{
            for (TripModel tripModel : tripList) {
                System.out.println(tripModel.getTripId());
                System.out.println(tripModel.getEndKilometergauge());
                // create Tab
                Tab tab = new Tab("Trip: " + tripModel.getTripId());
                GridPane gridPane = new GridPane();


                // create a label
                Label vehicle = new Label("Kenteken: " + tripModel.getLicenseplate());
                Label locationA = new Label("Start locatie: " + tripModel.getStartLocation());
                Label locationB = new Label("Eind locatie " + tripModel.getEndLocation());
                Label startKm = new Label("Kilometerstand start: " + tripModel.getStartKilometergauge());
                Label endKm = new Label("Kilometerstand eind: " + tripModel.getEndKilometergauge());

                gridPane.add(vehicle, 0, 1);
                gridPane.add(locationA, 0, 2);
                gridPane.add(locationB, 0, 3);
                gridPane.add(startKm, 0, 4);
                gridPane.add(endKm, 0, 5);
                // add label to the tab
                tab.setContent(gridPane);

                // add tab
                tabpane.getTabs().add(tab);
            }
            return tabpane;
        }catch(java.lang.NullPointerException e){
            Tab tab = new Tab("Geen trips");
            GridPane gridPane = new GridPane();

            // create a label
            Label noTrip = new Label("Geen trips gevonden");

            gridPane.add(noTrip, 1, 0);
            // add label to the tab
            tab.setContent(gridPane);

            // add tab
            tabpane.getTabs().add(tab);

            return tabpane;
        }

    }


    /**
     * @author Oussama Fahchouch
     */
    @Override
    public void updateView(){}

    /**
     * @author Oussama Fahchouch
     */
    @Override
    public Scene getScene() {
        return this.scene;
    };
}
