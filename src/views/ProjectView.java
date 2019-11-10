package views;

import controllers.ProjectViewController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import models.ProjectModel;
import models.TripModel;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Mike van Es
 */
public class ProjectView implements View {
    private ProjectViewController projectViewController;
    private Scene scene;
    private ObservableList<ProjectModel> searchData = FXCollections.observableArrayList();
    private TextField searchTextField;
    private WebEngine lastEngine;
    public final Map<WebEngine, String> gmapMap = new LinkedHashMap<>();

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

        rootPane.getChildren().addAll(this.projectViewController.getMenuPane(), this.projectViewController.getHeaderPane(), createProjectPane());
        rootPane.setId("mainBackground");
        Scene scene = new Scene(rootPane,(1920/1.5), (1080/1.5));
        this.scene = scene;
        this.scene.getStylesheets().add( this.getClass().getResource("/css/application.css").toExternalForm() );
        return scene;
    };

    /**
     * @author Mike van Es
     * @return ProjectOverviewPane
     */
    private Pane createProjectPane() {
        Pane ProjectPane = new Pane();

        ProjectPane.setMinSize((1345/1.5), (750/1.5));
        ProjectPane.setTranslateX((450/1.5));
        ProjectPane.setTranslateY((200/1.5));
        //Add this for the correct panel styling
        ProjectPane.setId("mainPanelBg");

        Label headerLabel = new Label(this.projectViewController.getProjectName());

        headerLabel.setFont(Font.font(24));
        headerLabel.setTranslateX((50/1.5));
        headerLabel.setTranslateY((25/1.5));

        ProjectPane.getChildren().addAll(headerLabel, addTabs());

        return ProjectPane;
    }

    /**
     * Creates a tabpane of the trips of a given project.
     * @author Mike van Es
     * @return TabPane
     */
    private TabPane addTabs(){
        // create a tabpane
        TabPane tabpane = new TabPane();
        tabpane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        tabpane.setMinSize((1245/1.5), (450/1.5));
        tabpane.setTranslateX((50/1.5));
        tabpane.setTranslateY((100/1.5));

        ArrayList<TripModel> tripList = projectViewController.getTrips();
        gmapMap.clear();
        try{
            for (TripModel tripModel : tripList) {
                Pane projectInsightPane = new Pane();
                HBox projectInfo = new HBox();
                GridPane gp = new GridPane();
                // create Tab
                Tab tab = new Tab("Trip: " + tripModel.getTripId());

                VBox vboxLeft = new VBox();
                vboxLeft.setId("labelTitles");
                VBox vboxRight = new VBox();
                vboxRight.setId("labelValues");
                // create a label
                Label vehicle = new Label("Kenteken: ");
                Label locationA = new Label("Start locatie: " );
                Label locationB = new Label("Eind locatie " );
                Label startKm = new Label("Kilometerstand start: " );
                Label endKm = new Label("Kilometerstand eind: " );

                vboxLeft.getChildren().addAll(vehicle);
                vboxLeft.getChildren().addAll(locationA);
                vboxLeft.getChildren().addAll(locationB);
                vboxLeft.getChildren().addAll(startKm);
                vboxLeft.getChildren().addAll(endKm);

                vboxRight.setAlignment(Pos.TOP_LEFT);

                // create a label
                Label vehicleV = new Label(tripModel.getLicenseplate());
                Label locationAV = new Label(tripModel.getStartLocation());
                Label locationBV = new Label(tripModel.getEndLocation());
                Label startKmV = new Label(""+tripModel.getStartKilometergauge());
                Label endKmV = new Label(""+tripModel.getEndKilometergauge());

                vboxLeft.setAlignment(Pos.TOP_LEFT);

                vboxRight.getChildren().addAll(vehicleV);
                vboxRight.getChildren().addAll(locationAV);
                vboxRight.getChildren().addAll(locationBV);
                vboxRight.getChildren().addAll(startKmV);
                vboxRight.getChildren().addAll(endKmV);

                gp.add(vboxLeft, 0,1);
                gp.add(vboxRight, 1, 1);
                projectInfo.getChildren().add(gp);

                // Create the gmaps
                projectInsightPane.getChildren().addAll(this.createGmaps(tripModel), gp);
                tab.setContent(projectInsightPane);



                // add tab
                tabpane.getTabs().add(tab);
            }


            this.createCallBackWebEngine();

            return tabpane;
        }catch(java.lang.NullPointerException e){
            System.out.println("Kan bestand niet vinden van regel: ");
            System.out.println(e.getStackTrace());
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
     * Creates webengines for google maps. gmapsMap constant gets filled with the references of the different engines. So we ca access from a different thread
     * @author Mike van Es
     * @param tripModel
     * @return HBox
     */
    public HBox createGmaps(TripModel tripModel){
        WebView webView = new WebView();
        // Create the WebEngine
        WebEngine webEngine = webView.getEngine();
        webEngine.load(this.getClass().getResource("/html/index.html").toString());

        String startloc = tripModel.getStartLocation();
        String endloc = tripModel.getEndLocation();


        //Save all values in a final array, so we can access it from the callback thread.
        gmapMap.put(webEngine, startloc+"END:"+endloc);

        // Create the VBox
        HBox webbox = new HBox();
        webbox.setId("gmaps");
        webbox.setTranslateY(150);
        // Add the WebView to the VBox
        webbox.getChildren().add(webView);

        //Save the last used engine, when this thread fires all the maps will be loaded.
        this.lastEngine = webEngine;
        return webbox;
    }

    /**
     * Callback method for WebEngine, this method runs on a different thread.
     * @author Mike van Es
     */
    private void createCallBackWebEngine(){
        lastEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
            public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    try {
                        for (Map.Entry<WebEngine, String> entry : gmapMap.entrySet()) {
                            //When the site is loaded call the js functions
                            //Parse the String
                            String[] split = entry.getValue().split("END:");
                            String startloc = split[0];
                            String endloc = split[1];

                            entry.getKey().executeScript("addByAdress('" + startloc + "', '" + endloc + "')");
                        }
                    }catch (netscape.javascript.JSException e){
                        //Reload when it fails, soemtimes the script tries to init the adress function before it is ready
                        for (Map.Entry<WebEngine, String> entry : gmapMap.entrySet()) {
                            entry.getKey().reload();
                        }
                    }
                }
            }
        });
    }


    /**
     * @author Mike van Es
     */
    @Override
    public void updateView(){}

    /**
     * @author Mike van Es
     */
    @Override
    public Scene getScene() {
        return this.scene;
    };
}
