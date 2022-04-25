package br.com.nava.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.nava.dtos.ProdutoDTO;
import br.com.nava.entities.ProdutoEntity;
import br.com.nava.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<ProdutoDTO> getAll() {
		List<ProdutoEntity> lista = produtoRepository.findAll();
		List<ProdutoDTO> listaDTO = new ArrayList<>();
		for(ProdutoEntity produtoEntity : lista) {
			listaDTO.add(produtoEntity.toDTO());
		}
		
		return listaDTO;
	}

	public ProdutoDTO getOne(Integer id) {
		return produtoRepository.findById(id).orElse(new ProdutoEntity()).toDTO();
	}

	public ProdutoDTO save(ProdutoEntity produto) {
		return produtoRepository.save(produto).toDTO();
	}

	public ProdutoDTO update(Integer id, ProdutoEntity produto) {
		Optional<ProdutoEntity> optional = produtoRepository.findById(id);

		if (optional.isPresent() == true) {
			ProdutoEntity produtos = optional.get();
			produtos.setNome(produto.getNome());
			produtos.setDescricao(produto.getDescricao());
			produtos.setPreco(produto.getPreco());

			return produtoRepository.save(produtos).toDTO();
		} else {
			return new ProdutoEntity().toDTO();
		}
	}

	public void delete(Integer id) {
		produtoRepository.deleteById(id);
	}
}