package com.labcorp.hr.workvacationtrack.service;

import java.util.stream.LongStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.labcorp.hr.entity.EmployeeI.EmployeeType;
import com.labcorp.hr.entity.Employee;
import com.labcorp.hr.workvacationtrack.repo.EmployeeRepo;

@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	EmployeeRepo empRepo;
	
    @Value("${employee.work.days}") 
    private int maxWorkDays;
    
    @Value("${employee.hourly.vacation.days}") 
    private float vacationDaysHourly;        
    @Value("${employee.salaried.vacation.days}") 
    private float vacationDaysSalaried;    
    @Value("${employee.manager.vacation.days}") 
    private float vacationDaysManager;

	@Override public void onApplicationEvent(ContextRefreshedEvent event) {
		LongStream.range(1, 11).forEach(i -> {
			empRepo.addEmployee(new Employee(i, EmployeeType.HOURLY, maxWorkDays, vacationDaysHourly));
			empRepo.addEmployee(new Employee(i+10, EmployeeType.SALARIED, maxWorkDays, vacationDaysSalaried));
			empRepo.addEmployee(new Employee(i+20, EmployeeType.MANAGER, maxWorkDays, vacationDaysManager));
		});
	}
}
