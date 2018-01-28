package contact;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import lida.DB;

public class ContactDAO extends BasicDAO<Contact, ObjectId> {
	
	private static Datastore datastore = DB.getDB().getDatastore();

	private ContactDAO() {
		super(Contact.class, datastore);
	}

	public static Contact findById(String contactId) {
		return new Contact(new ObjectId(), "Behailu", "6411231234", "behailu@gmail.com");
	}

}
