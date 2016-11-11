package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import entity.Employee;

public class ActionServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1321524682165408916L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");		
		String uri = 
			request.getRequestURI();
		uri = uri.substring(
				uri.lastIndexOf("/"),
				uri.lastIndexOf("."));
		EmployeeDAO empDAO = 
			new EmployeeDAO();
		if(uri.equals("/list")){
			try
			{
				List<Employee> emps =
					empDAO.findAll();
				request.setAttribute(
					"allEmps",emps);
				request.getRequestDispatcher("listEmp.jsp")
				.forward(request,response);				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}else if(uri.equals("/del")){
			int id = Integer.parseInt(
					request.getParameter("id"));
			try{
				empDAO.delete(id);
				response.sendRedirect("list.do");
			}catch(Exception ex){
				
			}
		}else if(uri.equals("/add")){
			Employee emp=new Employee();
			emp.setName(request.getParameter("name"));
			emp.setSalary(Double.parseDouble(request.getParameter("salary")));
			emp.setAge(Integer.parseInt(request.getParameter("age")));
			try{
			empDAO.save(emp);
			response.sendRedirect("list.do");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}else if(uri.equals("/load")){
			int id=Integer.parseInt(request.getParameter("id"));
			try{
				Employee emp=empDAO.findById(id);
				System.out.println(emp);
				request.setAttribute("emp", emp);
				request.getRequestDispatcher("updateEmp.jsp")
				.forward(request,response);	
			}catch (Exception e) {
				e.printStackTrace();
		}}else if(uri.equals("/save")){
			Employee emp=new Employee();
			try{
			emp.setName(request.getParameter("name"));
			emp.setSalary(Double.parseDouble(request.getParameter("salary")));
			emp.setAge(Integer.parseInt(request.getParameter("age")));
			empDAO.save(emp);
			response.sendRedirect("list.do");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
