package br.com.nava.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.nava.dtos.UsuarioDTO;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<UsuarioDTO> getAll() {		
		List<UsuarioEntity> lista = usuarioRepository.findAll();
		List<UsuarioDTO> listaDTO = new ArrayList<>();	
		for(UsuarioEntity usuarioEntity : lista) {
			listaDTO.add(usuarioEntity.toDTO());
		}
		return listaDTO;
	}

	public UsuarioDTO getOne(Integer id) {
		// forma resumida
		return usuarioRepository.findById(id).orElse(new UsuarioEntity()).toDTO();
	}

	public UsuarioDTO save(UsuarioEntity user) {
		return usuarioRepository.save(user).toDTO();
	}

	public UsuarioDTO update(Integer id, UsuarioEntity usuario) {
		Optional<UsuarioEntity> optional = usuarioRepository.findById(id);

		if (optional.isPresent() == true) {
			UsuarioEntity usuarios = optional.get();
			usuarios.setNome(usuario.getNome());
			usuarios.setEmail(usuario.getEmail());

			return usuarioRepository.save(usuarios).toDTO();
		} else {
			return new UsuarioEntity().toDTO();
		}
	}

	public void delete(Integer id) {
		usuarioRepository.deleteById(id);
	}
}