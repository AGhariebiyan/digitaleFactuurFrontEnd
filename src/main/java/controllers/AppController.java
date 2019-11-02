package controllers;


import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Key;
import java.util.Date;
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

	public static String createJWT(String issuer, String subject, long ttlMillis) {

		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("79554e2460ae336c07c3eb0208adbb4cc4af184c17b51e0a2373cc0f9bba87b5");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		@SuppressWarnings("deprecation") JwtBuilder builder = Jwts.builder()
				.setIssuedAt(now)
				.setSubject(subject)
				.setIssuer(issuer)
				.signWith(signatureAlgorithm, signingKey);
		//Let's set the JWT Claims

		//if it has been specified, let's add the expiration
		if (ttlMillis > 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
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
			System.out.println(requestType);
			//The request type, POST, GET etc.
			con.setRequestMethod(requestType);
			con.setRequestProperty("Content-Type", "application/json");

			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);

			// Debug
			// String contentType  = con.getRequestMethod();
			// System.out.println("Debug");
			// System.out.println(contentType);

			//The response wich we recieve e.g. 200, 400, 404
			int status = con.getResponseCode();

			// Example code to read new the results
			// Example code to read new the results
			// Example code to read new the results
			//if(status == 200) {
				// Example code to read new the results
				//BufferedReader in = new BufferedReader(
				//		new InputStreamReader(con.getInputStream()));
				//String inputLine;
				//StringBuffer content = new StringBuffer();
				//while ((inputLine = in.readLine()) != null) {
				//	System.out.println(inputLine);
				//	content.append(inputLine);
				//}

				// Close reader
				//in.close();
				// Close connection
				con.disconnect();

				return con.getInputStream();
			//}else{
				// Close connection
			//	con.disconnect();
			//	return null;
			//}

		} catch (IOException e) {

			e.printStackTrace();
		}

		return null;
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