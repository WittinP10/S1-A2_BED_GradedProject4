package com.greatlearning.employeemanagment.service;
import com.greatlearning.employeemanagment.repository.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.greatlearning.employeemanagment.model.*;

@Service
public class EmployeeService {

//	private Set<Employee> employee=new HashSet<>();
	
	private EmployeeRepository employeeRepository;
	public EmployeeService(EmployeeRepository employeeRepository) {
		
		this.employeeRepository=employeeRepository;
	}
	
	
	
	public Employee addEmployee(Employee employee) {
	Employee savedEmployee=this.employeeRepository.save(employee);
	return savedEmployee;
	}
	
	
	public Set <Employee> fetchAll(){
		
		return new HashSet<>(this.employeeRepository.findAll());
		
	}
	
	public Employee fetchById(int id) {
		
		return this.employeeRepository.
				findById(id).
				orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
	}
	
	public void deleteEmployee(int id) {
		
		this.employeeRepository.deleteById(id);
	}
	
	public Employee updateEmployee(int id,Employee employee) {
		
		Employee repEmployee=this.fetchById(id);
		repEmployee.setFname(employee.getFname());
		repEmployee.setLname(employee.getLname());
		repEmployee.setAge(employee.getAge());
		repEmployee.setEmail(employee.getEmail());
		this.employeeRepository.save(repEmployee);
		return repEmployee;
		
	}
	
}
