package com.heraizen.ems.dao;

import java.util.List;

import com.heraizen.ems.domain.DEPT;
import com.heraizen.ems.dto.EmployeeCountDTO;

public interface EmployeeDaoReports {
	public int getEmployeeCount(DEPT dept);

	public int totalEmployees();

	public List<EmployeeCountDTO> employeeCountByDept();
}
