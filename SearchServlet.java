package example.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import example.jdbc.Department;
import example.jdbc.DepartmentDao;
import example.jdbc.JdbcDao;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/searchDept")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		String deptNo=request.getParameter("deptNo");
		int dept_No=Integer.parseInt(deptNo);
		JdbcDao<Department, Integer> dao= new DepartmentDao();
		Department foundDepartment=dao.getOne(dept_No);
		RequestDispatcher dispather=null;
		if(foundDepartment == null) {
			out.print("<h2>Not Found due to invalid ID.please try Again.</h2>");
			dispather=request.getRequestDispatcher("searchDept.html");
			dispather.include(request, response);
		}
		else {
			out.print("<h2> Found</h2>");
			dispather=request.getRequestDispatcher("showDept");
			request.setAttribute("loadedDept", foundDepartment);
			dispather.forward(request, response);
			
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
