package company;

import java.util.HashMap;
import java.util.Map;

import lida.Lida;
import spark.Request;
import spark.Response;

public class CompanyController {

	public static String createCompanyForm(Request req, Response res) {
		
		// Create HashMap for template
		Map<String, Object> map = new HashMap<String, Object>();
		
		return Lida.render(map, "companiesForm.mustache");
	}

	public static String createCompany(Request req, Response res) {
			
		// Create HashMap for template
		String name = req.queryParams("company-name");
		String website = req.queryParams("company-website");
			
		CompanyDAO.createCompany(name, website);
		
		res.redirect("/sec/applications");
		return null;
	}

}
