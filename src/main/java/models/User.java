package models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

@Entity("users")
@Indexes(
    @Index(fields = @Field("name"))
)
public class User {
	
	@Id
	private ObjectId id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private String password;
	
	// Protected Constructors
	
	User() {}
    
    User(ObjectId id, String firstName, String lastName, String email, String phone, String address,
			String password) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.password = password;
	}

	User(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	// Getters and Setters

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ObjectId getSomeId() { 
        return id; 
    } 
    
    public void setSomeId(ObjectId someId) { 
        this.id = someId; 
    } 

}
