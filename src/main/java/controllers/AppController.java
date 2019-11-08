package controllers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.UserModel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * @author Mike van Es
 * @author Oussama Fahchouch
 * Singelton for getting the appcontoller in any place, so we can switch scenes.
 */
public class AppController extends ClassLoader {
	public static AppController appController;
	private Stage primaryStage;
	private Pane headerPane;
	private Pane menuPane;
	private UserModel currentUser;

	/**
	 * @author Mike van Es
	 */
	public static AppController getInstance() {
		if (appController == null) {
			appController = new AppController();
		}
		return appController;
	}

    /**
     * @author Mike van Es
     * Creates a new isntance of a given class and calls the function given in the parameters
     * @param1: String of the class name
     * @param2: String of the method name
     *
     */
	public static void loadView(String classBinName, String methodName){
		try {
			// Create a new JavaClassLoader
			ClassLoader classLoader = appController.getClass().getClassLoader();

			// Load the target class using its binary name
			Class loadedMyClass = classLoader.loadClass(classBinName);

			// Create a new instance from the loaded class
			Constructor constructor = loadedMyClass.getConstructor();
			Object myClassObject = constructor.newInstance();

			// Getting the target method from the loaded class and invoke it using its name
			Method method = loadedMyClass.getMethod(methodName);
			Object scene = method.invoke(myClassObject);

			appController.primaryStage.setScene((Scene) scene );
			appController.primaryStage.show();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Simple method to create a HTTP request
	 * @author Mike van Es
	 * @param url
	 * @return InpputStream
	 */

	public static InputStream httpRequest(String url, String requestType){
		try {

			//The URL wich we are going to send to.
			URL requestUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) requestUrl.openConnection();
			//The request type, POST, GET etc.
			con.setRequestMethod(requestType);
			con.setRequestProperty("Content-Type", "application/json");

			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);


			return con.getInputStream();

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
	}

	public void setCurrentUser(UserModel currentUser) {
		this.currentUser = currentUser;
	}

	public UserModel getCurrentUser() {
		return currentUser;
	}

	/**
	 * @author Oussama Fahchouch
	 * @param primaryStage
	 */
	public void setPrimaryStage(Stage primaryStage) {
		appController.primaryStage = primaryStage;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return headerPane
	 */
	public Pane getHeaderPane() {
		return headerPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	public void setHeaderPane(Pane headerPane) {
		this.headerPane = headerPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return headerPane
	 */
	public Pane getMenuPane() {
		return menuPane;
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	public void setMenuPane(Pane menuPane) {
		this.menuPane = menuPane;
	}
}