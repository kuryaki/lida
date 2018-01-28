package application;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import company.Company;
import lida.DB;
import user.User;

public class ApplicationDAO extends BasicDAO<Application, ObjectId> {
	
	private static Datastore datastore = DB.getDB().getDatastore();

	private ApplicationDAO() {
		super(Application.class, datastore);
	}

	public static List<Application> findJobApplicationsByUser(User user) {
		
		Query<Application> query = datastore.createQuery(Application.class).filter("user", user);
	
		// TODO populate companies
		List<Application> applications = query.asList(); 
		
		return applications;
	}
	

	public static Application createApplication(String title, String description, String source, User user, Company company) {
		
		Application application = new Application(title, description, source, user, company);
		
		return application;
	}

	public static void createApplication(String title, String description, String source, String city, String state,
			User user, Company company) {
		
		Application application = new Application(title, description, source, city, state, user, company);
		
		datastore.save(application);
	}

}
