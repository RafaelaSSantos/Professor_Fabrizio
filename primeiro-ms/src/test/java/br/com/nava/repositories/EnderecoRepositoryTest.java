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
import br.com.nava.entities.EnderecoEntity;

//permite manipular o banco de dados com rollback(desfazer um operação)
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class EnderecoRepositoryTest {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void findByIdWhenFoundTest() {
		EnderecoEntity enderecoEntity = createValidEndereco();

		// vai persistir a entidade no banco para testar findById ao final será
		// deletada.
		testEntityManager.persist(enderecoEntity);

		// buscar a entidade para testar o findById
		Optional<EnderecoEntity> endereco = enderecoRepository.findById(enderecoEntity.getId());

		// validando a respota se objeto encontrado não é nulo
		assertThat(endereco).isNotNull();
	}

	@Test
	void findByIdWhenNotFoundTest() {

		// buscar uma entidade na qual não existe no banco
		Optional<EnderecoEntity> endereco = enderecoRepository.findById(1);

		// verificar se optional não possue valor, isPresent possui valor falso
		assertThat(endereco.isPresent()).isFalse();
	}

	@Test
	void findAllTest() {
		EnderecoEntity enderecoEntity = createValidEndereco();

		// salvando temporario no banco
		testEntityManager.persist(enderecoEntity);

		List<EnderecoEntity> enderecoes = this.enderecoRepository.findAll();

		assertThat(enderecoes.size()).isEqualTo(1);
	}

	@Test
	void saveTest() {
		EnderecoEntity enderecoEntity = createValidEndereco();

		// salvando temporario no banco
		testEntityManager.persist(enderecoEntity);
		EnderecoEntity enderecoSalvo = enderecoRepository.save(enderecoEntity);
		assertThat(enderecoSalvo.getId()).isNotNull();	
		assertThat(enderecoSalvo.getRua()).isEqualTo(enderecoSalvo.getRua());
		assertThat(enderecoSalvo.getNumero()).isEqualTo(enderecoSalvo.getNumero());
		assertThat(enderecoSalvo.getCep()).isEqualTo(enderecoSalvo.getCep());
		assertThat(enderecoSalvo.getCidade()).isEqualTo(enderecoSalvo.getCidade());
		assertThat(enderecoSalvo.getEstado()).isEqualTo(enderecoSalvo.getEstado());
	}

	@Test
	void deleteTest() {
		EnderecoEntity enderecoEntity = createValidEndereco();

		// salvando temporario no banco
		EnderecoEntity enderecoTemporario = testEntityManager.persist(enderecoEntity);

		enderecoRepository.deleteById(enderecoTemporario.getId());

		// busquei o endereco deletado e comparei a resposta
		Optional<EnderecoEntity> deletado = enderecoRepository.findById(enderecoTemporario.getId());

		assertThat(deletado.isPresent()).isFalse();
	}

	// metodos
	private EnderecoEntity createValidEndereco() {
		EnderecoEntity enderecoEntity = new EnderecoEntity();
		enderecoEntity.setRua("Jasmin Nona");
		enderecoEntity.setNumero(10);
		enderecoEntity.setCep("0453-963");
		enderecoEntity.setCidade("São Paulo");
		enderecoEntity.setEstado("São Paulo");
		return enderecoEntity;
	}
}