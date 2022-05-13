package ElectronicComponentsShopMVC.Controller.Admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import ElectronicComponentsShopMVC.Entity.Category;
import ElectronicComponentsShopMVC.Entity.Product;
import ElectronicComponentsShopMVC.Entity.ProductDTO;
import ElectronicComponentsShopMVC.Service.User.CategoryService;

@Transactional
@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	SessionFactory factory;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	ServletContext context;

	@RequestMapping(value="/product/add", method = RequestMethod.GET)
	public String addProduct(ModelMap model) {
		ProductDTO prodDTO = new ProductDTO();
		model.addAttribute("category", categoryService.getAllCategory());
		model.addAttribute("productDTO", prodDTO);
		return "product_insert";
	}
	
	@RequestMapping(value="/product/add", method = RequestMethod.POST)
	public String insert(@ModelAttribute("productDTO") ProductDTO productDTO,ModelMap model,
							@ModelAttribute("category") Category category,
							@RequestParam("image") MultipartFile image) throws IOException {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Product product = new Product();
			product.setProduct_name(productDTO.getProduct_name());
			product.setProduct_price(productDTO.getProduct_price());
			product.setProduct_discount(productDTO.getProduct_discount());
			product.setProduct_detail(productDTO.getProduct_detail());
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
		return "redirect:/product.htm";
	}
	
	
	@SuppressWarnings("null")
	public String saveFile(MultipartFile file) throws IOException {
		if(file != null || !file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				//String rootPath = System.getProperty("C:\\Users\\azhel\\Desktop\\web\\ElectronicComponentsShopMVC\\src\\main\\webapp");
				String rootPath = "C:\\Users\\azhel\\Desktop\\web\\ElectronicComponentsShopMVC\\src\\main\\webapp";
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
