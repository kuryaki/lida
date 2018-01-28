package followup;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import contact.Contact;
import lida.DB;
import user.User;

public class FollowupDAO extends BasicDAO<Followup, ObjectId> {

	private static Datastore datastore = DB.getDB().getDatastore();

	private FollowupDAO() {
		super(Followup.class, datastore);
	}

	public static Followup createFollowUp(Contact contact, FollowupType type, LocalDate dueDate) {
		
		Followup followup = new Followup(contact, type, dueDate);

		datastore.save(followup);

		return followup;
	}
}
