package ElectronicComponentsShopMVC.Dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ElectronicComponentsShopMVC.Entity.Order;



@Repository
public class OrderDao {
	@Autowired
	SessionFactory factory;
	public List<Order> getNullOrder() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Order WHERE order_status IS NULL";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Order> list = query.list();
			return list;

		} catch (Exception e) {

			return null;
		}
	}
	

	public List<Order> getLatestNullOrder() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Order WHERE order_status IS NULL";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Order> list = query.list();
			Collections.reverse(list);
			return list;

		} catch (Exception e) {

			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> getUserOrder(int user_id) {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Order WHERE order_owner LIKE '" + user_id + "'";
			Query query = session.createQuery(hql);
			List<Order> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Order> getConfirmedOrder() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Order WHERE order_status = 1";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Order> list = query.list();
			return list;

		} catch (Exception e) {

			return null;
		}
	}
	
	public List<Order> getLatestConfirmedOrder() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Order WHERE order_status = 1";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Order> list = query.list();
			Collections.reverse(list);
			return list;

		} catch (Exception e) {

			return null;
		}
	}
	
	public List<Order> getDenyOrder() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Order WHERE order_status = 0";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Order> list = query.list();
			return list;

		} catch (Exception e) {

			return null;
		}
	}
	
	public List<Order> getLatestDenyOrder() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Order WHERE order_status = 0";
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<Order> list = query.list();
			Collections.reverse(list);
			return list;

		} catch (Exception e) {

			return null;
		}
	}
}
