package br.com.caelum.eats.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.caelum.eats.dto.FormaDePagamentoDto;
import br.com.caelum.eats.model.FormaDePagamento;
import br.com.caelum.eats.model.Restaurante;
import br.com.caelum.eats.model.RestauranteFormaDePagamento;
import br.com.caelum.eats.model.RestauranteFormaDePagamento.RestauranteFormaDePagamentoId;
import br.com.caelum.eats.repository.FormaDePagamentoRepository;
import br.com.caelum.eats.repository.RestauranteFormaDePagamentoRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class FormaDePagamentoController {

	private FormaDePagamentoRepository formaRepo;
	private RestauranteFormaDePagamentoRepository restauranteFormaDePagamentoRepo;

	@GetMapping("/formas-de-pagamento")
	public List<FormaDePagamentoDto> lista() {
		return formaRepo.findAllByOrderByNomeAsc().stream().map(FormaDePagamentoDto::new).collect(Collectors.toList());
	}

	@GetMapping("/admin/formas-de-pagamento/tipos")
	public List<FormaDePagamento.Tipo> tipos() {
		return Arrays.asList(FormaDePagamento.Tipo.values());
	}

	@PostMapping("/admin/formas-de-pagamento")
	public FormaDePagamentoDto adiciona(@RequestBody FormaDePagamento tipoDeCozinha) {
		return new FormaDePagamentoDto(formaRepo.save(tipoDeCozinha));
	}

	@PutMapping("/admin/formas-de-pagamento/{id}")
	public FormaDePagamentoDto atualiza(@RequestBody FormaDePagamento tipoDeCozinha) {
		return  new FormaDePagamentoDto(formaRepo.save(tipoDeCozinha));
	}

	@DeleteMapping("/admin/formas-de-pagamento/{id}")
	public void remove(@PathVariable("id") Long id) {
		formaRepo.deleteById(id);
	}

	@GetMapping("/restaurantes/{idRestaurante}/formas-de-pagamento")
	public List<FormaDePagamentoDto> lista(@PathVariable("idRestaurante") Long idRestaurante) {
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		List<FormaDePagamento> formasDePagamentoDoRestaurante = restauranteFormaDePagamentoRepo
				.findAllByRestauranteOrderByNomeAsc(restaurante);
		return formasDePagamentoDoRestaurante.stream().map(FormaDePagamentoDto::new).collect(Collectors.toList());
	}

	@PostMapping("/parceiros/restaurantes/{idRestaurante}/formas-de-pagamento")
	public void adiciona(@PathVariable("idRestaurante") Long idRestaurante,
			@RequestBody FormaDePagamento formaDePagamento) {
		RestauranteFormaDePagamentoId id = new RestauranteFormaDePagamentoId(idRestaurante, formaDePagamento.getId());
		Restaurante restaurante = new Restaurante();
		restaurante.setId(idRestaurante);
		RestauranteFormaDePagamento restauranteFormaDePagamento = new RestauranteFormaDePagamento(id, restaurante,
				formaDePagamento);
		restauranteFormaDePagamentoRepo.save(restauranteFormaDePagamento);
	}

	@DeleteMapping("/parceiros/restaurantes/{idRestaurante}/formas-de-pagamento/{idFormaDePagamento}")
	public void removeDoRestaurante(@PathVariable("idRestaurante") Long idRestaurante,
			@PathVariable("idFormaDePagamento") Long idFormaDePagamento) {
		RestauranteFormaDePagamentoId id = new RestauranteFormaDePagamentoId(idRestaurante, idFormaDePagamento);
		restauranteFormaDePagamentoRepo.deleteById(id);
	}

}
