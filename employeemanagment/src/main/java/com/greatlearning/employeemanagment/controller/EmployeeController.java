package com.greatlearning.employeemanagment.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.employeemanagment.model.Employee;
import com.greatlearning.employeemanagment.repository.EmployeeRepository;
import com.greatlearning.employeemanagment.service.EmployeeService;


@RestController("/employeemanagement")
public class EmployeeController {


	private EmployeeService employeeService;
	
	public EmployeeController (EmployeeService employeeService) {
		
		this.employeeService=employeeService;
	}
	
	//get all employee
	@GetMapping("/getall")
	public Set<Employee> getEmployee(){
		
	
		return employeeService.fetchAll();
		
		}
	
	//get specific employee
	@GetMapping("/getemployee/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) {
		
		return this.employeeService.fetchById(id);
		
	}
	//testing
	@GetMapping("/gethello")
	public String hello(){
		return "Hello";
	}
	
	//add an employee
	@PostMapping("/addemployee")
	public Employee saveEmployee (@RequestBody Employee employee){
		
		return this.employeeService.addEmployee(employee);
	}
	
	@DeleteMapping("/deleteemployee/{id}")
	public void deleteEmployee(@PathVariable("id") int id) {
		
		this.employeeService.deleteEmployee(id);
	
	}
	
	@PutMapping("/updateemployee/{id}")
	public Employee updateEmployee (@PathVariable("id") int id,@RequestBody Employee employee){
		
		 return this.employeeService.updateEmployee(id,employee);
	}
	
}
