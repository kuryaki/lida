package application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lida.Lida;
import spark.Request;
import spark.Response;
import user.User;
import user.UserDAO;

public class ApplicationController {
	
	public static String getJobApplications(Request req, Response res) {
		
		// Create HashMap for template
		Map<String, Object> map = new HashMap<String, Object>();
		String userId = req.session().attribute("userId");
		
		if (userId != null) {
			
			User user = UserDAO.findUserById(userId);
			List<Application> applications = ApplicationDAO.findUserApplicationsByUserId(userId);
			
			map.put("user", user);
			map.put("applications", applications);
			
			String result = Lida.render(map, "dashboard.html");
			
			return result;
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
