package br.com.nava.exemplos;

/*CRUD completo de uma entidade professor que possui os seguintes atributos: id, nome, cpf (string) , rua, número, CEP (String)*/
//
//import java.util.ArrayList;
//import org.springframework.web.bind.annotation.*;
//import br.com.nava.entities.ProfessorEntity;
//
//@RestController
//@RequestMapping("professores")
//public class ProfessorControllerRefatorado {
//
//	private ArrayList<ProfessorEntity> listaProfessores = new ArrayList<ProfessorEntity>();
//	private int cont = 1;
//
//	@GetMapping
//	public ArrayList<ProfessorEntity> getAllProfessores() {
//		return listaProfessores;
//	}
//
//	@GetMapping("{id}")
//	public ProfessorEntity getOneProfessor(@PathVariable int id) {
//		int indice = findIndex(id);
//		return (indice >= 0 ? listaProfessores.get(indice) : null);
//	}
//
//	@PostMapping
//	public ProfessorEntity saveProfessor(@RequestBody ProfessorEntity prof) {
//		prof.setId(0);
//		listaProfessores.add(prof);
//		cont++;
//
//		return prof;
//	}
//
//	@PatchMapping("{id}")
//	public ProfessorEntity updateProfessor(@PathVariable int id, @RequestBody ProfessorEntity prof) {
//		int indice = findIndex(id);
//
//		if (indice >= 0) {
//
//			listaProfessores.get(indice).setNome(prof.getNome());
//			listaProfessores.get(indice).setCpf(prof.getCpf());
//			listaProfessores.get(indice).setRua(prof.getRua());
//			listaProfessores.get(indice).setNumero(prof.getNumero());
//			listaProfessores.get(indice).setCep(prof.getCep());
//
//			return listaProfessores.get(indice);
//		}
//		return null;
//	}
//
//	@DeleteMapping("{id}")
//	public void deleteProfessor(@PathVariable int id) {
//		int indice = findIndex(id);
//		if (indice >= 0)
//			listaProfessores.remove(indice);
//	}
//
//	public int findIndex(int id) {
//
//		for (int i = 0; i < listaProfessores.size(); i++) {
//			if (listaProfessores.get(i).getId() == id) {
//				return i;
//			}
//		}
//		return -1;
//	}
//}