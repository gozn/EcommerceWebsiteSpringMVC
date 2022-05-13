package ElectronicComponentsShopMVC.Service.Admin;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ElectronicComponentsShopMVC.Dao.CategoryDao;
import ElectronicComponentsShopMVC.Dao.ProductDao;
import ElectronicComponentsShopMVC.Dao.UserDao;
import ElectronicComponentsShopMVC.Entity.Customer;
import ElectronicComponentsShopMVC.Entity.Emloyee;
import ElectronicComponentsShopMVC.Entity.Role;
import ElectronicComponentsShopMVC.Entity.Users;
import ElectronicComponentsShopMVC.Service.Algorithm.AES256;

@Service
@Transactional
public class AdminService {
	@Autowired
	UserDao userDao;
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	ProductDao productDao;

	public int getCountAllProduct() {
		return productDao.getcountAllProduct();
	}

	public int getCountAllCategory() {
		return categoryDao.getcountAllCategory();
	}

	public int getCountAllUser() {
		return userDao.getcountAllUser();
	}

	public int getCountAllCustomer() {
		return userDao.getcountAllCustomer();
	}

	public int getCountAllEmloyee() {
		return userDao.getcountAllEmloyee();
	}

	public List<Users> GetAllUserCus() {
		return userDao.getAllAccountCus();
	}
	
	public List<Users> GetLatestUserCus() {
		return userDao.getLatestAccountCus();
	}

	public List<Users> GetAllUserEml() {
		return userDao.getAllAccountEml();
	}
	
	public List<Users> GetLatestUserEml() {
		return userDao.getLatestAccountEml();
	}

	public List<Role> GetAllRole() {
		return userDao.getAllRole();
	}

	public Users checkdEdit(int userId) {

		return userDao.getUserByID(userId);
	}

	public boolean changePassword(Users user) {
		Users u = userDao.getUserByUserName(user.getUsers_username());
		Customer customer = new Customer();
		if (u.getUsers_password() != user.getUsers_password()) { // kiểm tra thay đổi của password
			user.setUsers_password(BCrypt.hashpw(user.getUsers_password(), BCrypt.gensalt(12))); // mã hóa password
		}
		return userDao.updateUserCus(user, customer);
	}
	
	public boolean updateUserCus(Users user) {
		Users u = userDao.getUserByUserName(user.getUsers_username());
		Customer customer = new Customer();
		if (u.getUsers_password() != user.getUsers_password()) { // kiểm tra thay đổi của password
			user.setUsers_password(BCrypt.hashpw(user.getUsers_password(), BCrypt.gensalt(12))); // mã hóa password
		}
		user.getCustomer().setUser(user);
		user.setRole(user.getRole());
		customer.setKhachhang_id(user.getUsers_id());
		customer.setKhachhang_name(user.getCustomer().getKhachhang_name());
		customer.setKhachhang_sdt(user.getCustomer().getKhachhang_sdt());
		customer.setKhachhang_gioitinh(user.getCustomer().isKhachhang_gioitinh());
		customer.setUser(user);
		return userDao.updateUserCus(user, customer);
	}
	
	
	public boolean updateUserCus2(Users user) {
		Users u = userDao.getUserByUserName(user.getUsers_username());;
		Customer customer = new Customer();
		 System.out.println(u.getUsers_password()); //pass ban dau`
		if (u.getUsers_password() != user.getUsers_password()) { // kiểm tra thay đổi của password
			user.setUsers_password(u.getUsers_password()); // mã hóa password
		}	

		user.getCustomer().setUser(user);
		user.setRole(user.getRole());
		customer.setKhachhang_id(user.getUsers_id());
		customer.setKhachhang_name(user.getCustomer().getKhachhang_name());
		customer.setKhachhang_sdt(user.getCustomer().getKhachhang_sdt());
		customer.setKhachhang_gioitinh(user.getCustomer().isKhachhang_gioitinh());
		customer.setKhachhang_anh(user.getCustomer().getKhachhang_anh());
		customer.setUser(user);
		return userDao.updateUserCus(user, customer);
	}

