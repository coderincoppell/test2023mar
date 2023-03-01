package com.labcorp.hr.entity;

public interface EmployeeI {
	public enum EmployeeType {
		HOURLY,
		SALARIED,
		MANAGER
	}
	public long getEmployeeId();
	public EmployeeType getType();
	
	public int getWorkedDays();
	public float getVacationTaken();
	public int Work(int workedDays) throws Exception;
	public float TakeVacation(float vacationUsed) throws Exception;
}
