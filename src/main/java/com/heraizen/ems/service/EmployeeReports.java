package com.heraizen.ems.service;

import java.util.List;

import com.heraizen.ems.domain.DEPT;
import com.heraizen.ems.dto.EmployeeCountDTO;

public interface EmployeeReports {
		
	public int getEmployeeCount(DEPT dept);
	public int totalEmployees();
	public List<EmployeeCountDTO> employeeCountByDept();
}
