package br.com.nava.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.nava.dtos.ProdutoDTO;
import br.com.nava.entities.ProdutoEntity;
import br.com.nava.services.ProdutoService;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<ProdutoDTO>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ProdutoDTO> getOne(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.getOne(id));
	}
	
	@PostMapping
	public ResponseEntity<ProdutoDTO> save(@Valid @RequestBody ProdutoEntity produto) {
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produto));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable Integer id, @RequestBody ProdutoEntity produto) {
		return ResponseEntity.status(HttpStatus.OK).body(produtoService.update(id, produto));
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		produtoService.delete(id);
	}
}