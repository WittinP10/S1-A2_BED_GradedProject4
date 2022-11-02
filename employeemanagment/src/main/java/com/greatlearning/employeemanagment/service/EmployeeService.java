package com.greatlearning.employeemanagment.service;

import com.greatlearning.employeemanagment.repository.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.greatlearning.employeemanagment.model.*;

@Service
public class EmployeeService {

//	private Set<Employee> employee=new HashSet<>();

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {

		this.employeeRepository = employeeRepository;
	}

	public Employee addEmployee(Employee employee) {
		Employee savedEmployee = this.employeeRepository.save(employee);
		return savedEmployee;
	}

	public Set<Employee> fetchAll() {

		return new HashSet<>(this.employeeRepository.findAll());

	}

	public Employee fetchById(int id) {

		return this.employeeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Id"));
	}

	public void deleteEmployee(int id) {

		this.employeeRepository.deleteById(id);
	}

	public Employee updateEmployee(int id, Employee employee) {

		Employee repEmployee = this.fetchById(id);
		repEmployee.setFname(employee.getFname());
		repEmployee.setLname(employee.getLname());
		repEmployee.setAge(employee.getAge());
		repEmployee.setEmail(employee.getEmail());
		this.employeeRepository.save(repEmployee);
		return repEmployee;

	}

	public Set<Employee> fetchByName(String name) {

//		List <Employee> empWithName=new ArrayList();
//		ListIterator<Employee> list=empWithName.listIterator();

		Set<Employee> empWithName = new HashSet<>();

		Set<Employee> employee = this.fetchAll();

		for (Employee emp : employee) {

			if (emp.getFname().equals(name)) {
				empWithName.add(emp);
			}

		}
		return empWithName;
	}

	public List<Employee> fetchByOrder(String order) {

		List<Employee> employee = new ArrayList<>(this.employeeRepository.findAll());

		Collections.sort(employee, new Sortbyname());
		
		if (order .equals("asc")) {
			return employee;
		}

		else {
			Collections.reverse(employee);
			return employee;
		}

	

	}

}
