package com.heraizen.ems.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heraizen.ems.dao.EmployeeDaoReports;
import com.heraizen.ems.domain.DEPT;
import com.heraizen.ems.dto.EmployeeCountDTO;

@Service
public class EmployeeReportsImpl implements EmployeeReports {

	private EmployeeDaoReports employeeDaoReports;

	@Autowired
	public EmployeeReportsImpl(EmployeeDaoReports employeeDaoReports) {
		this.employeeDaoReports = employeeDaoReports;
	}

	@Override
	public int getEmployeeCount(DEPT dept) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totalEmployees() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EmployeeCountDTO> employeeCountByDept() {
		return employeeDaoReports.employeeCountByDept();
	}

}
