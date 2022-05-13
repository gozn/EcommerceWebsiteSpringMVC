package ElectronicComponentsShopMVC.Controller.User;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ElectronicComponentsShopMVC.Entity.Cart;
import ElectronicComponentsShopMVC.Entity.Category;
import ElectronicComponentsShopMVC.Entity.Product;
import ElectronicComponentsShopMVC.Service.User.CartService;
import ElectronicComponentsShopMVC.Service.User.CategoryService;
import ElectronicComponentsShopMVC.Service.User.ProductService;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	CartService cartService;

	@RequestMapping(value = "product")
	public String showCategory(ModelMap model, HttpSession session) {
		List<Product> listPro = productService.getAllProduct();
		int page = listPro.size() / 8;
		if (listPro.size() % 8 != 0) {
			page += 1;
		}
		model.addAttribute("page", page);
		model.addAttribute("all_category", categoryService.getAllCategory());
		model.addAttribute("all_product", productService.getProductLimit(0));
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		return "product";
	}

	@RequestMapping(value = "product/{product-id}")
	public String productSingle(@PathVariable("product-id") int productID, ModelMap model, HttpSession session) {
		Product p = productService.getProductID(productID);
		model.addAttribute("productsingle", p);
		model.addAttribute("all_product", productService.getProductByCategoryID(p.getCategoryID().getCategory_id()));
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		return "product_single";
	}

	@RequestMapping(value = "pages/{page-id}")
	public String showProductLimit(@PathVariable("page-id") int first, ModelMap model, HttpSession session) {
		List<Product> listPro = productService.getAllProduct();
		int page = listPro.size() / 8;
		if (listPro.size() % 8 != 0) {
			page += 1;
		}
		model.addAttribute("page", page);
		model.addAttribute("all_category", categoryService.getAllCategory());
		model.addAttribute("all_product", productService.getProductLimit((first - 1) * 8));
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		return "product";
	}

	@RequestMapping(value = { "category/{category-id}" })
	public String showCategoryByID(@PathVariable("category-id") int categoryID, ModelMap model, HttpSession session) {
		Category c = categoryService.getCategoryID(categoryID);
		List<Product> listPro = productService.getProductByCategoryID(c.getCategory_id());
		int page = listPro.size() / 8;
		if (listPro.size() % 8 != 0) {
			page += 1;
		}
		model.addAttribute("pagecategory", page);
		model.addAttribute("category", c);
		model.addAttribute("all_category", categoryService.getAllCategory());
		model.addAttribute("all_product", productService.getProductByCategoryIDLimit(categoryID, 0));
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		return "productcategory";
	}

	@RequestMapping(value = "pages/{category-id}/{page-id}")
	public String showProductLimitCategory(@PathVariable("category-id") int categoryid,
			@PathVariable("page-id") int first, ModelMap model, HttpSession session) {
		Category c = categoryService.getCategoryID(categoryid);
		List<Product> listPro = productService.getProductByCategoryID(categoryid);
		int page = listPro.size() / 8;
		if (listPro.size() % 8 != 0) {
			page += 1;
		}
		model.addAttribute("category", c);
		model.addAttribute("pagecategory", page);
		model.addAttribute("all_category", categoryService.getAllCategory());
		model.addAttribute("all_product", productService.getProductByCategoryIDLimit(categoryid, (first - 1) * 8));
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		return "productcategory";
	}

	

}
