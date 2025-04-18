package example.servlets;

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
 * Servlet implementation class addDeptServlet
 */
@WebServlet("/addDept")
public class addDeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out= response.getWriter();
		String deptNo=request.getParameter("deptNo");
		int dept_No=Integer.parseInt(deptNo);
		String Name=request.getParameter("deptName");
		String loc=request.getParameter("deptLoc");
		
		Department currentDept=new Department(dept_No,Name,loc);
		JdbcDao<Department, Integer> dao=new DepartmentDao();
		dao.add(currentDept);
		out.println("<h2>Department Added Successfully.</h2>");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
