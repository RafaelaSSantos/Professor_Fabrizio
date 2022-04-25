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
import br.com.nava.dtos.ProdutoDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	void getAllTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/produtos").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ProdutoDTO[] lista = mapper.readValue(responseStr, ProdutoDTO[].class);

		assertThat(lista).isNotEmpty();
	}

	@Test
	void getOneTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/produtos/1").contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ProdutoDTO produto = mapper.readValue(responseStr, ProdutoDTO.class);

		assertThat(produto.getId()).isEqualTo(1);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void saveTest() throws Exception {
		ProdutoDTO produto = createValidProduto();

		ResultActions response = mockMvc
				.perform(post("/produtos").content(mapper.writeValueAsString(produto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ProdutoDTO produtoSalvo = mapper.readValue(responseStr, ProdutoDTO.class);

		//validação
		isProdutoValid(produto, produtoSalvo);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void alterarTest() throws Exception {
		ProdutoDTO produto = createValidProduto();

		ResultActions response = mockMvc.perform(
				patch("/produtos/1").content(mapper.writeValueAsString(produto)).contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

		System.out.println(responseStr);

		ProdutoDTO produtoSalvo = mapper.readValue(responseStr, ProdutoDTO.class);

		//validação
		isProdutoValid(produto, produtoSalvo);
		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	@Test
	void deleteTest() throws Exception {
		ResultActions response = mockMvc.perform(delete("/produtos/2").contentType("application/json"));

		MvcResult result = response.andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(200);
	}

	//metodo
	private ProdutoDTO createValidProduto() {
		ProdutoDTO produto = new ProdutoDTO();
		produto.setNome("Paçoca");
		produto.setDescricao("Gostoso");
		produto.setPreco(35);
		return produto;
	}
	
	private void isProdutoValid(ProdutoDTO produto, ProdutoDTO produtoSalvo) {
		assertThat(produtoSalvo.getId()).isPositive();
		assertThat(produtoSalvo.getNome()).isEqualTo(produto.getNome());
		assertThat(produtoSalvo.getDescricao()).isEqualTo(produto.getDescricao());
		assertThat(produtoSalvo.getPreco()).isEqualTo(produto.getPreco());
	}
}