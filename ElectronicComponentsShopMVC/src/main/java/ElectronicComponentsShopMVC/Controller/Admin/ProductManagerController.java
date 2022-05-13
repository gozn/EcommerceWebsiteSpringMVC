package ElectronicComponentsShopMVC.Controller.Admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ElectronicComponentsShopMVC.Entity.Category;
import ElectronicComponentsShopMVC.Entity.Product;
import ElectronicComponentsShopMVC.Entity.ProductDTO;
import ElectronicComponentsShopMVC.Service.Admin.ProductServiceAD;
import ElectronicComponentsShopMVC.Service.User.CategoryService;
import ElectronicComponentsShopMVC.Service.User.ProductService;


@Transactional
@Controller
@RequestMapping("/admin")
public class ProductManagerController {
	@Autowired
	ProductService productService;

	@Autowired
	ProductServiceAD productServiceAD;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ServletContext context;
	
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "/manager_product")
	public String dataTable(Model model) {
		model.addAttribute("view_product", productService.getAllProduct());
		model.addAttribute("view_category", categoryService.getAllCategory());
		return "manager_product";
	}
	
	@RequestMapping(value = "/latest_product")
	public String oldestProduct(Model model) {
		model.addAttribute("view_product", productService.getLatestProd());
		model.addAttribute("view_category", categoryService.getAllCategory());
		return "manager_product";
	}

	@RequestMapping(value = "insertPro", method = RequestMethod.GET)
	public String insertPro(ModelMap model) {
		ProductDTO prodDTO = new ProductDTO();
		model.addAttribute("productDTO", prodDTO);
		model.addAttribute("view_category", categoryService.getAllCategory());
		return "product-manager/insertPro";
	}

	@RequestMapping(value = "insertPro", method = RequestMethod.POST)
	public String insertPro_(HttpServletRequest request, ModelMap model,
			@Validated @ModelAttribute("productDTO") ProductDTO productDTO,
			@RequestParam("image") MultipartFile image,
			@ModelAttribute("category") Category category,
			BindingResult errors) throws IOException{
		if (errors.hasErrors()) {
			model.addAttribute("massage_insert", "Thêm không thành công");
			model.addAttribute("view_category", categoryService.getAllCategory());
			return "product-manager/insertPro";
		} else {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				Product product = new Product();
				product.setProduct_name(productDTO.getProduct_name());
				product.setProduct_price(productDTO.getProduct_price());
				product.setProduct_discount(productDTO.getProduct_discount());
				product.setProduct_detail(productDTO.getProduct_detail());
				product.setProduct_quantity(productDTO.getProduct_quantity());
				//product.setProduct_images(productDTO.getProduct_images());
				product.setCategoryID(categoryService.getCategoryID(productDTO.getCategoryID()));
				product.setProduct_images(saveFile(image));
				session.save(product);
				t.commit();
				model.addAttribute("message", "Thêm thành công!");
				
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Thêm thất bại!");
			} finally {
				session.close();
			}
			return "redirect:/admin/manager_product.htm";
		}
	}
	
	
	@RequestMapping(value="deletePro/{product-id}")
	public String deletePro(ModelMap model, @PathVariable("product-id") int product_id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Product prod = (Product) session.get(Product.class, product_id);
		try {
			session.delete(prod);
			t.commit();
			model.addAttribute("message", "Xoá thành công!");
		}
		catch(Exception e){
			t.rollback();
			model.addAttribute("message", "Xóa thất bại");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/manager_product.htm";
	}

	@RequestMapping(value="editPro/{product-id}",method=RequestMethod.GET)
	public String editProduct(@PathVariable("product-id") int productId, ModelMap model) {
		Session session = factory.openSession();
		Product product = (Product) session.get(Product.class, productId);
		model.addAttribute("productDTO", product);
		model.addAttribute("view_category", categoryService.getAllCategory());
		return "product-manager/editPro";
	}
	
	@RequestMapping(value="editPro")
	public String update(ModelMap model, @RequestParam("product_name") String product_name,
											@RequestParam("product_detail") String product_detail,
											@RequestParam("product_price") int product_price,											
											@RequestParam("product_discount") int product_discount,
											@RequestParam("categoryID") int categoryID,
											@RequestParam("product_quantity") int product_quantity,
											@RequestParam("product_id") int product_id,
											@RequestParam("image") MultipartFile image,
											@RequestParam("product_images") String product_images) throws IOException {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();	
		Product prod = (Product) session.get(Product.class, product_id);
		prod.setProduct_name(product_name);
		prod.setProduct_detail(product_detail);
		prod.setProduct_discount(product_discount);
		prod.setProduct_price(product_price);
		prod.setProduct_quantity(product_quantity);
		prod.setCategoryID(categoryService.getCategoryID(categoryID));
		if(image == null || image.isEmpty()) {
			prod.setProduct_images(product_images);
		}
		else prod.setProduct_images(saveFile(image));
		
		
		try {
			session.update(prod);
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
		return "redirect:/admin/manager_product.htm";
	}



	@SuppressWarnings("null")
	public String saveFile(MultipartFile file) throws IOException {
		if(file != null || !file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				//String rootPath = System.getProperty("C:\\Users\\azhel\\Desktop\\web\\ElectronicComponentsShopMVC\\src\\main\\webapp");
				String rootPath = "C:\\Users\\azhel\\Desktop\\web\\final\\ElectronicComponentsShopMVC\\src\\main\\webapp";
				//create file locate
				File dir = new File(rootPath + File.separator + "assets/images/shop");
				if(!dir.exists()) {
					dir.mkdir();
				}
				
				//create file on server
				String name = String.valueOf(file.getOriginalFilename());
				File serverFile = new File(dir.getAbsoluteFile()+File.separator+name);
				
				System.out.println("==========Path of image on server: "+serverFile.getPath());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
				
				return name;
			} catch (Exception e) {
				System.out.println("==========Path of image on server: "+e.getMessage());
			}
			
		} 
		else {
			System.out.println("=============File not exits=============");
		}
		return null;
	}
}
