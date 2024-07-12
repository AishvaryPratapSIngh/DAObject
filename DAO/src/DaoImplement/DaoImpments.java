package DaoImplement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Dao.dao;
import model.Employee;

public class DaoImpments implements dao {
	private Connection con = null;
	private String url = "jdbc:mysql://localhost:3306/dao";
	private String username = "root";
	private String password = "aish0305";

	public DaoImpments() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addEmployee(Employee e) {
		String query = "INSERT INTO `employee`(`name`,`email`,`department`,`salary`) VALUES(?,?,?,?)";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, e.getName());
			ps.setString(2, e.getEmail());
			ps.setString(3, e.getDepartment());
			ps.setInt(4, e.getSalary());
			System.out.println(ps.executeUpdate());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Employee getEmployee(int id) {
		String query = "SELECT * FROM `employee` WHERE `id` = ?";
		PreparedStatement ps = null;
		ResultSet res = null;
		Employee emp = null;
		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			res = ps.executeQuery();
			if (res.next()) {
				int id1 = res.getInt("id");
				String name = res.getString("name");
				String email = res.getString("email");
				String department = res.getString("department");
				int salary = res.getInt("salary");
				emp = new Employee(id1, name, email, department, salary);
			}
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		close(res, ps, con);
		return emp;
	}

	@Override
	public List<Employee> getAll() {
		String query = "SELECT * FROM `employee`";
		Statement st = null;
		ResultSet res = null;
		Employee emp = null;
		List<Employee> al = new ArrayList<>();
		try {
			st = con.createStatement();
			res = st.executeQuery(query);
			while (res.next()) {
				int id1 = res.getInt("id");
				String name = res.getString("name");
				String email = res.getString("email");
				String department = res.getString("department");
				int salary = res.getInt("salary");
				emp = new Employee(id1, name, email, department, salary);
				al.add(emp);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return al;
	}

	@Override
	public void update(Employee e, String col, String new_val, int id1) {
		String query = "UPDATE `employee` SET `" + col + "` = ? WHERE `id` = ?";
		try (PreparedStatement ps = con.prepareStatement(query)) {
			switch (col.toLowerCase()) {
			case "name":
				ps.setString(1, new_val);
				break;
			case "email":
				ps.setString(1, new_val);
				break;
			case "department":
				ps.setString(1, new_val);
				break;
			case "salary":
				ps.setInt(1, Integer.parseInt(new_val));
				break;
			default:
				return;
			}
			ps.setInt(2, id1);
			ps.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void delete(int e) {
		// TODO Auto-generated method stub

	}

	void close(ResultSet res, PreparedStatement ps, Connection con) {

		try {
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
