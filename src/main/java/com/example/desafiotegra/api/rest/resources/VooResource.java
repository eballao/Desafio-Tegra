package com.example.desafiotegra.api.rest.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.desafiotegra.api.model.Voo;
import com.example.desafiotegra.api.model.VooEscala;
import com.example.desafiotegra.api.model.VooImportacao;
import com.example.desafiotegra.api.repository.VooRepository;
import com.example.desafiotegra.api.repository.filter.VooFilter;

@RestController
@RequestMapping("/voos")
public class VooResource {
	
	@Autowired
	private VooRepository vooRepository;
	
	@GetMapping
	public List<Voo> listar(){
		return vooRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Voo> criar(@RequestBody Voo Voo, HttpServletResponse response) {
		Voo VooSalvo = vooRepository.save(Voo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{Voo}").buildAndExpand(VooSalvo.getVoo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(VooSalvo);
	}
	
	@GetMapping("/filtrar")
	public List<VooEscala> pesquisar(VooFilter vooFilter){
		List<Voo> voos =  vooRepository.findByFiltro(vooFilter);
		List<VooEscala> voosEscalas = new ArrayList<>();
		
		for (Voo voo : voos) {
			if(voo.getOrigem().equals(vooFilter.getOrigem()) && voo.getDestino().equals(vooFilter.getDestino())) {
				final List<Voo> trechos = new ArrayList<>();
				trechos.add(voo);
				VooEscala vooEscala = new VooEscala(vooFilter.getOrigem(), vooFilter.getDestino(), voo.getSaida(), voo.getChegada(), trechos);
				voosEscalas.add(vooEscala);
			}
			for (Voo voo2 : voos) {
				if(voo.getOrigem().equals(vooFilter.getOrigem()) && voo.getDestino().equals(voo2.getOrigem()) 
						&& voo.getChegada().isBefore(voo2.getSaida()) && voo2.getSaida().isBefore(voo.getChegada().plusHours(12L))) {
					final List<Voo> trechos = new ArrayList<>();
					trechos.add(voo);
					trechos.add(voo2);
					VooEscala vooEscala = new VooEscala(vooFilter.getOrigem(), vooFilter.getDestino(), voo.getSaida(), voo2.getChegada(), trechos);
					voosEscalas.add(vooEscala);
				}
			}
		}
		
		return voosEscalas;
	}
	
	@PostMapping("/importar")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<List<Voo>> importarVoos(@RequestBody List<VooImportacao> voos) {
		
		List<Voo> voosImportados = new ArrayList<>();
		for (VooImportacao voo : voos) {
			Voo novoVoo = new Voo(voo.getVoo(), voo.getOrigem(), voo.getDestino(), voo.getDataJson(), voo.getSaidaJson(), voo.getChegadaJson(), voo.getPreco(), "99Planes");
			voosImportados.add(novoVoo);
		}
		
		List<Voo> voosSalvos = vooRepository.saveAll(voosImportados);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(voosSalvos);
	}
	
}
