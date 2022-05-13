package ElectronicComponentsShopMVC.Controller.Admin;

import java.util.List;

import org.hibernate.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ElectronicComponentsShopMVC.Entity.Order;
import ElectronicComponentsShopMVC.Entity.Product;
import ElectronicComponentsShopMVC.Entity.orderDetails;
import ElectronicComponentsShopMVC.Service.Admin.OrderService;
import ElectronicComponentsShopMVC.Service.User.CartService;

@Transactional
@Controller
@RequestMapping("/admin")
public class OrderController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	CartService cartService;
	
	@RequestMapping(value="/manager_order")
	public String managerOrder(ModelMap model) {
		model.addAttribute("nullOrder", orderService.getNullOrders());
		return "manager_order";
	}
	
	@RequestMapping(value="/latest_order")
	public String latestNullOrder(ModelMap model) {
		model.addAttribute("nullOrder", orderService.getLatestNullOrder());
		return "manager_order";
	}
	
	@RequestMapping(value="/confirmedOrder")
	public String confirmedOrder(ModelMap model) {
		model.addAttribute("confirmedOrder", orderService.getConfirmedOrders());
		return "order_manager/confirmedOrder";
	}
	
	@RequestMapping(value="/latestConfirmedOrder")
	public String latestConfirmedOrder(ModelMap model) {
		model.addAttribute("confirmedOrder", orderService.getLatestConfirmedOrders());
		return "order_manager/confirmedOrder";
	}
	
	@RequestMapping(value="/denyOrder")
	public String denyOrder(ModelMap model) {
		model.addAttribute("denyOrder", orderService.getDenyOrders());
		return "order_manager/denyOrder";
	}
	
	@RequestMapping(value="/latestDenyOrder")
	public String latestDenyOrder(ModelMap model) {
		model.addAttribute("denyOrder", orderService.getLatestDenyOrders());
		return "order_manager/denyOrder";
	}
	
	@RequestMapping(value="/orderDetails/{id}")
	public String orderDetails(ModelMap model, @PathVariable("id") int id, HttpServletRequest request) {
		
		Session session = factory.getCurrentSession();
		String hql = "FROM orderDetails WHERE order_id LIKE '" + id + "'";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<orderDetails> od = query.list();
		model.addAttribute("od", od);
		model.addAttribute("id", id);
		model.addAttribute("size", od.size());
		Order order = (Order) session.get(Order.class, id);
		model.addAttribute("total", order.getTotal());
		return "order_manager/orderDetails";
	}
	
	
	@RequestMapping(value="accept/{order_id}")
	public String accept(ModelMap model, @PathVariable("order_id") int order_id, HttpSession session2) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Order order = (Order) session.get(Order.class, order_id);
		order.setOrder_status("1");
//		order.setOrder_approver((int) session2.getAttribute("userID"));
		try {
			
			//
			String hql2 = "FROM orderDetails WHERE order_id LIKE '" + order_id + "'";
			Query query = session.createQuery(hql2);
			@SuppressWarnings("unchecked")
			List<orderDetails> od = query.list();
			int s;
			for(int i = 0; i  < od.size(); i++) {
				 s = od.get(i).getQuantity();
				 Product prod = od.get(i).getProduct();
				 prod.setProduct_quantity(prod.getProduct_quantity() - s);
				 session.update(prod);
			}
			
			//
			
			session.update(order);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công!");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/manager_order.htm";
	}
	
	
	@RequestMapping(value="deny/{order_id}")
	public String deny(ModelMap model, @PathVariable("order_id") int order_id,
										@RequestParam("reason") String reason) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Order order = (Order) session.get(Order.class, order_id);
		order.setOrder_status("0");
		order.setDeny_reason(reason);
		try {
			session.update(order);
			t.commit();
			model.addAttribute("message", "Cập nhật thành công!");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message", "Cập nhật thất bại");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/manager_order.htm";
	}
}
