package contact;

import application.Application;
import application.ApplicationDAO;
import spark.Request;
import spark.Response;

public class ContactController {

	public static String createContact(Request req, Response res) {
		// isAuthenticated
		String userId = req.session().attribute("userId");
		if (userId == null) {
			res.redirect("/");
			return null;
		}
		
		// Create HashMap for template
		String applicationId = req.params(":applicationId");
		
		String name = req.queryParams("contact-name");
		String phone = req.queryParams("contact-phone");
		String email = req.queryParams("contact-email");
		
		Contact contact = ContactDAO.createContact(name, phone, email);
		
		Application application = ApplicationDAO.findJobApplicationById(applicationId);
		
		application.getContacts().add(contact);
		
		ApplicationDAO.update(application);
		
		res.redirect("/followups/"+applicationId);
		return null;
	}

}
