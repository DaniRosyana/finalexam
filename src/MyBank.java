import java.math.BigDecimal;
import java.util.Date;

import id.web.skyforce.bank.model.Account;
import id.web.skyforce.bank.model.Address;
import id.web.skyforce.bank.model.Customer;
import id.web.skyforce.bank.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MyBank {
	public static void main(String[] args) {

		Session session = HibernateUtil.openSession();
		Transaction trx = session.beginTransaction();

		Customer customer = new Customer();
		customer.setFirstName("Bill");
		customer.setLastName("Gates");
		customer.setGender('M');
		customer.setBirthDate(new Date());
		customer.setIdNumber("1234 5678");

		try {

			Account active = new Account();
			active.setAccountNo("XYZ123");
			active.setBalance(new BigDecimal(40000000000D));
			active.setStatus('A');

			active.setCustomer(customer);

			session.save(active);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Account inactive = new Account();
			inactive.setAccountNo("XYZ456");
			inactive.setBalance(new BigDecimal(20000000D));
			inactive.setStatus('I');

			inactive.setCustomer(customer);
			session.save(inactive);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Address address = new Address();
			address.setStreet("Silicon Valley No.13");
			address.setCity("San Francisco");
			address.setPostalCode("94207");
			session.save(address);
		} catch (Exception e) {
			e.printStackTrace();
		}

		trx.commit();
		session.close();

	}
}
