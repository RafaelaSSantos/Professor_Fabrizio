package br.com.nava.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.nava.entities.ProdutoEntity;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.entities.VendaEntity;
import br.com.nava.repositories.ProdutoRepository;
import br.com.nava.repositories.UsuarioRepository;
import br.com.nava.repositories.VendaRepository;

@Service
public class BDService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	//Exemplo de inserção para relacionamento many to many
	public void inserirVendas() {
		
		//busca uma lista de usuario para gerar um deles
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		
		//buscar lista de produtos 
		List<ProdutoEntity> produtos = produtoRepository.findAll();
		
		VendaEntity venda = new VendaEntity();
		venda.setValorTotal((float) 120.0);
		//peguei o primeiro elemento da lista de usuarios
		venda.setUsuario(usuarios.get(0));
		//alterei a lista de produtos em vendas
		venda.setProdutos(produtos);
		
		VendaEntity vendaSalva = vendaRepository.save(venda);
		
		List<VendaEntity>listaVendas = new ArrayList<VendaEntity>();
		listaVendas.add(vendaSalva);
		
		//para cada produto altera a lista de vendas
		for(int i = 0; i < produtos.size(); i++) {
			produtos.get(i).setVendas(listaVendas);			
			//atualizar o produto de acordo com sua venda
			produtoRepository.save(produtos.get(i));
		}
	} 
	
//  referente a vendaService onde se realiza o public VendaEntity save(VendaEntity venda) 
	
//	public VendaEntity save(VendaEntity venda) {
//		return vendaRepository.save(venda);
//	}

//	public VendaEntity save(VendaEntity venda) {
//
//		VendaEntity minhaVenda = this.vendaRepository.save(venda);
//
//		if (venda.getProdutos().isEmpty() == false) {
//			List<ProdutoEntity> produtos = venda.getProdutos();
//			for (int i = 0; i < produtos.size(); i++) {
//				produtos.get(i).setVendas(Arrays.asList(minhaVenda));
//			}
//			produtoRepository.saveAll(produtos);
//		}
//		return minhaVenda;
//	}

	//outra forma de fazer a mesma coisa
//	
//	//explicação do professor para preencher atualizar as venda e produto para ser chamando na table venda_produto
//	public VendaEntity save(VendaEntity venda) {
//		//primeiro teremos que salvar a venda (para preencher a lista de vendas para cada produto)
//		
//		VendaEntity vendaSalva = vendaRepository.save(venda);
//		
//		// depois teremos que alterar a lista de vendas para cada produtos	
//		// para cada produto da venda do body, temos que atualizar a venda salva no banco
//		
//		//todos os produtos da venda
//		List<ProdutoEntity> listaProdutos = venda.getProdutos();
//	    List<VendaEntity>listaVendas = new ArrayList<VendaEntity>();
//	    listaVendas.add(vendaSalva);		
//		// atualizando as vendas para cada produto acima
//		
//		for(int i = 0; i < listaProdutos.size(); i++) {
//			// Arrays.asList(): converte um conjunto de objetos em uma lista
//			listaProdutos.get(i).setVendas( Arrays.asList(vendaSalva)  );
			//listaProdutos.get(i).setVendas(listaVendas);
//		}
//		
//		//salvando as atualizações no banco de dados
//		produtoRepository.saveAll(listaProdutos);
//		
//		return vendaSalva;	
//	}	
}