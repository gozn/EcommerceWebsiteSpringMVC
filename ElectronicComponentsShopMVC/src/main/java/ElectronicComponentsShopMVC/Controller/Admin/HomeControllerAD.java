package ElectronicComponentsShopMVC.Controller.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import ElectronicComponentsShopMVC.Dao.UserDao;
import ElectronicComponentsShopMVC.Service.Admin.AdminService;

@Controller
@RequestMapping("/")
public class HomeControllerAD {
	@Autowired
	AdminService adminService;
	@Autowired
	UserDao userDao;
	
	@RequestMapping("admin")
	public String admin(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("LoginInfo") == null) {
			return "redirect:/login_register.htm";
		}
		model.addAttribute("countCategory", adminService.getCountAllCategory());
		model.addAttribute("countProduct", adminService.getCountAllProduct());
		model.addAttribute("countUser", adminService.getCountAllUser());
		model.addAttribute("countCustomer", adminService.getCountAllCustomer());
		model.addAttribute("countEmloyee", adminService.getCountAllEmloyee());

		return "admin";
	}

	@RequestMapping(value = "logoutAD")
	public String LoginAccount(HttpSession session, HttpServletRequest request) {
		session.removeAttribute("LoginInfo");
		return "index";
	}

}
