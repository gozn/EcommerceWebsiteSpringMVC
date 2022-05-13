package ElectronicComponentsShopMVC.Controller.User;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ElectronicComponentsShopMVC.Dao.UserDao;
import ElectronicComponentsShopMVC.Entity.Cart;
import ElectronicComponentsShopMVC.Entity.Product;
import ElectronicComponentsShopMVC.Service.Admin.AdminService;
import ElectronicComponentsShopMVC.Service.Algorithm.blockXSS;
import ElectronicComponentsShopMVC.Service.User.CartService;
import ElectronicComponentsShopMVC.Service.User.CategoryService;
import ElectronicComponentsShopMVC.Service.User.ProductService;
import ElectronicComponentsShopMVC.Service.User.UserService;

@Controller
//@RequestMapping("/")
@Transactional
public class HomeController {
	
	
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;
	@Autowired
	UserDao userDao;
	@Autowired
	AdminService adminService;
	@Autowired
	SessionFactory factory;
	@Autowired
	CartService cartService;
	
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	void error404(HttpServletResponse response) throws IOException {

			response.sendError(404, "Không tìm thấy trang");
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	void error500(HttpServletResponse response) throws IOException {

			response.sendError(500, "Không tìm thấy trang");
	}

	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	void error400(HttpServletResponse response) throws IOException{
		response.sendError(400, "error400");
	}
	
	@RequestMapping(value = "index")
	public String Index(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		HttpSession session = request.getSession();
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		model.addAttribute("all_category", categoryService.getAllCategory());
		model.addAttribute("all_product", productService.getAllProductFeature());
		model.addAttribute("user", session.getAttribute("LoginInfo"));
		
		return "index";
	}

	@RequestMapping(value= "search")
	public String search(HttpServletRequest request, ModelMap model, @RequestParam("search") String search, RedirectAttributes redirectAttributes) throws Exception {
		if(blockXSS.isContainSpecialWord(search)){
			String message="đã fix xss";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:/product.htm";
		}
		try {
			Session session = factory.getCurrentSession();
			FullTextSession fullTextSession = Search.getFullTextSession(session);
			fullTextSession.createIndexer().startAndWait();
		} catch(Exception e) {
			throw e;
		}
		
		Session session = factory.getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		QueryBuilder qb = fullTextSession.getSearchFactory()
				.buildQueryBuilder().forEntity(Product.class).get();
		org.apache.lucene.search.Query query = qb
				.keyword().onFields("product_name") // Chỉ định tìm theo cột nào
				.matching(search)
				.createQuery();

		org.hibernate.Query hibQuery =
						fullTextSession.createFullTextQuery(query, Product.class);
		@SuppressWarnings("unchecked")
		List<Product> prod = hibQuery.list();	
		model.addAttribute("all_product", prod);
		return "product";
	}
	
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String load(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute("all_product", productService.getAllProductFeature());
		model.addAttribute("all_category", categoryService.getAllCategory());
		model.addAttribute("user", session.getAttribute("LoginInfo"));
		
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		return "index";
	}

	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public String checkout(HttpSession session,ModelMap model, HttpServletRequest request) {
		session = request.getSession();
		model.addAttribute("user", session.getAttribute("LoginInfo"));
		model.addAttribute("all_category", categoryService.getAllCategory());
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		if (session.getAttribute("LoginInfo") == null) {
			return "redirect:/login_register.htm";
		}
		return "checkout";
	}
}
