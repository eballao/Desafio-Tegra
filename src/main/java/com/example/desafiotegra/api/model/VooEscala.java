package com.example.desafiotegra.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class VooEscala {

	private String origem;
	
	private String destino;
	
	private LocalDateTime saida;
	
	private LocalDateTime chegada;
	
	private List<Voo> trechos;
	
	public VooEscala() {
		
	}
	
	public VooEscala(String origem, String destino, LocalDateTime saida, LocalDateTime chegada, List<Voo> trechos){
		this.origem = origem;
		this.destino = destino;
		this.saida = saida;
		this.chegada = chegada;
		this.trechos = trechos;
	}
	

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public LocalDateTime getChegada() {
		return chegada;
	}

	public void setChegada(LocalDateTime chegada) {
		this.chegada = chegada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	public List<Voo> getTrechos() {
		return trechos;
	}

	public void setTrechos(List<Voo> trechos) {
		this.trechos = trechos;
	}
	
}
