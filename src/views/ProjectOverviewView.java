package views;

import controllers.ProjectController;
import controllers.TripController;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import models.ProjectModel;
import models.TripModel;

/**
 * @author Mike van Es
 */
public class ProjectOverviewView implements View {
    private ProjectController projectController;
    private Scene scene;


    /**
     * @author Mike van Es
     */
    public ProjectOverviewView() {
        this.projectController = new ProjectController();

        this.setScene(this.createView());
        this.scene.getStylesheets().add("/resources/application.css");
        this.scene.getStylesheets().add("/resources/project.css");

    }

    /**
     * @author Mike van Es
     */
    @Override
    public Scene createView(){
        Pane rootPane = new Pane();

        rootPane.getChildren().addAll(createProjectOverviewPane());
        rootPane.setId("mainBackground");
        Scene scene = new Scene(rootPane,1920, 1080);

        return scene;
    };


    /**
     * @author Mike van Es
     * @return ProjectOverviewPane
     */
    private Pane createProjectOverviewPane() {
        Pane ProjectOverviewPane = new Pane();

        ProjectOverviewPane.setMinSize(1345, 650);
        ProjectOverviewPane.setTranslateX(450);
        ProjectOverviewPane.setTranslateY(200);
        //Add this for the correct panel styling
        ProjectOverviewPane.setId("mainPanelBg");

        Label headerLabel = new Label("Projecten");

        headerLabel.setFont(Font.font(24));
        headerLabel.setTranslateX(50);
        headerLabel.setTranslateY(25);

        ProjectOverviewPane.getChildren().addAll(headerLabel, ProjectOverviewTablePane());

        return ProjectOverviewPane;
    }


    /**
     * @author Mike van Es
     * @return ProjectOverviewTablePane
     */
    private TableView ProjectOverviewTablePane() {
        TableView tableView = new TableView();

        TableColumn<String, ProjectModel> column1 = new TableColumn<>("Project naam");
        column1.setCellValueFactory(new PropertyValueFactory<>("projectName"));

        TableColumn<String, ProjectModel> column2 = new TableColumn<>("Aantal ritten");
        column2.setCellValueFactory(new PropertyValueFactory<>("totalTrips"));

        TableColumn<String, ProjectModel> column3 = new TableColumn<>("Gereden kilometers");
        column3.setCellValueFactory(new PropertyValueFactory<>("totalKilometers"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);

        for (int i = 0; i < projectController.getProjects().size(); i++){
            tableView.getItems().add(projectController.getProjects().get(i));
            System.out.println(projectController.getProjects().get(i).getTotalTrips());
        }

        tableView.setMinSize(1245, 450);
        tableView.setTranslateX(50);
        tableView.setTranslateY(100);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableView;
    }

    /**
     * @author Mike van Es
     * @return startLocationPane
     */
    private Pane projectNamePane() {
        Pane projectNamePane = new Pane();


        projectNamePane.setMinSize(203, 450);
        projectNamePane.setTranslateX(0);
        projectNamePane.setTranslateY(0);

        projectNamePane.getChildren().addAll(projectOverviewTableHeaderLabels("Project naam"));


        return projectNamePane;
    }

    /**
     * @author Mike van Es
     * @return totalTripsPane
     */
    private Pane totalTripsPane() {
        Pane totalTripsPane = new Pane();

        totalTripsPane.setMinSize(203, 450);
//		endLocationPane.setBorder(blackBorder);
        totalTripsPane.setTranslateX(203);
        totalTripsPane.setTranslateY(0);

        totalTripsPane.getChildren().addAll(projectOverviewTableHeaderLabels("Aantal ritten"));

        return totalTripsPane;
    }

    /**
     * @author Mike van Es
     * @return totalDrivenKilometersPane
     */
    private Pane totalDrivenKilometersPane() {
        Pane totalDrivenKilometersPane = new Pane();

        totalDrivenKilometersPane.setMinSize(203, 450);
//		licenseplatePane.setBorder(blackBorder);
        totalDrivenKilometersPane.setTranslateX(609);
        totalDrivenKilometersPane.setTranslateY(0);

        totalDrivenKilometersPane.getChildren().addAll(projectOverviewTableHeaderLabels("Gereden kilomneters"));

        return totalDrivenKilometersPane;
    }

    /**
     * @author Oussama Fahchouch
     * @return headerLabel
     */
    private Label projectOverviewTableHeaderLabels(String labelContent) {
        Label headerLabel = new Label(labelContent);

        headerLabel.setFont(Font.font(18));
        headerLabel.setTranslateX(5);
        headerLabel.setTranslateY(0);

        return headerLabel;
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
    public void setScene(Scene sceneToSet) {
        this.scene = sceneToSet;
    }

    /**
     * @author Oussama Fahchouch
     */
    @Override
    public Scene getScene() {
        return this.scene;
    };
}
