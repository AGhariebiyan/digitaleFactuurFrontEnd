package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LoginView implements View{

    private Scene scene;
    private String css;

    public LoginView(){
        this.css = this.getClass().getResource("/css/login.css").toExternalForm();
        this.scene = createView();
    }

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
//        btnRegistreren.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//
//            }
//        });
        // FORMULIER
        VBox vBox = new VBox(username, password, btnLogin, btnRegistreren);
        vBox.setId("login_form");
        vBox.setSpacing(10);

        BorderPane rootPane = new BorderPane();
        rootPane.setId("login_root");
        rootPane.setCenter(vBox);

        Scene scene = new Scene(rootPane, 1280, 720);
        scene.getStylesheets().addAll(css);

        return scene;
    }

    @Override
    public void updateView() {

    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

}
