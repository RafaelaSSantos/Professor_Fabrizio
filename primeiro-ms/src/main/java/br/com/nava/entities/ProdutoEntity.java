package br.com.nava.entities;

import java.util.List;
import javax.persistence.*;
import org.modelmapper.ModelMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.com.nava.dtos.ProdutoDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PRODUTOS")
public class ProdutoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nome;	
	private String descricao;
	private float preco;	
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(
			name = "VENDA_PRODUTO",
			joinColumns = @JoinColumn(name = "PRODUTO_ID"),
			inverseJoinColumns = @JoinColumn(name = "VENDA_ID"))
	private List<VendaEntity> vendas;
	
	//conversão de entity para dto
	public ProdutoDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		ProdutoDTO dto = mapper.map(this,  ProdutoDTO.class);
		return dto;
	}
}