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
import org.springframework.web.bind.annotation.RequestParam;

import com.nutricao.aplicacaonutricao.dto.PacienteDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.PacienteService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

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
	
	@GetMapping("acessar")
	public String redirecionar(Model model) {
		
		return PAGINAS.PAGINA_PACIENTES;
	}
	
	@GetMapping
	public ResponseEntity<Object> findAllPacientes(Model model,
				@RequestParam(name="page",defaultValue="0") int page,
				@RequestParam(name="size",defaultValue="3") int size
){
		
		Page<PacienteDTO> dados = service.findAllPaciente(PageRequest.of(page, size));
		
		return ResponseEntity.ok(JsonMapper.mapearJson(dados));
		
	}
	
	@GetMapping("/{id}")
	public String findByIdPaciente(@PathVariable Long id,Model model) {
		
		PacienteDTO paciente = service.findPacienteById(id);
		model.addAttribute("paciente", paciente);
		return PAGINAS.PAGINA_PERFIL_PACIENTE;
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
