package com.easybuy.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easybuy.service.impl.EUServiceImpl;

/**
 * Servlet implementation class CheckEUIdServlet
 */
@WebServlet("/CheckEUIdServlet")
public class CheckEUIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckEUIdServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	// 尽管在使用ajax的post方法但是代码还是在get方法里面写 是不是以后都要在get里面写啊
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String EUId = request.getParameter("EUId");
		System.out.println(EUId);
		System.out.println("nnihao");
		EUServiceImpl service = new EUServiceImpl();
		Boolean isExist = service.checkEUId(EUId);
		System.out.println("jieguoshio"+isExist);
		response.getWriter().write("{\"isExist\":" + isExist + "}");

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
