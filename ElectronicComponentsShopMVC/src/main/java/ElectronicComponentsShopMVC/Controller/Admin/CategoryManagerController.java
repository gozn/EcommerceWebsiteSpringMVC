package ElectronicComponentsShopMVC.Controller.Admin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ElectronicComponentsShopMVC.Entity.Category;
import ElectronicComponentsShopMVC.Service.Admin.CategoryServiceAD;
import ElectronicComponentsShopMVC.Service.Algorithm.blockXSS;
import ElectronicComponentsShopMVC.Service.User.CategoryService;



@Transactional
@Controller
@RequestMapping("/admin")
public class CategoryManagerController {
	@Autowired
	CategoryServiceAD categoryServiceAD;

	@Autowired
	SessionFactory factory;
	
	@Autowired
	CategoryService categoryService;

	@RequestMapping("manager_category")
	public String dataTable(Model model) {
		model.addAttribute("view_category", categoryService.getAllCategory());
		return "manager_category";
	}
	
	@RequestMapping("latest_category")
	public String latestCat(Model model) {
		model.addAttribute("view_category", categoryService.getLatestCat());
		return "manager_category";
	}

	
	@RequestMapping(value = "/insertCat", method = RequestMethod.GET)
	public String insertCategory(Model model) {
		model.addAttribute("insertCategory", new Category());
		return "category-manager/insertCat";
	}

	@RequestMapping(value = "/insertCat", method = RequestMethod.POST)
	public String insertCategory_(HttpServletRequest request, Model model,
			@Validated @ModelAttribute("insertCategory") Category category, BindingResult errors, RedirectAttributes redirectAttributes) {
		String message;
		if(blockXSS.isContainSpecialWord(category.getCategory_name())){
			message="đã fix xss";
			redirectAttributes.addFlashAttribute("message", message);
			return "redirect:manager_category.htm";
		}
		if (errors.hasErrors()) {
			model.addAttribute("massage_insert", "Thêm không thành công");
			return "category-manager/insertCat";
		} else {
			boolean check = categoryServiceAD.addCategory(category);
			if (check == true) {
				model.addAttribute("view_category", categoryService.getAllCategory());
				return "redirect:manager_category.htm";
			} else {
				model.addAttribute("massage_insert", "Thêm không thành công");
				return "redirect:" + request.getHeader("Referer");
			}
		}

	}

	@RequestMapping(value="editCat/{category-id}")
	public String editCategory(@PathVariable("category-id") int categoryID, ModelMap model) {
		Session session = factory.openSession();
		Category category = (Category) session.get(Category.class, categoryID);
		model.addAttribute("Category", category);
		return "category-manager/editCat";
	}
	

	@RequestMapping("editCat")
	public String editCategory_(ModelMap model, @RequestParam("category_id") int category_id,
												@RequestParam("category_name") String category_name) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Category cat = (Category) session.get(Category.class, category_id);
		cat.setCategory_name(category_name);
		try {
		
			session.update(cat);
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
		return "redirect:/admin/manager_category.htm";
	}

	
	
	@RequestMapping(value = "deleteCat/{category-id}")
	public String deleteCat(HttpServletRequest request, @PathVariable("category-id") int categoryID, ModelMap model) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Category cat = (Category) session.load(Category.class, categoryID);
		try {
			session.delete(cat);
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
		return "redirect:/admin/manager_category.htm";
		
	}
}

