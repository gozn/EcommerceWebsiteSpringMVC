package ElectronicComponentsShopMVC.Service.User;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ElectronicComponentsShopMVC.Dao.UserDao;
import ElectronicComponentsShopMVC.Entity.Users;

@Service
@Transactional
@Repository
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private SessionFactory factory;
	
	@Transactional
	public Users checkUserLogin(String username, String password) {
		Users user = userDao.getUserByUserName(username);
		if (user != null) {
			// kiểm tra password trong database với password vừa lấy về (đã mã hóa)
			if (BCrypt.checkpw(password, user.getUsers_password())) {
				return user;
			} else {
				return null;
			}
		}
		return null;
	}

	public Users checkUsernameForgetPass(String username) {
		return userDao.getUserByUserName(username);
	}
	
	public List<Users> getUser() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Users";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Users> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}
	}
}
