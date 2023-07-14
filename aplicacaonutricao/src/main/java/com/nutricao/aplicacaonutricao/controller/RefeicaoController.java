package com.nutricao.aplicacaonutricao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nutricao.aplicacaonutricao.dto.AlimentoRefeicaoDTO;
import com.nutricao.aplicacaonutricao.dto.RefeicaoDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.RefeicaoService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
@RequestMapping("refeicao")
public class RefeicaoController {

	private final RefeicaoService service;

//	@GetMapping("{paciente}")
//	public String verRefeicao(@PathVariable Long paciente,Model model) {
//		
//		model.addAttribute("paciente",paciente);
//
//		System.out.println(paciente);
//		return "dietapaciente";
//	}
//	

	@PostMapping("{refeicao}")
	public ResponseEntity<Object> adicionarRefeicao(@PathVariable Long refeicao, Model model,
			@ModelAttribute(name = "alimento") AlimentoRefeicaoDTO a) {

		service.addalimento(refeicao, a);

		return ResponseEntity.ok("ok");
	}

	@PostMapping("nova/{paciente}")
	public ResponseEntity<Object> adicionarRefeicao(@PathVariable Long paciente, Model model,
			@ModelAttribute(name = "refeicao") RefeicaoDTO r) {

		service.salvar(paciente, r);

		return ResponseEntity.ok("ok");
	}
	
	
	@DeleteMapping("alimento/{alimento}")
	public ResponseEntity<Object> removerAlimento(@PathVariable Long alimento, Model model) {

		service.removerAlimento(alimento);

		return ResponseEntity.ok("ok");
	}

//	@PostMapping("{paciente}")
//	public ResponseEntity<Object> adicionarRefeicao(@PathVariable Long paciente,Model model,
//			@ModelAttribute(name = "refeicao") RefeicaoDTO refeicaoDTO) {
//		
//		System.out.println(refeicaoDTO.getNome());
//		String json=service.novaRefeicao(refeicaoDTO,paciente);
//		
//		model.addAttribute("paciente",paciente);
//		return ResponseEntity.ok(json);
//	}
//	
//
//	@GetMapping("{paciente}/crud")
//	public ResponseEntity<Object> todasRefs(@PathVariable Long paciente,Model model) {
//
//		return ResponseEntity.ok(service.todasAsRefeicoesEmJson(paciente));
//	}
//	
	@GetMapping("/{refeicao}")
	public ResponseEntity<Object> findRefeicaoById(@PathVariable Long refeicao, Model model) {
		RefeicaoDTO findById = service.findById(refeicao);
		return ResponseEntity.ok(JsonMapper.mapearJson(findById));
	}

	@DeleteMapping("/{refeicao}")
	public ResponseEntity<Object> deletarRefeicao(@PathVariable Long refeicao, Model model) {
		service.deleteRefeicao(refeicao);
		return ResponseEntity.ok("ok");
	}

}
