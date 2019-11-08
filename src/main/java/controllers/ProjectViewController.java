package controllers;

import com.google.gson.Gson;
import javafx.scene.layout.Pane;
import models.ProjectModel;
import models.TripModel;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ProjectViewController {
    private ProjectController projectController = new ProjectController();
    private AppController appController = AppController.getInstance();
    private ProjectModel projectModel;

    /**
     * Controller for the project insight view
     */
    public ProjectViewController(){
        InputStream request = appController.httpRequest("http://localhost:8080/project/getProject", "GET");
        try {
            String result = IOUtils.toString(request, StandardCharsets.UTF_8);
            Gson gson = new Gson();
            this.projectModel = gson.fromJson(result, ProjectModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the amount of trips on a given project trips
     * @return ArrayList of TripsModel
     */
    public ArrayList<TripModel> getTrips(){
        try {
            return projectModel.getTrips();
        }catch(java.lang.NullPointerException e){
            return null;
        }


    }

    /**
     * Returns the string of the projectname
     * @return String of projectname
     */
    public String getProjectName(){
        return projectModel.getProjectName();
    }

    /**
     * @author Oussama Fahchouch
     * @return headerPane
     */
    public Pane getHeaderPane() {
        return AppController.getInstance().getHeaderPane();
    }

    /**
     * @author Oussama Fahchouch
     * @return headerPane
     */
    public Pane getMenuPane() {
        return AppController.getInstance().getMenuPane();
    }

}
