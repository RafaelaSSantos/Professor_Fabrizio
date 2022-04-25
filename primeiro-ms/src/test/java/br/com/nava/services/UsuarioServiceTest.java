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
import br.com.nava.dtos.UsuarioDTO;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.repositories.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioServiceTest {

	@Autowired
	private UsuarioService usuarioService;

	// anotação @mockBean serve para sinalizar que iremos simular a camada
	// repository
	@MockBean
	private UsuarioRepository usuarioRepository;

	@Test
	void getAllTest() {
		// vamos criar uma lista de entidade usuario com objetivo
		// de retorna a mesma qunado o usuarioEntity.findAll() for adcionado
		List<UsuarioEntity> listaMockada = new ArrayList<UsuarioEntity>();

		UsuarioEntity usuarioEntity = createValidUsuario();

		listaMockada.add(usuarioEntity);

		// quando o repository for adicionado, retorna a lista mockada
		when(usuarioRepository.findAll()).thenReturn(listaMockada);

		List<UsuarioDTO> retorno = usuarioService.getAll();

		// validação
		isUsuarioValid(retorno.get(0), listaMockada.get(0));
	}

	// quando o objeto é achado no banco de dados
	@Test
	void getOneWhenFoundObjectTest() {

		UsuarioEntity usuarioEntity = createValidUsuario();

		Optional<UsuarioEntity> optional = Optional.of(usuarioEntity);

		when(usuarioRepository.findById(1)).thenReturn(optional);

		// execução
		UsuarioDTO obj = usuarioService.getOne(1);

		// validação
		isUsuarioValid(obj, usuarioEntity);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void getOneWhenNotFoundObjectTest() {

		// simula o caso de não achar o registro no banco
		Optional<UsuarioEntity> optional = Optional.empty();

		when(usuarioRepository.findById(1)).thenReturn(optional);

		// execução
		UsuarioDTO obj = usuarioService.getOne(1);

		// objeto com valores padrão
		UsuarioEntity usuarioEntity = new UsuarioEntity();

		// validação
		isUsuarioValid(obj, usuarioEntity);
	}

	@Test
	void saveTest() {
		// 1- cenario
		// objeto com dados valisdos de um usuario
		UsuarioEntity usuarioEntity = createValidUsuario();

		// quando o usuarioEntity.save for adicionado, retornaremos um objeto de
		// usuario com dados validos
		when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioEntity);

		UsuarioDTO obj = usuarioService.save(usuarioEntity);

		// validar resposta
		isUsuarioValid(obj, usuarioEntity);
	}

	// quando o objeto é achado no banco de dados
	@Test
	void updateWhenFoundObjectTest() {
		UsuarioEntity usuarioEntity = createValidUsuario();

		Optional<UsuarioEntity> optional = Optional.of(usuarioEntity);

		when(usuarioRepository.findById(usuarioEntity.getId())).thenReturn(optional);
		when(usuarioRepository.save(usuarioEntity)).thenReturn(usuarioEntity);

		UsuarioDTO usuarioAlterado = usuarioService.update(usuarioEntity.getId(), usuarioEntity);

		// validação
		isUsuarioValid(usuarioAlterado, usuarioEntity);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void updateWhenNotFoundObjectTest() {
		
		//cenario
		//Optional.empty() por que não achou onjeto a ser alterado
		Optional<UsuarioEntity> optional = Optional.empty();
		
		UsuarioEntity usuarioEntity = createValidUsuario();

		//mock
		when(usuarioRepository.findById(1)).thenReturn(optional);
		
		//estamos passando coo argmento o usuarioEntity, pois em suposição ele estará no banco neste cenario
		//excecução
		UsuarioDTO usuarioAlterado = usuarioService.update(1, usuarioEntity);

		// validação
		isUsuarioValid(usuarioAlterado, new UsuarioEntity());		
	}

	@Test
	void deleteTest() {
		
		//verificar se tem erro
		assertDoesNotThrow(() -> usuarioService.delete(1));
		
		//verificar se foi utilizado
		verify(usuarioRepository, times(1)).deleteById(1);
	}

	// metodos
	private UsuarioEntity createValidUsuario() {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setId(1);		
		usuarioEntity.setNome("Jasmin Nona");
		usuarioEntity.setEmail("jasmin@gamil.com");
		return usuarioEntity;
	}

	// metodos
	private void isUsuarioValid(UsuarioDTO obj, UsuarioEntity usuarioEntity) {
		assertThat(obj.getId()).isEqualTo(usuarioEntity.getId());		
		assertThat(obj.getNome()).isEqualTo(usuarioEntity.getNome());
		assertThat(obj.getEmail()).isEqualTo(usuarioEntity.getEmail());
	}
}