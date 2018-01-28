package lida;

import static spark.Spark.*;

import java.util.Map;

import application.ApplicationController;
import company.CompanyController;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import user.UserController;

public class Lida {
	public static void main(String[] args) {
		
		exception(Exception.class, (e, req, res) -> e.printStackTrace());
		
		port(8000);
		
		staticFiles.location("/static");
        
		// Home
        get("/", (req, res) -> UserController.loginAndRegisterScreen());
        
        // Register
        get("/register", (req, res) -> UserController.loginAndRegisterScreen());
        post("/register", (req, res) -> UserController.register(req, res));
        
        // Login
        get("/login", (req, res) -> UserController.loginAndRegisterScreen());
        post("/login", (req, res) -> UserController.login(req, res));
        
        /// Logout
        get("/logout", (req, res) -> UserController.logout(req, res));
        
        // Applications
        get("/dashboard", (req, res) -> ApplicationController.getJobApplications(req, res));
        get("/applications", (req, res) -> ApplicationController.createJobApplicationForm(req, res));
        post("/applications", (req, res) -> ApplicationController.createJobApplication(req, res));
        
        // Companies
        get("/companies", (req, res) -> CompanyController.createCompanyForm(req, res));
        post("/companies", (req, res) -> CompanyController.createCompany(req, res));
        
    }

	public static String render(Map<String, Object> map, String template) {
		return new MustacheTemplateEngine().render(new ModelAndView(map, template));
	}
	
}
