package br.com.nava.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.nava.dtos.UsuarioDTO;
import br.com.nava.entities.UsuarioEntity;
import br.com.nava.services.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService userService;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> getAll() {		
		return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<UsuarioDTO> getOne(@PathVariable Integer id) {
	     return ResponseEntity.status(HttpStatus.OK).body(userService.getOne(id));
	}
	
	@PostMapping
	public  ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioEntity usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.save(usuario));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable Integer id, @RequestBody UsuarioEntity usuario) {		
			return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, usuario));
	}	
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		userService.delete(id);
	}
}