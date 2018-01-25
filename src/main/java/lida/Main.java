package lida;

import static spark.Spark.*;

import controllers.DashboardController;
import controllers.UserController;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {
	public static void main(String[] args) {
		
		exception(Exception.class, (e, req, res) -> e.printStackTrace());
		
		port(8000);
		
		staticFiles.location("/static");
        
		// Unauthenticated routes
        get("/", (req, res) -> UserController.loginAndRegisterScreen(), new MustacheTemplateEngine());
        get("/register", (req, res) -> UserController.loginAndRegisterScreen(), new MustacheTemplateEngine());
        get("/login", (req, res) -> UserController.loginAndRegisterScreen(), new MustacheTemplateEngine());
        
        post("/register", (req, res) -> UserController.register(req, res), new MustacheTemplateEngine());
        post("/login", (req, res) -> UserController.login(req, res), new MustacheTemplateEngine());
        
        // Authenticated routes
        get("/logout", (req, res) -> UserController.logout(req, res), new MustacheTemplateEngine());
        get("/dashboard", (req, res) -> DashboardController.getJobApplications(req, res), new MustacheTemplateEngine());
    }
	
}
