package com.nutricao.aplicacaonutricao.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nutricao.aplicacaonutricao.dto.AlimentoDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.AlimentoService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("alimentos/crud")
public class AlimentosController {

	
	
	private final AlimentoService service;
	

	
	@PostMapping
	public ResponseEntity<String> salvar(Model model,@ModelAttribute(name = "alimento") AlimentoDTO ali) {
		service.salvar(ali);
		return ResponseEntity.ok("salvo");
	}
	
	@GetMapping
	public ResponseEntity<Object>  findAllAlimentos(Model model,
			
			@RequestParam(name="page",defaultValue = "0") Integer page,
			@RequestParam(name="size",defaultValue="3") Integer size	
			
			) {
		Page<AlimentoDTO> saida = service.findAllAlimento(PageRequest.of(page, size));
		return ResponseEntity.ok(JsonMapper.mapearJson(saida));
	}
	
	
	@GetMapping("{nome}")
	public ResponseEntity<Object>  findByNomeAlimento(@PathVariable String nome,
			@RequestParam(name="page",defaultValue = "0") Integer page,
			@RequestParam(name="size",defaultValue="3") Integer size,
			Model model) {
		Page<AlimentoDTO> saida = service.findAllByNome(nome,PageRequest.of(page, size));
		System.out.println(saida.getTotalPages());
		return ResponseEntity.ok(JsonMapper.mapearJson(saida));
	}
	@GetMapping("id/{id}")
	public ResponseEntity<Object>  findByNomeAlimento(@PathVariable Long id,
			
			Model model) {
		AlimentoDTO alimento = service.findAlimentoById(id);
		return ResponseEntity.ok(JsonMapper.mapearJson(alimento));
	}
	
}
