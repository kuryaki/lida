package company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import application.ApplicationDAO;
import lida.Lida;
import spark.Request;
import spark.Response;
import user.User;
import user.UserDAO;

public class CompanyController {

	public static List<Company> createTestData() {
		
		List<Company> companies = new ArrayList<Company>();
		
		companies.add(CompanyDAO.createCompany("Google", "https://www.google.com"));
		companies.add(CompanyDAO.createCompany("Facebook", "https://www.facebook.com"));
		companies.add(CompanyDAO.createCompany("Amazon", "https://www.amazon.com"));
		
		return companies;
	}

	public static String createCompanyForm(Request req, Response res) {
		
		// isAuthenticated
//		String userId = req.session().attribute("userId");
//		if (userId == null) {
//			res.redirect("/");
//			return null;
//		}
		
		// Create HashMap for template
		Map<String, Object> map = new HashMap<String, Object>();
		
		return Lida.render(map, "companies.html");
	}

	public static String createCompany(Request req, Response res) {
		
		// isAuthenticated
//		String userId = req.session().attribute("userId");
//		if (userId == null) {
//			res.redirect("/");
//			return null;
//		}
		
		// Create HashMap for template
		String name = req.queryParams("company-name");
		String website = req.queryParams("company-website");
		
		
		CompanyDAO.createCompany(name, website);
		
		res.redirect("/applications");
		return null;
	}

}
