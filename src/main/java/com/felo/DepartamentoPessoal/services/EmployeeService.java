package com.felo.DepartamentoPessoal.services;

import com.felo.DepartamentoPessoal.model.entities.Employee;
import com.felo.DepartamentoPessoal.repository.EmployeeRepository;
import com.felo.DepartamentoPessoal.services.exceptions.DatabaseException;
import com.felo.DepartamentoPessoal.services.exceptions.ObjectNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}
	
	public Employee findById(UUID employeeId) {
		Optional<Employee> employee = employeeRepository.findById(employeeId);
		return employee.orElseThrow(() -> new ObjectNotFoundException(null));
	}
	
	public Employee insertEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public void deleteEmployee(UUID employeeId) {
		try {
			employeeRepository.deleteById(employeeId);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException(employeeId);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Employee updateEmployee(UUID employeeId, Employee employee) {
		try {
			Employee entity = employeeRepository.getReferenceById(employeeId);
			updateData(entity, employee);
			return employeeRepository.save(entity);
		}
		catch (EntityNotFoundException e) {
			throw new ObjectNotFoundException(employeeId);
		}
	}
	
	private void updateData(Employee entity, Employee employee) {
		entity.setName(employee.getName());
		entity.setCpf(employee.getCpf());
		entity.setAge(employee.getAge());
		entity.setGender(employee.getGender());
		entity.setOffice(employee.getOffice());
		entity.setStatus(employee.getStatus());
	}
	
}
