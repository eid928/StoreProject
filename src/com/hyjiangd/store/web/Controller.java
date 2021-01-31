package com.hyjiangd.store.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hyjiangd.store.domain.Customer;
import com.hyjiangd.store.service.CustomerService;
import com.hyjiangd.store.service.ServiceException;
import com.hyjiangd.store.service.imp.CustomerServiceImp;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private CustomerService customerService = new CustomerServiceImp();
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ("reg".equals(action)) {
			// 使用者註冊會員
			String userid = request.getParameter("userid");
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			String birthday = request.getParameter("birthday");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			
			// 後端驗證
			List<String> errors = new ArrayList<>();
			if(userid == null || userid.equals("")) {
				errors.add("帳號不能為空。");
			}
			if(name == null || name.equals("")) {
				errors.add("姓名不能為空。");
			}
			if(password == null || password.equals("")) {
				errors.add("密碼不能為空。");
			}
			if(password2 == null || password2.equals("")) {
				errors.add("請再次輸入密碼。");
			}
			if(!password.equals(password2)) {
				errors.add("兩次輸入的密碼不一致。");
			}
			
			String pattern = "^((((19|20)(([02468][048])|([13579][26]))-02-29))|((20[0-9][0-9])|(19[0-9][0-9]))-((((0[1-9])|(1[0-2]))-((0[1-9])|(1\\d)|(2[0-8])))|((((0[13578])|(1[02]))-31)|(((0[1,3-9])|(1[0-2]))-(29|30)))))$";
			
			if (!Pattern.matches(pattern, birthday)) {
				errors.add("生日日期格式無效。");
			}
			if (errors.size() > 0) { // 驗證失敗，將錯誤放進request後轉寄回去
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
			} else { // 驗證成功，創建會員
				Customer customer = new Customer();
				customer.setId(userid);
				customer.setName(name);
				customer.setPassword(password);
				customer.setAddress(address);
				customer.setPhone(phone);
				try {
					Date date = df.parse(birthday);
					customer.setBirthday(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// 將會員註冊進資料庫
				try {
					// 成功
					customerService.register(customer);
					request.getRequestDispatcher("login.jsp").forward(request, response);
				} catch (ServiceException e) {
					// 客戶帳號已經註冊
					errors.add("此帳號已經註冊。");
					request.setAttribute("errors", errors);
					request.getRequestDispatcher("customer_reg.jsp").forward(request, response);
				}
			}
		} else if ("login".equals(action)) {
			// 使用者登錄會員
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");
			Customer customer = new Customer();
			customer.setId(userid);
			customer.setPassword(password);
			if (customerService.login(customer)) { // 登錄成功，將使用者訊息放進session屬性中
				HttpSession session = request.getSession();
				session.setAttribute("customer", customer);
				request.getRequestDispatcher("main.jsp").forward(request, response);
			} else { // 登錄失敗，將錯誤訊息放進request中跳回login頁面
				List<String> errors = new ArrayList<>();
				errors.add("您輸入的帳號密碼有誤。");
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
