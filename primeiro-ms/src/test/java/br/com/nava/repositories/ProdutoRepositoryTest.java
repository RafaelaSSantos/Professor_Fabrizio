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
import br.com.nava.entities.ProdutoEntity;

//permite manipular o banco de dados com rollback(desfazer um operação)
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void findByIdWhenFoundTest() {
		ProdutoEntity produtoEntity = createValidProduto();

		// vai persistir a entidade no banco para testar findById ao final será
		// deletada.
		testEntityManager.persist(produtoEntity);

		// buscar a entidade para testar o findById
		Optional<ProdutoEntity> produto = produtoRepository.findById(produtoEntity.getId());

		// validando a respota se objeto encontrado não é nulo
		assertThat(produto).isNotNull();
	}

	@Test
	void findByIdWhenNotFoundTest() {

		// buscar uma entidade na qual não existe no banco
		Optional<ProdutoEntity> produto = produtoRepository.findById(1);

		// verificar se optional não possue valor, isPresent possui valor falso
		assertThat(produto.isPresent()).isFalse();
	}

	@Test
	void findAllTest() {
		ProdutoEntity produtoEntity = createValidProduto();

		// salvando temporario no banco
		testEntityManager.persist(produtoEntity);

		List<ProdutoEntity> produtoes = this.produtoRepository.findAll();

		assertThat(produtoes.size()).isEqualTo(1);
	}

	@Test
	void saveTest() {
		ProdutoEntity produtoEntity = createValidProduto();

		// salvando temporario no banco
		testEntityManager.persist(produtoEntity);

		ProdutoEntity produtoSalvo = produtoRepository.save(produtoEntity);
		assertThat(produtoSalvo.getId()).isNotNull();
		assertThat(produtoSalvo.getNome()).isEqualTo(produtoSalvo.getNome());
		assertThat(produtoSalvo.getDescricao()).isEqualTo(produtoSalvo.getDescricao());
		assertThat(produtoSalvo.getPreco()).isEqualTo(produtoSalvo.getPreco());	
	}

	@Test
	void deleteTest() {
		ProdutoEntity produtoEntity = createValidProduto();

		// salvando temporario no banco
		ProdutoEntity produtoTemporario = testEntityManager.persist(produtoEntity);

		produtoRepository.deleteById(produtoTemporario.getId());

		// busquei o produto deletado e comparei a resposta
		Optional<ProdutoEntity> deletado = produtoRepository.findById(produtoTemporario.getId());

		assertThat(deletado.isPresent()).isFalse();
	}

	// metodos
	private ProdutoEntity createValidProduto() {
		ProdutoEntity produtoEntity = new ProdutoEntity();		
		produtoEntity.setNome("Jujuba");
		produtoEntity.setDescricao("Novo sabor");
		produtoEntity.setPreco(10);
		return produtoEntity;
	}
}