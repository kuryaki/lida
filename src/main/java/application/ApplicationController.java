package application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import company.Company;
import company.CompanyDAO;
import lida.Lida;
import spark.Request;
import spark.Response;
import user.User;
import user.UserDAO;

public class ApplicationController {
	
	public static String getJobApplications(Request req, Response res) {
		
		// Session Data
		String userId = req.session().attribute("userId");
		
		// Create HashMap for template
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = UserDAO.findUserById(userId);
		List<Application> applications = ApplicationDAO.findJobApplicationsByUser(user);
		
		map.put("user", user);
		map.put("applications", applications);
		
		String result = Lida.render(map, "dashboard.mustache");
		
		return result;
		
		
	}

	public static String createJobApplicationForm(Request req, Response res) {
		
		// Create HashMap for template
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Company> companies = CompanyDAO.getCompanies();
		
		map.put("companies", companies);
		
		return Lida.render(map, "applicationsForm.mustache");
	}

	public static String createJobApplication(Request req, Response res) {
		
		// Session Data
		String userId = req.session().attribute("userId");
		
		String title = req.queryParams("application-title");
		String description = req.queryParams("application-description");
		String companyName = req.queryParams("application-company");
		String city = req.queryParams("application-city");
		String state = req.queryParams("application-state");
		String source = req.queryParams("application-source");
		
		
		User user = UserDAO.findUserById(userId);
		Company company = CompanyDAO.findCompanyByName(companyName);
		ApplicationDAO.createApplication(title, description, source, city, state, user, company);
		
		res.redirect("/sec/dashboard");
		return null;
	}

	public static String findJobApplicationById(Request req, Response res) {
		
		String applicationId = req.params(":applicationId");
		Application application = ApplicationDAO.findJobApplicationById(applicationId);
		
		// Create HashMap for template
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("application", application);
		
		return Lida.render(map, "applicationsDescribe.mustache");
	}

}
