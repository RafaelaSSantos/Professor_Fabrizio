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
import br.com.nava.dtos.ProdutoDTO;
import br.com.nava.entities.ProdutoEntity;
import br.com.nava.repositories.ProdutoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoServiceTest {

	@Autowired
	private ProdutoService produtoService;

	// anotação @mockBean serve para sinalizar que iremos simular a camada
	// repository
	@MockBean
	private ProdutoRepository produtoRepository;

	@Test
	void getAllTest() {
		// vamos criar uma lista de entidade produto com objetivo
		// de retorna a mesma qunado o produtoEntity.findAll() for adcionado
		List<ProdutoEntity> listaMockada = new ArrayList<ProdutoEntity>();

		ProdutoEntity produtoEntity = createValidProduto();

		listaMockada.add(produtoEntity);

		// quando o repository for adicionado, retorna a lista mockada
		when(produtoRepository.findAll()).thenReturn(listaMockada);

		List<ProdutoDTO> retorno = produtoService.getAll();

		// validação
		isProdutoValid(retorno.get(0), listaMockada.get(0));
	}

	// quando o objeto é achado no banco de dados
	@Test
	void getOneWhenFoundObjectTest() {

		ProdutoEntity produtoEntity = createValidProduto();

		Optional<ProdutoEntity> optional = Optional.of(produtoEntity);

		when(produtoRepository.findById(1)).thenReturn(optional);

		// execução
		ProdutoDTO obj = produtoService.getOne(1);

		// validação
		isProdutoValid(obj, produtoEntity);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void getOneWhenNotFoundObjectTest() {

		// simula o caso de não achar o registro no banco
		Optional<ProdutoEntity> optional = Optional.empty();

		when(produtoRepository.findById(1)).thenReturn(optional);

		// execução
		ProdutoDTO obj = produtoService.getOne(1);

		// objeto com valores padrão
		ProdutoEntity produtoEntity = new ProdutoEntity();

		// validação
		isProdutoValid(obj, produtoEntity);
	}

	@Test
	void saveTest() {
		// 1- cenario
		// objeto com dados valisdos de um produto
		ProdutoEntity produtoEntity = createValidProduto();

		// quando o produtoEntity.save for adicionado, retornaremos um objeto de
		// produto com dados validos
		when(produtoRepository.save(produtoEntity)).thenReturn(produtoEntity);

		ProdutoDTO obj = produtoService.save(produtoEntity);

		// validar resposta
		isProdutoValid(obj, produtoEntity);
	}

	// quando o objeto é achado no banco de dados
	@Test
	void updateWhenFoundObjectTest() {
		ProdutoEntity produtoEntity = createValidProduto();

		Optional<ProdutoEntity> optional = Optional.of(produtoEntity);

		when(produtoRepository.findById(produtoEntity.getId())).thenReturn(optional);
		when(produtoRepository.save(produtoEntity)).thenReturn(produtoEntity);

		ProdutoDTO produtoAlterado = produtoService.update(produtoEntity.getId(), produtoEntity);

		// validação
		isProdutoValid(produtoAlterado, produtoEntity);
	}

	// quando o objeto NÃO é achado no banco de dados
	@Test
	void updateWhenNotFoundObjectTest() {
		
		//cenario
		//Optional.empty() por que não achou onjeto a ser alterado
		Optional<ProdutoEntity> optional = Optional.empty();
		
		ProdutoEntity produtoEntity = createValidProduto();

		//mock
		when(produtoRepository.findById(1)).thenReturn(optional);
		
		//estamos passando coo argmento o produtoEntity, pois em suposição ele estará no banco neste cenario
		//excecução
		ProdutoDTO produtoAlterado = produtoService.update(1, produtoEntity);

		// validação
		isProdutoValid(produtoAlterado, new ProdutoEntity());		
	}

	@Test
	void deleteTest() {
		
		//verificar se tem erro
		assertDoesNotThrow(() -> produtoService.delete(1));
		
		//verificar se foi utilizado
		verify(produtoRepository, times(1)).deleteById(1);
	}

	// metodos
	private ProdutoEntity createValidProduto() {
		ProdutoEntity produtoEntity = new ProdutoEntity();
		produtoEntity.setId(1);
		produtoEntity.setNome("Marmelada");
		produtoEntity.setDescricao("Feito com pura goiaba");
		produtoEntity.setPreco(10);
		return produtoEntity;
	}

	// metodos
	private void isProdutoValid(ProdutoDTO obj, ProdutoEntity produtoEntity) {
		assertThat(obj.getId()).isEqualTo(produtoEntity.getId());
		assertThat(obj.getNome()).isEqualTo(produtoEntity.getNome());
		assertThat(obj.getDescricao()).isEqualTo(produtoEntity.getDescricao());
		assertThat(obj.getPreco()).isEqualTo(produtoEntity.getPreco());
	}
}