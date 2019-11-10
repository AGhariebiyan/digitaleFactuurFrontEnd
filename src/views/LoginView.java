package views;

import java.awt.Desktop;
import java.net.URL;

import controllers.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * The login view is compiled in this view. A login.css file is used for the design
 *
 * @author Ali Rezaa Ghariebiyan
 * @version 08-11-2019
 */
public class LoginView implements View{

    private String css;
    private UserController userController;
    private Scene scene;

    // css class voor de vormgeving
    public LoginView(){
        this.css = this.getClass().getResource("/css/login.css").toExternalForm();
        this.userController = new UserController();
    }
    
    /**
     * @author Ali Rezaa Ghariebiyan
     */
    @Override
    public Scene createView(){
        TextField username = new TextField();
        username.setPromptText("Gebruikersnaam");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        Button btnLogin = new Button("Login");
        btnLogin.setId("btnLogin");
        Hyperlink btnRegistreren = new Hyperlink();
        btnRegistreren.setText("Geen account? Registreer nu!");
        Text melding = new Text("");
        
//        btnRegistreren.setOnAction(e -> { 
//        	try {
//        	    Desktop.getDesktop().browse(new URL("https://www.digitalefactuur.nl/prijzen--aanmelden#/").toURI());
//        	} catch (Exception excep) {System.out.println(excep);}
//        	
//        });

        // On Click invoke the getCredentials method with the user and password
        btnLogin.setOnAction(event -> {
            if (!getCredentials(username.getText().toLowerCase(), password.getText().toLowerCase()) == true)
            {
                melding.setFill(Color.DARKRED);
                melding.setText("username of password is wrong!");
            }
        });

//        btnLogin.setOnAction(e -> getCredentials(username.getText().toLowerCase(), password.getText().toLowerCase()));

        // Logo
        VBox vBoxLogo = new VBox();
        vBoxLogo.setAlignment(Pos.CENTER);
        vBoxLogo.setId("logo");

        // FORMULIER
        VBox vBoxForm = new VBox(username, password, btnLogin, btnRegistreren, melding);
        vBoxForm.setAlignment(Pos.CENTER);
        vBoxForm.setId("login_form");
        vBoxForm.setSpacing(10);

        VBox form = new VBox(vBoxLogo, vBoxForm);
        form.setId("form");
        form.setSpacing(25);
        VBox.setMargin(vBoxLogo, new Insets(0,0,0,150));

        BorderPane rootPane = new BorderPane();
        rootPane.setId("login_root");
        rootPane.setCenter(form);

        Scene scene = new Scene(rootPane, 1280, 720);
        scene.getStylesheets().addAll(css);

        return scene;
    }

    /**
     * the authorize method in the UserController is called. If successful, the method returns true.
     *
     * @author Ali Rezaa Ghariebiyan
     * @version 08-11-2019
     */
    public boolean getCredentials(String user, String passwd){
        if (userController.authorize(user, passwd)){
            UserController.appController.getInstance().loadView("views.DashboardView", "createView");
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * @author Ali Rezaa Ghariebiyan
     */
    @Override
    public void updateView() {

    }

    /**
     * @author Ali Rezaa Ghariebiyan
     */
    @Override
    public Scene getScene() {
        return this.scene;
    }

}
