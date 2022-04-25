package br.com.nava.entities;

import javax.persistence.*;
import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.nava.dtos.EnderecoDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="ENDERECOS")
public class EnderecoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String rua;
	private int numero;
	private String cep;
	private String cidade;
	private String estado;	
	
	@JsonIgnore
	@OneToOne(mappedBy = "endereco")
	@ToString.Exclude
	private UsuarioEntity usuario;	
	
	//conversão de entity para dto
	public EnderecoDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		EnderecoDTO dto = mapper.map(this, EnderecoDTO.class);
		return dto;
	}
}