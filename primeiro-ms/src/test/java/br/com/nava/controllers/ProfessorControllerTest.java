package br.com.nava.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.UnsupportedEncodingException;
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
import br.com.nava.dtos.ProfessorDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfessorControllerTest {
	
	//reponsavel por criar as requisocoes REST para a camada Controller
	@Autowired
	private MockMvc mockMvc;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void getAllTest() throws Exception{
		//armazena o objeto que fara o test e colher o resultado
		ResultActions response = mockMvc.perform(get("/professores").contentType("application/json"));
		
		//Pegando o resultado via mvcResult
		MvcResult result = response.andReturn();
		
		//pegando o resultado no formato de String e pasando con o fomato UTF-8
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr );		
		
		//Coverte o resultado de string em um array de objetos de professordto
		ProfessorDTO[] lista = mapper.readValue(responseStr, ProfessorDTO[].class);
		
		//verificando se a lista não é vazia 
		assertThat(lista).isNotEmpty();
	}
	
	@Test
	void getOneTest() throws Exception {
		//armazena o objeto que fará o test e colher o resultado
		ResultActions response = mockMvc.perform(get("/professores/5").contentType("application/json"));
		
		//pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		
		//pegando o resultado no formato de String e pasando con o fomato UTF-8
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		ProfessorDTO professor = mapper.readValue(responseStr, ProfessorDTO.class);
		
		assertThat(professor.getId() ).isEqualTo(5);
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
	
	@Test
	void saveTest() throws Exception {
		
		//criando um objeto professorDTO para enviarmos junto com a requisição
		ProfessorDTO professor = createValidProfessor();	
		
		//armazena o objeto que fará o test e colher o resultado
		ResultActions response = mockMvc.perform(post("/professores")
				.content(mapper.writeValueAsString(professor)).contentType("application/json"));
		
		//pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		
		//pegando o resultado no formato de String e pasando con o fomato UTF-8
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
	
		ProfessorDTO professorSalvo = mapper.readValue(responseStr, ProfessorDTO.class);
		
		//verificar se foi salvo
		isProfessorValid(professor, professorSalvo);
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}	
	
	
	@Test
	void alterarTest() throws Exception{
		
		//criando um objeto professorDTO para enviarmos junto com a requisição
		ProfessorDTO professor = createValidProfessor();	
		
		//armazena o objeto que fará o test e colher o resultado
		ResultActions response = mockMvc.perform(patch("/professores/10")
				.content(mapper.writeValueAsString(professor)).contentType("application/json"));
		
		//pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		
		//pegando o resultado no formato de String e pasando con o fomato UTF-8
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
	
		ProfessorDTO professorSalvo = mapper.readValue(responseStr, ProfessorDTO.class);
		
		//verificar se foi salvo
		isProfessorValid(professor, professorSalvo);
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
	
	@Test
	void deleteTest() throws Exception{		
		//armazena o objeto que fará o test e colher o resultado
		ResultActions response = mockMvc.perform(delete("/professores/2").contentType("application/json"));
		
		//pegando o resultado via MvcResult
		MvcResult result = response.andReturn();
		
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
	
	@Test
	void getNameTest() throws Exception {
		
		ResultActions response = mockMvc.perform(get("/professores/search-by-name/Sama").contentType("application/json"));
		
		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);

		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
	
	// metodos
	private ProfessorDTO createValidProfessor() {
		ProfessorDTO professor = new ProfessorDTO();		
		professor.setNome("Rafael Sil");
		professor.setCpf("889.456.143-85");
		professor.setCep("067300020");
		professor.setRua("Joana Novas");
		professor.setNumero(145);
		return professor;
	}

	private void isProfessorValid(ProfessorDTO professor, ProfessorDTO professorSalvo) {
		assertThat(professorSalvo.getId()).isPositive();
		assertThat(professorSalvo.getNome()).isEqualTo(professor.getNome());
		assertThat(professorSalvo.getCpf()).isEqualTo(professor.getCpf());
		assertThat(professorSalvo.getCep()).isEqualTo(professor.getCep());
		assertThat(professorSalvo.getRua()).isEqualTo(professor.getRua());
		assertThat(professorSalvo.getNumero()).isEqualTo(professor.getNumero());
	}
}