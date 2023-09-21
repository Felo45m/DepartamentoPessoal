package com.felo.DepartamentoPessoal.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.felo.DepartamentoPessoal.model.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="TB_EMPLOYEE")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Pattern(regexp = "^[A-Z]+(.)*")
	private String name;
	
	@CPF
	private String cpf;
	
	@NotBlank
	private String office;
	
	@NotBlank
	private String gender;
	
	@JsonFormat(pattern = "dd/MM/yyyy") 
	private LocalDate birthDate;

	@NotNull
	private Status status;
	
	public Employee() {
		
	}

	public Employee(Long id, String name, String cpf, String office, String gender, LocalDate birthDate, Status status) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.office = office;
		this.gender = gender;
		this.birthDate = birthDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(id, other.id);
	}

}
