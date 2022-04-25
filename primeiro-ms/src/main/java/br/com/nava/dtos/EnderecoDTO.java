package br.com.nava.dtos;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.modelmapper.ModelMapper;
import br.com.nava.entities.EnderecoEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Preenchimento Obrigatório")
	@NotEmpty(message = "Preenchimento Obrigatório")
	@NotNull(message = "Preenchimento Obrigatório")
	private String rua;
	
	private int numero;
	private String cep;
	private String cidade;
	private String estado;	
	
	//conversão de dto para entity
	public EnderecoEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, EnderecoEntity.class);
	}
}