	public boolean deleteUserCus(Users user) {
		Customer customer = new Customer();
		customer.setKhachhang_id(user.getUsers_id());
		customer.setKhachhang_name(user.getCustomer().getKhachhang_name());
		customer.setKhachhang_sdt(user.getCustomer().getKhachhang_sdt());
		customer.setKhachhang_gioitinh(user.getCustomer().isKhachhang_gioitinh());
		customer.setUser(user);
		return userDao.deleteUserCus(user, customer);
	}

	@Transactional
	public boolean addCustomer(Users user) {
		String password = user.getUsers_password();
		Customer customer = new Customer();
		int role_id = 3;
		Role role = userDao.getRoleID(role_id);
		if (role == null) {
			return false;
		} else {
			user.setUsers_password(BCrypt.hashpw(password, BCrypt.gensalt(12))); // mã hóa password
			user.setRole(role);
			customer.setKhachhang_name(user.getCustomer().getKhachhang_name());
			customer.setKhachhang_sdt(user.getCustomer().getKhachhang_sdt());
			customer.setKhachhang_gioitinh(user.getCustomer().isKhachhang_gioitinh());
			customer.setUser(user);
			user.setCustomer(customer);
		}
		return userDao.addCustomer(user, customer);
	}

	public boolean updateUserIml(Users user) {
		Users u = userDao.getUserByUserName(user.getUsers_username());
		Emloyee emloyee = new Emloyee();
		if (u.getUsers_password() != user.getUsers_password()) { // kiểm tra thay đổi của password
			user.setUsers_password(BCrypt.hashpw(user.getUsers_password(), BCrypt.gensalt(12))); // mã hóa password
		}
		user.getEmloyee().setUsernv(user);
		user.setRole(user.getRole());
		emloyee.setNhanvien_id(user.getUsers_id());
		emloyee.setNhanvien_name(user.getEmloyee().getNhanvien_name());
		emloyee.setNhanvien_sdt(user.getEmloyee().getNhanvien_sdt());
		emloyee.setNhanvien_gioitinh(user.getEmloyee().isNhanvien_gioitinh());
		String luong = AES256.encrypt2(user.getEmloyee().getNhanvien_luong(), "123");
		emloyee.setNhanvien_luong(luong);
		emloyee.setUsernv(user);
		return userDao.updateUserIml(user, emloyee);
	}

	public boolean deleteUserIml(Users user) {
		Emloyee emloyee = new Emloyee();
		emloyee.setNhanvien_id(user.getUsers_id());
		emloyee.setNhanvien_name(user.getEmloyee().getNhanvien_name());
		emloyee.setNhanvien_sdt(user.getEmloyee().getNhanvien_sdt());
		emloyee.setNhanvien_gioitinh(user.getEmloyee().isNhanvien_gioitinh());
		emloyee.setUsernv(user);
		return userDao.deleteUserIml(user, emloyee);
	}

	@Transactional
	public boolean addEmloyee(Users user) {
		String password = user.getUsers_password();
		Emloyee emloyee = new Emloyee();
		Role role = userDao.getRoleID(user.getRole().getRole_id());
		if (role == null) {
			return false;
		} else {
			user.setUsers_password(BCrypt.hashpw(password, BCrypt.gensalt(12))); // mã hóa password
			user.setRole(role);
			emloyee.setNhanvien_name(user.getEmloyee().getNhanvien_name());
			emloyee.setNhanvien_sdt(user.getEmloyee().getNhanvien_sdt());
			emloyee.setNhanvien_gioitinh(user.getEmloyee().isNhanvien_gioitinh());
			emloyee.setUsernv(user);
			String luong = AES256.encrypt2(user.getEmloyee().getNhanvien_luong(), "123");
			emloyee.setNhanvien_luong(luong);
			user.setEmloyee(emloyee);
		}
		return userDao.addEmployeer(user, emloyee);
	}
}
