package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;


public class EmployeeDAO {
	
	public List<Employee> findAll() throws Exception{
		List<Employee> emps = new ArrayList<Employee>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(
					"select * from t_emp_wangxin");
			rs = stmt.executeQuery();
			while(rs.next()){
				Employee emp = new Employee(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getDouble("salary"),
							rs.getInt("age")
						);
				emps.add(emp);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.close(conn);
		}
		return emps;
	}
	
	public void delete(int id) throws Exception{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("delete from t_emp_wangxin where id=?");
			stmt.setInt(1, id);
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.close(conn);
		}
	}
	
	public void save(Employee emp) throws Exception{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("insert into t_emp_wangxin values(emp_id_seq_wangxin.nextval,?,?,?)");
			stmt.setString(1, emp.getName());
			stmt.setDouble(2, emp.getSalary());
			stmt.setInt(3, emp.getAge());
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.close(conn);
		}
	}
	
	public Employee findById(int id) throws Exception{
		Employee emp = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("select * from t_emp_wangxin where id=?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()){
				emp = new Employee(
							rs.getInt("id"),
							rs.getString("name"),
							rs.getDouble("salary"),
							rs.getInt("age")
						);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.close(conn);
		}
		return emp;
	}

	public void modify(Employee emp)throws Exception{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement("update t_emp_wangxin set name=?,salary=?,age=? where id=?");
			stmt.setString(1, emp.getName());
			stmt.setDouble(2, emp.getSalary());
			stmt.setInt(3, emp.getAge());
			stmt.setInt(4, emp.getId());
			stmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}finally{
			DBUtil.close(conn);
		}
	}

	public static void main(String[] args) throws Exception{
		EmployeeDAO dao = new EmployeeDAO();
		List<Employee> emps = 
			dao.findAll();
		for(Employee emp:emps){
			System.out.println(emp);
		}
	}
}
