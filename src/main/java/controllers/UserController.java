package controllers;

import java.util.HashMap;
import java.util.Map;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import lida.DB;
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
		User found = UserDAO.findUserByEmailAndPassword(email, password);
		
		// Create HashMap for template
		Map<String, String> map = new HashMap<String, String>();
        map.put("name", found.getFirstName());
		
        // Render Template
		return new ModelAndView(map, "dashboard.html");
	}
	
	public static ModelAndView register(Request req, Response res) {
		
		// Obtain request parameters
		String firstName = req.queryParams("register-firstname");
		String lastName = req.queryParams("register-lastname");
		String email = req.queryParams("register-email");
		String password = req.queryParams("register-password");
		
		// Use DAO to insert data into DB
		User newUser = UserDAO.createUser(firstName, lastName, email, password);
		
		// Create HashMap for template
		Map<String, String> map = new HashMap<String, String>();
        map.put("name", newUser.getFirstName());
		
        // Render Template
		return new ModelAndView(map, "dashboard.html");
	}
	
	public static ModelAndView loginAndRegisterScreen() {
		
		Map<String, String> map = new HashMap<String, String>();
		
		return new ModelAndView(map, "home.html");
	}
}
