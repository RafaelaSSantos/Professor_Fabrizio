package br.com.nava.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.com.nava.dtos.VendaDTO;
import br.com.nava.entities.VendaEntity;
import br.com.nava.repositories.VendaRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VendaServiceTest {

	@Autowired
	private VendaService vendaService;

	// anotação @mockBean serve para sinalizar que iremos simular a camada
	// repository
	@MockBean
	private VendaRepository vendaRepository;

	@Test
	void getAllTest() {
		// vamos criar uma lista de entidade venda com objetivo
		// de retorna a mesma qunado o vendaEntity.findAll() for adcionado
		List<VendaEntity> listaMockada = new ArrayList<VendaEntity>();

		VendaEntity vendaEntity = createValidVenda();

		listaMockada.add(vendaEntity);

		// quando o repository for adicionado, retorna a lista mockada
		when(vendaRepository.findAll()).thenReturn(listaMockada);

		List<VendaDTO> retorno = vendaService.getAll();

		// validação
		isVendaValid(retorno.get(0), listaMockada.get(0));
	}

	// quando o objeto é achado no banco de dados
	@Test
	void getOneWhenFoundObjectTest() {

		VendaEntity vendaEntity = createValidVenda();

		Optional<VendaEntity> optional = Optional.of(vendaEntity);

		when(vendaRepository.findById(1)).thenReturn(optional);

		// execução
		VendaDTO obj = vendaService.getOne(1);

		// validação
		isVendaValid(obj, vendaEntity);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void getOneWhenNotFoundObjectTest() {

		// simula o caso de não achar o registro no banco
		Optional<VendaEntity> optional = Optional.empty();

		when(vendaRepository.findById(1)).thenReturn(optional);

		// execução
		VendaDTO obj = vendaService.getOne(1);

		// objeto com valores padrão
		VendaEntity vendaEntity = new VendaEntity();

		// validação
		isVendaValid(obj, vendaEntity);
	}

	@Test
	void saveTest() {
		// 1- cenario
		// objeto com dados validos de um venda
		VendaEntity vendaEntity = createValidVenda();

		// quando o vendaEntity.save for adicionado, retornaremos um objeto de
		// venda com dados validos
		when(vendaRepository.save(vendaEntity)).thenReturn(vendaEntity);

		VendaDTO obj = vendaService.save(vendaEntity);

		// validar resposta
		isVendaValid(obj, vendaEntity);
	}

	// quando o objeto é achado no banco de dados
	@Test
	void updateWhenFoundObjectTest() {
		VendaEntity vendaEntity = createValidVenda();

		Optional<VendaEntity> optional = Optional.of(vendaEntity);

		when(vendaRepository.findById(vendaEntity.getId())).thenReturn(optional);
		when(vendaRepository.save(vendaEntity)).thenReturn(vendaEntity);

		VendaDTO vendaAlterado = vendaService.update(vendaEntity.getId(), vendaEntity);

		// validação
		isVendaValid(vendaAlterado, vendaEntity);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void updateWhenNotFoundObjectTest() {
		
		//cenario
		//Optional.empty() por que não achou onjeto a ser alterado
		Optional<VendaEntity> optional = Optional.empty();
		
		VendaEntity vendaEntity = createValidVenda();

		//mock
		when(vendaRepository.findById(1)).thenReturn(optional);
		
		//estamos passando coo argmento o vendaEntity, pois em suposição ele estará no banco neste cenario
		//excecução
		VendaDTO vendaAlterado = vendaService.update(1, vendaEntity);

		// validação
		isVendaValid(vendaAlterado, new VendaEntity());		
	}

	@Test
	void deleteTest() {
		
		//verificar se tem erro
		assertDoesNotThrow(() -> vendaService.delete(1));
		
		//verificar se foi utilizado
		verify(vendaRepository, times(1)).deleteById(1);
	}

	// metodos
	private VendaEntity createValidVenda() {
		VendaEntity vendaEntity = new VendaEntity();
		vendaEntity.setId(1);
		vendaEntity.setValorTotal(45);
		return vendaEntity;
	}

	// metodos
	private void isVendaValid(VendaDTO obj, VendaEntity vendaEntity) {
		assertThat(obj.getId()).isEqualTo(vendaEntity.getId());
		assertThat(obj.getValorTotal()).isEqualTo(vendaEntity.getValorTotal());	
	}
}