package com.labcorp.hr.entity;

import lombok.Builder;

public class Employee extends EmployeeBase implements EmployeeI {
	
	@Builder
	public Employee(long pEmployeeId, EmployeeType pEmployeeType, int pMaxWorkDays, float pMaxVacationDays) {
		super(pEmployeeId, pEmployeeType, pMaxWorkDays, pMaxVacationDays);
	}
}
