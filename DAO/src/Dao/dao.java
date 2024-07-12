package Dao;

import java.util.List;

import model.Employee;

public interface dao {
	public void addEmployee(Employee e);

	public Employee getEmployee(int e);

	public List<Employee> getAll();

	public void update(Employee e, String col, String new_val, int id);

	public void delete(int e);

}
