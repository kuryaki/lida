package company;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

@Entity("companies")
@Indexes(
    @Index(fields = @Field("name"))
)
public class Company {
	
	@Id
	private ObjectId id;
	private String name;
	private String website;
	
	public Company () {}

	public Company(ObjectId id, String name, String website) {
		super();
		this.id = id;
		this.name = name;
		this.website = website;
	}

	public Company(String name, String website) {
		this.name = name;
		this.website = website;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
	
	

}
