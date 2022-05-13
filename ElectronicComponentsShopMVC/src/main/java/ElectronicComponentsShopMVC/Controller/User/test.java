//package ElectronicComponentsShopMVC.Controller.User;
//
//import java.util.List;
//
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import ElectronicComponentsShopMVC.Entity.Order;
//import ElectronicComponentsShopMVC.Entity.Product;
//import ElectronicComponentsShopMVC.Entity.orderDetails;
//import ElectronicComponentsShopMVC.Service.Admin.OrderService;
//import ElectronicComponentsShopMVC.Service.User.CartService;
//
//@Transactional
//@Controller
//@Repository
//public class test {
//	@Autowired
//	OrderService orderService;
//	
//	@Autowired
//	CartService cartService;
//	
//	@Autowired
//	SessionFactory factory;
//	
//	@RequestMapping("order/{uid}")
//	public String order(ModelMap model, @PathVariable("uid") int uid) {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM Order WHERE order_owner LIKE '" + uid + "'";
//		Query query = session.createQuery(hql);
//		@SuppressWarnings("unchecked")
//		List<Order> list = query.list();
//		model.addAttribute("order", list);
//		return "order";
//	}
//	
//	@RequestMapping("orderDetails/{id}")
//	public String orderDetails(ModelMap model, @PathVariable("id") int id) {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM orderDetails WHERE order_id LIKE '" + id + "'";
//		Query query = session.createQuery(hql);
//		@SuppressWarnings("unchecked")
//		List<orderDetails> od = query.list();
//		model.addAttribute("od", od);
//		model.addAttribute("id", id);
//		Order order = (Order) session.get(Order.class, id);
//		model.addAttribute("total", order.getTotal());
//		return "orderDetails";
//	}
//	
//	@RequestMapping("hihi")
//	public String hihi() {
//		Session session =factory.openSession();
//		Order oder = (Order) session.get(Order.class, 6);
//		Product prod = (Product) session.get(Product.class, 1073);
//		Transaction t = session.beginTransaction();
//		
//		
//		orderDetails od = new orderDetails();
//		
//		
//		od.setOrder(oder);
//		od.setProduct(prod);
//		od.setQuantity(23);
//		try {
//			session.save(od);
//			t.commit();
//			System.out.println("thành công");
//		}
//		catch(Exception e){
//			t.rollback();
//			System.out.println("thất bại");
//		}
//		finally {
//			session.close();
//		}
//		return "zz";
//	}
//}
