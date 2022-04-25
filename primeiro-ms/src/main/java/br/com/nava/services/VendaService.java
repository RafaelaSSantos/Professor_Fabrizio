package br.com.nava.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.nava.dtos.VendaDTO;
import br.com.nava.entities.ProdutoEntity;
import br.com.nava.entities.VendaEntity;
import br.com.nava.repositories.ProdutoRepository;
import br.com.nava.repositories.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public List<VendaDTO> getAll() {
		List<VendaEntity> lista = vendaRepository.findAll();
		List<VendaDTO> listaDTO = new ArrayList<>();

		for (VendaEntity vendaEntity : lista) {
			listaDTO.add(vendaEntity.toDTO());
		}
		return listaDTO;
	}

	public VendaDTO getOne(Integer id) {
		return vendaRepository.findById(id).orElse(new VendaEntity()).toDTO();
	}

	// explicação do professor para preencher atualizar as venda e produto para ser
	// chamando na table venda_produto
	public VendaDTO save(VendaEntity venda) {
		// primeiro teremos que salvar a venda (para preencher a lista de vendas para
		// cada produto)

		VendaEntity vendaSalva = vendaRepository.save(venda);

		// depois teremos que alterar a lista de vendas para cada produtos
		// para cada produto da venda do body, temos que atualizar a venda salva no
		// banco
		if (venda.getProdutos() != null) {
			// todos os produtos da venda
			List<ProdutoEntity> listaProdutos = venda.getProdutos();

			// atualizando as vendas para cada produto acima

			for (int i = 0; i < listaProdutos.size(); i++) {
				// Arrays.asList(): converte um conjunto de objetos em uma lista
				listaProdutos.get(i).setVendas(Arrays.asList(vendaSalva));
			}

			// salvando as atualizações no banco de dados
			produtoRepository.saveAll(listaProdutos);
		}
		return vendaSalva.toDTO();
	}

	public VendaDTO update(Integer id, VendaEntity venda) {
		Optional<VendaEntity> optional = vendaRepository.findById(id);

		if (optional.isPresent() == true) {
			VendaEntity vendas = optional.get();
			vendas.setValorTotal(venda.getValorTotal());

			return vendaRepository.save(vendas).toDTO();
		} else {
			return new VendaEntity().toDTO();
		}
	}

	public void delete(Integer id) {
		vendaRepository.deleteById(id);
	}
}