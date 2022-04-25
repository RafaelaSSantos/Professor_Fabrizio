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
import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entities.ProfessorEntity;
import br.com.nava.repositories.ProfessorRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProfessorServiceTest {

	@Autowired
	private ProfessorService professorService;

	// anotação @mockBean serve para sinalizar que iremos simular a camada
	// repository
	@MockBean
	private ProfessorRepository professorRepository;

	@Test
	void getAllTest() {
		// vamos criar uma lista de entidade professor com objetivo
		// de retorna a mesma qunado o professorEntity.findAll() for adcionado
		List<ProfessorEntity> listaMockada = new ArrayList<ProfessorEntity>();

		ProfessorEntity professorEntity = createValidProfessor();

		listaMockada.add(professorEntity);

		// quando o repository for adicionado, retorna a lista mockada
		when(professorRepository.findAll()).thenReturn(listaMockada);

		List<ProfessorDTO> retorno = professorService.getAll();

		// validação
		isProfessorValid(retorno.get(0), listaMockada.get(0));
	}

	// quando o objeto é achado no banco de dados
	@Test
	void getOneWhenFoundObjectTest() {

		ProfessorEntity professorEntity = createValidProfessor();

		Optional<ProfessorEntity> optional = Optional.of(professorEntity);

		when(professorRepository.findById(1)).thenReturn(optional);

		// execução
		ProfessorDTO obj = professorService.getOne(1);

		// validação
		isProfessorValid(obj, professorEntity);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void getOneWhenNotFoundObjectTest() {

		// simula o caso de não achar o registro no banco
		Optional<ProfessorEntity> optional = Optional.empty();

		when(professorRepository.findById(1)).thenReturn(optional);

		// execução
		ProfessorDTO obj = professorService.getOne(1);

		// objeto com valores padrão
		ProfessorEntity professorEntity = new ProfessorEntity();

		// validação
		isProfessorValid(obj, professorEntity);
	}

	@Test
	void saveTest() {
		// 1- cenario
		// objeto com dados valisdos de um professor
		ProfessorEntity professorEntity = createValidProfessor();

		// quando o professorEntity.save for adicionado, retornaremos um objeto de
		// professor com dados validos
		when(professorRepository.save(professorEntity)).thenReturn(professorEntity);

		ProfessorDTO obj = professorService.save(professorEntity);

		// validar resposta
		isProfessorValid(obj, professorEntity);
	}

	// quando o objeto é achado no banco de dados
	@Test
	void updateWhenFoundObjectTest() {
		ProfessorEntity professorEntity = createValidProfessor();

		Optional<ProfessorEntity> optional = Optional.of(professorEntity);

		when(professorRepository.findById(professorEntity.getId())).thenReturn(optional);
		when(professorRepository.save(professorEntity)).thenReturn(professorEntity);

		ProfessorDTO professorAlterado = professorService.update(professorEntity.getId(), professorEntity);

		// validação
		isProfessorValid(professorAlterado, professorEntity);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void updateWhenNotFoundObjectTest() {
		
		//cenario
		//Optional.empty() por que não achou onjeto a ser alterado
		Optional<ProfessorEntity> optional = Optional.empty();
		
		ProfessorEntity professorEntity = createValidProfessor();

		//mock
		when(professorRepository.findById(1)).thenReturn(optional);
		
		//estamos passando coo argmento o professorEntity, pois em suposição ele estará no banco neste cenario
		//excecução
		ProfessorDTO professorAlterado = professorService.update(1, professorEntity);

		// validação
		isProfessorValid(professorAlterado, new ProfessorEntity());		
	}

	@Test
	void deleteTest() {
		
		//verificar se tem erro
		assertDoesNotThrow(() -> professorService.delete(1));
		
		//verificar se foi utilizado
		verify(professorRepository, times(1)).deleteById(1);
	}

	// metodos
	private ProfessorEntity createValidProfessor() {
		ProfessorEntity professorEntity = new ProfessorEntity();
		professorEntity.setId(1);
		professorEntity.setNome("Maria");
		professorEntity.setRua("Jasmin Nona");
		professorEntity.setNumero(10);
		professorEntity.setCep("0453-963");

		return professorEntity;
	}

	// metodos
	private void isProfessorValid(ProfessorDTO obj, ProfessorEntity professorEntity) {
		assertThat(obj.getId()).isEqualTo(professorEntity.getId());
		assertThat(obj.getNome()).isEqualTo(professorEntity.getNome());
		assertThat(obj.getNumero()).isEqualTo(professorEntity.getNumero());
		assertThat(obj.getCep()).isEqualTo(professorEntity.getCep());
		assertThat(obj.getRua()).isEqualTo(professorEntity.getRua());
	}
}