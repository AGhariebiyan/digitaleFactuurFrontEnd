package views;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class LoginView implements View{

    private Scene scene;
    private String css;

    public LoginView(){
        this.css = this.getClass().getResource("/css/login.css").toExternalForm();
//        this.scene = createView();
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

        // Logo
        VBox vBoxLogo = new VBox();
        vBoxLogo.setAlignment(Pos.CENTER);
        vBoxLogo.setId("logo");

        // FORMULIER
        VBox vBoxForm = new VBox(username, password, btnLogin, btnRegistreren);
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

    @Override
    public void updateView() {

    }

    @Override
    public void setScene(Scene sceneToSet) {

    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

}
