package com.heraizen.ems.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.heraizen.ems.dao.EmployeeRepo;
import com.heraizen.ems.domain.Employee;
import com.heraizen.ems.dto.EmployeeCountDTO;
import com.heraizen.ems.service.EmployeeReports;
import com.heraizen.ems.service.EmployeeService;
@SpringBootTest
public class EmployeeReportsTest {
	@Autowired
	private EmployeeReports empReports;
	@Autowired
	private EmployeeRepo employeeRepo;
	@Autowired
	private EmployeeService employeeService;
	@BeforeEach
	void clear() {
		employeeRepo.deleteAll();
	}
	
	@Test
	void employeeCountByDept() {
		List<Employee> employees =SeedDataUtil.getEmployeeObjects();
		List<Employee> retEmployees = employeeService.addEmployees(employees);
		List<EmployeeCountDTO> list = empReports.employeeCountByDept();
		list.forEach(System.out::println);
		assertTrue(list.size() > 0);
	}
}
