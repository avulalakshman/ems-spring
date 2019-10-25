package com.heraizen.ems.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.heraizen.ems.dao.EmployeeRepo;
import com.heraizen.ems.domain.DEPT;
import com.heraizen.ems.domain.Employee;
import com.heraizen.ems.service.EmployeeService;

@SpringBootTest
class EmployeeBookApplicationTests {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private EmployeeRepo employeeRepo;

	@BeforeEach
	void clear() {
		employeeRepo.deleteAll();
	}

	@Test
	void addEmployeeWithValidData() {
		Employee employee = SeedDataUtil.getEmployeeObject();
		Employee retObjct = employeeService.addEmployee(employee);
		assertAll(() -> assertTrue(retObjct.getId() != null, () -> "Employee Object id shouldn't null"),
				() -> assertTrue(employee.getName().equals(retObjct.getName()), "Both names must be same"));
	}

	@Test
	void addMultipleEmployees() {
		List<Employee> employees =SeedDataUtil.getEmployeeObjects();
		List<Employee> retEmployees = employeeService.addEmployees(employees);
		assertTrue(employees.size() == retEmployees.size());
		assertTrue(retEmployees.stream().map(e -> e.getId()).allMatch(e -> !e.isEmpty() || e != null));
	}

	@Test
	void searchEmployeeWithExisting() {
		List<Employee> employees =SeedDataUtil.getEmployeeObjects();
		employeeService.addEmployees(employees);
		List<Employee> searchList_email = employeeService.search("com");
		List<Employee> searchList_name = employeeService.search("man");
		List<Employee> searchList_not_match = employeeService.search("Zee");

		assertAll(() -> assertTrue(searchList_email.size() == 3), () -> assertTrue(searchList_name.size() == 2),
				() -> assertTrue(searchList_not_match.size() == 0));

	}

	@Test
	void searchEmployeeById() {
		List<Employee> employees = SeedDataUtil.getEmployeeObjects();
		List<Employee> retList = employeeService.addEmployees(employees);
		assertTrue(retList.stream().map(e -> e.getId()).allMatch(e -> employeeService.searchById(e).isPresent()));
	}
	
	@Test
	void deleteEmployee() {
		List<Employee> employees = SeedDataUtil.getEmployeeObjects();
		List<Employee> retList = employeeService.addEmployees(employees);
		Employee employee = retList.get(0);
		boolean isDeleted = employeeService.deleteEmployee(employee.getId());
		List<Employee> afterDeleteList = employeeService.employees();
		assertAll(()->assertTrue(isDeleted),()->assertTrue(afterDeleteList.size() == 2));
	}
	@Test
	void updateEmployee() {
		Employee employee = SeedDataUtil.getEmployeeObject();
		
		Employee savedEmployee = employeeService.addEmployee(employee);
		savedEmployee.setName("Ramesh");
		savedEmployee.setEmail("ramesh.n@heraizen.com");
		
		Employee updatedEmployee = employeeService.updateEmployee(savedEmployee);
		
		assertAll(
				()->assertTrue(savedEmployee.getName().equals(updatedEmployee.getName())),
				()->assertTrue(savedEmployee.getEmail().equals(updatedEmployee.getEmail())),
				()->assertTrue(savedEmployee.getId().equals(updatedEmployee.getId())
				));
	}



}
