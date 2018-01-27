package company;

import java.util.ArrayList;
import java.util.List;

public class CompanyController {

	public static List<Company> createTestData() {
		
		List<Company> companies = new ArrayList<Company>();
		
		companies.add(CompanyDAO.createCompany("Google", "https://www.google.com"));
		companies.add(CompanyDAO.createCompany("Facebook", "https://www.facebook.com"));
		companies.add(CompanyDAO.createCompany("Amazon", "https://www.amazon.com"));
		
		return companies;
	}

}
