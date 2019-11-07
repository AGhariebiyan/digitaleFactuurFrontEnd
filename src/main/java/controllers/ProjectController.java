package controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import javafx.scene.layout.Pane;
import models.ProjectModel;
import models.TripModel;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;


public class ProjectController {
    private ArrayList<ProjectModel> projectModel = new ArrayList();

    public ProjectController(){
        // Onload make the controller get the current projects per user / this function is now filled with example code.
        this.fetchProjectsFromBackEnd();
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


    /**
     * Fetches the projects from the back-end and parses the JSON string
     */
    private void fetchProjectsFromBackEnd(){
        //Make a call to the API to fetch all the projects / example projects are shown below.
        InputStream projectStream = AppController.getInstance().httpRequest("http://localhost:8080/project/getAllProject", "GET");
        try {
            String result = IOUtils.toString(projectStream, StandardCharsets.UTF_8);
            if(result.contains("[")) {
                Gson gson = new Gson();
                Type jsonObject = new TypeToken<Collection<JsonObject>>() {
                }.getType();
                Collection<JsonObject> projectColl = gson.fromJson(result, jsonObject);

                //Loop through the json objects
                for (JsonObject projects : projectColl) {
                    // Remove any excess ""
                    String projectName = projects.get("name").toString().replaceAll("^\"|\"$", "");
                    ProjectModel tmpModel = new ProjectModel(Integer.parseInt(projects.get("id").toString()), projectName);

                    Type tripObject = new TypeToken<Collection<TripModel>>() {
                    }.getType();
                    //For each trip create a new tripmodel and add it to the collection
                    Collection<TripModel> tripColl = gson.fromJson(projects.get("trips").toString(), tripObject);

                    //Add the trips to the model
                    for (TripModel trips : tripColl) {
                        tmpModel.addTrip(trips);
                    }
                    projectModel.add(tmpModel);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<ProjectModel> getProjects(){
        return projectModel;
    }
}
