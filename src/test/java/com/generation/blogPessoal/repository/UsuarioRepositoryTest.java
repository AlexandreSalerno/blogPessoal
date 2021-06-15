package com.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.blogPessoal.model.Usuario;
import com.generation.blogPessoal.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository repository;
	
	@BeforeAll
	public void start() {
		Usuario usuario = new Usuario("email1", "123");
		if((repository.findByUsuario("email1").isEmpty())){
			service.cadastrarUsuario(usuario);
		}
		usuario = new Usuario("email2", "1234");
		if((repository.findByUsuario("email2").isEmpty())){
			service.cadastrarUsuario(usuario);
		}
		usuario = new Usuario("email3", "12345");
		if((repository.findByUsuario("email3").isEmpty())){
			service.cadastrarUsuario(usuario);
		}
	}
	
	@Test
	public void getByUsuario() throws Exception {
		Optional<Usuario> usuario = repository.findByUsuario("email2");
		assertTrue(usuario.get().getUsuario().equals("email2"));
	}
	
	@Test
	public void getAllByUsuarioContainingIgnoreCase() {
		List<Usuario> usuario = repository.findAllByUsuarioContainingIgnoreCase("mail");
		assertEquals(3, usuario.size());
	}
	
	@AfterAll
	public void end() {
		repository.deleteAll();
	}
}
