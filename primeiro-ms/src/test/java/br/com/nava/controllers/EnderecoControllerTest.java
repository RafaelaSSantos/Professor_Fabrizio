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
import br.com.nava.dtos.EnderecoDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	void getAllTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/enderecos").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		EnderecoDTO[] lista = mapper.readValue(responseStr, EnderecoDTO[].class);

		assertThat(lista).isNotEmpty();
	}

	@Test
	void getOneTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/enderecos/5").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		EnderecoDTO endereco = mapper.readValue(responseStr, EnderecoDTO.class);

		assertThat(endereco.getId()).isEqualTo(5);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void saveTest() throws Exception {

		EnderecoDTO endereco = createValidEndereco();

		ResultActions response = mockMvc.perform(
				post("/enderecos").content(mapper.writeValueAsString(endereco)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		EnderecoDTO enderecoSalvo = mapper.readValue(responseStr, EnderecoDTO.class);

		// validar
		isEnderecoValid(endereco, enderecoSalvo);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void alterarTest() throws Exception {
		EnderecoDTO endereco = createValidEndereco();

		ResultActions response = mockMvc.perform(
				patch("/enderecos/1").content(mapper.writeValueAsString(endereco)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		EnderecoDTO enderecoSalvo = mapper.readValue(responseStr, EnderecoDTO.class);

		// validar
		isEnderecoValid(endereco, enderecoSalvo);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void deleteTest() throws Exception {
		ResultActions response = mockMvc.perform(delete("/enderecos/11").contentType("application/json"));

		MvcResult result = response.andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	// metodos
	private EnderecoDTO createValidEndereco() {
		EnderecoDTO endereco = new EnderecoDTO();
		endereco.setRua("Joaquim Novas");
		endereco.setNumero(45);
		endereco.setCep("06730000");
		endereco.setCidade("Marilha");
		endereco.setEstado("SP");
		return endereco;
	}

	private void isEnderecoValid(EnderecoDTO endereco, EnderecoDTO enderecoSalvo) {
		assertThat(enderecoSalvo.getId()).isPositive();
		assertThat(enderecoSalvo.getRua()).isEqualTo(endereco.getRua());
		assertThat(enderecoSalvo.getNumero()).isEqualTo(endereco.getNumero());
		assertThat(enderecoSalvo.getCep()).isEqualTo(endereco.getCep());
		assertThat(enderecoSalvo.getCidade()).isEqualTo(endereco.getCidade());
		assertThat(enderecoSalvo.getEstado()).isEqualTo(endereco.getEstado());
	}
}