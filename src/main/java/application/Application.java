package application;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import company.Company;
import user.User;

@Entity("applications")
@Indexes(
    @Index(fields = @Field("user"))
)
public class Application {
	
	@Id
	private ObjectId id;
	
	@Reference
	private User user;
	
	@Reference
	private Company company;
	
	private String title;
	private String description;
	private String source;
	private LocalDate creationDate;
	private String state;
	private String city;
	
	public Application () {}
	

	public Application(ObjectId id, User user, Company company, String title, String description, String source, LocalDate creationDate, String state,
			String city) {
		super();
		this.id = id;
		this.user = user;
		this.company = company;
		this.title = title;
		this.description = description;
		this.source = source;
		this.creationDate = creationDate;
		this.state = state;
		this.city = city;
	}

	public Application(String title, Company company, String city, String state) {
		this.title = title;
		this.company = company;
		this.city = city;
		this.state = state;
	}


	public Application(String title, String description, String source, User user, Company company) {
		this.title = title;
		this.description = description;
		this.company = company;
		this.source = source;
		this.user = user;
	}


	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	
	

}
