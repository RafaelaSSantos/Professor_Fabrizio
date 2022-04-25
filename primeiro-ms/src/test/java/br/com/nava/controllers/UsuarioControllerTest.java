package br.com.nava.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.nava.dtos.UsuarioDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void getAllTest() throws Exception{		
		ResultActions response = mockMvc.perform(get("/usuarios").contentType("application/json"));
	
		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr );		
	
		UsuarioDTO[] lista = mapper.readValue(responseStr, UsuarioDTO[].class);

		assertThat(lista).isNotEmpty();
	}
	
	@Test
	void getOneTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/usuarios/1").contentType("application/json"));

		MvcResult result = response.andReturn();
	
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		UsuarioDTO usuario = mapper.readValue(responseStr, UsuarioDTO.class);
		
		assertThat(usuario.getId() ).isEqualTo(1);
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
	
	@Test
	void saveTest() throws Exception {
		UsuarioDTO usuario = createValidUsuario();			
		
		ResultActions response = mockMvc.perform(post("/usuarios").content(mapper.writeValueAsString(usuario)).contentType("application/json"));

		MvcResult result = response.andReturn();
	
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
	
		UsuarioDTO usuarioSalvo = mapper.readValue(responseStr, UsuarioDTO.class);

		isUsuarioValid(usuario, usuarioSalvo);	
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
	
	@Test
	void alterarTest() throws Exception{
		UsuarioDTO usuario = createValidUsuario();

		ResultActions response = mockMvc.perform(patch("/usuarios/2").content(mapper.writeValueAsString(usuario)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
	
		UsuarioDTO usuarioSalvo = mapper.readValue(responseStr, UsuarioDTO.class);
		
		isUsuarioValid(usuario, usuarioSalvo);			
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
	
	@Test
	void deleteTest() throws Exception{
		ResultActions response = mockMvc.perform(delete("/usuarios/5").contentType("application/json"));

		MvcResult result = response.andReturn();
		
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
	
	//metodos
	private UsuarioDTO createValidUsuario() {
		UsuarioDTO usuario = new UsuarioDTO();			
		usuario.setNome("Carlos");
		usuario.setEmail("carlos@gmail.com");
		return usuario;
	}

	private void isUsuarioValid(UsuarioDTO usuario, UsuarioDTO usuarioSalvo) {
		assertThat(usuarioSalvo.getId()).isPositive();
		assertThat(usuarioSalvo.getNome()).isEqualTo(usuario.getNome());	
		assertThat(usuarioSalvo.getEmail()).isEqualTo(usuario.getEmail());
	}	
}