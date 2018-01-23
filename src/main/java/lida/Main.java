package lida;

import static spark.Spark.*;

import controllers.UserController;
import spark.template.mustache.MustacheTemplateEngine;

public class Main {
	public static void main(String[] args) {
		
		exception(Exception.class, (e, req, res) -> e.printStackTrace());
		port(getHerokuAssignedPort());
		staticFiles.location("/static");
        
        get("/hello", (req, res) -> UserController.register(), new MustacheTemplateEngine());
    }
	
	static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
