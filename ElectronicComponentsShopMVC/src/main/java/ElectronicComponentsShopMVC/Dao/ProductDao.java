package ElectronicComponentsShopMVC.Dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ElectronicComponentsShopMVC.Entity.Product;

@Repository
public class ProductDao {
	@Autowired
	private SessionFactory factory;

	public void indexProducts() throws Exception {
		try {
			Session session = factory.getCurrentSession();
			FullTextSession fullTextSession = Search.getFullTextSession(session);
			fullTextSession.createIndexer().startAndWait();
		} catch(Exception e) {
			throw e;
		}
	}
	
	public Product getProductID(int product_id) {
		try {
			Session session = factory.getCurrentSession();
			Product product = (Product) session.get(Product.class, product_id);
			return product;

		} catch (Exception e) {
			// Không tồn tại category_id
			return null;
		}
	}

	public int getcountAllProduct() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "SELECT count(*) FROM Product";
			Query query = session.createQuery(hql);
			Long obj = (long) query.uniqueResult();
			return obj.intValue();

		} catch (Exception e) {

			return -1;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProduct() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Product";
			Query query = session.createQuery(hql);
			List<Product> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getLatestProd() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Product";
			Query query = session.createQuery(hql);
			List<Product> list = query.list();
			Collections.reverse(list);
			return list;

		} catch (Exception e) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductFeature() {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Product ORDER BY product_id DESC";
			Query query = session.createQuery(hql);
			List<Product> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductLimit(int first) {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Product ORDER BY product_id DESC";
			Query query = session.createQuery(hql).setFirstResult(first).setMaxResults(8);
			List<Product> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductByCategoryID(int category_id) {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Product WHERE category_id LIKE '" + category_id + "'";
			Query query = session.createQuery(hql);
			List<Product> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductByCategoryIDLimit(int category_id, int first) {
		try {
			Session session = factory.getCurrentSession();
			String hql = "FROM Product WHERE category_id LIKE '" + category_id + "'";
			Query query = session.createQuery(hql).setFirstResult(first).setMaxResults(8);
			List<Product> list = query.list();
			return list;

		} catch (Exception e) {
			return null;
		}

	}

	public boolean addProduct(Product product) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(product);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			check = false;
		} finally {
			session.close();
		}
		return check;
	}

	public boolean updateProduct(Product product) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(product);
			t.commit();
		} catch (Exception e) {
			t.rollback();
			check = false;
		} finally {
			session.close();
		}
		return check;
	}

	public boolean deleteProduct(Product product) {
		boolean check = true;
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(product);
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
