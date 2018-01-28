package followup;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import application.Application;
import application.ApplicationDAO;
import company.Company;
import company.CompanyDAO;
import contact.Contact;
import contact.ContactDAO;
import lida.Lida;
import spark.Request;
import spark.Response;
import user.User;
import user.UserDAO;

public class FollowupController {

	public static String createFollowUpForm(Request req, Response res) {
		
		// isAuthenticated
		String userId = req.session().attribute("userId");
		if (userId == null) {
			res.redirect("/");
			return null;
		}
		
		String applicationId = req.params(":applicationId");
		Application application = ApplicationDAO.findJobApplicationById(applicationId);
		
		// Create HashMap for template
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact(new ObjectId(), "Behailu", "6411231234", "behailu@gmail.com"));
		contacts.add(new Contact(new ObjectId(), "David", "6411231234", "david@gmail.com"));
		
		map.put("contacts", contacts);
		map.put("application", application);
		
		return Lida.render(map, "followups.html");
	}

	public static String createFollowUp(Request req, Response res) {
		
		// isAuthenticated
		String userId = req.session().attribute("userId");
		if (userId == null) {
			res.redirect("/");
			return null;
		}
		
		String applicationId = req.params(":applicationId");
		Application application = ApplicationDAO.findJobApplicationById(applicationId);
		
		// Create HashMap for template
		String contactId = req.queryParams("followup-contact");
		String type = req.queryParams("followup-type");
		String date = req.queryParams("followup-date");
		
		
		Contact contact = ContactDAO.findById(contactId);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy h:m a");
		LocalDate dueDate = LocalDate.parse(date, formatter);
		
		Followup followup = FollowupDAO.createFollowUp(contact, FollowupType.valueOf(type), dueDate);
		
		application.getFollowups().add(followup);
		application.setLastFollowup(followup);
		
		ApplicationDAO.update(application);
		
		res.redirect("/dashboard");
		return null;
	}

}
