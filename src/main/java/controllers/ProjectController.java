package controllers;

//import com.sun.deploy.net.HttpResponse;

import javafx.scene.layout.Pane;
import models.ProjectModel;
import models.TripModel;

import java.util.ArrayList;

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
        String JWTstring = AppController.getInstance().createJWT("DFjwtProjcets", "getProjects", 0);
        AppController.getInstance().httpRequest("http://localhost:8085/project/getProject?jwtToken=Hoi%20Daan&projectId=50", "GET");





        //Loop through all the project, for each project create a new model and save it in the projectModelArraylist and for each trip in the project create a new tripmodel and save it in the ArrayList.
        TripModel trip1 = new TripModel();
        trip1.setStartKilometergauge(0);
        trip1.setEndKilometergauge(50);
        TripModel trip2 = new TripModel();
        trip2.setStartKilometergauge(50);
        trip2.setEndKilometergauge(100);

        ArrayList<TripModel> tripsPerProject = new ArrayList<>();
        tripsPerProject.add(trip1);
        tripsPerProject.add(trip2);

        ProjectModel p1 = new ProjectModel(22, "project1", tripsPerProject);
        ProjectModel p2 = new ProjectModel(23, "project2", tripsPerProject);

        projectModel.add(p1);
        projectModel.add(p2);

    }

    public ArrayList<ProjectModel> getProjects(){
        return projectModel;
    }
}
