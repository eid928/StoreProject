package com.hyjiangd.store.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hyjiangd.store.domain.Customer;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = {"*.jsp", "/Controller"})
public class LoginCheckFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String action = req.getParameter("action");
		
		Customer customer = (Customer) req.getSession().getAttribute("customer");
		if (customer == null && !"login".equals(action) && !"reg_init".equals(action)) { 
			// 除非欲登錄或欲進入註冊頁面，否則session中沒有customer屬性的話返回登錄頁面 
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
