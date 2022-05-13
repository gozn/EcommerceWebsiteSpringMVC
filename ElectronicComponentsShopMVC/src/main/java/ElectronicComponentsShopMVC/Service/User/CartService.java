package ElectronicComponentsShopMVC.Service.User;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import ElectronicComponentsShopMVC.Entity.Cart;


@Transactional
@Service
public class CartService {
	private static final String SESSION_KEY_SHOPPING_CART="gioHang";
	
	//phuong thuc lay gio hang
	public Cart getGioHang(HttpSession session) {
		Cart cart = (Cart)session.getAttribute(SESSION_KEY_SHOPPING_CART);
		if(cart == null) {
			cart = new Cart();
			setCart(session, cart);
		}
		return cart;
	}
	
	//thiet lap 1 gio hang
	public void setCart(HttpSession session, Cart cart) {
		session.setAttribute(SESSION_KEY_SHOPPING_CART, cart);
	}
	
	//xoa 1 gio hang
	public void removeCart(HttpSession session) {
		session.removeAttribute(SESSION_KEY_SHOPPING_CART);
	}
}
