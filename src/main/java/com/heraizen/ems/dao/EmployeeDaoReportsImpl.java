package com.heraizen.ems.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

import java.util.List;

import org.omg.CORBA.INTERNAL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.heraizen.ems.domain.DEPT;
import com.heraizen.ems.dto.EmployeeCountDTO;

@Repository
public class EmployeeDaoReportsImpl implements EmployeeDaoReports {

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeDaoReportsImpl.class);
	private MongoOperations mongoOperations;

	@Autowired
	public EmployeeDaoReportsImpl(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}

	@Override
	public int getEmployeeCount(DEPT dept) {
		return 0;
	}

	@Override
	public int totalEmployees() {
		return 0;
	}

	@Override
	public List<EmployeeCountDTO> employeeCountByDept() {
		Aggregation agg = newAggregation(
						
						   group("dept").count().as("count"),
				           project().and("_id").as("dept").and("count").as("count")
				           
						  );

		List<EmployeeCountDTO> dbObjects = mongoOperations.aggregate(agg, "employee", EmployeeCountDTO.class)
				.getMappedResults();
		LOG.info("Employee count by deptno : " + agg);
		return dbObjects;
	}

}
