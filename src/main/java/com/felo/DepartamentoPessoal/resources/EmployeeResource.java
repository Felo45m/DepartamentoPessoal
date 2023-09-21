package com.felo.DepartamentoPessoal.resources;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.felo.DepartamentoPessoal.model.entities.Employee;
import com.felo.DepartamentoPessoal.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/funcionarios/")
public class EmployeeResource {
	
	@Autowired
	EmployeeService employeeService;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException e) {
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			
			errors.put(fieldName, errorMessage);
		});
		
		return errors;
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> List = employeeService.findAll();
		return ResponseEntity.ok().body(List);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Employee> findById(@PathVariable Long id) {
		Employee client = employeeService.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@PostMapping
	public ResponseEntity<Employee> insertEmployee(@RequestBody @Valid Employee employee) {
		employee = employeeService.insertEmployee(employee);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(employee.getId()).toUri();
		return ResponseEntity.created(uri).body(employee);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody @Valid Employee employee) {
		employee = employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok().body(employee);
	}

}
