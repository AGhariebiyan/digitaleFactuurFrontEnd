package controllers;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import javafx.scene.layout.Pane;
import models.ProjectModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
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

    private void fetchProjectsFromBackEnd(){
        //Make a call to the API to fetch all the projects / example projects are shown below.
        InputStream projectStream = AppController.getInstance().httpRequest("http://localhost:8080/project/getAllProject", "GET");

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(projectStream));
            String inputLine = in.readLine();
            in.close();
            System.out.println(inputLine);
            Gson gson = new Gson();
            Type collectionType = new TypeToken<Collection<ProjectModel>>(){}.getType();
            Collection<ProjectModel> enums = gson.fromJson(inputLine, collectionType);
            for (ProjectModel projects : enums) {
                System.out.println(projects.getProjectName());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }



        //Loop through all the project, for each project create a new model and save it in the projectModelArraylist and for each trip in the project create a new tripmodel and save it in the ArrayList.
//        TripModel trip1 = new TripModel();
//        trip1.setStartKilometergauge(0);
//        trip1.setEndKilometergauge(50);
//        TripModel trip2 = new TripModel();
//        trip2.setStartKilometergauge(50);
//        trip2.setEndKilometergauge(100);
//
//        ArrayList<TripModel> tripsPerProject = new ArrayList<>();
//        tripsPerProject.add(trip1);
//        tripsPerProject.add(trip2);
//
//        ProjectModel p1 = new ProjectModel(22, "project1", tripsPerProject);
//        ProjectModel p2 = new ProjectModel(23, "project2", tripsPerProject);
//
//        projectModel.add(p1);
//        projectModel.add(p2);

    }

    public ArrayList<ProjectModel> getProjects(){
        return projectModel;
    }
}
