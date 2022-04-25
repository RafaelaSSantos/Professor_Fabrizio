package br.com.nava.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import br.com.nava.entities.UsuarioEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO{
	
	private int id;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@NotNull(message = "Preenchimento Obrigatório")
	private String nome;
	
	private String email;	

	//conversão de dto para entity
	public UsuarioEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, UsuarioEntity.class);
	}
}