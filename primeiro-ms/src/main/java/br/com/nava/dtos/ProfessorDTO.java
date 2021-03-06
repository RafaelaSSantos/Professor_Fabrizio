package br.com.nava.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;
import br.com.nava.entities.ProfessorEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {

	private int id;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@NotNull(message = "Preenchimento Obrigatório")
	@Length(min = 3, max = 80, message = "O número de caracteres deve ser entre 3 e 80")
	//regex sem o espaço
	//@Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ]*$", message = "É valido apenas caracteres")
	//regex com espaço
	@Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]*$", message = "É valido apenas caracteres")
	private String nome;

	private String cpf;	
	private String rua;
	private String cep;	
	private int numero;
	
	//conversão de dto para entity
	public ProfessorEntity toEntity() {
		ModelMapper mapper = new ModelMapper();			
		return mapper.map(this, ProfessorEntity.class);
	}
}