package com.nutricao.aplicacaonutricao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nutricao.aplicacaonutricao.dto.PacienteDTO;
import com.nutricao.aplicacaonutricao.dto.RefeicaoDTO;
import com.nutricao.aplicacaonutricao.service.DietaService;
import com.nutricao.aplicacaonutricao.service.PacienteService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

@Controller
@RequestMapping("dietas")
public class DietaController {

	private final DietaService service;
	
	private final PacienteService pacienteService;
	
	

	public DietaController(DietaService service,PacienteService pacienteService) {
		super();
		this.service = service;
		this.pacienteService = pacienteService;

	}

	@GetMapping("{paciente}")
	public String verDieta(@PathVariable Long paciente,Model model) {
		
//		model.addAttribute("paciente",paciente);
//		System.out.println(paciente);
		
		PacienteDTO pacienteDTO = pacienteService.findPacienteById(paciente);
		model.addAttribute("paciente", pacienteDTO);
		
		
		return PAGINAS.PAGINA_DIETA_PACIENTE;
	}
	
	@PostMapping("{paciente}")
	public ResponseEntity<Object> adicionarRefeicao(@PathVariable Long paciente,Model model,
			@RequestBody RefeicaoDTO refeicaoDTO) {
		
		String json=service.novaRefeicao(refeicaoDTO,paciente);
		
		model.addAttribute("paciente",paciente);
		return ResponseEntity.ok(json);
	}
	

	@GetMapping("{paciente}/crud")
	public ResponseEntity<Object> todasRefs(@PathVariable Long paciente,Model model) {

		return ResponseEntity.ok(service.todasAsRefeicoesEmJson(paciente));
	}
	
	@GetMapping("/refeicao/{refeicao}")
	public ResponseEntity<Object> findRefeicaoById(@PathVariable Long refeicao,Model model) {

		return ResponseEntity.ok(service.findRefeicaoById(refeicao));
	}
	
	
	
}
