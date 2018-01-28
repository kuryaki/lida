package followup;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import contact.Contact;

@Entity("followups")
public class Followup {

	@Id
	ObjectId id;
	FollowupType type;
	LocalDate date;
	
	@Reference
    private Contact contact;
	
	public Followup() {
	}

	public Followup(ObjectId id, FollowupType type, LocalDate date, Contact contact) {
		super();
		this.id = id;
		this.type = type;
		this.date = date;
		this.contact = contact;
	}

	// Getters and Setters

	public Followup(Contact contact, FollowupType type, LocalDate dueDate) {
		this.contact = contact;
		this.type = type;
		this.date = dueDate;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public FollowupType getType() {
		return type;
	}

	public void setType(FollowupType type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
