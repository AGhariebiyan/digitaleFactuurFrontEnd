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
		this.scene = createView();
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
		
		tripsOverviewPane.getChildren().addAll(headerLabel, addTripButtonPane());
		
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
	public Scene getScene() {
		return this.scene;
	};
}
