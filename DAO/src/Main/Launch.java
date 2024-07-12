package Main;

import java.util.List;
import java.util.Scanner;

import DaoImplement.DaoImpments;
import model.Employee;

public class Launch {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			System.out.println(
					"What you want to do? Add or display the info: 1 = add, 2 = display,3 = diplay All, 4= Update ,5= Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				addemp();
				break;
			case 2:
				getemp();
				break;
			case 3:
				getall();
				break;
			case 4:
				Updates();
				break;
			case 5:
				System.out.println("Exiting...");
				sc.close();
				System.exit(0);
			default:
				System.out.println("Invalid choice. Please choose 1, 2, or 3 ,4.");
			}
		}
	}

	static void addemp() {
		Employee e = new Employee();
		System.out.println("Enter the name of the employee");
		e.setName(sc.next());
		System.out.println("Enter the email of the employee");
		e.setEmail(sc.next());
		System.out.println("Enter the department of the employee");
		e.setDepartment(sc.next());
		System.out.println("Enter the salary of the employee");
		e.setSalary(sc.nextInt());
		DaoImpments dao = new DaoImpments();
		dao.addEmployee(e);
	}

	static void getemp() {
		DaoImpments dao = new DaoImpments();
		System.out.println("Enter the id of the employee whose data you want");
		int id = sc.nextInt();
		Employee e = dao.getEmployee(id);
		if (e != null) {
			System.out.println(
					e.getId() + " " + e.getName() + " " + e.getEmail() + " " + e.getDepartment() + " " + e.getSalary());
		} else {
			System.out.println("Employee not found");
		}
	}

	static void getall() {
		DaoImpments dao = new DaoImpments();
		List<Employee> emps = dao.getAll();
		for (Employee e : emps) {
			System.out.println(e);
		}
	}

	static void Updates() {
		DaoImpments dao = new DaoImpments();
		Employee e = new Employee();
		System.out.println("Enter the col name you want update");
		String update = sc.next();
		System.out.println("Enter the new value of the col");
		String new_value = sc.next();
		System.out.println("Enter the id i which you want to update");
		int id = sc.nextInt();
		dao.update(e, update, new_value, id);
	}
}
