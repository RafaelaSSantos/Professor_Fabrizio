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
import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.entities.EnderecoEntity;
import br.com.nava.repositories.EnderecoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoServiceTest {

	@Autowired
	private EnderecoService enderecoService;

	// anotação @mockBean serve para sinalizar que iremos simular a camada
	// repository
	@MockBean
	private EnderecoRepository enderecoRepository;

	@Test
	void getAllTest() {
		// vamos criar uma lista de entidade endereco com objetivo
		// de retorna a mesma qunado o enderecoEntity.findAll() for adcionado
		List<EnderecoEntity> listaMockada = new ArrayList<EnderecoEntity>();

		EnderecoEntity enderecoEntity = createValidEndereco();

		listaMockada.add(enderecoEntity);

		// quando o repository for adicionado, retorna a lista mockada
		when(enderecoRepository.findAll()).thenReturn(listaMockada);

		List<EnderecoDTO> retorno = enderecoService.getAll();

		// validação
		isEnderecoValid(retorno.get(0), listaMockada.get(0));
	}

	// quando o objeto é achado no banco de dados
	@Test
	void getOneWhenFoundObjectTest() {

		EnderecoEntity enderecoEntity = createValidEndereco();

		Optional<EnderecoEntity> optional = Optional.of(enderecoEntity);

		when(enderecoRepository.findById(1)).thenReturn(optional);

		// execução
		EnderecoDTO obj = enderecoService.getOne(1);

		// validação
		isEnderecoValid(obj, enderecoEntity);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void getOneWhenNotFoundObjectTest() {

		// simula o caso de não achar o registro no banco
		Optional<EnderecoEntity> optional = Optional.empty();

		when(enderecoRepository.findById(1)).thenReturn(optional);

		// execução
		EnderecoDTO obj = enderecoService.getOne(1);

		// objeto com valores padrão
		EnderecoEntity enderecoEntity = new EnderecoEntity();

		// validação
		isEnderecoValid(obj, enderecoEntity);
	}

	@Test
	void saveTest() {
		// 1- cenario
		// objeto com dados valisdos de um endereco
		EnderecoEntity enderecoEntity = createValidEndereco();

		// quando o enderecoEntity.save for adicionado, retornaremos um objeto de
		// endereco com dados validos
		when(enderecoRepository.save(enderecoEntity)).thenReturn(enderecoEntity);

		EnderecoDTO obj = enderecoService.save(enderecoEntity);

		// validar resposta
		isEnderecoValid(obj, enderecoEntity);
	}

	// quando o objeto é achado no banco de dados
	@Test
	void updateWhenFoundObjectTest() {
		EnderecoEntity enderecoEntity = createValidEndereco();

		Optional<EnderecoEntity> optional = Optional.of(enderecoEntity);

		when(enderecoRepository.findById(enderecoEntity.getId())).thenReturn(optional);
		when(enderecoRepository.save(enderecoEntity)).thenReturn(enderecoEntity);

		EnderecoDTO enderecoAlterado = enderecoService.update(enderecoEntity.getId(), enderecoEntity);

		// validação
		isEnderecoValid(enderecoAlterado, enderecoEntity);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void updateWhenNotFoundObjectTest() {
		
		//cenario
		//Optional.empty() por que não achou onjeto a ser alterado
		Optional<EnderecoEntity> optional = Optional.empty();
		
		EnderecoEntity enderecoEntity = createValidEndereco();

		//mock
		when(enderecoRepository.findById(1)).thenReturn(optional);
		
		//estamos passando coo argmento o enderecoEntity, pois em suposição ele estará no banco neste cenario
		//excecução
		EnderecoDTO enderecoAlterado = enderecoService.update(1, enderecoEntity);

		// validação
		isEnderecoValid(enderecoAlterado, new EnderecoEntity());		
	}

	@Test
	void deleteTest() {
		
		//verificar se tem erro
		assertDoesNotThrow(() -> enderecoService.delete(1));
		
		//verificar se foi utilizado
		verify(enderecoRepository, times(1)).deleteById(1);
	}

	// metodos
	private EnderecoEntity createValidEndereco() {
		EnderecoEntity enderecoEntity = new EnderecoEntity();
		enderecoEntity.setId(1);		
		enderecoEntity.setRua("Jasmin Nona");
		enderecoEntity.setNumero(10);
		enderecoEntity.setCep("0453-963");
		enderecoEntity.setCidade("São Paulo");
		enderecoEntity.setEstado("São Paulo");
		return enderecoEntity;
	}

	// metodos
	private void isEnderecoValid(EnderecoDTO obj, EnderecoEntity enderecoEntity) {
		assertThat(obj.getId()).isEqualTo(enderecoEntity.getId());		
		assertThat(obj.getNumero()).isEqualTo(enderecoEntity.getNumero());
		assertThat(obj.getCep()).isEqualTo(enderecoEntity.getCep());
		assertThat(obj.getRua()).isEqualTo(enderecoEntity.getRua());
		assertThat(obj.getCidade()).isEqualTo(enderecoEntity.getCidade());
		assertThat(obj.getEstado()).isEqualTo(enderecoEntity.getEstado());
	}
}