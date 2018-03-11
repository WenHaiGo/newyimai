package com.easybuy.servlets;

import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.easybuy.dbutil.PageUtil;
import com.easybuy.model.EPCateg;
import com.easybuy.model.EProduct;
import com.easybuy.service.impl.EProductServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String param = request.getParameter("param");

		EProductServiceImpl epService = new EProductServiceImpl();
		if (param != null && param.equals("specialProduct")) {
			// 这个参数真的不应该传入，既然变量名都是特卖的了为什么还要传入参数啊 传入参数和不传入参数会不会影响性能啊
			List<EProduct> list = epService.getSpecialSaleProduct(1);
			// 将list转换为json来一波
			Gson gson = new Gson();
			String productList = gson.toJson(list);
			response.getWriter().write(productList);
		}

		if (param != null && param.equals("productView")) {
			String str = request.getParameter("EPId");
			if (str != null) {
				System.out.println(str);
				int EPID = Integer.parseInt(str);
				// 根据商品id得到该id商品所有的商品信息。
				EProduct ep = epService.getDetailProduct(EPID);

				request.setAttribute("EProduct", ep);

				request.getRequestDispatcher("product-view.jsp").forward(request, response);
			}
		}

		if (param != null && param.equals("productCateg")) {
			List<EPCateg> list = epService.getCateg();
			Gson gson = new Gson();
			String productCategList = gson.toJson(list);
			response.getWriter().write(productCategList);

		}

		if (param != null && param.equals("hotProduct")) {
			List<EProduct> list = epService.getHotProduct(99);
			// 将list转换为json来一波
			Gson gson = new Gson();
			String productList = gson.toJson(list);
			response.getWriter().write(productList);
		}

		if (param != null && param.equals("categ")) {
			System.out.println("========================");
			// 获取子类的id
			String str = request.getParameter("EPCId");
			// 如何判断一定是数字字符串？
			int EPCId = Integer.parseInt(str);

			// 默认是第一页
			int pageNo = 1;

			String pageNoStr = request.getParameter("pageNo");
			PageUtil<EProduct> pageUtil = null;
			if (pageNoStr != null) {
				pageNo = Integer.parseInt(pageNoStr);
				pageUtil = epService.getCategProduct(EPCId, pageNo, 8);
			} else {
				pageUtil = epService.getCategProduct(EPCId, pageNo, 8);
			}

			// 因为是从一个页面跳转到另外一个页面，而且需要上一页的数据 所以不通过ajax 而是通过jsp实现
			request.setAttribute("pageUtil", pageUtil);
			request.setAttribute("EPCId", EPCId);
			request.getRequestDispatcher("product-list.jsp").forward(request, response);

		}

		if (param != null && param.equals("save")) {

			save(request, response);

		}
	}

	void save(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("productName");
		String pclass = request.getParameter("parentClass");
		String cclass = request.getParameter("childClass");
		String file = request.getParameter("photo");
		String price = request.getParameter("productPrice");
		String stock = request.getParameter("stock");
		String desc = request.getParameter("desc");
		String isSpecialPriceStr = request.getParameter("isSpecial");
		//利用三目运算获取是否特卖的int值
		int isSpecialPrice = isSpecialPriceStr.equals("yes")?1:0;
		
		System.out.println(isSpecialPrice);
		// 完成赋值
		EProduct ep = new EProduct();
		ep.setEPCChildId(Integer.parseInt(cclass));
		// 这就是大类
		ep.setEPCId(Integer.parseInt(pclass));
		ep.setEPDesc(desc);
		ep.setEPFile(file);
		ep.setEPName(name);
		ep.setEPPrice(Integer.parseInt(price));
		ep.setEPStock(Integer.parseInt(stock));
		ep.setIsSpecialPrice(isSpecialPrice);
		EProductServiceImpl esi = new EProductServiceImpl();
		boolean isSave = false;
		try {
			isSave = esi.save(ep);

			if (isSave) {
				System.out.println("ok");
			} else {
				System.out.println("no");
			}
		} catch (SQLException e) {
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
