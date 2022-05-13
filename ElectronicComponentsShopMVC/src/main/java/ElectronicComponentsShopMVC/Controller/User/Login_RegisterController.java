package ElectronicComponentsShopMVC.Controller.User;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ElectronicComponentsShopMVC.Entity.Cart;
import ElectronicComponentsShopMVC.Entity.Users;
import ElectronicComponentsShopMVC.Service.Admin.AdminService;
import ElectronicComponentsShopMVC.Service.Algorithm.blockXSS;
import ElectronicComponentsShopMVC.Service.User.CartService;
import ElectronicComponentsShopMVC.Service.User.RegisterCustomerService;
import ElectronicComponentsShopMVC.Service.User.UserService;

@Controller
public class Login_RegisterController {
	@Autowired
	@Qualifier(value = "userService")
	UserService userService;

	@Autowired
	RegisterCustomerService registerCustomerService;

	@Autowired
	JavaMailSender mailer;

	@Autowired
	AdminService adminService;
	
	@Autowired
	CartService cartService;

	@RequestMapping(value = "/login_register")
	public String login_register(HttpSession session, ModelMap model) {
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		if (session.getAttribute("LoginInfo") != null) {
			return "redirect:/index.htm";
		}
		return "login_register";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam("username_login") String username,
			@RequestParam("password_login") String password, HttpSession session, ModelMap model) {
		
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		Users user = userService.checkUserLogin(username, password);
		if (user != null) {
			session.setAttribute("LoginInfo", user);
			session.setAttribute("isLogin", true);
			session.setAttribute("role", user.getRole().getRole_name());
			session.setAttribute("userID", user.getUsers_id());
			return "redirect:/home.htm";
		} else {
			model.addAttribute("Status_login", "Đăng nhập thất bại!");
		}
		return "login_register";
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String LoginAccount(HttpSession session, HttpServletRequest request) {
		//session.removeAttribute("LoginInfo");
		session = request.getSession(false);
	    session.invalidate();
		return "redirect:/index.htm";
	}

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(@RequestParam("email_register") String email,
			@RequestParam("password_register") String password, @RequestParam("name_register") String name,
			@RequestParam("gender_register") boolean gender, @RequestParam("sdt_register") String sdt,
			HttpSession session, ModelMap model) {
		if(blockXSS.isContainSpecialWord(sdt) || blockXSS.isContainSpecialWord(name)){
			String message="đã fix xss";
			model.addAttribute("message", message);
			return "redirect:/login_register.htm";
		}
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		boolean result = registerCustomerService.addCustomer(email, password, name, sdt, gender);
		if (result == true) {
			model.addAttribute("message_register_success", "Đăng kí Thành công");
		} else {
			model.addAttribute("message_register_fail", "Dăng kí Thất bại");
		}
		return "login_register";
	}

	@RequestMapping(value = "forgetpass")
	public String forgetPass(ModelMap model) {
		return "forgetpass";
	}

	@RequestMapping(value = "forgetpass", method = RequestMethod.POST)
	public String forgetPass_(@RequestParam("username_login") String username, ModelMap model, HttpSession session) {
		String pass = RandomStringUtils.randomAlphanumeric(12);
		Users user = userService.checkUsernameForgetPass(username);
		Cart cart = cartService.getGioHang(session);
		model.addAttribute("cartCount", cart.getItems().size());
		if (user != null && user.getRole().getRole_id() == 3) {
			try {
				MimeMessage mail = mailer.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(mail);
				helper.setFrom("n18dcat093@student.ptithcm.edu.vn", "ADMIN ESSENTIAL COLLECTION SHOP");
				helper.setTo(user.getUsers_username());
				helper.setReplyTo("admin@gmail.com", "ADMIN ESSENTIAL COLLECTION SHOP");
				helper.setSubject("Hỗ Trợ Quên Mật Khẩu");
				helper.setText("Mật khẩu mới của bạn là : " + pass, true);
				user.setUsers_password(pass);
				boolean check = adminService.updateUserCus(user);
				if (check == false) {
					model.addAttribute("message_user", "Thay đổi mật khẩu không thành công");
					return "forgetpass";
				}
				mailer.send(mail);
				return "login_register";
			} catch (Exception e) {
				model.addAttribute("message_mail", " Gửi mail không thành công");
			}
		}
		model.addAttribute("checkuser", "Tài khoản không hợp lệ!");
		return "forgetpass";
	}
}
