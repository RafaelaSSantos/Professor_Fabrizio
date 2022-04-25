package br.com.nava.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.nava.dtos.VendaDTO;
import br.com.nava.entities.VendaEntity;
import br.com.nava.services.VendaService;

@RestController
@RequestMapping("vendas")
public class VendaController {

	@Autowired
	private VendaService vendaService;

	@GetMapping
	public ResponseEntity<List<VendaDTO>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(vendaService.getAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<VendaDTO> getOne(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(vendaService.getOne(id));
	}

	@PostMapping
	public ResponseEntity<VendaDTO> save(@Valid @RequestBody VendaEntity venda) {
		return ResponseEntity.status(HttpStatus.OK).body(vendaService.save(venda));
	}

	@PatchMapping("{id}")
	public ResponseEntity<VendaDTO> update(@PathVariable Integer id, @RequestBody VendaEntity venda) {
		return ResponseEntity.status(HttpStatus.OK).body(vendaService.update(id, venda));
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		vendaService.delete(id);
	}
}