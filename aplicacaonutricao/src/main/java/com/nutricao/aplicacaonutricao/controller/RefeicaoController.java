package com.nutricao.aplicacaonutricao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nutricao.aplicacaonutricao.dto.AlimentoRefeicaoDTO;
import com.nutricao.aplicacaonutricao.dto.RefeicaoDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.RefeicaoService;

@Controller
@RequestMapping("refeicao")
public class RefeicaoController {

	private final RefeicaoService service;

	public RefeicaoController(RefeicaoService service2) {
		// TODO Auto-generated constructor stub
		this.service=service2;
	}

	@PostMapping("{refeicao}")
	public ResponseEntity<Object> adicionarAlimentoRefeicao(@PathVariable Long refeicao, Model model,
			@RequestBody AlimentoRefeicaoDTO a) {

		service.addalimento(refeicao, a);

		return ResponseEntity.ok("ok");
	}

	@PostMapping("nova/{paciente}")
	public ResponseEntity<Object> adicionarRefeicao(@PathVariable Long paciente, Model model,
		 @RequestBody RefeicaoDTO r) {

		service.salvar(paciente, r);

		return ResponseEntity.ok("ok");
	}
	
	
	@DeleteMapping("alimento/{alimento}")
	public ResponseEntity<Object> removerAlimentoRefeicao(@PathVariable(name="alimento") Long alimentoRefeicao, Model model) {

		service.removerAlimento(alimentoRefeicao);

		return ResponseEntity.ok("ok");
	}

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
