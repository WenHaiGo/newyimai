package com.easybuy.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easybuy.model.EProduct;
import com.easybuy.service.EProductService;
import com.easybuy.service.impl.EProductServiceImpl;

/**
 * Servlet implementation class ManageProductServlet
 */
@WebServlet("/ManageProductServlet")
public class ManageProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageProductServlet() {
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
		request.setCharacterEncoding("utf-8");
		String param = request.getParameter("param");
		if (param != null && param.equals("allProduct")) {

			showAllProduct(request, response);
		}
		
		
	}

	private void showAllProduct(HttpServletRequest request, HttpServletResponse response) {
		EProductService eps = new EProductServiceImpl();
		try {
			List<EProduct> allProductList = eps.getAllProduct();
			request.setAttribute("allProduct", allProductList);
			request.getRequestDispatcher("manageProduct.jsp").forward(request, response);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		doGet(request, response);
	}

}
