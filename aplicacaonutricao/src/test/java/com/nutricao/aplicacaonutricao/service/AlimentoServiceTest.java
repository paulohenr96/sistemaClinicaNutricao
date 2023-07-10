package com.nutricao.aplicacaonutricao.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nutricao.aplicacaonutricao.dto.AlimentoDTO;
import com.nutricao.aplicacaonutricao.exception.AlimentoNotFoundException;
import com.nutricao.aplicacaonutricao.mapper.AlimentoMapperImp;
import com.nutricao.aplicacaonutricao.model.Alimento;
import com.nutricao.aplicacaonutricao.repository.AlimentoRepository;

@ExtendWith(SpringExtension.class)
public class AlimentoServiceTest {

	
	@Mock
	AlimentoRepository repository;
	
	@Mock
	AlimentoMapperImp mapper;
	
	@InjectMocks
	AlimentoService service;
	
	
	
	@BeforeEach
	void setUp() {
		
	
	}
	
	
	@Test
	void salva() {
		Alimento ali=new Alimento();
		ali.setNome("novo");
		ali.setCaloria(geraValor());
		ali.setCarboidrato(geraValor());
		ali.setGordura(geraValor());
		ali.setProteina(geraValor());
		
		when(mapper.toEntity(any(AlimentoDTO.class))).thenReturn(ali);
		
		service.salvar(new AlimentoDTO());
		
		verify(repository).save(ali);
		verifyNoMoreInteractions(repository);
	}
	
	
	@Test
	void findAlimentoByIdComSucesso() {
		Alimento ali=new Alimento();
		ali.setId(1L);
		ali.setNome("novo");
		ali.setCaloria(geraValor());
		ali.setCarboidrato(geraValor());
		ali.setGordura(geraValor());
		ali.setProteina(geraValor());
		
		AlimentoDTO dto = mapper.toDTO(ali);
		
		
		when(repository.findById(anyLong())).thenReturn(Optional.of(ali));

		
		AlimentoDTO saida = service.findAlimentoById(1L);
		assertEquals(dto,saida);
		
	}
	
	@Test
	void findAlimentoByIdExcessao() {
		Long idPesquisar=1L;
		
		
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(AlimentoNotFoundException.class,()->service.findAlimentoById(idPesquisar));
	}
	
	
	@Test
	void findAllAlimentoSucesso() {
		int pagina=0;
		
		List<Alimento> lista=new ArrayList();
		lista.add(new Alimento(1L,"alimento1",geraValor(),geraValor(),geraValor(),geraValor(),geraValor()));
		lista.add(new Alimento(2L,"alimento2",geraValor(),geraValor(),geraValor(),geraValor(),geraValor()));
		lista.add(new Alimento(3L,"alimento3",geraValor(),geraValor(),geraValor(),geraValor(),geraValor()));
		
		List<AlimentoDTO> saidaEsperada=new ArrayList();
		


		when(mapper.toDTO(any(Alimento.class))).thenReturn(new AlimentoDTO(1L,"dto",geraValor(),geraValor(),geraValor(),geraValor(),geraValor()));
		when(repository.findAll(any(PageRequest.class))).thenReturn(new PageImpl<>(lista));
		
		Page<AlimentoDTO> saida = service.findAllAlimento(PageRequest.of(pagina, 3));
		
		assertEquals(3,saida.getContent().size());
		


	}
	
	
	
	@Test
	void findAlimentoByNomeComSucesso() {
	
		List<Alimento> lista=new ArrayList();
		lista.add(new Alimento(1L,"alimento1",geraValor(),geraValor(),geraValor(),geraValor(),geraValor()));
		lista.add(new Alimento(2L,"alimento2",geraValor(),geraValor(),geraValor(),geraValor(),geraValor()));
		lista.add(new Alimento(3L,"alimento3",geraValor(),geraValor(),geraValor(),geraValor(),geraValor()));
		lista.add(new Alimento(4L,"alimento4",geraValor(),geraValor(),geraValor(),geraValor(),geraValor()));
		lista.add(new Alimento(5L,"alimento5",geraValor(),geraValor(),geraValor(),geraValor(),geraValor()));
		lista.add(new Alimento(6L,"alimento6",geraValor(),geraValor(),geraValor(),geraValor(),geraValor()));
		lista.add(new Alimento(7L,"alimento7",geraValor(),geraValor(),geraValor(),geraValor(),geraValor()));


		
		
		when(repository.findAllByNome(anyString(),any(PageRequest.class))).thenReturn(new PageImpl<>(lista));

		
		 Page<AlimentoDTO> saida = service.findAllByNome("ali",PageRequest.of(0, 7));
		assertEquals(lista.size(),saida.getContent().size());
		
	}
	
	@Test
	void deleteAlimentoSucesso() {
		Long id=1L;
		
		service.deleteAlimento(id);
		
		verify(repository).deleteById(id);
		verifyNoMoreInteractions(repository);
	}
	
	@Test
	void updateRetornaStatusCreate() {
		AlimentoDTO ali=new AlimentoDTO();
		ali.setId(1L);
		ali.setNome("novo");
		ali.setCaloria(geraValor());
		ali.setCarboidrato(geraValor());
		ali.setGordura(geraValor());
		ali.setProteina(geraValor());
		
		when(repository.findById(1L)).thenReturn(Optional.empty());
		HttpStatus saida = service.updateAlimento(ali);
		
		assertEquals(HttpStatus.CREATED,saida);
	}
	
	@Test
	void updateRetornaStatusOK() {
		AlimentoDTO ali=new AlimentoDTO();
		ali.setId(1L);
		ali.setNome("novo");
		ali.setCaloria(geraValor());
		ali.setCarboidrato(geraValor());
		ali.setGordura(geraValor());
		ali.setProteina(geraValor());
		
		when(repository.findById(1L)).thenReturn(Optional.of(new Alimento(1L,"alimento1",geraValor(),geraValor(),geraValor(),geraValor(),geraValor())));
		HttpStatus saida = service.updateAlimento(ali);
		
		assertEquals(HttpStatus.OK,saida);
	}
	
	BigDecimal geraValor() {
		Random rand=new Random();
		int nextInt = rand.nextInt(40)+1;
		
		return BigDecimal.valueOf(nextInt);
		
	}
	
	
}
