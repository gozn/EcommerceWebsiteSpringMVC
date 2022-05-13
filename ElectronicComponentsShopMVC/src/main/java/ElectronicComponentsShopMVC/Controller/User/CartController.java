package ElectronicComponentsShopMVC.Controller.User;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import ElectronicComponentsShopMVC.Entity.Cart;
import ElectronicComponentsShopMVC.Entity.Order;
import ElectronicComponentsShopMVC.Entity.Product;
import ElectronicComponentsShopMVC.Entity.cartItem;
import ElectronicComponentsShopMVC.Entity.orderDetails;
import ElectronicComponentsShopMVC.Service.Admin.OrderService;
import ElectronicComponentsShopMVC.Service.User.CartService;
import ElectronicComponentsShopMVC.Service.User.CategoryService;
import ElectronicComponentsShopMVC.Service.User.ProductService;



@Controller
public class CartController {
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	void error404(HttpServletResponse response) throws IOException {

			response.sendError(404, "Không tìm thấy trang");
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	void error500(HttpServletResponse response) throws IOException {

			response.sendError(500, "Không tìm thấy trang");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	void error400(HttpServletResponse response) throws IOException{
		response.sendError(400, "zz");
	}
	
	@Autowired
	CartService cartService;
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@Autowired
	OrderService orderService;
	
	@Autowired
	SessionFactory factory;
	
	
	@RequestMapping(value = "cart")
	public String cart(HttpSession session, ModelMap model) {
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		model.addAttribute("prod", productService.getAllProduct());
		return "cart";
	}
	
	@RequestMapping(value="/addToCart/{product-id}")
	public String add(HttpSession session, @PathVariable("product-id") int product_id, ModelMap model) {
		Cart cart = cartService.getGioHang(session);
		int quantity = 1;
		Product product = productService.getProductID(product_id); //lay san pham	
		cart.addItem(product, quantity);		
		return "redirect:/product.htm";
	}
	
	@RequestMapping(value="/addToCart")
	public String add(HttpSession session, @RequestParam("product_id") int product_id,
			@RequestParam(value="quantity", required = false, defaultValue="1") int quantity, ModelMap model) {
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		Product product = productService.getProductID(product_id); //lay san pham	
		cart.addItem(product, quantity);		
		return "redirect:/product.htm";
	}
	
	@RequestMapping(value="cart/delete")
	public String delete(HttpSession session, @RequestParam(value="product_id") int product_id, ModelMap model) {
		Product product = productService.getProductID(product_id);
		Cart cart = cartService.getGioHang(session);
		cart.deleteItem(product);
		model.addAttribute("cartCount", cart.getItems().size());
		return "cart";
	}
	
	//sua sp trong gio hang
	@RequestMapping(value="cart/update")
	public String update(HttpSession session, @RequestParam("product_id") int product_id, @RequestParam(value="quantity") int quantity, ModelMap model) {
		Product product = productService.getProductID(product_id);
		Cart cart = cartService.getGioHang(session);
		cart.updateItem(product, quantity);
		model.addAttribute("cartCount", cart.getItems().size());
		return "cart";
	}
	
	@RequestMapping(value="checkout")
	public String checkout(HttpSession session) {
		Cart  cart = cartService.getGioHang(session);
		if(cart.getSize() == 0) {
			return "redirect:/product.htm";
		}
		return "checkout";
	}
	
	
	
	@RequestMapping(value="reviewOrder")
	public String reviewOrder(HttpSession session2, ModelMap model, @RequestParam("order_owner") int id,
												@RequestParam("order_ownername") String name,
												@RequestParam("order_email") String email,
												@RequestParam("order_address") String address,
												@RequestParam("order_phone") String phone,
												@RequestParam("order_note") String note,
												@RequestParam("order_items") String order_items,
												@ModelAttribute("Order") Order order) {
		
		
		
		//Post order
		//tas = total and ship
		Cart cart = cartService.getGioHang(session2);
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		order.setOrder_owner(id);
		try {
			order.setTotal((int) cart.getTas());
			session.save(order);
			t.commit();
			model.addAttribute("message", "Thêm thành công!");
			
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "Thêm thất bại!");
		} 
		finally {
			session.close();
		}
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		model.addAttribute("address", address);
		model.addAttribute("phone", phone);
		model.addAttribute("note", note);	
		
		//post order_details
		orderDetails od = new orderDetails();
		int z = order.getOrder_id();
		for(cartItem item : cart.getItems()) {
			Session sessionz = factory.openSession();
			Transaction t2 = sessionz.beginTransaction();
			Order orderz = (Order) sessionz.get(Order.class, z);
			Product prod = item.getProd();
			int quantity = item.getQuantity();
			od.setProduct(prod);
			od.setQuantity(quantity);
			od.setOrder(orderz);
			
			
			od.setCreate_at(new Date());
			try {
				sessionz.save(od);
				t2.commit();
				System.out.println("thành công");
			}
			catch(Exception e){
				t2.rollback();
				System.out.println("thất bại");
			}
		}
		cart.clearItem();
		return "reviewOrder";
	}
}
