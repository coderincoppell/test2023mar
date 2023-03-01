package com.labcorp.hr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "id", "employmentType", "maxWorkDays", "workedDays", "maxVacationDays", "vacationTaaken" })
public class EmployeeBase implements EmployeeI {
	
	public long employeeId = -1;
	@JsonIgnore
	private EmployeeType employeeType = EmployeeType.HOURLY;
	private final int maxWorkDays;
	private int workedDays = 0;
	@JsonIgnore
	private float vacationUsed = 0.0f;		
	private final float maxVacationDays; 

	public EmployeeBase(long pEmployeeId, EmployeeType pEmployeeType, int pMaxWorkDays, float pMaxVacationDays) {
		this.employeeId = pEmployeeId;
		this.employeeType = pEmployeeType;
		this.maxWorkDays = pMaxWorkDays;
		this.maxVacationDays = pMaxVacationDays;
	}
	
	@Override
	@JsonProperty("id")
	public long getEmployeeId() {
		return employeeId;
	}
	
	@Override
	@JsonProperty("employmentType")
	public EmployeeType getType() {
		return this.employeeType;
	}
	
	@Override
	public int Work(int pWorkedDays) throws Exception {
		if(this.workedDays + pWorkedDays > maxWorkDays) {
			throw new Exception(this.workedDays + " working days have alredy been reported and the input of: " + pWorkedDays + " work days will exceed the allowed vacation days of: " + this.maxWorkDays);
		}
		this.workedDays += pWorkedDays;
		return this.workedDays;
	}
	
	public int getWorkedDays() {
		return this.workedDays;
	}
	
	@Override
	public float TakeVacation(float pVacationUsed) throws Exception {
		if(this.vacationUsed + pVacationUsed > maxVacationDays) {
			throw new Exception(this.vacationUsed + " Vacation days have alredy been used and the input: " + pVacationUsed + " vacation will exceed the allowed vacation days of: " + this.maxVacationDays);
		}
		this.vacationUsed += pVacationUsed;
		return this.vacationUsed;
	}
	
	@Override
	public float getVacationTaken() {
		return this.vacationUsed;
	}
}
