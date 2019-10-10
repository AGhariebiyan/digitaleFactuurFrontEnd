package views;

import controllers.TripController;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import models.TripModel;

/**
 * @author Oussama Fahchouch
 */
public class TripOverviewView implements View {
	private TripModel tripModel;
	private TripController tripController;
	private Scene scene;
	private Border blackBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
	
	/**
	 * @author Oussama Fahchouch
	 */
	public TripOverviewView() {
		this.setScene(this.createView());
	}

	/**
	 * @author Oussama Fahchouch
	 */
	@Override
	public Scene createView(){
		Pane rootPane = new Pane();
		
		rootPane.getChildren().addAll(createTripsOverviewPane());	
		
		Scene scene = new Scene(rootPane,1920, 1080);
		
		return scene;
	};
	
	
	/**
	 * @author Oussama Fahchouch
	 * @return tripsOverviewPane
	 */
	private Pane createTripsOverviewPane() {
		Pane tripsOverviewPane = new Pane();
		
		tripsOverviewPane.setMinSize(1345, 650);
		tripsOverviewPane.setBorder(blackBorder);
		tripsOverviewPane.setTranslateX(450);
		tripsOverviewPane.setTranslateY(200);
		
		Label headerLabel = new Label("Trips");	
		
		headerLabel.setFont(Font.font(24));
		headerLabel.setTranslateX(50);
		headerLabel.setTranslateY(25);
		
		tripsOverviewPane.getChildren().addAll(headerLabel, addTripButtonPane(), tripOverviewTablePane());
		
		return tripsOverviewPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return addTripButtonPane
	 */
	private Pane addTripButtonPane() {
		Pane addTripButtonPane = new Pane();
		
		addTripButtonPane.setMinSize(75, 75);
		addTripButtonPane.setBorder(blackBorder);
		addTripButtonPane.setTranslateX(1295);
		addTripButtonPane.setTranslateY(-37.50);
		
		return addTripButtonPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return addTripButtonPane
	 */
	private Pane tripOverviewTablePane() {
		Pane tripOverviewTablePane = new Pane();
		
		tripOverviewTablePane.setMinSize(1245, 450);
		tripOverviewTablePane.setBorder(blackBorder);
		tripOverviewTablePane.setTranslateX(50);
		tripOverviewTablePane.setTranslateY(100);
		
		tripOverviewTablePane.getChildren().addAll(startLocationPane(), endLocationPane(), drivenKilometersPane(), licenseplatePane(), projectPane(), deleteButtonPane());
		
		return tripOverviewTablePane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return startLocationPane
	 */
	private Pane startLocationPane() {
		Pane startLocationPane = new Pane();

		
		startLocationPane.setMinSize(203, 450);
//		startLocationPane.setBorder(blackBorder);
		startLocationPane.setTranslateX(0);
		startLocationPane.setTranslateY(0);
		
		startLocationPane.getChildren().addAll(tripOverviewTableHeaderLabels("start location"));

		
		return startLocationPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return endLocationPane
	 */
	private Pane endLocationPane() {
		Pane endLocationPane = new Pane();
		
		endLocationPane.setMinSize(203, 450);
//		endLocationPane.setBorder(blackBorder);
		endLocationPane.setTranslateX(203);
		endLocationPane.setTranslateY(0);
		
		endLocationPane.getChildren().addAll(tripOverviewTableHeaderLabels("end location"));

		return endLocationPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return ridenKilometersPane
	 */
	private Pane drivenKilometersPane() {
		Pane drivenKilometersPane = new Pane();
		
		drivenKilometersPane.setMinSize(203, 450);
//		drivenKilometersPane.setBorder(blackBorder);
		drivenKilometersPane.setTranslateX(406);
		drivenKilometersPane.setTranslateY(0);
		
		drivenKilometersPane.getChildren().addAll(tripOverviewTableHeaderLabels("driven kilometers"));
		
		return drivenKilometersPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return licenseplatePane
	 */
	private Pane licenseplatePane() {
		Pane licenseplatePane = new Pane();
		
		licenseplatePane.setMinSize(203, 450);
//		licenseplatePane.setBorder(blackBorder);
		licenseplatePane.setTranslateX(609);
		licenseplatePane.setTranslateY(0);

		licenseplatePane.getChildren().addAll(tripOverviewTableHeaderLabels("licenseplate"));
		
		return licenseplatePane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return projectPane
	 */
	private Pane projectPane() {
		Pane projectPane = new Pane();
		
		projectPane.setMinSize(203, 450);
//		projectPane.setBorder(blackBorder);
		projectPane.setTranslateX(812);
		projectPane.setTranslateY(0);
		
		projectPane.getChildren().addAll(tripOverviewTableHeaderLabels("project number"));
		
		return projectPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return deleteButtonPane
	 */
	private Pane deleteButtonPane() {
		Pane deleteButtonPane = new Pane();
		
		deleteButtonPane.setMinSize(203, 450);
//		deleteButtonPane.setBorder(blackBorder);
		deleteButtonPane.setTranslateX(1040);
		deleteButtonPane.setTranslateY(0);

		deleteButtonPane.getChildren().addAll(tripOverviewTableHeaderLabels("delete"));

		return deleteButtonPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return headerLabel
	 */
	private Label tripOverviewTableHeaderLabels(String labelContent) {
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
