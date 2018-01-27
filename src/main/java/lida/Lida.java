package lida;

import static spark.Spark.*;

import java.util.Map;

import application.ApplicationController;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import user.UserController;

public class Lida {
	public static void main(String[] args) {
		
		exception(Exception.class, (e, req, res) -> e.printStackTrace());
		
		port(8000);
		
		staticFiles.location("/static");
        
		// Unauthenticated routes
        get("/", (req, res) -> UserController.loginAndRegisterScreen());
        get("/register", (req, res) -> UserController.loginAndRegisterScreen());
        get("/login", (req, res) -> UserController.loginAndRegisterScreen());
        
        post("/register", (req, res) -> UserController.register(req, res));
        post("/login", (req, res) -> UserController.login(req, res));
        
        // Authenticated routes
        get("/logout", (req, res) -> UserController.logout(req, res));
        get("/dashboard", (req, res) -> ApplicationController.getJobApplications(req, res));
        
        
    }

	public static String render(Map<String, Object> map, String template) {
		return new MustacheTemplateEngine().render(new ModelAndView(map, template));
	}
	
}
