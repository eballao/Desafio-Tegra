package com.example.desafiotegra.api.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "voo")
public class Voo {

	@Id
	@JsonIgnore
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@JsonIgnoreProperties(ignoreUnknown=true)
	@JsonIgnore
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@Column(length = 8)
	@Size(max = 8)
	private String voo;
	
	@Column(name = "origem", length = 3)
	@Size(max = 3)
	private String origem;
	
	@Column(name = "destino", length = 3)
	@Size(max = 3)
	private String destino;
	
	@Column(name = "data_saida")
	private LocalDateTime saida;

	@Column(name = "data_chegada")
	private LocalDateTime chegada;
	
	@Column(name = "operadora")
	private String operadora;
	
	@Column(name = "preco", nullable = false, precision = 10, scale = 2)
	private BigDecimal preco;
	
	public Voo() {
		
	}
	
	public Voo(String voo, String origem, String destino, LocalDate data, LocalTime horaSaida, LocalTime horaChegada, BigDecimal preco, String operadora){
		this.voo = voo;
		this.origem = origem;
		this.destino = destino;
		this.saida = data.atTime(horaSaida.getHour(), horaChegada.getMinute());
		this.chegada = data.atTime(horaChegada.getHour(), horaChegada.getMinute());
		this.preco = preco;
		this.operadora = operadora;
	}
	
	public Voo(String voo, String origem, String destino, LocalDate data, Time horaSaida, Time horaChegada, BigDecimal preco, String operadora) {
		this.voo = voo;
		this.origem = origem;
		this.destino = destino;
		this.saida = data.atTime(horaSaida.getHours(), horaSaida.getMinutes());
		this.chegada = data.atTime(horaChegada.getHours(), horaChegada.getMinutes());
		this.preco = preco;
		this.operadora = operadora;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVoo() {
		return voo;
	}

	public void setVoo(String voo) {
		this.voo = voo;
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

	public BigDecimal getPreco() {
		return preco;
	}

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	public LocalDateTime getChegada() {
		return chegada;
	}

	public void setChegada(LocalDateTime chegada) {
		this.chegada = chegada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voo other = (Voo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
