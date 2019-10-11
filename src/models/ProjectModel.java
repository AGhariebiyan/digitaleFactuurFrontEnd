package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Mike van Es
 */
public class ProjectModel {
    /**
     * @author Mike van Es
     * TODO: ADD VEHICLE MODEL, THIS MODEL DID NOT EXIST YET, FIX POSSIBLE POINTER EXCEPTIONS
     */

    private int id;
    private String name;
    private ArrayList<TripModel> tripModel;


    // Empty constructor.
    public ProjectModel(int id, String name, ArrayList<TripModel> trip){
        this.id = id;
        this.name = name;
        this.tripModel = trip;
    }

    public void addProject(int id, String name, ArrayList<TripModel> trip){


        this.id = id;
        this.name = name;
        this.tripModel = trip;


    }

    public String getProjectName(){
        return this.name;
    }

    public double getTotalKilometers(){
        double totalKilometers = 0;
        for (int i = 0; i < tripModel.size(); i++) {
            totalKilometers = totalKilometers + tripModel.get(i).getEndKilometergauge() - tripModel.get(i).getStartKilometergauge();
        }

        return totalKilometers;
    }

    public int getTotalTrips(){
        return tripModel.size();
    }




}
