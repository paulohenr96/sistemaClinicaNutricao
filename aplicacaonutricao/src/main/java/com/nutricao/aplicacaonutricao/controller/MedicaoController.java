package com.nutricao.aplicacaonutricao.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nutricao.aplicacaonutricao.dto.MedicaoDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.MedicaoService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("medicoes")
public class MedicaoController {

	private final MedicaoService service;

	@GetMapping("{paciente}")
	public String paginaMedicao(@PathVariable Long paciente, Model model) {

		model.addAttribute("paciente", paciente);

		return PAGINAS.PAGINA_MEDICAO_PACIENTE;
	}

	@PostMapping("/{paciente}")
	public ResponseEntity<Object> adicionarMedicao(@PathVariable Long paciente, Model model,
			@RequestBody MedicaoDTO medicao) {

		service.salvar(medicao,paciente);

		return ResponseEntity.ok("ok");
	}

	@GetMapping("{paciente}/crud")
	public ResponseEntity<String> todasAsMedicoesPaginadas(@PathVariable Long paciente, Model model,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "5") Integer size) {

		Page<MedicaoDTO> medicao = service.findAllMedicaoPaginado(paciente, PageRequest.of(page, size));
		System.out.println(medicao.getNumberOfElements());
		return ResponseEntity.ok(JsonMapper.mapearJson(medicao));
	}

	@GetMapping("{paciente}/data")
	public ResponseEntity<String> todasAsMedicoesParaGraficoData(@PathVariable Long paciente, Model model) {

		Page<MedicaoDTO> medicao = service.findAllMedicaoOrdenada(paciente, "data");

		return ResponseEntity.ok(JsonMapper.mapearJson(medicao));
	}

	@DeleteMapping("/{medicao}")
	public ResponseEntity<Object> findRefeicaoById(@PathVariable Long medicao,Model model) {
		service.deleteMedicaoByID(medicao);
		return ResponseEntity.ok("ok");
	}

}
