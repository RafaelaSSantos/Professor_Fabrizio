package br.com.nava.entities;

import java.util.List;
import javax.persistence.*;
import org.modelmapper.ModelMapper;
import br.com.nava.dtos.VendaDTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VENDAS")
public class VendaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "VALOR_TOTAL")
	private float valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "USUARIO_ID")
	private UsuarioEntity usuario;
	
	@ManyToMany(mappedBy = "vendas")
	private List<ProdutoEntity> produtos;
	
	//conversão de entity para dto
	public VendaDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		VendaDTO dto = mapper.map(this, VendaDTO.class);
		return dto;
	}
}