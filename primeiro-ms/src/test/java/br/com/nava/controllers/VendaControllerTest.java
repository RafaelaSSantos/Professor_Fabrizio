package br.com.nava.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.nio.charset.StandardCharsets;
import java.util.List;

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
import br.com.nava.dtos.VendaDTO;
import br.com.nava.repositories.ProdutoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VendaControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void getAllTest() throws Exception{		
		ResultActions response = mockMvc.perform(get("/vendas").contentType("application/json"));
	
		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr );		
	
		VendaDTO[] lista = mapper.readValue(responseStr, VendaDTO[].class);

		assertThat(lista).isNotEmpty();
	}
	
	@Test
	void getOneTest() throws Exception {
		ResultActions response = mockMvc.perform(get("/vendas/1").contentType("application/json"));

		MvcResult result = response.andReturn();
	
		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
		
		VendaDTO venda = mapper.readValue(responseStr, VendaDTO.class);
		
		assertThat(venda.getId() ).isEqualTo(1);
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
	
	@Test
	void saveTest() throws Exception {		
		VendaDTO venda = new VendaDTO();		
		venda.setValorTotal((float) 30.0);		
		
		ResultActions response = mockMvc.perform(post("/vendas")
				.content(mapper.writeValueAsString(venda))				
				.contentType("application/json"));	
		
		MvcResult result = response.andReturn();
		
		String responseStr = result.getResponse().getContentAsString();
		
		System.out.println(responseStr);
	
		VendaDTO vendaSalvo = mapper.readValue(responseStr, VendaDTO.class);

		assertThat(vendaSalvo.getValorTotal()).isEqualTo(venda.getValorTotal());				
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
	
	@Test
	void alterarTest() throws Exception{
		VendaDTO venda = new VendaDTO();		
		venda.setValorTotal(55);	

		ResultActions response = mockMvc.perform(patch("/vendas/1")
				.content(mapper.writeValueAsString(venda))
				.contentType("application/json"));

		MvcResult result = response.andReturn();

		String responseStr = result.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		System.out.println(responseStr);
	
		VendaDTO vendaSalvo = mapper.readValue(responseStr, VendaDTO.class);

		assertThat(vendaSalvo.getId()).isPositive();
		assertThat(vendaSalvo.getValorTotal()).isEqualTo(venda.getValorTotal());			
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
	
	@Test
	void deleteTest() throws Exception{
		ResultActions response = mockMvc.perform(delete("/vendas/3").contentType("application/json"));

		MvcResult result = response.andReturn();
		
		assertThat(result.getResponse().getStatus() ).isEqualTo(200);
	}
}