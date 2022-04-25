package br.com.nava.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import br.com.nava.entities.UsuarioEntity;

//permite manipular o banco de dados com rollback(desfazer um operação)
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void findByIdWhenFoundTest() {
		UsuarioEntity usuarioEntity = createValidUsuario();

		// vai persistir a entidade no banco para testar findById ao final será
		// deletada.
		testEntityManager.persist(usuarioEntity);

		// buscar a entidade para testar o findById
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(usuarioEntity.getId());

		// validando a respota se objeto encontrado não é nulo
		assertThat(usuario).isNotNull();
	}

	@Test
	void findByIdWhenNotFoundTest() {

		// buscar uma entidade na qual não existe no banco
		Optional<UsuarioEntity> usuario = usuarioRepository.findById(1);

		// verificar se optional não possue valor, isPresent possui valor falso
		assertThat(usuario.isPresent()).isFalse();
	}

	@Test
	void findAllTest() {
		UsuarioEntity usuarioEntity = createValidUsuario();

		// salvando temporario no banco
		testEntityManager.persist(usuarioEntity);

		List<UsuarioEntity> usuarioes = this.usuarioRepository.findAll();

		assertThat(usuarioes.size()).isEqualTo(1);
	}

	@Test
	void saveTest() {
		UsuarioEntity usuarioEntity = createValidUsuario();

		// salvando temporario no banco
		testEntityManager.persist(usuarioEntity);
		UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);
		assertThat(usuarioSalvo.getId()).isNotNull();	
		assertThat(usuarioSalvo.getNome()).isEqualTo(usuarioSalvo.getNome());
		assertThat(usuarioSalvo.getEmail()).isEqualTo(usuarioSalvo.getEmail());
	}

	@Test
	void deleteTest() {
		UsuarioEntity usuarioEntity = createValidUsuario();

		// salvando temporario no banco
		UsuarioEntity usuarioTemporario = testEntityManager.persist(usuarioEntity);

		usuarioRepository.deleteById(usuarioTemporario.getId());

		// busquei o usuario deletado e comparei a resposta
		Optional<UsuarioEntity> deletado = usuarioRepository.findById(usuarioTemporario.getId());

		assertThat(deletado.isPresent()).isFalse();
	}

	// metodos
	private UsuarioEntity createValidUsuario() {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setNome("Jasmin Nona");
		usuarioEntity.setEmail("jasmin@gmail.com");	
		return usuarioEntity;
	}
}