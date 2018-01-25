package controllers;

import java.util.HashMap;
import java.util.Map;

import models.User;
import models.UserDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UserController {
	
	public static ModelAndView login(Request req, Response res) {
		
		// Obtain request parameters
		String email = req.queryParams("login-email");
		String password = req.queryParams("login-password");
		
		// Use DAO to get data from DB
		User user = UserDAO.findUserByEmailAndPassword(email, password);
		
		// Create HashMap for template
		Map<String, String> map = new HashMap<String, String>();
		
		if (user != null) { // Authenticated
				       
	        // Create Session
	        req.session().attribute("userId", user.getId().toString());
			
	        // Redirect to Dashboard
	        res.redirect("/dashboard");
	        return null;
			
		} else { // Bad Credentials
			
			// Render Template
			map.put("error", "Bad Credentials");
			return new ModelAndView(map, "home.html");
		}
	}
	
	public static ModelAndView register(Request req, Response res) {
		
		// Obtain request parameters
		String firstName = req.queryParams("register-firstname");
		String lastName = req.queryParams("register-lastname");
		String email = req.queryParams("register-email");
		String password = req.queryParams("register-password");
		
		// Create HashMap for template
		Map<String, String> map = new HashMap<String, String>();
		
		// Use DAO to get data from DB
		User existingUser = UserDAO.findUserByEmail(email);
		
		if (existingUser != null) { // existing
			// Render Template
			map.put("error", "User already created");
			return new ModelAndView(map, "home.html");
		}
		
		// Use DAO to insert data into DB
		User newUser = UserDAO.createUser(firstName, lastName, email, password);
		
        // Create Session
        req.session().attribute("userId", newUser.getId().toString());
		
        // Redirect to Dashboard
        res.redirect("/dashboard");
        return null;
	}
	
	public static ModelAndView loginAndRegisterScreen() {
		
		Map<String, String> map = new HashMap<String, String>();
		
		return new ModelAndView(map, "home.html");
	}

	public static ModelAndView logout(Request req, Response res) {
    		
		Map<String, String> map = new HashMap<String, String>();
		
		req.session().removeAttribute("userId");
		
		return new ModelAndView(map, "home.html");
	}
}
