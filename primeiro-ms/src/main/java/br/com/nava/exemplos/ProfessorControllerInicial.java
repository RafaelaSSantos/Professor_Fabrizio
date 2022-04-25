package br.com.nava.exemplos;

/*CRUD completo de uma entidade professor que possui os seguintes atributos: id, nome, cpf (string) , rua, número, CEP (String)*/
//
//import java.util.ArrayList;
//import org.springframework.web.bind.annotation.*;
//import br.com.nava.entities.ProfessorEntity;
//
//@RestController
//@RequestMapping("professores")
//public class ProfessorControllerInicial {
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
//		for (int i = 0; i < listaProfessores.size(); i++) {
//			if (listaProfessores.get(i).getId() == id) {
//				return listaProfessores.get(i);
//			}
//		}
//		return null;
//
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
//		for (int i = 0; i < listaProfessores.size(); i++) {
//			if (listaProfessores.get(i).getId() == id) {
//				listaProfessores.get(i).setNome(prof.getNome());
//				listaProfessores.get(i).setCpf(prof.getCpf());
//				listaProfessores.get(i).setRua(prof.getRua());
//				listaProfessores.get(i).setNumero(prof.getNumero());
//				listaProfessores.get(i).setCep(prof.getCep());
//
//				return listaProfessores.get(i);
//			}
//		}
//
//		return null;
//	}
//
//	@DeleteMapping("{id}")
//	public void deleteProfessor(@PathVariable int id) {
//		for (int i = 0; i < listaProfessores.size(); i++) {
//			if (listaProfessores.get(i).getId() == id) {
//				listaProfessores.remove(i);
//			}
//		}
//	}
//}