package com.easybuy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.easybuy.model.ECartProduct;
import com.easybuy.model.EProduct;
import com.easybuy.service.impl.EProductServiceImpl;
import com.easybuy.service.impl.EUServiceImpl;

/**
 * Servlet implementation class UserActionServlet
 */
@WebServlet("/UserActionServlet")
public class UserActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserActionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	EUServiceImpl eus = new EUServiceImpl();
	EProductServiceImpl eps = new EProductServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		// 先判断是否登录。
		HttpSession session = request.getSession();
		String EUId = (String) session.getAttribute("userName");
		System.out.println("当前用户是当前用户是当前用户是当前用户是" + EUId);
		// clean code 里面说尽量不要做这种判断
		if (EUId != null) {
			// 登录了之后再次判断具体哪一种请求
			String param = request.getParameter("param");
			if (param != null && param.equals("cart")) {
				viewAllCartProduct(request, response, EUId);
			}

			if (param != null && param.equals("cartDele")) {
				deleCartProductByPid(request, response);
			}
			// 添加到购物车
			if (param != null && param.equals("cartAdd")) {
				addCartProductByPid(request, response, EUId);
			}
		}
		// 如果没有登录
		else {
			try {
				request.setAttribute("notLogin", "未登录");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	void addCartProductByPid(HttpServletRequest request, HttpServletResponse response, String EUId) throws IOException {
		// 添加的时候应该首先询问是否有该商品，若有则在数量上增加一 如果没有就新建一个商品
		int EPId = Integer.parseInt(request.getParameter("EPId"));
		boolean isSave = eps.cartSave(EUId, EPId, 1);
		// 这里不会做弹出窗口，直接输出了
		if (isSave) {
			PrintWriter pw = response.getWriter();
			pw.write("添加成功");
		}

		else {
			PrintWriter pw = response.getWriter();
			pw.write("添加失败");
		}
	}

	void deleCartProductByPid(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getParameter("EPId") + "wohainiashdsiufhfhfhfhfhfhfhfhfhfhfhfhfhfhfhfh");
		int EPId = Integer.parseInt(request.getParameter("EPId"));
		System.out.println("删除的商品是" + EPId);
		boolean isDelete = eps.deleCartProductByPid(EPId);
		if (isDelete) {
			try {

				response.sendRedirect("UserActionServlet?param=cart");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println("删除失败");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	// 得到购物车所有的商品
	void viewAllCartProduct(HttpServletRequest request, HttpServletResponse response, String EUId) {
		List<EProduct> cartProductList = eps.getAllCartProduct(EUId);
		List<ECartProduct> cartProductInfo = eps.getCartProductInfo(EUId);
		request.setAttribute("cartProductInfo", cartProductInfo);
		request.setAttribute("cartProductList", cartProductList);
		try {
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
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
