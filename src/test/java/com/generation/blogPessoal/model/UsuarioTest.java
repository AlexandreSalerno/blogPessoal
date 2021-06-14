package com.generation.blogPessoal.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioTeste {

	public Usuario usuario;

	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();

	@BeforeEach
	public void start() {
		usuario = new Usuario("Ale", "123456789");
	}

	@Test
	void testAtributosUsuario() {
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
		System.out.println(violacao.toString());
		assertTrue(violacao.isEmpty());
	}

	@Test
	void testSenhaNula() {
		Usuario usuarioSemSenha = new Usuario();
		usuarioSemSenha.setUsuario("Maria1990");
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioSemSenha);
		System.out.println(violacao.toString());
		assertFalse(violacao.isEmpty());
	}

	@Test
	void testUsuarioNulo() {
		Usuario usuarioSemUsuario = new Usuario();
		usuarioSemUsuario.setSenha("1123456789");
		Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioSemUsuario);
		System.out.println(violacao.toString());
		assertFalse(violacao.isEmpty());
	}

}
