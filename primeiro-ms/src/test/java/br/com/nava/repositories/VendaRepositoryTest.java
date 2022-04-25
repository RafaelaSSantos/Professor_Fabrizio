
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
import br.com.nava.entities.VendaEntity;

//permite manipular o banco de dados com rollback(desfazer um operação)
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class VendaRepositoryTest {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void findByIdWhenFoundTest() {
		VendaEntity vendaEntity = createValidVenda();

		// vai persistir a entidade no banco para testar findById ao final será
		// deletada.
		testEntityManager.persist(vendaEntity);

		// buscar a entidade para testar o findById
		Optional<VendaEntity> venda = vendaRepository.findById(vendaEntity.getId());

		// validando a respota se objeto encontrado não é nulo
		assertThat(venda).isNotNull();
	}

	@Test
	void findByIdWhenNotFoundTest() {

		// buscar uma entidade na qual não existe no banco
		Optional<VendaEntity> venda = vendaRepository.findById(1);

		// verificar se optional não possue valor, isPresent possui valor falso
		assertThat(venda.isPresent()).isFalse();
	}

	@Test
	void findAllTest() {
		VendaEntity vendaEntity = createValidVenda();

		// salvando temporario no banco
		testEntityManager.persist(vendaEntity);

		List<VendaEntity> vendaes = this.vendaRepository.findAll();

		assertThat(vendaes.size()).isEqualTo(1);
	}

	@Test
	void saveTest() {
		VendaEntity vendaEntity = createValidVenda();

		// salvando temporario no banco
		testEntityManager.persist(vendaEntity);
		VendaEntity vendaSalvo = vendaRepository.save(vendaEntity);
		assertThat(vendaSalvo.getId()).isNotNull();	
		assertThat(vendaSalvo.getValorTotal()).isEqualTo(vendaSalvo.getValorTotal());
		
	}

	@Test
	void deleteTest() {
		VendaEntity vendaEntity = createValidVenda();

		// salvando temporario no banco
		VendaEntity vendaTemporario = testEntityManager.persist(vendaEntity);

		vendaRepository.deleteById(vendaTemporario.getId());

		// busquei o venda deletado e comparei a resposta
		Optional<VendaEntity> deletado = vendaRepository.findById(vendaTemporario.getId());

		assertThat(deletado.isPresent()).isFalse();
	}

	// metodos
	private VendaEntity createValidVenda() {
		VendaEntity vendaEntity = new VendaEntity();
		vendaEntity.setValorTotal(50);
	
		return vendaEntity;
	}
}