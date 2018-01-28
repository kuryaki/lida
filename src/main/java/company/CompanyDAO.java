package company;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import lida.DB;

public class CompanyDAO extends BasicDAO<Company, ObjectId> {
	
	private static Datastore datastore = DB.getDB().getDatastore();

	private CompanyDAO() {
		super(Company.class, datastore);
	}

	public static Company createCompany(String name, String website) {

		Company company = new Company(name, website);
		
		datastore.save(company);
		
		return company;
	}
	
	public static Company findCompanyByName(String name) {
		
		Query<Company> query = datastore.createQuery(Company.class)
				.field("name").equal(name);
		
		return query.get();
	}

}
