package com.labcorp.hr.workvacationtrack.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.labcorp.hr.entity.EmployeeI;

@Component
public class EmployeeRepo {
	private static Map<EmployeeI.EmployeeType, List<EmployeeI>> employeeList = new HashMap<>(); 
	
	/**
	 * Adds an employee to the respective list by type 
	 * @param emp
	 * @return resulting employee count after the employee has been added
	 */
	public int addEmployee(EmployeeI emp) {
		if( ! EmployeeRepo.employeeList.containsKey(emp.getType())) {
			EmployeeRepo.employeeList.put(emp.getType(), new ArrayList<EmployeeI>());
		}
		
		List<EmployeeI> empTypeList = EmployeeRepo.employeeList.get(emp.getType());
		empTypeList.add(emp);
		
		return empTypeList.size();
	}
	
	public EmployeeI getEmployee(long eid) {
		EmployeeI emp = null;
		EmployeeI.EmployeeType[] EmpTypeArr = employeeList.keySet().toArray(new EmployeeI.EmployeeType[0]);
		for(int i=0; i<EmpTypeArr.length && emp == null; i++) {
			emp = employeeList.get(EmpTypeArr[i])
					.stream()
					.filter(e -> e.getEmployeeId() == eid)
					.findFirst()
					.orElse(null);
		}
		return emp;
	}
}
