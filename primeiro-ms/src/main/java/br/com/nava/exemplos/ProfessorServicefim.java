package br.com.nava.exemplos;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.com.nava.entities.ProfessorEntity;

/*@Service
public class ProfessorServicefim {
	
	public void mostrar() {
		System.out.println("mostrar");
	}
	
	public ProfessorEntity getOne(int id, ArrayList<ProfessorEntity> listaProfessores) {
		int indice = findIndex(id, listaProfessores);
		return (indice >= 0 ? listaProfessores.get(indice) : null);
	}

	public int findIndex(int id, ArrayList<ProfessorEntity> listaProfessores) {
		for (int i = 0; i < listaProfessores.size(); i++) {
			if (listaProfessores.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	public ProfessorEntity update(int id, ProfessorEntity prof, ArrayList<ProfessorEntity> listaProfessores) {
		
		int indice = findIndex(id,listaProfessores);

		if (indice >= 0) {

			listaProfessores.get(indice).setNome(prof.getNome());
			listaProfessores.get(indice).setCpf(prof.getCpf());
			listaProfessores.get(indice).setRua(prof.getRua());
			listaProfessores.get(indice).setNumero(prof.getNumero());
			listaProfessores.get(indice).setCep(prof.getCep());

			return listaProfessores.get(indice);
		}
		return null;
	}	
	
	public void delete(int id, ArrayList<ProfessorEntity> listaProfessores) {
		int indice = findIndex(id, listaProfessores);
		if (indice >= 0) listaProfessores.remove(indice);
	}	
}*/