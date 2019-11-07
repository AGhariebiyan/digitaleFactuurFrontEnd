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

    public ArrayList<TripModel> getTrips(){
        try {
            return projectModel.getTrips();
        }catch(java.lang.NullPointerException e){
            return null;
        }


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
