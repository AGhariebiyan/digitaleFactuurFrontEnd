package views;

import controllers.ProjectController;
import javafx.beans.binding.Bindings;
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
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import models.ProjectModel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

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
        this.projectController.fetchProjectsFromBackEnd();
    }

    /**
     * @author Mike van Es
     * @return Scene
     */
    @Override
    public Scene createView(){
        Pane rootPane = new Pane();

        rootPane.getChildren().addAll(this.projectController.getMenuPane(), this.projectController.getHeaderPane(), createProjectOverviewPane() );
        rootPane.setId("mainBackground");
        Scene scene = new Scene(rootPane,(1920/1.5), (1080/1.5));
        this.scene = scene;
        this.scene.getStylesheets().add( this.getClass().getResource("/css/application.css").toExternalForm() );
//        this.scene.getStylesheets().add( this.getClass().getResource("/css/project.css").toExternalForm() );
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

    /**
     * Adds the search field so we can search the table
     * @return TextField
     */
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

        TableColumn<Integer, ProjectModel> column1 = new TableColumn<>("Project id");
        column1.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        column1.setId("getProjectId");

        TableColumn<String, ProjectModel> column2 = new TableColumn<>("Project naam");
        column2.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        column2.setId("getProjectName");

        TableColumn<String, ProjectModel> column3 = new TableColumn<>("Aantal ritten");
        column3.setCellValueFactory(new PropertyValueFactory<>("totalTrips"));
        column3.setId("getTotalTrips");

        TableColumn<Double, ProjectModel> column4 = new TableColumn<>("Gereden kilometers");
        column4.setCellValueFactory(new PropertyValueFactory<>("totalKilometers"));
        column4.setId("getTotalKilometers");

        tableView.setPlaceholder(new Label("Geen projecten gevonden"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);


        Map<Integer, ProjectModel> projectModelMap = projectController.getProjects();
        for (ProjectModel pm : projectModelMap.values()){
            tableView.getItems().add(pm);
            this.searchData.add(pm);
        }

        tableView.setMinSize((1245/1.5), (450/1.5));
        tableView.setTranslateX((50/1.5));
        tableView.setTranslateY((100/1.5));
        tableView.setFixedCellSize(25);
        tableView.prefHeightProperty().bind(tableView.fixedCellSizeProperty().multiply(Bindings.size(tableView.getItems()).add(1.03)));
        tableView.minHeightProperty().bind(tableView.prefHeightProperty());
        tableView.maxHeightProperty().bind(tableView.prefHeightProperty());

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        addSearchFilter(tableView);

        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue == null) {
                        return;
                    }

                    if(((ProjectModel) newValue).getTotalTrips() == 0) {
                        AlertView alert = new AlertView();
                        alert.alert("Er zijn geen trips voor dit project");
                        return;
                    }else {
                        this.projectController.loadProjectView(((ProjectModel) newValue).getProjectId());
                    }
                }
        );



        return tableView;
    }

    /**
     * @author Mike van Es
     * @Param: TableView tableviewObject
     * Function to filter the previous made table. Works with anytable aslong as the tableview is passed and the tablecolumns has the correct ids's,
     * these should be the name of your get function in the model
     */
    private void addSearchFilter(TableView tableView){
        //Wrap the ObservableList in a FilteredList.
        FilteredList<?> filteredData = new FilteredList<>(this.searchData, p -> true);
        //Set the filter Predicate whenever the filter changes.
        this.searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(model -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                //Compare table columns with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                //get the class object of the observable object.
                Class classObj = model.getClass();
                // Retrieve a list of methods of the given model
                Method[] methods = classObj.getDeclaredMethods();
                // Retrieve a list of of the tableview columns
                ObservableList<?> columns = tableView.getColumns();

                Boolean foundResult = false;

                for (int parentIndex = 0; parentIndex < columns.size(); parentIndex++) {
                    for (int childIndex = 0; childIndex < methods.length; childIndex++) {
                        if (methods[childIndex].toString().contains("get")) {
                            if( ((TableColumn) columns.get(parentIndex)).getId().equals(methods[childIndex].getName())){
                                try {
                                    Method m = classObj.getMethod(methods[childIndex].getName());
                                    Object result = m.invoke(model);
                                    if (result.toString().toLowerCase().contains(lowerCaseFilter)) {
                                        foundResult = true;
                                    }
                                } catch (NoSuchMethodException e) {
                                    e.printStackTrace();
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                } catch (InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
                return foundResult; // Does not match.
            });
        });

        //Wrap the FilteredList in a SortedList.
        SortedList<?> sortedData = new SortedList<>(filteredData);

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
    public Scene getScene() {
        return this.scene;
    };
}
