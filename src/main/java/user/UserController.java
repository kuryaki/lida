package user;

import java.util.HashMap;
import java.util.Map;

import lida.Lida;
import spark.Request;
import spark.Response;

public class UserController {
	
	public static String login(Request req, Response res) {
		
		// Obtain request parameters
		String email = req.queryParams("login-email");
		String password = req.queryParams("login-password");
		
		// Use DAO to get data from DB
		User user = UserDAO.findUserByEmailAndPassword(email, password);
		
		// Create HashMap for template
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (user != null) { // Authenticated
				       
	        // Create Session
	        req.session().attribute("userId", user.getId().toString());
			
	        // Redirect to Dashboard
	        res.redirect("/dashboard");
	        return null;
			
		} else { // Bad Credentials
			
			// Render Template
			map.put("error", "Bad Credentials");
			return Lida.render(map, "home.html");
		}
	}
	
	public static String register(Request req, Response res) {
		
		// Obtain request parameters
		String firstName = req.queryParams("register-firstname");
		String lastName = req.queryParams("register-lastname");
		String email = req.queryParams("register-email");
		String password = req.queryParams("register-password");
		
		// Create HashMap for template
		Map<String, Object> map = new HashMap<String, Object>();
		
		// Use DAO to get data from DB
		User existingUser = UserDAO.findUserByEmail(email);
		
		if (existingUser != null) { // existing
			// Render Template
			map.put("error", "User already created");
			return Lida.render(map, "home.html");
		}
		
		// Use DAO to insert data into DB
		User user = UserDAO.createUser(firstName, lastName, email, password);
		
        // Create Session
        req.session().attribute("userId", user.getId().toString());
		
        // Redirect to Dashboard
        res.redirect("/dashboard");
        return null;
	}
	
	public static String loginAndRegisterScreen() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		return Lida.render(map, "home.html");
	}

	public static String logout(Request req, Response res) {
    		
		req.session().removeAttribute("userId");
		res.redirect("/");
		
		return null;
	}

	public static User createTestData(Request req, Response res) {
		
		// Obtain request parameters
		String email = req.queryParams("login-email");
		String password = req.queryParams("login-password");
		
		// Use DAO to get data from DB
		User user = UserDAO.findUserByEmailAndPassword(email, password);
		
		return user;
	}
}
