package br.com.nava.controllers;

/*CRUD completo de uma entidade professor que possui os seguintes atributos: id, nome, cpf (string) , rua, número, CEP (String)*/

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.nava.dtos.ProfessorDTO;
import br.com.nava.services.ProfessorService;

@RestController
@RequestMapping("professores")
public class ProfessorController {	
	
	//@Autowired == ProfessorService professorService = new ProfessorService();
	
	@Autowired
	private ProfessorService professorService;	
	
	@GetMapping()
	public ResponseEntity<List<ProfessorDTO>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProfessorDTO> getOne(@PathVariable int id) {		
		return ResponseEntity.status(HttpStatus.OK).body(professorService.getOne(id));
	}
	
	@PostMapping()
	public ResponseEntity<ProfessorDTO> save(@Valid @RequestBody ProfessorDTO professor) {			
		return ResponseEntity.status(HttpStatus.OK).body(professorService.save(professor.toEntity()));		
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<ProfessorDTO> update(@PathVariable int id, @RequestBody ProfessorDTO professor) {		
		return ResponseEntity.status(HttpStatus.OK).body(professorService.update(id, professor.toEntity()));
	}
	
	@DeleteMapping("{id}")
//	public void delete(@PathVariable int id) {professorService.delete(id);}
	public ResponseEntity<Void> delete(@PathVariable int id) {
		professorService.delete(id);		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping(value = "search-by-name/{name}")
	public ResponseEntity<List<ProfessorDTO>>searchByName(@PathVariable String name){
		
		List<ProfessorDTO> lista = professorService.searchByName(name);
		
		return ResponseEntity.ok().body(lista);
		
		//forma simples
		//return ResponseEntity.ok().body(professorService.searchByName(name));
	}
}