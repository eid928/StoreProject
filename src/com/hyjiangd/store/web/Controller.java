package com.hyjiangd.store.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hyjiangd.store.domain.Customer;
import com.hyjiangd.store.domain.Goods;
import com.hyjiangd.store.service.CustomerService;
import com.hyjiangd.store.service.GoodsService;
import com.hyjiangd.store.service.OrdersService;
import com.hyjiangd.store.service.ServiceException;
import com.hyjiangd.store.service.imp.CustomerServiceImp;
import com.hyjiangd.store.service.imp.GoodsServiceImp;
import com.hyjiangd.store.service.imp.OrdersServiceImp;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private CustomerService customerService = new CustomerServiceImp();
	private GoodsService goodsService = new GoodsServiceImp();
	private OrdersService ordersService = new OrdersServiceImp();
	// 分頁實體變數
	private int totalPageNumber = 0; 
	private int pageSize = 20;
	private int currentPage = 1;
    
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
		} else if ("list".equals(action)) {
			// 顯示商品列表，第一頁
			List<Goods> goodsList = goodsService.queryAll();
			
			totalPageNumber = (int) Math.ceil((double)goodsList.size() / (double)pageSize);
			System.out.println("總頁數 = " + totalPageNumber);
			
			request.setAttribute("totalPageNumber", totalPageNumber);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("goodsList", goodsList.subList(pageSize*(currentPage-1), Math.min(pageSize*currentPage, goodsList.size())));
			// 若使用者在最後一頁，進入商品詳細頁面後又按商品列表(Controller?action=list)想返回最後一頁
			// action=list會呼叫這個method
			// 此時因為是最後一頁，pageSize*currentPage將會超過list.size()，所以要取min()
			request.getRequestDispatcher("goods_list.jsp").forward(request, response);
		} else if ("paging".equals(action)) {
			// 翻頁商品列表
			String page = request.getParameter("page");
			if("prev".equals(page)) { // 上一頁
				currentPage = currentPage == 1? 1 : currentPage - 1;
			} else if ("next".equals(page)) { // 下一頁
				currentPage = currentPage == totalPageNumber? currentPage : currentPage + 1;;
			} else {
				currentPage = Integer.parseInt(page);
			}
			
			System.out.println("currentPage = " + currentPage);
			
			List<Goods> goodsList = goodsService.queryByStartEnd(pageSize*(currentPage-1), pageSize*currentPage - 1);
			// 與前面不同，queryByStartEnd的底層是sql的limit, offset，即使超過最大值也無所謂
			request.setAttribute("totalPageNumber", totalPageNumber);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("goodsList", goodsList);
			request.getRequestDispatcher("goods_list.jsp").forward(request, response);	
		} else if ("detail".equals(action)) {
			// 查看商品詳細訊息
			String id = request.getParameter("id");
			Goods goods = goodsService.queryDetail(Long.parseLong(id));
			
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("goods_detail.jsp").forward(request, response);
		} else if ("add".equals(action)) {
			// 加進購物車
			Long goodsid = Long.parseLong(request.getParameter("id"));
			String goodsName = request.getParameter("name");
			float goodsPrice = Float.parseFloat(request.getParameter("price"));
			
			// 購物車為List<Map<String, Object>>，一個Map為一個商品
			// 從session中取出購物車
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> cart = (List<Map<String, Object>>)request.getSession().getAttribute("cart");
			// 若尚未建構購物車，則建構
			if (cart == null) {
				cart = new ArrayList<Map<String, Object>>();
				request.getSession().setAttribute("cart", cart);
			}
			
			boolean alreadyInCart = false;
			
			for (Map<String, Object> item : cart) {
				Long goodsidInCart = (Long) item.get("goodsid");
				if (goodsid.equals(goodsidInCart)) { // 若購物車內已有相同的商品，則商品數量+1
					// 兩個Long物件應用equals進行比較，==是判斷兩者是否指向相等
					Integer quantity = (Integer) item.get("quantity");
					quantity += 1;
					item.put("quantity", quantity);
					alreadyInCart = true;
				}
			}
			
			if (!alreadyInCart) { // 若購物車沒有相同的商品，則建構一個商品map並放到購物車內
				Map<String, Object> item = new HashMap<>();
				
				item.put("goodsid", goodsid);
				item.put("goodsName", goodsName);
				item.put("goodsPrice", goodsPrice);
				item.put("quantity", 1);
				cart.add(item);
			}
			
			System.out.println(cart);
			String page = request.getParameter("page");
			
			if ("list".equals(page)) { // 從列表添加，則跳回列表
				request.getRequestDispatcher("Controller?action=list").forward(request, response);
			} else if ("detail".equals(page)) { // 從詳細頁面添加，則跳回詳細頁面
				request.getRequestDispatcher("Controller?action=detail&id=" + goodsid).forward(request, response);
			}
		} else if ("cart".equals(action)) { 
			// 查看購物車
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
			
			double total = 0;
			// 購物車已存在session中
			// 此處主要目的是算出總金額傳給cart.jsp
			if (cart != null) {
				for (Map<String, Object> item : cart) {
					Integer quantity = (Integer) item.get("quantity");
					Float goodsPrice = (Float) item.get("goodsPrice");
					double subtotal = goodsPrice * quantity;
					total += subtotal;
				}
			}
			
			request.setAttribute("total", total);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
			
		} else if ("sub_ord".equals(action)) {
			// 提交訂單
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");
			
			for (Map<String, Object> item : cart) {
				Long goodsid = (Long) item.get("goodsid");
				int quantity = Integer.parseInt(request.getParameter("quantity_" + goodsid));
				// 這邊數量並非由session的購物車中取出，因為使用者可能在前端更改數量再提交訂單。在前端更動是不會更新session中的購物車的。
				item.put("quantity", quantity);
			}
			
			String ordersid = ordersService.submitOrders(cart);
			request.setAttribute("ordersid", ordersid);
			request.getRequestDispatcher("order_finish.jsp").forward(request, response);
			// 將購物車清空
			request.getSession().removeAttribute("cart");
		} else if ("main".equals(action)) {
			request.getRequestDispatcher("main.jsp").forward(request, response);
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
