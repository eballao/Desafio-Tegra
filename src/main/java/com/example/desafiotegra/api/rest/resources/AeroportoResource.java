package com.example.desafiotegra.api.rest.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.desafiotegra.api.model.Aeroporto;
import com.example.desafiotegra.api.repository.AeroportoRepository;

@RestController
@RequestMapping("/aeroportos")
public class AeroportoResource {
	
	@Autowired
	private AeroportoRepository aeroportoRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Aeroporto> criar(@RequestBody Aeroporto aeroporto, HttpServletResponse response) {
		Aeroporto aeroportoSalvo = aeroportoRepository.save(aeroporto);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{aeroporto}").buildAndExpand(aeroportoSalvo.getAeroporto()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(aeroportoSalvo);
	}
	
	@PostMapping("/importar")
	public ResponseEntity<List<Aeroporto>> importarAeroportos(@RequestBody List<Aeroporto> aeroportos) {
		List<Aeroporto> aeroportosSalvos = aeroportoRepository.saveAll(aeroportos);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(aeroportosSalvos);
	}
	
	@GetMapping
	public List<Aeroporto> listar(){
		return aeroportoRepository.findAll();
	}
	
	@GetMapping("/{sigla}")
	public Optional<Aeroporto> buscarPorSigla(@PathVariable String sigla) {
		return aeroportoRepository.findById(sigla.toUpperCase());
	}

}
