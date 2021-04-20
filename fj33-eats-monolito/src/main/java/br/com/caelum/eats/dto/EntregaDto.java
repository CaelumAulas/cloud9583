package br.com.caelum.eats.dto;

import br.com.caelum.eats.model.Entrega;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntregaDto {

	private Long id;
	private ClienteDto cliente;
	private String cep;
	private String endereco;
	private String complemento;

	public EntregaDto(Entrega entrega) {
		this(entrega.getId(), new ClienteDto(entrega.getCliente()), entrega.getCep(), entrega.getEndereco(), entrega.getComplemento());
	}

}
