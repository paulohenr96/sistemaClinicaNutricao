package com.nutricao.aplicacaonutricao.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nutricao.aplicacaonutricao.dto.ConsultaDTO;
import com.nutricao.aplicacaonutricao.dto.PacienteDTO;
import com.nutricao.aplicacaonutricao.mapper.JsonMapper;
import com.nutricao.aplicacaonutricao.service.ConsultaService;
import com.nutricao.aplicacaonutricao.service.PacienteService;
import com.nutricao.aplicacaonutricao.util.PAGINAS;

@Controller
@RequestMapping("consultas")
public class ConsultaController {

	private final ConsultaService service;
	
	private final PacienteService pacienteService;

	public ConsultaController(ConsultaService service,PacienteService pacienteService) {
		super();
		this.service = service;
		this.pacienteService = pacienteService;

	}

	@GetMapping
	public String findAllPacientes(Model model) {
		return PAGINAS.PAGINA_CONSULTAS;
	}

	@GetMapping("{paciente}")
	public String verConsulta(@PathVariable Long paciente,Model model) {
		
		PacienteDTO dto = pacienteService.findPacienteById(paciente);
		model.addAttribute("paciente",dto);

		return PAGINAS.PAGINA_DIETA_PACIENTE;
	}
	
	@PreAuthorize(value = "authenticated")
	@PostMapping("{paciente}")
	public ResponseEntity<Object> novaConsulta(@PathVariable Long paciente,Model model,
			@RequestBody ConsultaDTO consultaDTO) {
		
		service.salvar(consultaDTO,paciente);
		
		return ResponseEntity.ok("");
	}
	
	@GetMapping("proximaconsulta/{paciente}")
	public  ResponseEntity<Object> proximaConsulta(@PathVariable Long paciente,Model model) {
		

		ConsultaDTO consulta = service.findProximaConsultaByPaciente(paciente);
		
		return ResponseEntity.ok(JsonMapper.mapearJson(consulta));
	}
	
	@GetMapping("todos")
	public  ResponseEntity<Object> todasConsultas(@RequestParam(name="data") String data,Model model) {
		
	 System.out.println("Data => "+data);
	 List<ConsultaDTO> list = service.findConsultaByData(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		return ResponseEntity.ok(JsonMapper.mapearJson(list));
	}
	@GetMapping("agendadas")
	public  ResponseEntity<Object> todasConsultasAgendadas(@RequestParam(name="data") String data,Model model) {
		
	 System.out.println("Data => "+data);
	 List<ConsultaDTO> list = service.findConsultaAgendadaByData(LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		
		return ResponseEntity.ok(JsonMapper.mapearJson(list));
	}
	
	@PutMapping("cancelada/{consulta}")
	public  ResponseEntity<Object> cancelarConsulta(@PathVariable Long consulta,Model model) {
		
		service.cancelar(consulta);
		
		return ResponseEntity.ok("ok");
	}
	
	@PutMapping("realizada/{consulta}")
	public  ResponseEntity<Object> realizarConsulta(@PathVariable Long consulta,Model model) {
		
		service.realizar(consulta);
		
		return ResponseEntity.ok("ok");
	}
	@GetMapping("todas/{paciente}")
	public ResponseEntity<Object> todasAsConsultasDoPaciente(@PathVariable Long paciente,
				@RequestParam(name="size",defaultValue = "3") int size,
				@RequestParam(name="page",defaultValue="0") int page){
		
				
			Page<ConsultaDTO> pagina = service.findAllConsultaByPaciente(paciente,PageRequest.of(page, size));
			
			return ResponseEntity.ok(JsonMapper.mapearJson(pagina));
			
		
	}
	
	
	
	//	@GetMapping("{paciente}/crud")
//	public ResponseEntity<Object> todasRefs(@PathVariable Long paciente,Model model) {
//
//		return ResponseEntity.ok(service.todasAsRefeicoesEmJson(paciente));
//	}
//	
//	@GetMapping("/refeicao/{refeicao}")
//	public ResponseEntity<Object> findRefeicaoById(@PathVariable Long refeicao,Model model) {
//
//		return ResponseEntity.ok(service.findRefeicaoById(refeicao));
//	}
//	
	
	
}
