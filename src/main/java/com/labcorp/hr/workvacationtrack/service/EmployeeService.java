package com.labcorp.hr.workvacationtrack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.labcorp.hr.entity.EmployeeI;
import com.labcorp.hr.workvacationtrack.repo.EmployeeRepo;

@Component
public class EmployeeService {
	
	@Autowired
	EmployeeRepo repo;
	
	public int addEmployee(EmployeeI emp) {
		return repo.addEmployee(emp);
	}
	
	public EmployeeI getEmployee(long eid) {
		return repo.getEmployee(eid);
	}
}
