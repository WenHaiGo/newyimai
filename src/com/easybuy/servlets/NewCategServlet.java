package com.easybuy.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easybuy.model.EPCateg;
import com.easybuy.service.impl.CategServiceImpl;
import com.google.gson.Gson;

/**
 * Servlet implementation class NewCategServlet
 */
@WebServlet("/NewCategServlet")
public class NewCategServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewCategServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 为了使得ajax中文显示正常
		response.setContentType("text/html; charset=utf-8");
		String param = request.getParameter("param");

		// 修改分类前把之前的信息传到编辑页面
		if (param != null && param.equals("currentCateg")) {
			updateCategById(request, response);
		}

		if (param != null && param.equals("allParent")) {
			getAllParent(request, response);
		}
		if (param != null && param.equals("update")) {
			updateByCid(request, response);
		}
		// 通过大类id获取所有子类
		if (param != null && param.equals("getByPid")) {
			getCategByPid(request, response);
		}
		if (param != null && param.equals("delete")) {
			delCategByPid(request, response);
		}
	}

	void delCategByPid(HttpServletRequest request, HttpServletResponse response) {
		// 获取要删除的cid
		String cidStr = request.getParameter("cid");
		CategServiceImpl csi = new CategServiceImpl();
		if(cidStr!=null) {
			int cid = Integer.parseInt(cidStr);
			try {
				boolean isDel = csi.delByCid(cid);
				if(isDel) {
					response.getWriter().print("yes");
				}
				else {
					response.getWriter().print("no");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void getCategByPid(HttpServletRequest request, HttpServletResponse response) {
		CategServiceImpl csi = new CategServiceImpl();
		String pidStr = request.getParameter("pid");
		if (pidStr != null) {
			int pid = Integer.parseInt(pidStr);
			try {
				List<EPCateg> list = csi.getByPid(pid);
				Gson gson = new Gson();
				String allChildCateg = gson.toJson(list);
				System.out.println(allChildCateg);
				response.getWriter().write(allChildCateg);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	void updateByCid(HttpServletRequest request, HttpServletResponse response) {
		String pidStr = request.getParameter("pid");
		String cname = request.getParameter("cname");
		String cidStr = request.getParameter("cid");
		if (pidStr != null && cidStr != null) {
			int pid = Integer.parseInt(pidStr);
			int cid = Integer.parseInt(cidStr);
			CategServiceImpl categServiceImpl = new CategServiceImpl();
			EPCateg categ = new EPCateg();
			categ.setEPCId(cid);
			categ.setEPCName(cname);
			categ.setEPCParentId(pid);
			try {
				boolean isUpdate = categServiceImpl.updateById(cid, categ);

				System.out.println("nihao" + isUpdate);
				if (isUpdate) {
					response.getWriter().print("yes");
				} else {
					response.getWriter().print("no");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	void getAllParent(HttpServletRequest request, HttpServletResponse response) {
		CategServiceImpl categServiceImpl = new CategServiceImpl();
		try {
			List<EPCateg> categ = categServiceImpl.getByPid(0);
			Gson gson = new Gson();
			String AllParentCateg = gson.toJson(categ);
			response.getWriter().write(AllParentCateg);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void updateCategById(HttpServletRequest request, HttpServletResponse response) {

		String idString = request.getParameter("cid");
		CategServiceImpl categServiceImpl = new CategServiceImpl();
		if (idString != null) {
			int id = Integer.parseInt(idString);
			try {
				EPCateg categ = categServiceImpl.getByCid(id);
				Gson gson = new Gson();
				String beforeCateg = gson.toJson(categ);
				response.getWriter().write(beforeCateg);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
