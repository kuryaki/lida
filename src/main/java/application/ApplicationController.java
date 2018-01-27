package application;

import java.util.HashMap;
import java.util.Map;

import lida.Lida;
import spark.Request;
import spark.Response;
import user.User;

public class ApplicationController {
	
	public static String getJobApplications(Request req, Response res) {
		
		// Create HashMap for template
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = req.session().attribute("userId");
		
		if (userId != null) {
			
			User user = ApplicationDAO.findUserApplications(userId);
			
			if (user != null) {
				// Render Template
				map.put("user", user);
				
				return Lida.render(map, "dashboard.html");
			}
		}
		
		res.redirect("/");
		return null;
	}

//	private static Map<String, Object> mapUserApplications(List<Application> applications) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		
//		applications.forEach(application -> {
//		});
//		return map;
//	}

	
}
