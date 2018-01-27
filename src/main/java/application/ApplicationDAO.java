package application;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import company.Company;
import lida.DB;
import user.User;
import user.UserDAO;

public class ApplicationDAO extends BasicDAO<Application, ObjectId> {
	
	private static Datastore datastore = DB.getDB().getDatastore();

	private ApplicationDAO() {
		super(Application.class, datastore);
	}

	public static User findUserApplications(String userId) {
		
		User user = UserDAO.findUserById(userId);
		
		Query<Application> query = datastore
				.find(Application.class)
				.field("user")
				.equal(new Key<User>(User.class, userId, null));
	
		// TODO populate companies
		List<Application> applications = new ArrayList<Application>(query.asList()); 

		// TODO remove fake data
		Company google = new Company("google", "https://www.google.com");
		applications.add(new Application("Senior web developer", google, "San Francisco", "CA"));
		
		Company facebook = new Company("facebook", "https://www.facebook.com");
		applications.add(new Application("Java developer", facebook, "Palo Alto", "CA"));
		
		user.setApplications(applications);
		
		return user;
	}
	

	public static Application createApplication(String title, String description, String source, User user, Company company) {
		
		Application application = new Application(title, description, source, user, company);
		
		return application;
	}
	

}