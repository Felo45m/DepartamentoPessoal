package com.felo.DepartamentoPessoal.services.exceptions;

import java.io.Serial;
import java.util.UUID;

public class ObjectNotFoundException extends RuntimeException {
	@Serial
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(UUID id) {
		super("Objeto não encontrado");
	}

}
