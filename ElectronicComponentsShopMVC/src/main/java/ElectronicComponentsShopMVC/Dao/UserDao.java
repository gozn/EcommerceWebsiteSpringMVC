package ElectronicComponentsShopMVC.Dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ElectronicComponentsShopMVC.Entity.Customer;
import ElectronicComponentsShopMVC.Entity.Emloyee;
import ElectronicComponentsShopMVC.Entity.Role;
import ElectronicComponentsShopMVC.Entity.Users;

@Repository
public class UserDao {
	@Autowired
	private SessionFactory factory;

	@SuppressWarnings("unchecked")
	public Users getUserByUserName(String username) {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Users WHERE users_username LIKE '" + username + "'";
			Query query = session.createQuery(hql);
			List<Users> list = query.list();
			return list.get(0);

		} catch (Exception e) {
			// Không tồn tại username
			return null;
		}

	}

	public Users getUserByID(int userId) {
		try {
			Session session = factory.getCurrentSession();
			Users u = (Users) session.get(Users.class, userId);
			return u;

		} catch (Exception e) {
			// Không tồn tại roleid
			return null;
		}

	}

	public boolean addCustomer(Users user, Customer customer) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(user);
			session.save(customer);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			check = false;
		} finally {
			session.close();
		}
		return check;
	}

	public Role getRoleID(int role_id) {
		try {
			Session session = factory.getCurrentSession();
			Role role = (Role) session.get(Role.class, role_id);
			return role;

		} catch (Exception e) {
			// Không tồn tại roleid
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Role> getAllRole() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Role WHERE role_id != 1 AND role_id != 3";
			Query query = session.createQuery(hql);
			List<Role> list = query.list();
			return list;

		} catch (Exception e) {
			// Không tồn tại username
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Users> getAllAccountCus() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Users WHERE role_id LIKE 3";
			Query query = session.createQuery(hql);
			List<Users> list = query.list();
			return list;

		} catch (Exception e) {
			// Không tồn tại username
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> getLatestAccountCus() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Users WHERE role_id LIKE 3";
			Query query = session.createQuery(hql);
			List<Users> list = query.list();
			Collections.reverse(list);
			return list;

		} catch (Exception e) {
			// Không tồn tại username
			return null;
		}
	}

	public boolean updateUserCus(Users user, Customer customer) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
			session.update(customer);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			check = false;
		} finally {
			session.close();
		}
		return check;
	}

	public boolean deleteUserCus(Users user, Customer customer) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(customer);
			session.delete(user);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			check = false;
		} finally {
			session.close();
		}
		return check;
	}

	@SuppressWarnings("unchecked")
	public List<Users> getAllAccountEml() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Users WHERE role_id != 3";
			Query query = session.createQuery(hql);
			List<Users> list = query.list();
			return list;

		} catch (Exception e) {
			// Không tồn tại username
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> getLatestAccountEml() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Users WHERE role_id != 3";
			Query query = session.createQuery(hql);
			List<Users> list = query.list();
			Collections.reverse(list);
			return list;

		} catch (Exception e) {
			// Không tồn tại username
			return null;
		}
	}

	public boolean updateUserIml(Users user, Emloyee emloyee) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
			session.update(emloyee);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			check = false;
		} finally {
			session.close();
		}
		return check;
	}

	public boolean deleteUserIml(Users user, Emloyee emloyee) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(emloyee);
			session.delete(user);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			check = false;
		} finally {
			session.close();
		}
		return check;
	}

	public boolean addEmployeer(Users user, Emloyee emloyee) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(user);
			session.save(emloyee);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			check = false;
		} finally {
			session.close();
		}
		return check;
	}

	public int getcountAllUser() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "SELECT count(*) FROM Users";
			Query query = session.createQuery(hql);
			Long obj = (long) query.uniqueResult();
			return obj.intValue();

		} catch (Exception e) {

			return -1;
		}
	}

	public int getcountAllCustomer() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "SELECT count(*) FROM Customer";
			Query query = session.createQuery(hql);
			Long obj = (long) query.uniqueResult();
			return obj.intValue();

		} catch (Exception e) {

			return -1;
		}
	}

	public int getcountAllEmloyee() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "SELECT count(*) FROM Emloyee";
			Query query = session.createQuery(hql);
			Long obj = (long) query.uniqueResult();
			return obj.intValue();

		} catch (Exception e) {

			return -1;
		}
	}
}