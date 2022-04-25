package br.com.nava.entities;

import javax.persistence.*;
import org.modelmapper.ModelMapper;
import br.com.nava.dtos.ProfessorDTO;
import lombok.*;

/*CRUD completo de uma entidade professor que possui os seguintes atributos: id, nome, cpf (string) , rua, numero, CEP (String)*/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PROFESSORES")
public class ProfessorEntity {

	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nome;
	private String cpf;
	private String rua;
	private int numero;
	private String cep;
	
	//conversão de entity para dto
	public ProfessorDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		ProfessorDTO dto = mapper.map(this, ProfessorDTO.class);		
		return dto;
	}
}