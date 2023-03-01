package com.labcorp.hr.workvacationtrack;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.labcorp.hr.workvacationtrack.controller.EmployeeController;
import com.labcorp.hr.workvacationtrack.repo.EmployeeRepo;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class WorkVacationTrackAppTests {

	@Autowired
  	private MockMvc mockMvc;

  	@Autowired
  	private EmployeeRepo employeeRepo;
	  
	@Autowired
	EmployeeController employeeController;
	
	@Test
	void contextLoads() {
		assertThat(employeeController).isNotNull();
	}

	@Test
	void getUserWorks() throws Exception {
		mockMvc.perform(get("/employee/{id}", 1l))
		.andExpect(status().is2xxSuccessful());
	}
	

	@Test
	void getUserFails() throws Exception {
		mockMvc.perform(get("/employee/{id}", 1000l))
		.andExpect(status().is4xxClientError());
	}
	
	@Test
	void logWorkWorks() throws Exception {
		long empId = 1l;
		assertThat(employeeRepo.getEmployee(empId).getWorkedDays()).isEqualTo(0);
		
		mockMvc.perform(post("/employee/{id}/work/{days}", empId, 1))
		.andExpect(status().is2xxSuccessful());
		
		assertThat(employeeRepo.getEmployee(empId).getWorkedDays()).isEqualTo(1);
		
		mockMvc.perform(post("/employee/{id}/work/{days}", empId, 5))
		.andExpect(status().is2xxSuccessful());
		
		assertThat(employeeRepo.getEmployee(1l).getWorkedDays()).isEqualTo(6);
	}
	
	@Test
	void logWorkWithBadRequest() throws Exception {
		long empId = 10l;
		int maxWorkDays = 260;
		assertThat(employeeRepo.getEmployee(empId).getWorkedDays()).isEqualTo(0);
		
		mockMvc.perform(post("/employee/{id}/work/{days}", empId, 1))
		.andExpect(status().is2xxSuccessful());
		
		assertThat(employeeRepo.getEmployee(empId).getWorkedDays()).isEqualTo(1);
		
		mockMvc.perform(post("/employee/{id}/work/{days}", empId, maxWorkDays))
		.andExpect(status().is4xxClientError());
		
		assertThat(employeeRepo.getEmployee(empId).getWorkedDays()).isEqualTo(1);
	}
	
	@Test
	void logVacationWorks() throws Exception {
		long empId = 1l;
		assertThat(employeeRepo.getEmployee(empId).getVacationTaken()).isEqualTo(0);
		
		mockMvc.perform(post("/employee/{id}/vacation/{vacation}", empId, 1.5f))
									.andExpect(status().is2xxSuccessful());
		assertThat(employeeRepo.getEmployee(empId).getVacationTaken()).isEqualTo(1.5f);
		
		mockMvc.perform(post("/employee/{id}/vacation/{vacation}", empId, 2.5))
									.andExpect(status().is2xxSuccessful());
		assertThat(employeeRepo.getEmployee(empId).getVacationTaken()).isEqualTo(4.0f);
	}
	
	@Test
	void logVacationWithBadRequest() throws Exception {
		long empId = 10l;
		float maxVacationDays = 30.0f;
		assertThat(employeeRepo.getEmployee(empId).getVacationTaken()).isEqualTo(0);
		
		mockMvc.perform(post("/employee/{id}/vacation/{vacation}", empId, 2.5))
		.andExpect(status().is2xxSuccessful());
		
		assertThat(employeeRepo.getEmployee(empId).getVacationTaken()).isEqualTo(2.5f);
		
		mockMvc.perform(post("/employee/{id}/vacation/{vacation}", empId, maxVacationDays))
		.andExpect(status().is4xxClientError());
		
		assertThat(employeeRepo.getEmployee(empId).getVacationTaken()).isEqualTo(2.5f);
	}
}
