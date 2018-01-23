package controllers;

import java.util.HashMap;
import java.util.Map;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import lida.DB;
import models.User;
import spark.ModelAndView;

public class UserController {
	
	private static Datastore datastore = DB.getDB().getDatastore();
	
	public static ModelAndView register() {
	
		Query<User> query = datastore.createQuery(User.class).field("firstName").equal("behailu");
		User behailu = query.get();
		
		Map<String, String> map = new HashMap<String, String>();
        map.put("name", behailu.getFirstName());
		
		return new ModelAndView(map, "hello.html");
	}
	
	public static ModelAndView login() {
		
		final User behailu = new User();
		behailu.setFirstName("behailu");
		datastore.save(behailu);
		
		
		Map<String, String> map = new HashMap<String, String>();
        map.put("name", behailu.getFirstName());
		
		return new ModelAndView(map, "hello.html");
	}
}
