package controllers;

import views.View;

/**
 * @author Oussama Fahchouch
 */
public class AppController {
	private static AppController appController = null;
	
	/**
	 * @author Oussama Fahchouch
	 * @return AppController
	 */
	public static AppController getInstance() {
		if (appController == null) { appController = new AppController(); }
        return appController; 
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param view
	 */
	public static void loadView(View view){};
}
