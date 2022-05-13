package ElectronicComponentsShopMVC.Service.User;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ElectronicComponentsShopMVC.Dao.UserDao;
import ElectronicComponentsShopMVC.Entity.Customer;
import ElectronicComponentsShopMVC.Entity.Role;
import ElectronicComponentsShopMVC.Entity.Users;

@Service
@Transactional
public class RegisterCustomerService {
	@Autowired
	UserDao userDao = new UserDao();

	@Transactional
	public boolean addCustomer(String email, String password, String name, String sdt, boolean gender) {
		Users user = new Users();
		Customer customer = new Customer();
		int role_id = 3;
		Role role = userDao.getRoleID(role_id);
		if (role == null) {
			return false;
		} else {
			user.setUsers_username(email);
			user.setUsers_password(BCrypt.hashpw(password, BCrypt.gensalt(12))); // mã hóa password
			user.setRole(role);
			customer.setKhachhang_name(name);
			customer.setKhachhang_sdt(sdt);
			customer.setKhachhang_gioitinh(gender);
			customer.setUser(user);
			user.setCustomer(customer);
		}
		return userDao.addCustomer(user, customer);
	}
}
