package br.com.nava.dtos;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import br.com.nava.entities.ProdutoEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
	
	private int id;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@NotNull(message = "Preenchimento Obrigatório")
	private String nome;	
	
	private String descricao;
	private float preco;		
	

	
	//conversão de dto para entity
	public ProdutoEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, ProdutoEntity.class);
	}	
}