package controllers;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author Mike van Es
 * @author Oussama Fahchouch
 */
public class AppController extends ClassLoader {
	public static AppController appController;
	private Stage primaryStage;
	private Pane headerPane;
	private Pane menuPane;

	/**
	 * @author Oussama Fahchouch
	 */
	public static AppController getInstance() {
		if (appController == null) {
			appController = new AppController();
		}
		return appController;
	}
	
	/**
	 * @author Mike van Es
	 * @param: view
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