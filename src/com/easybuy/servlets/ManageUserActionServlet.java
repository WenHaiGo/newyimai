package com.easybuy.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.coyote.RequestGroupInfo;

/**
 * Servlet implementation class manageUserActionServlet
 */
@WebServlet("/ManageUserActionServlet")
public class ManageUserActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageUserActionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 判断是否登录
		System.out.println("进入manageuserActionServlet");
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("manageUser");
		// 如果未登录直接去登陆
		if (user == null) {
			// 关于为什么使用转发目的就是为了保留普通用户的信息，
			// java web到底怎么样才算一个请求呢 什么时候一个请求失效了？
			// request.getRequestDispatcher("manage/manageLogin.jsp").forward(request,
			// response);
			response.sendRedirect("manageLogin.jsp");
		} else {
			request.getRequestDispatcher("manageIndex.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
