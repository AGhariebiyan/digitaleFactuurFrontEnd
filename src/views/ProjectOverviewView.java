package views;

import controllers.ProjectController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import models.ProjectModel;

/**
 * @author Mike van Es
 */
public class ProjectOverviewView implements View {
    private ProjectController projectController;
    private Scene scene;
    private ObservableList<ProjectModel> searchData = FXCollections.observableArrayList();
    private TextField searchTextField;


    /**
     * @author Mike van Es
     */
    public ProjectOverviewView() {
        this.projectController = new ProjectController();
    }

    /**
     * @author Mike van Es
     * @return
     */
    @Override
    public Scene createView(){
        Pane rootPane = new Pane();

        rootPane.getChildren().addAll(createProjectOverviewPane());
        rootPane.setId("mainBackground");
        Scene scene = new Scene(rootPane,(1920/1.5), (1080/1.5));
        this.scene = scene;
        this.scene.getStylesheets().add("/resources/application.css");
        this.scene.getStylesheets().add("/resources/project.css");
        return scene;
    };


    /**
     * @author Mike van Es
     * @return ProjectOverviewPane
     */
    private Pane createProjectOverviewPane() {
        Pane ProjectOverviewPane = new Pane();

        ProjectOverviewPane.setMinSize((1345/1.5), (750/1.5));
        ProjectOverviewPane.setTranslateX((450/1.5));
        ProjectOverviewPane.setTranslateY((200/1.5));
        //Add this for the correct panel styling
        ProjectOverviewPane.setId("mainPanelBg");

        Label headerLabel = new Label("Projecten");

        headerLabel.setFont(Font.font(24));
        headerLabel.setTranslateX((50/1.5));
        headerLabel.setTranslateY((25/1.5));

        ProjectOverviewPane.getChildren().addAll(headerLabel,  tableSearchField(), ProjectOverviewTablePane());

        return ProjectOverviewPane;
    }

    private TextField tableSearchField(){
        this.searchTextField = new TextField ();
        this.searchTextField.setPromptText("Zoeken...");
        this.searchTextField.setFocusTraversable(false);
        this.searchTextField.setTranslateX((1024/1.5));
        this.searchTextField.setTranslateY((35/1.5));

        return searchTextField;
    }

    /**
     * @author Mike van Es
     * @return ProjectOverviewTablePane
     */
    private TableView ProjectOverviewTablePane() {
        TableView tableView = new TableView();

        TableColumn<String, ProjectModel> column1 = new TableColumn<>("Project id");
        column1.setCellValueFactory(new PropertyValueFactory<>("projectId"));

        TableColumn<String, ProjectModel> column2 = new TableColumn<>("Project naam");
        column2.setCellValueFactory(new PropertyValueFactory<>("projectName"));

        TableColumn<String, ProjectModel> column3 = new TableColumn<>("Aantal ritten");
        column3.setCellValueFactory(new PropertyValueFactory<>("totalTrips"));

        TableColumn<String, ProjectModel> column4 = new TableColumn<>("Gereden kilometers");
        column4.setCellValueFactory(new PropertyValueFactory<>("totalKilometers"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);

        for (int i = 0; i < projectController.getProjects().size(); i++){
            tableView.getItems().add(projectController.getProjects().get(i));
            this.searchData.add(projectController.getProjects().get(i));
        }

        tableView.setMinSize((1245/1.5), (450/1.5));
        tableView.setTranslateX((50/1.5));
        tableView.setTranslateY((100/1.5));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        addSearchFilter(tableView);
        return tableView;
    }

    /**
     * @author Mike van Es
     * Function to filter the previous made table
     */
    private void addSearchFilter(TableView tableView){
        //Wrap the ObservableList in a FilteredList.
        FilteredList<ProjectModel> filteredData = new FilteredList<>(this.searchData, p -> true);

        //Set the filter Predicate whenever the filter changes.
        this.searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(project -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                //Compare table columns with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (Integer.toString(project.getProjectId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (project.getProjectName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (Integer.toString(project.getTotalTrips()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else if (Double.toString(project.getTotalKilometers()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false; // Does not match.
            });
        });

        //Wrap the FilteredList in a SortedList.
        SortedList<ProjectModel> sortedData = new SortedList<>(filteredData);

        //Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());

        //Add sorted (and filtered) data to the table.
        tableView.setItems(sortedData);
    }

    /**
     * @author Mike van Es
     * @return startLocationPane
     */
    private Pane projectNamePane() {
        Pane projectNamePane = new Pane();


        projectNamePane.setMinSize((203/1.5), (450/1.5));
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

        totalTripsPane.setMinSize((203/1.5), (450/1.5));
//		endLocationPane.setBorder(blackBorder);
        totalTripsPane.setTranslateX((203/1.5));
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

        totalDrivenKilometersPane.setMinSize((203/1.5), (450/1.5));
//		licenseplatePane.setBorder(blackBorder);
        totalDrivenKilometersPane.setTranslateX((609/1.5));
        totalDrivenKilometersPane.setTranslateY(0);

        totalDrivenKilometersPane.getChildren().addAll(projectOverviewTableHeaderLabels("Gereden kilomneters"));

        return totalDrivenKilometersPane;
    }

    /**
     * @author Mike van Es
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
