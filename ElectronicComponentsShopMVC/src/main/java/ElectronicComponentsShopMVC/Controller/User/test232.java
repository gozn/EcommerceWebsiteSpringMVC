package ElectronicComponentsShopMVC.Controller.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import ElectronicComponentsShopMVC.Entity.Order;
import ElectronicComponentsShopMVC.Entity.Product;
import ElectronicComponentsShopMVC.Entity.orderDetails;

public class test232 {
	
	@Autowired
	static
	SessionFactory factory;
	public static void main() {
		
		Session session = factory.getCurrentSession();
//		s.setCategory_id(1);
//		Order order = new Order(0, 0, "test", "test", "0383197893", "test", null, null, null);
//		Product prod = new Product(0, "test", 1, 1, "test", "test", 0);
		Order oder = (Order) session.get(Order.class, 6);
		Product prod = (Product) session.get(Product.class, 1073);
		Transaction t = session.beginTransaction();
		orderDetails od = new orderDetails();
		od.setOrder(oder);
		od.setProduct(prod);
		od.setQuantity(23);
		try {
			session.delete(od);
			t.commit();
			System.out.println("thành công");
		}
		catch(Exception e){
			t.rollback();
			System.out.println("thất bại");
		}
		finally {
			session.close();
		}
	}
}
