package controllers;


import javafx.scene.Scene;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import views.AddTripView;

/**
 * @author Oussama Fahchouch
 */
public class AppController extends ClassLoader {
	public static AppController appController;
	private Stage primaryStage;


	public static AppController getInstance() {
		if (appController == null) {
			appController = new AppController();
		}
		return appController;
	}
	/**
	 * @author Oussama Fahchouch
	 * @param: view
	 */
	public static void loadView(String classBinName, String methodName){
		try {

//			System.out.println(appController);
			// Create a new JavaClassLoader
			ClassLoader classLoader = appController.getClass().getClassLoader();

			// Load the target class using its binary name
			Class loadedMyClass = classLoader.loadClass(classBinName);

//			System.out.println("Loaded class name: " + loadedMyClass.getName());

			// Create a new instance from the loaded class
			Constructor constructor = loadedMyClass.getConstructor();
			Object myClassObject = constructor.newInstance();

			// Getting the target method from the loaded class and invoke it using its name
			Method method = loadedMyClass.getMethod(methodName);
//			System.out.println("Invoked method name: " + method.getName());
			Object scene = method.invoke(myClassObject);

//			System.out.println("Loading new scene" + scene + " "+ appController.primaryStage);
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
	 * @return
	 */


	/**
	 * @author Oussama Fahchouch
	 * @param primaryStage
	 */
	public void setPrimaryStage(Stage primaryStage) {
		appController.primaryStage = primaryStage;
	};
}
