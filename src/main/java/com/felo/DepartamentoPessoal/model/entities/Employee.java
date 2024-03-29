package com.felo.DepartamentoPessoal.model.entities;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felo.DepartamentoPessoal.model.enums.Status;

@Setter
@Getter
@Entity
@Table(name="TB_EMPLOYEE")
public class Employee implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID employeeId;

	@NotBlank
	@Pattern(regexp = "^[A-Z]+(.)*", message = "Por favor, digite apenas letras.")
	private String name;
	
	@CPF(message = "Campo inválido!")
	private String cpf;
	
	@NotBlank(message = "Campo não informado! Por favor, informe dados válidos.")
	private String office;
	
	@NotBlank(message = "Campo não informado! Por favor, informe dados válidos.")
	private String gender;

	@Min(value = 18, message = "A idade deve ser no mínimo 18 anos")
	@Max(value = 65, message = "A idade deve ser no máximo 65 anos")
	@NotNull(message = "A idade não pode ser nula")
	private Integer age;

	@NotNull(message = "Campo inválido! Por favor, informe um status válido [ATIVO, INATIVO")
	private Status status;
	
	public Employee() {
		
	}

	public Employee(String name, String cpf, String office, String gender, Integer age, Status status) {
		this.name = name;
		this.cpf = cpf;
		this.office = office;
		this.gender = gender;
		this.age = age;
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(employeeId, employee.employeeId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeId);
	}
}