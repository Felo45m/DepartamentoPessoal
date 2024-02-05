package com.felo.DepartamentoPessoal.repository;

import com.felo.DepartamentoPessoal.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{

}
