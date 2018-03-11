package com.easybuy.servlets;

import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.easybuy.model.ENews;
import com.easybuy.model.EUser;
import com.easybuy.service.ENewsService;
import com.easybuy.service.impl.ENewsServiceImpl;
import com.easybuy.service.impl.EUServiceImpl;

/**
 * Servlet implementation class Manage
 */
@WebServlet("/ManageOperateServlet")
public class ManageOperateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ManageOperateServlet() {
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
		System.out.println("进入一号地点");
		// String param = request.getParameter("param");
		String param = request.getParameter("param");
		System.err.println("cnsahi shu " + param);
		if (param != null && param.equals("addUser")) {
			addUser(request, response);
		}
		if (param != null && param.equals("submitUpdate")) {
			System.out.println("jinrhewuifdhgui");
			int id = Integer.parseInt(request.getParameter("submitUpdateId"));
			updateUserById(request, response, id);
		}

		if (param != null && param.equals("updateUser")) {
			int id = Integer.parseInt(request.getParameter("updateId"));
			// 根据id得到user然后把user转发到编辑页面
			EUServiceImpl eus = new EUServiceImpl();
			EUser user = eus.findUserById(id);
			request.setAttribute("user", user);
			request.getRequestDispatcher("manageUpdateUser.jsp").forward(request, response);
		}

		if (param != null && param.equals("delUser")) {
			// 获取要删除用户的id
			int id = Integer.parseInt(request.getParameter("id"));
			delUserById(request, response, id);
		}
		if (param != null && param.equals("manageNews")) {
			showAllNews(request, response);
		}

		if (param != null && param.equals("updateNews")) {

			int updateId = Integer.parseInt(request.getParameter("updateNewsId"));
			updateNewsById(request, response, updateId);
		}
		if (param != null && param.equals("submitUpdateNews")) {

			int updateId = Integer.parseInt(request.getParameter("submitUpdateNewsId"));
			ENews en = new ENews();
			en.setENContent(request.getParameter("content"));
			en.setENId(Integer.parseInt(request.getParameter("submitUpdateNewsId")));
			Date time = new Date(System.currentTimeMillis());
			en.setENTime(time);
			en.setENTitle(request.getParameter("title"));
			ENewsService ens = new ENewsServiceImpl();
			boolean isUpdate = ens.updateNewsById(en, updateId);
			if (isUpdate) {
				try {
					response.sendRedirect("ManageOperateServlet?param=manageNews");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			else {
				System.out.println("修改失败");
			}
		}

		if (param != null && param.equals("delNews")) {

			int delId = Integer.parseInt(request.getParameter("delNewsId"));
			delNewsById(request, response, delId);
		}

	}

	private void updateNewsById(HttpServletRequest request, HttpServletResponse response, int updateId) {
		ENewsServiceImpl ens = new ENewsServiceImpl();
		ENews en = ens.findNewsById(updateId);
		if (en == null)
			System.out.println("niahoishulhfdilhdsilfhdslkjfhdlkjsdflhkjsdhlkjds");
		else {
			System.out.println("111111111111111111111111");
		}
		// System.out.println("nihao" + en.getENContent());
		request.setAttribute("news", en);

		try {
			request.getRequestDispatcher("manageUpdateNews.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void delNewsById(HttpServletRequest request, HttpServletResponse response, int delId) {
		ENewsServiceImpl en = new ENewsServiceImpl();
		boolean isDel = en.delNewsById(delId);
		if (isDel) {
			try {
				response.sendRedirect("ManageOperateServlet?param=manageNews");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			System.out.println("删除失败");
		}
	}

	private void showAllNews(HttpServletRequest request, HttpServletResponse response) {
		ENewsServiceImpl ens = new ENewsServiceImpl();
		List<ENews> allNews = ens.newsTitle();
		request.setAttribute("allNews", allNews);
		try {
			request.getRequestDispatcher("manageNews.jsp").forward(request, response);

		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void updateUserById(HttpServletRequest request, HttpServletResponse response, int id) {
		EUser user = new EUser();
		String userName = request.getParameter("userName");
		String name = request.getParameter("name");
		String passWord = request.getParameter("passWord");
		String sex = request.getParameter("sex");
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthday = request.getParameter("birthday");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		String photo = request.getParameter("photo");
		user.setEUAddress(address);
		// 处理时间
		String birthTime = birthyear + "-" + birthmonth + "-" + birthday;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			// 将util的时间转换为sql里的代码希望可以保存到数据库中
			date = format.parse(birthTime);
			java.sql.Date date2 = new Date(date.getTime());
			user.setEUBirthday(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		user.setEUId(userName);

		user.setEUMoible(mobile);

		user.setEUName(name);
		// 上传头像应该是上传到一个图片文件夹里，然后向数据里面保存一个地址就好了
		user.setEUPhoto(photo);

		user.setEUPwd(passWord);

		user.setEUSex(sex);
		EUServiceImpl eus = new EUServiceImpl();
		boolean isUpdate = eus.updateUserById(user, id);

		if (isUpdate) {
			try {
				response.sendRedirect("ManageUserServlet?param=showAllUser");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			System.out.println("修改失败");
		}
	}

	private void delUserById(HttpServletRequest request, HttpServletResponse response, int id) {
		EUServiceImpl eus = new EUServiceImpl();
		boolean isDel = eus.delUserById(id);
		if (isDel) {
			try {
				response.sendRedirect("ManageUserServlet?param=showAllUser");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else {
			System.out.println("删除失败");
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		// 真实姓名
		EUser user = new EUser();
		String name = request.getParameter("name");
		String passWord = request.getParameter("passWord");
		String sex = request.getParameter("sex");
		String birthyear = request.getParameter("birthyear");
		String birthmonth = request.getParameter("birthmonth");
		String birthday = request.getParameter("birthday");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		String photo = request.getParameter("photo");
		user.setEUAddress(address);
		// 处理时间
		String birthTime = birthyear + "-" + birthmonth + "-" + birthday;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			// 将util的时间转换为sql里的代码希望可以保存到数据库中
			date = format.parse(birthTime);
			java.sql.Date date2 = new Date(date.getTime());
			user.setEUBirthday(date2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		user.setEUId(userName);

		user.setEUMoible(mobile);

		user.setEUName(name);
		// 上传头像应该是上传到一个图片文件夹里，然后向数据里面保存一个地址就好了
		user.setEUPhoto(photo);

		user.setEUPwd(passWord);

		user.setEUSex(sex);

		// 将user上传到数据库
		EUServiceImpl eus = new EUServiceImpl();
		boolean isSave = eus.saveUserFromManage(user);

		if (isSave) {
			try {
				System.out.println("shifpuidhsin " + isSave);
				request.getRequestDispatcher("manage-result.html").forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;

		}

		else {
			System.out.println("保存失败");
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
