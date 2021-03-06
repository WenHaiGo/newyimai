package com.easybuy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.If;

import com.easybuy.model.EUser;
import com.easybuy.service.impl.EUServiceImpl;
import com.google.gson.Gson;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ManageUserServlet
 */
@WebServlet("/ManageUserServlet")
public class ManageUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入manageuserservlet");
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String param = request.getParameter("param");
		if (param != null && param.equals("manageLogin")) {
			login(request, response);
		}
		if (param != null && param.equals("allUser")) {
			try {
				showAllUser(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void showAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EUServiceImpl eus = new EUServiceImpl();
		List<EUser> allUser = eus.showAllUser();
		Gson gson = new Gson();
		String userList = gson.toJson(allUser);
		System.out.println(userList);
		PrintWriter out = response.getWriter();
		out.print(userList);

		out.flush();
		out.close();
		out = null;

	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		EUServiceImpl eus = new EUServiceImpl();
		String EUId = request.getParameter("EUId");
		String EUPwd = request.getParameter("EUPwd");

		boolean isExist = eus.checkEUId(EUId, EUPwd);
		JSONObject json = new JSONObject();
		if (isExist) {
			// 登陆成功就在session里面保存管理员登陆数据
			HttpSession session = request.getSession();// 获取session
			session.setAttribute("manageUser", EUId);
			System.out.println("登陆ok" + session.getAttribute("manageUser"));
			json.put("isExist", true);
		}

		else {
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
		System.out.println("jinrupsot");
		doGet(request, response);
	}

}
