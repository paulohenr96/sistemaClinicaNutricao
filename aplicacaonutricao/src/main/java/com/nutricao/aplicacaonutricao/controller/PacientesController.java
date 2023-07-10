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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nutricao.aplicacaonutricao.dto.PacienteDTO;
import com.nutricao.aplicacaonutricao.service.PacienteService;

@Controller
@RequestMapping("pacientes/crud")
public class PacientesController {

	
	
	private final PacienteService service;
	
	public PacientesController(PacienteService service) {
		this.service = service;
	}
	
	
	@PostMapping
	public ResponseEntity<String> salvar(Model model,@ModelAttribute(name = "paciente") PacienteDTO paciente) {
		service.salvar(paciente);
		return ResponseEntity.ok("salvo");
	}
	
	@GetMapping
	public String findAllPacientes(Model model) {
		Page<PacienteDTO> page = service.findAllPaciente(PageRequest.of(0, 50));
		model.addAttribute("pacientes", page);
		System.out.println(page.getContent());
		return "pacientes.html";
	}
	
	@GetMapping("/{id}")
	public String findByIdPaciente(@PathVariable Long id,Model model) {
		
		PacienteDTO paciente = service.findPacienteById(id);
		System.out.println(paciente.getDescricao());
		model.addAttribute("paciente", paciente);
		return "perfilpaciente.html";
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePaciente(@PathVariable Long id,Model model) {
		service.deletePaciente(id);
		
		return  ResponseEntity.ok("Paciente Removido Com Sucesso.");
	}
	
	@PutMapping
	public ResponseEntity<String> editar(Model model,@ModelAttribute(name = "paciente") PacienteDTO paciente) {
		System.out.println("Descricao = > "+paciente.getDescricao());
		service.updatePaciente(paciente);
		
		return ResponseEntity.ok("Mudanças realizadas com sucesso.");
	}
	
}
