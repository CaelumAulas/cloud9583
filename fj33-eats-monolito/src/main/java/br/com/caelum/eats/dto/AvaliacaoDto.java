package br.com.caelum.eats.dto;

import br.com.caelum.eats.model.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoDto {

	private Long id;
	private Integer nota;
	private String comentario;

	public AvaliacaoDto(Avaliacao avaliacao) {
		this(avaliacao.getId(), avaliacao.getNota(), avaliacao.getComentario());
	}
}
