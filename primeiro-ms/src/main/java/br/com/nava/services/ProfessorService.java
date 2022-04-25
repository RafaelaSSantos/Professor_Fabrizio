package br.com.nava.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.entities.ProfessorEntity;
import br.com.nava.repositories.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
//
//	public void mostrar() {
//		System.out.println("mostrar");
//	}

//	public List<ProfessorEntity> getAll() {
//		return professorRepository.findAll();
//	}
	public List<ProfessorDTO> getAll(){
		List<ProfessorEntity> lista =  professorRepository.findAll();
		
		List<ProfessorDTO> listaDTO = new ArrayList<>();
		
		/*
		 * for (int i = 0; i < lista.size(); i++) {
			ProfessorEntity professorEntity = lista.get(i);
		}*/
		// foreach
		// 1-) Tipo da variável de cada elemento da lista
		// 2-) nome local da variável 
		// - 3 lista com elementos a ser percorrido
		for (ProfessorEntity professorEntity : lista) {						
			
//			ProfessorDTO dto = new ProfessorDTO();			
//			dto.setId( professorEntity.getId() );
//			dto.setCep( professorEntity.getCep() );
//			dto.setCpf( professorEntity.getCpf() );
//			dto.setNome( professorEntity.getNome() );
//			dto.setNumero( professorEntity.getNumero() );
//			dto.setRua( professorEntity.getRua() );			
//			listaDTO.add(dto);
			listaDTO.add(professorEntity.toDTO());
		}
		
		return listaDTO;		
	}

	public ProfessorDTO getOne(int id) {
		Optional<ProfessorEntity> optional = professorRepository.findById(id);

		ProfessorEntity professor = optional.orElse(new ProfessorEntity());

		return professor.toDTO();
	}

	public ProfessorDTO save(ProfessorEntity professor) {
		return professorRepository.save(professor).toDTO();
	}	

	public ProfessorDTO update(int id, ProfessorEntity professor) {
		Optional<ProfessorEntity> optional = professorRepository.findById(id);
		if (optional.isPresent() == true) {
			ProfessorEntity prof = optional.get();
			prof.setNome(professor.getNome());
			prof.setCpf(professor.getCpf());
			prof.setCep(professor.getCep());
			prof.setRua(professor.getRua());
			prof.setNumero(professor.getNumero());

			return professorRepository.save(prof).toDTO();
		} else {
			return new ProfessorEntity().toDTO();
		}
	}
	
	public void delete(int id) {		
		professorRepository.deleteById(id);
	}
	
	public List<ProfessorDTO> searchByName(String nome){

		//List<ProfessorEntity> lista =  professorRepository.findByNomeContains(nome);		
		//List<ProfessorEntity> lista =  professorRepository.searchByNome2(nome);
		List<ProfessorEntity> lista =  professorRepository.searchByNomeNativeSQL(nome);
		//List<ProfessorEntity> lista =  professorRepository.searchByNome(nome);
				
		List<ProfessorDTO> dtos = new ArrayList<>();
		
		for (ProfessorEntity professorEntity : lista) {
			dtos.add( professorEntity.toDTO() );
		}
		
		return dtos;
	}
}