package com.labcorp.hr.workvacationtrack.controller;

import java.rmi.NoSuchObjectException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labcorp.hr.entity.EmployeeI;
import com.labcorp.hr.workvacationtrack.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/")
@Slf4j
public class EmployeeController {
	@Autowired
	EmployeeService empSrvc;
	
	@GetMapping("/employee/{eid}")
	public ResponseEntity<EmployeeI> geEmployees(@PathVariable long eid) throws NoSuchObjectException {
		ResponseEntity<EmployeeI> response;
		EmployeeI emp = empSrvc.getEmployee(eid);
		if(emp == null) {
			throw new NoSuchObjectException("Employee does not exist with the provided id: " + eid);
		} else {
			response = new ResponseEntity<EmployeeI>(emp, HttpStatus.OK);
		}
		return response;
	}
	
	@PostMapping("employee/{eid}/work/{daysWorked}")
	public ResponseEntity<EmployeeI> logWork(@PathVariable long eid, @PathVariable int daysWorked) throws NoSuchObjectException {
		ResponseEntity<EmployeeI> response;
		EmployeeI emp = empSrvc.getEmployee(eid);		
		if(emp == null) {
			log.error("Employee not found for id: " + eid);
			throw new NoSuchObjectException("Employee does not exist with the provided id: " + eid);
		} else {
			try {
				emp.Work(daysWorked);
				response = new ResponseEntity<EmployeeI>(emp, HttpStatus.OK);
			} catch (Exception e) {
				response = new ResponseEntity<EmployeeI>(emp, HttpStatus.BAD_REQUEST);
				log.error("Exception while updating vacation for employee-id: " + emp.getEmployeeId() + " with worked days input: " +  daysWorked);
			}
		}
		return response;		
	}
	
	@PostMapping("employee/{eid}/vacation/{vacationUsed}")
	public ResponseEntity<EmployeeI> logVacation(@PathVariable long eid, @PathVariable float vacationUsed) throws NoSuchObjectException {
		ResponseEntity<EmployeeI> response;
		EmployeeI emp = empSrvc.getEmployee(eid);		
		if(emp == null) {
			throw new NoSuchObjectException("Employee does not exist with the provided id: " + eid);
		} else {
			try {
				emp.TakeVacation(vacationUsed);
				response = new ResponseEntity<EmployeeI>(emp, HttpStatus.OK);
			} catch (Exception e) {
				response = new ResponseEntity<EmployeeI>(emp, HttpStatus.BAD_REQUEST);
				log.error("Exception while updating vacation for employee-id: " + emp.getEmployeeId() + " with vacation used input: " +  vacationUsed);
			}
		}
		return response;		
	}
}
