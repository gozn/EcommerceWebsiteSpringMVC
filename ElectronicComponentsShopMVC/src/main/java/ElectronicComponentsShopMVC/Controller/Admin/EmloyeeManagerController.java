package ElectronicComponentsShopMVC.Controller.Admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import ElectronicComponentsShopMVC.Entity.Role;
import ElectronicComponentsShopMVC.Entity.Users;
import ElectronicComponentsShopMVC.Service.Admin.AdminService;

@Controller
@RequestMapping("/admin/")
public class EmloyeeManagerController {
	@Autowired
	AdminService adminService;

	@RequestMapping("manager_emloyee")
	public String dataTable(Model model) {
//		List<Users> e = adminService.GetAllUserEml();
//		for(Users es : e) {
//			String luong = AES256.decrypt2(es.getEmloyee().getNhanvien_luong(), "123");
//			model.addAttribute("luong", luong);
//		}
		model.addAttribute("view_userEml", adminService.GetAllUserEml());
		return "manager_emloyee";
	}

	@RequestMapping("latest_emloyee")
	public String latestEmployee(Model model) {
		model.addAttribute("view_userEml", adminService.GetLatestUserEml());
		return "manager_emloyee";
	}
	
	@RequestMapping(value = "insertEml", method = RequestMethod.GET)
	public String insertEmloyee(Model model) {
		model.addAttribute("insertEmloyee", new Users());
		model.addAttribute("view_role", adminService.GetAllRole());
		return "emloyee-manager/insertEml";
	}

	@RequestMapping(value = "insertEml", method = RequestMethod.POST)
	public String insertEmloyee_(HttpServletRequest request, Model model,
			@Validated @ModelAttribute("insertEmloyee") Users user, BindingResult errors) {
		if (errors.hasErrors()) {
			model.addAttribute("massage_insert", "Thêm không thành công");
			model.addAttribute("view_role", adminService.GetAllRole());
			return "emloyee-manager/insertEml";
		} else {
			boolean check = adminService.addEmloyee(user);
			if (check == true) {
				
				model.addAttribute("view_userEml", adminService.GetAllUserEml());
				List<Role> list = adminService.GetAllRole();
				model.addAttribute("view_role", list);
				return "redirect:manager_emloyee.htm";
			} else {
				model.addAttribute("massage_insert", "Thêm không thành công");
				return "redirect:" + request.getHeader("Referer");
			}
		}
	}

	@RequestMapping("editEml/{user-id}")
	public String editEmloyeee(@PathVariable("user-id") int userId, ModelMap model) {
		Users user = adminService.checkdEdit(userId);
		model.addAttribute("user", user);
		model.addAttribute("view_role", adminService.GetAllRole());
		return "emloyee-manager/editEml";
	}

	@RequestMapping("editEml")
	public String editCustomer_(HttpServletRequest request, @ModelAttribute("user") Users user, ModelMap model,
			BindingResult errors) {
		if (user.getUsers_username().trim().length() == 0) {
			model.addAttribute("massage_edit", "Cập nhật không thành công");
			return "redirect:" + request.getHeader("Referer");
		}
		if (user.getEmloyee() == null) {
			model.addAttribute("massage_edit", "Cập nhật không thành công");
			return "redirect:" + request.getHeader("Referer");
		}
		boolean check = adminService.updateUserIml(user);
		if (check == true) {
			model.addAttribute("view_userEml", adminService.GetAllUserEml());
			return "redirect:manager_emloyee.htm";
		} else {
			model.addAttribute("massage_edit", "Cập nhật không thành công");
			return "redirect:" + request.getHeader("Referer");
		}
	}

	@RequestMapping(value = "deleteEml/{user-id}")
	public String deleteCus(HttpServletRequest request, @PathVariable("user-id") int userId, ModelMap model) {
		Users user = adminService.checkdEdit(userId);
		boolean check = adminService.deleteUserIml(user);
		if (check == true) {
			model.addAttribute("view_userEml", adminService.GetAllUserEml());
		} else {
			model.addAttribute("massage_delete", "Xóa không thành công");
		}

		return "redirect:" + request.getHeader("Referer");
	}
}
