package contact;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import lida.DB;
import user.User;

public class ContactDAO extends BasicDAO<Contact, ObjectId> {
	
	private static Datastore datastore = DB.getDB().getDatastore();

	private ContactDAO() {
		super(Contact.class, datastore);
	}

	public static Contact findById(String contactId) {
		
		Query<Contact> query = datastore.createQuery(Contact.class).field("id").equal(new ObjectId(contactId));

		return query.get();
	}

	public static Contact createContact(String name, String phone, String email) {
		
		Contact contact = new Contact(name, phone, email);
		
		datastore.save(contact);
		
		return contact;
	}

}
