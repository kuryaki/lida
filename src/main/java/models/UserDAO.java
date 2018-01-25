package models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import lida.DB;

public class UserDAO extends BasicDAO<User, ObjectId> {
	
	private static Datastore datastore = DB.getDB().getDatastore();

	private UserDAO() {
		super(User.class, datastore);
	}
	
	public static User createUser(String firstName, String lastName, String email, String password) {
		
		User newUser = new User(firstName, lastName, email, password);
		Query<User> query = datastore.createQuery(User.class)
				.field("email").equal(email)
				.field("password").equal(password);
		
		datastore.save(newUser);
		
		User found = query.get();
		
		return found;
	}
	
	public static User findUserByEmailAndPassword(String email, String password) {
		
		Query<User> query = datastore.createQuery(User.class)
				.field("email").equal(email)
				.field("password").equal(password);
		
		User found = query.get();
		
		return found;
	}

	public static User findUserById(String userId) {
		Query<User> query = datastore.createQuery(User.class)
				.field("id").equal(new ObjectId(userId));
		return query.get();
	}

	public static User findUserByEmail(String email) {
		
		Query<User> query = datastore.createQuery(User.class)
				.field("email").equal(email);
		
		User found = query.get();
		
		return found;
	}

}
