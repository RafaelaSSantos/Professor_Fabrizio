package br.com.nava.dtos;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;

import br.com.nava.entities.ProdutoEntity;
import br.com.nava.entities.VendaEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaDTO {
	
	private int id;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@NotNull(message = "Preenchimento Obrigatório")
	private float valorTotal;
	
	private List<ProdutoEntity> produtos;
	
	//conversão de dto para entity
	public VendaEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, VendaEntity.class);
	}
}