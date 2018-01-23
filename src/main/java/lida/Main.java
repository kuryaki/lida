package lida;

import static spark.Spark.*;

import controllers.UserController;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {
	public static void main(String[] args) {
		
		exception(Exception.class, (e, req, res) -> e.printStackTrace());
		
		port(8000);
		
		staticFiles.location("/static");
        
        get("/hello", (req, res) -> UserController.register(), new MustacheTemplateEngine());
    }
	
}
