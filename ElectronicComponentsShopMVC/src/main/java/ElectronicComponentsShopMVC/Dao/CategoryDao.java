package ElectronicComponentsShopMVC.Dao;

import java.util.Collections;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ElectronicComponentsShopMVC.Entity.Category;

@Repository
public class CategoryDao {
	@Autowired
	private SessionFactory factory;

	public Category getCategoryID(int category_id) {
		try {
			Session session = factory.getCurrentSession();
			Category category = (Category) session.get(Category.class, category_id);
			return category;

		} catch (Exception e) {
			// Không tồn tại category_id
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategory() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Category";
			Query query = session.createQuery(hql);
			List<Category> list = query.list();
			return list;

		} catch (Exception e) {

			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> getLatestCat() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Category";
			Query query = session.createQuery(hql);
			List<Category> list = query.list();
			Collections.reverse(list);
			return list;

		} catch (Exception e) {

			return null;
		}
	}

	public int getcountAllCategory() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "SELECT count(*) FROM Category";
			Query query = session.createQuery(hql);
			Long obj = (long) query.uniqueResult();
			return obj.intValue();

		} catch (Exception e) {

			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public Category getCategoryByName(String category_name) {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Category WHERE category_name LIKE '" + category_name + "'";
			Query query = session.createQuery(hql);
			List<Category> list = query.list();
			return list.get(0);

		} catch (Exception e) {
			// Không tồn tại username
			return null;
		}

	}

	public boolean addCategory(Category category) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(category);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			check = false;
		} finally {
			session.close();
		}
		return check;
	}

	public boolean updateCategory(Category category) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(category);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			check = false;
		} finally {
			session.close();
		}
		return check;
	}

	public boolean deleteCategory(Category category) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(category);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			check = false;
		} finally {
			session.close();
		}
		return check;
	}
}