package com.felo.DepartamentoPessoal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.felo.DepartamentoPessoal.model.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Optional<Employee> findById(Long id);
}
