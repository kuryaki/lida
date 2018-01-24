package lida;

import static spark.Spark.*;

import controllers.UserController;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {
	public static void main(String[] args) {
		
		exception(Exception.class, (e, req, res) -> e.printStackTrace());
		
		port(8000);
		
		staticFiles.location("/static");
        
        get("/", (req, res) -> UserController.loginAndRegisterScreen(), new MustacheTemplateEngine());
        
        post("/register", (req, res) -> UserController.register(req, res), new MustacheTemplateEngine());
        post("/login", (req, res) -> UserController.login(req, res), new MustacheTemplateEngine());
    }
	
}
