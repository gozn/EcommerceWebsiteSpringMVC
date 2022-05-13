package ElectronicComponentsShopMVC.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		boolean isLogined = session.getAttribute("isLogin") != null ? (Boolean) session.getAttribute("isLogin") : false;
		if (!isLogined) {
			response.sendRedirect(request.getContextPath() + "/login_register.htm");
			return false;
		}
		return true;
	}
}
