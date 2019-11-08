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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class ProjectController {
    private Map<Integer, ProjectModel> projectModel = new HashMap<>();
    private int selectedProject;
    private AppController appController = AppController.getInstance();

    public ProjectController(){
        // Onload make the controller get the current projects per user / this function is now filled with example code.
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
    public void fetchProjectsFromBackEnd(){
        //Make a call to the API to fetch all the projects / example projects are shown below.
        InputStream projectStream = appController.httpRequest("http://localhost:8080/project/getAllProject", "GET");
        try {
            String result = IOUtils.toString(projectStream, StandardCharsets.UTF_8);
            if(result.contains("[")) {
                Gson gson = new Gson();
                Type jsonObject = new TypeToken<Collection<JsonObject>>(){}.getType();
                Collection<JsonObject> projectColl = gson.fromJson(result, jsonObject);

                //Loop through the json objects
                for (JsonObject projects : projectColl) {
                    // Remove any excess ""
                    String projectName = projects.get("name").toString().replaceAll("^\"|\"$", "");
                    int projectId = Integer.parseInt(projects.get("id").toString());
                    ProjectModel tmpModel = new ProjectModel(projectId, projectName);

                    Type tripObject = new TypeToken<Collection<TripModel>>() {}.getType();
                    //For each trip create a new tripmodel and add it to the collection
                    Collection<TripModel> tripColl = gson.fromJson(projects.get("trips").toString(), tripObject);

                    //Add the trips to the model
                    for (TripModel trips : tripColl) {
                        tmpModel.addTrip(trips);
                    }
                    projectModel.put(projectId, tmpModel);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadProjectView(int pid){
        this.selectedProject = pid;
        Gson gson = new Gson();
        String projectString = gson.toJson(projectModel.get(pid));

        String url = "http://localhost:8080/project/setProject?project="+projectString;
        if(url.contains(" "))
            url = url.replace(" ", "%20");

        appController.httpRequest(url, "POST");
        appController.loadView("views.ProjectView", "createView");
    }


    public Map<Integer, ProjectModel> getProjects(){
        return projectModel;
    }

    public ProjectModel returnSelectedProject(){
        return projectModel.get(this.selectedProject);
    }
}
