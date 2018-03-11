package com.easybuy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.easybuy.model.EUser;
import com.easybuy.service.impl.EUServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	EUServiceImpl euService = new EUServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		System.err.println("进入了user色rvlet");

		String param = request.getParameter("param");
		if (param != null && param.equals("reg")) {
			// TODO Auto-generated method stub
			register(request, response);
		}

		if (param != null && param.equals("login")) {
			login(request, response);
		}
		
	}

	void register(HttpServletRequest request, HttpServletResponse response) {
		String EUId = request.getParameter("EUId");
		String EUPwd = request.getParameter("EUPwd");

		// 在使用session上有一个问题就是：是不是只需要声明一次就可以后面一直存了呢？
		HttpSession session = request.getSession();// 获取session
		boolean flag = false;
		try {
			flag = euService.save(EUId, EUPwd);
			System.out.println("注册"+flag);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (flag) {
			session.setAttribute("userName", EUId);
			try {
				response.sendRedirect("reg-result.html");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void login(HttpServletRequest request, HttpServletResponse response) {
		String EUId = request.getParameter("EUId");
		String EUPwd = request.getParameter("EUPwd");
		boolean isExist = euService.checkEUId(EUId, EUPwd);
		if (isExist) {
			HttpSession session = request.getSession();// 获取session
			session.setAttribute("userName", EUId);
		}
		JSONObject json = new JSONObject();
		if (isExist) {
			json.put("isExist", true);
		} else {
			json.put("isExist", false);
		}
		try {
			response.getWriter().println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
