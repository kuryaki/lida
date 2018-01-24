package controllers;

import java.util.HashMap;
import java.util.Map;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import lida.DB;
import models.User;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UserController {
	
	private static Datastore datastore = DB.getDB().getDatastore();
	
	public static ModelAndView login(Request req, Response res) {
	
		Query<User> query = datastore.createQuery(User.class).field("email").equal(req.queryParams("login-email"));
		User user = query.get();
		
		Map<String, String> map = new HashMap<String, String>();
        map.put("name", user.getFirstName());
		
		return new ModelAndView(map, "hello.html");
	}
	
	public static ModelAndView register(Request req, Response res) {
		
		User newUser = new User();
		newUser.setFirstName(req.queryParams("register-firstname"));
		newUser.setLastName(req.queryParams("register-lastname"));
		newUser.setEmail(req.queryParams("register-email"));
		newUser.setPassword(req.queryParams("register-password"));
		
		datastore.save(newUser);
		
		Map<String, String> map = new HashMap<String, String>();
        map.put("name", newUser.getFirstName());
		
		return new ModelAndView(map, "hello.html");
	}
	
	public static ModelAndView loginAndRegisterScreen() {
		
		Map<String, String> map = new HashMap<String, String>();
		
		return new ModelAndView(map, "home.html");
	}
}
