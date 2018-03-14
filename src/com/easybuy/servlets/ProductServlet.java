package com.easybuy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

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
		// 加载所有商品
		if (param != null && param.equals("allPro")) {
			EProductServiceImpl esi = new EProductServiceImpl();
			try {
				List<EProduct> list = esi.getAllProduct();
				Gson gson = new Gson();
				String proList = gson.toJson(list);
				System.out.println(proList);
				PrintWriter out = response.getWriter();
				out.print(proList);
				// 释放资源
				out.flush();
				out.close();
				out = null;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 分类下的商品分页展示
		if (param != null && param.equals("currentCateg")) {

			System.out.println("========================");
			// 获取子类的id
			String cidStr = request.getParameter("cid");
			// 如何判断一定是数字字符串？
			int cid = Integer.parseInt(cidStr);

			int pageNo = 1;

			String pageNoStr = request.getParameter("pageNo");
			PageUtil<EProduct> pageUtil = null;
			System.out.println(pageNoStr);
			if (pageNoStr != null) {
				pageNo = Integer.parseInt(pageNoStr);
				pageUtil = epService.getCategProduct(cid, pageNo, 8);
			} else {
				pageUtil = epService.getCategProduct(cid, pageNo, 8);
			}

			EProductServiceImpl esi = new EProductServiceImpl();
			try {
				pageUtil = esi.getProductByCid(cid, pageNo, 8);
				Gson gson = new Gson();
				String pageUtilJ = gson.toJson(pageUtil);
				System.out.println("gdfhgrgregrlheigl" + pageUtilJ);
				response.getWriter().print(pageUtilJ);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// 添加商品和修改商品
		if (param != null && param.equals("save")) {

			save(request, response);

		}

		if (param != null && param.equals("delete")) {
			deleteById(request, response);
		}

		if (param != null && param.equals("getByProId")) {
			getByProId(request, response);

		}
		if (param != null && param.equals("specialProduct")) {
			// 这个参数真的不应该传入，既然变量名都是特卖的了为什么还要传入参数啊 传入参数和不传入参数会不会影响性能啊
			List<EProduct> list = epService.getSpecialSaleProduct(1);
			// 将list转换为json来一波
			Gson gson = new Gson();
			String productList = gson.toJson(list);
			response.getWriter().write(productList);
		}

		if (param != null && param.equals("saveCart")) {
			HttpSession session = request.getSession();
			// 获取商品id
			String proIdStr = request.getParameter("proId");
			int proId = Integer.parseInt(proIdStr);
			System.out.println("==============="+proId);
			// 先去查看session是否有已经存在的session cartlist
			if (session.getAttribute("cart") != null) {
				//将商品list取出来
				@SuppressWarnings("unchecked")
				List<Integer> list = (List<Integer>) session.getAttribute("cart");
				list.add(proId);
				//将商品list重新放到session里面
				session.setAttribute("cart", list);
			}
			// 如果没有就去创建购物车session
			else {
				// 创建放购车商品的list
				List<Integer> list = new ArrayList<>();
				// 给list里面添加商品
				list.add(proId);
				// 将该list放到session里面
				session.setAttribute("cart", list);
			}
			
			System.out.println(((List<Integer>)session.getAttribute("cart")).size());
		}
		/*
		
		
		
		
		
		
		*/
		if (param != null && param.equals("proview")) {
			System.out.println("========================");
			// 获取商品的id
			String proIdStr = request.getParameter("proId");
			System.out.println("sdsf" + proIdStr);
			// 如何判断一定是数字字符串？
			int proId = Integer.parseInt(proIdStr);
			EProductServiceImpl esi = new EProductServiceImpl();
			try {
				EProduct e = esi.getProById(proId);
				Gson gson = new Gson();
				String pro = gson.toJson(e);
				System.out.println("gdfhgrgregrlheigl" + pro);
				response.getWriter().print(pro);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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

	}

	void getByProId(HttpServletRequest request, HttpServletResponse response) {
		String proIdStr = request.getParameter("proId");
		if (proIdStr != null) {
			int proId = Integer.parseInt(proIdStr.trim());
			EProductServiceImpl esi = new EProductServiceImpl();
			try {
				EProduct e = esi.getProById(proId);
				Gson gson = new Gson();
				String ep = gson.toJson(e);
				PrintWriter out = response.getWriter();
				System.out.println("saa" + ep);
				out.print(ep);

				// 释放资源：

				out.close();
				out = null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	void deleteById(HttpServletRequest request, HttpServletResponse response) {
		EProductServiceImpl epi = new EProductServiceImpl();
		String proIdStr = request.getParameter("proId");
		System.out.println("==========" + proIdStr);
		int proId = Integer.parseInt(proIdStr);
		try {
			boolean isDel = epi.delById(proId);
			PrintWriter out = response.getWriter();
			if (isDel) {
				out.print("yes");

			} else {
				out.print("no");
			}

			out.flush();
			out.close();
			out = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void save(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("productName").trim();
		String pclass = request.getParameter("parentClass").trim();
		String cclass = request.getParameter("childClass").trim();
		String file = request.getParameter("photo").trim();
		String price = request.getParameter("productPrice").trim();
		String stock = request.getParameter("stock").trim();
		String desc = request.getParameter("desc").trim();
		String isSpecialPriceStr = request.getParameter("isSpecial").trim();
		// 利用三目运算获取是否特卖的int值
		int isSpecialPrice = isSpecialPriceStr.equals("yes") ? 1 : 0;

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
		String proIdStr = request.getParameter("proId");
		try {
			if (proIdStr == null) {
				isSave = esi.save(ep);
			}

			else {
				int proId = Integer.parseInt(proIdStr);
				isSave = esi.updateProById(proId, ep);
			}

			// 输出结果
			if (isSave) {
				response.sendRedirect("manage/manage-result.html");
			} else {
				System.out.println("wrong");

			}
		} catch (SQLException e) {
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
