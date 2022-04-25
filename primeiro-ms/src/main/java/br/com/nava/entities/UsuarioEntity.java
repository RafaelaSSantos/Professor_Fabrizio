package br.com.nava.entities;

import java.util.List;
import javax.persistence.*;
import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.nava.dtos.UsuarioDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USUARIOS")
public class UsuarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nome;
	private String email;		
	
	@OneToOne
	@JoinColumn(name = "endereco_id")	
	private EnderecoEntity endereco;	
	
	@JsonIgnore	
	@OneToMany(mappedBy = "usuario")
	private List<VendaEntity> vendas;
	
	//conversão de entity para dto
	public UsuarioDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		UsuarioDTO dto = mapper.map(this, UsuarioDTO.class);
		return dto;
	}
}