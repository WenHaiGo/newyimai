package com.easybuy.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easybuy.dbutil.CategUtils;
import com.easybuy.model.EPCateg;
import com.easybuy.service.impl.CategServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class CategServlet
 */
@WebServlet("/CategServlet")
public class CategServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategServlet() {
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
		if (param != null && param.equals("allCateg")) {
			showAllCateg(request, response);
		}

		if (param != null && param.equals("delCateg")) {
			delCateg(request, response);
		}

		if (param != null && param.equals("updateCateg")) {
			updateCategById(request, response);

		}
		if (param != null && param.equals("submitUpdate")) {
			submitUpdate(request, response);

		}
		// 需要在添加商品页面展示出所有分类
		if (param != null && param.equals("categ")) {

		}

	}

	private void submitUpdate(HttpServletRequest request, HttpServletResponse response) {
		String idString = request.getParameter("id");
		EPCateg categ = new EPCateg();
		String pidString = request.getParameter("parentId");
		// 这里有必要根据分类名字获取相对应的id 否则都是默认的0
		try {
			if (idString != null && pidString != null) {
				int pid = new CategServiceImpl().getIdByName(pidString);
				System.out.println("sdusdhfjhsjfhjsadhfja" + pid);
				categ.setEPCId(Integer.parseInt(idString));
				categ.setEPCName(request.getParameter("className"));
				categ.setEPCParentId(pid);
				CategServiceImpl csi = new CategServiceImpl();
				csi.updateById(Integer.parseInt(idString), categ);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void updateCategById(HttpServletRequest request, HttpServletResponse response) {
		String idString = request.getParameter("id");
		// 得到这个分类的所有数据然后转发给编辑页面
		CategServiceImpl csi = new CategServiceImpl();
		if (idString != null) {
			try {
				EPCateg categ = csi.getByCid(Integer.parseInt(idString));
				List<CategUtils> list = new CategServiceImpl().getCategoryOfTree();
				request.setAttribute("allCateg", list);
				request.setAttribute("currentCateg", categ);
				request.getRequestDispatcher("productClass-modify.jsp").forward(request, response);
			} catch (NumberFormatException | SQLException e) {
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

	}

	private boolean delCateg(HttpServletRequest request, HttpServletResponse response) {
		String categId = request.getParameter("categId");
		CategServiceImpl csi = new CategServiceImpl();
		if (categId != null) {
			try {
				boolean isDel = csi.delCateg(Integer.parseInt(categId));
				if (isDel) {
					request.getRequestDispatcher("manageProductCateg.jsp").forward(request, response);
				} else {
					return false;

				}
			} catch (NumberFormatException | SQLException e) {
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
		return false;
	}

	private void showAllCateg(HttpServletRequest request, HttpServletResponse response) {
		CategServiceImpl csi = new CategServiceImpl();
		try {
			List<CategUtils> list = csi.getCategoryOfTree();
			/*
			 * 把分类树转换为json对象
			 */
			Gson gson = new Gson();
			String categTree = gson.toJson(list);
			System.out.println("dssssssssssss"+categTree);
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().write(categTree);

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
