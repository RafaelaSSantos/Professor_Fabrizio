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
import br.com.nava.entities.ProfessorEntity;

//permite manipular o banco de dados com rollback(desfazer um operação)
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProfessorRepositoryTest {

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void findByIdWhenFoundTest() {
		ProfessorEntity professorEntity = createValidProfessor();

		// vai persistir a entidade no banco para testar findById ao final será
		// deletada.
		testEntityManager.persist(professorEntity);

		// buscar a entidade para testar o findById
		Optional<ProfessorEntity> professor = professorRepository.findById(professorEntity.getId());

		// validando a respota se objeto encontrado não é nulo
		assertThat(professor).isNotNull();
	}

	@Test
	void findByIdWhenNotFoundTest() {

		// buscar uma entidade na qual não existe no banco
		Optional<ProfessorEntity> professor = professorRepository.findById(1);

		// verificar se optional não possue valor, isPresent possui valor falso
		assertThat(professor.isPresent()).isFalse();
	}

	@Test
	void findAllTest() {
		ProfessorEntity professorEntity = createValidProfessor();

		// salvando temporario no banco
		testEntityManager.persist(professorEntity);

		List<ProfessorEntity> professores = this.professorRepository.findAll();

		assertThat(professores.size()).isEqualTo(1);
	}

	@Test
	void saveTest() {
		ProfessorEntity professorEntity = createValidProfessor();

		// salvando temporario no banco
		testEntityManager.persist(professorEntity);

		ProfessorEntity professorSalvo = professorRepository.save(professorEntity);
		assertThat(professorSalvo.getId()).isNotNull();
		assertThat(professorSalvo.getNome()).isEqualTo(professorSalvo.getNome());
		assertThat(professorSalvo.getCpf()).isEqualTo(professorSalvo.getCpf());
		assertThat(professorSalvo.getRua()).isEqualTo(professorSalvo.getRua());
		assertThat(professorSalvo.getNumero()).isEqualTo(professorSalvo.getNumero());
		assertThat(professorSalvo.getCep()).isEqualTo(professorSalvo.getCep());
	}

	@Test
	void deleteTest() {
		ProfessorEntity professorEntity = createValidProfessor();

		// salvando temporario no banco
		ProfessorEntity profTemporario = testEntityManager.persist(professorEntity);

		professorRepository.deleteById(profTemporario.getId());

		// busquei o professor deletado e comparei a resposta
		Optional<ProfessorEntity> deletado = professorRepository.findById(profTemporario.getId());

		assertThat(deletado.isPresent()).isFalse();
	}

	// metodos
	private ProfessorEntity createValidProfessor() {
		ProfessorEntity professorEntity = new ProfessorEntity();
		// professorEntity.setId(1);
		professorEntity.setNome("Maria");
		professorEntity.setRua("Jasmin Nona");
		professorEntity.setNumero(10);
		professorEntity.setCep("0453-963");

		return professorEntity;
	}
}