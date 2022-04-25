package br.com.nava.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.nava.dtos.EnderecoDTO;
import br.com.nava.entities.EnderecoEntity;
import br.com.nava.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<EnderecoDTO> getAll() {
		List<EnderecoEntity> lista = enderecoRepository.findAll();
		List<EnderecoDTO> listaDTO = new ArrayList<>();

		for (EnderecoEntity enderecoEntity : lista) {
			listaDTO.add(enderecoEntity.toDTO());
		}
		return listaDTO;
	}

	public EnderecoDTO getOne(Integer id) {
		return enderecoRepository.findById(id).orElse(new EnderecoEntity()).toDTO();
	}

	public EnderecoDTO save(EnderecoEntity endereco) {
		return enderecoRepository.save(endereco).toDTO();
	}

	public EnderecoDTO update(Integer id, EnderecoEntity endereco) {
		Optional<EnderecoEntity> optional = enderecoRepository.findById(id);

		if (optional.isPresent() == true) {
			EnderecoEntity enderecos = optional.get();
			enderecos.setRua(endereco.getRua());
			enderecos.setNumero(endereco.getNumero());
			enderecos.setCep(endereco.getCep());
			enderecos.setCidade(endereco.getCidade());
			enderecos.setEstado(endereco.getEstado());
			enderecos.setUsuario(endereco.getUsuario());

			return enderecoRepository.save(enderecos).toDTO();
		} else {
			return new EnderecoEntity().toDTO();
		}
	}

	public void delete(Integer id) {
		enderecoRepository.deleteById(id);
	}
}