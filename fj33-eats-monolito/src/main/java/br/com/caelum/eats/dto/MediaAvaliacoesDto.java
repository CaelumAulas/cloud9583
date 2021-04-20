package br.com.caelum.eats.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaAvaliacoesDto {

	private Long restauranteId;
	private Double media;

}
