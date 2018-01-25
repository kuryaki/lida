package controllers;

import java.util.HashMap;
import java.util.Map;

import models.User;
import models.UserDAO;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class DashboardController {

	public static ModelAndView getJobApplications(Request req, Response res) {
		
		String userId = req.session().attribute("userId");
		
		// Create HashMap for template
		Map<String, String> map = new HashMap<String, String>();
		
		if (userId != null) {
			User user = UserDAO.findUserById(userId);
			
			if (user != null) {
				// Render Template
				map.put("name", user.getFirstName());
				return new ModelAndView(map, "dashboard.html");
				
			}
		}
		
		res.redirect("/");
		return null;
		
	}

}
