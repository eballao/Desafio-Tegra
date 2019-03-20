package com.example.desafiotegra.api.model;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class VooImportacao {

	@CsvBindByName(column = "numero_voo")
	private String voo;
	
	@CsvBindByName(column = "aeroporto_origem")
	private String origem;
	
	@CsvBindByName(column = "aeroporto_destino")
	private String destino;
	
	private String operadora;
	
	@JsonAlias("data_saida")
	private LocalDate dataJson;
	
	@CsvDate("yyyy-MM-dd")
	@CsvBindByName(column = "data")
	private Date dataCSV;
	
	@JsonAlias("saida")
	private LocalTime saidaJson;
	
	@JsonAlias("chegada")
	private LocalTime chegadaJson;
	
	@CsvDate("HH:mm")
	@CsvBindByName(column = "horario_saida")
	private Time saidaCSV;
	
	@CsvDate("HH:mm")
	@CsvBindByName(column = "horario_chegada")
	private Time chegadaCSV;
	
	@CsvBindByName(column = "preco")
	@JsonAlias("valor")
	private BigDecimal preco;

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

	public String getOperadora() {
		return operadora;
	}

	public void setOperadora(String operadora) {
		this.operadora = operadora;
	}

	public LocalDate getDataJson() {
		return dataJson;
	}

	public void setDataJson(LocalDate dataJson) {
		this.dataJson = dataJson;
	}

	public Date getDataCSV() {
		return dataCSV;
	}

	public void setDataCSV(Date dataCSV) {
		this.dataCSV = dataCSV;
	}

	public LocalTime getSaidaJson() {
		return saidaJson;
	}

	public void setSaidaJson(LocalTime saidaJson) {
		this.saidaJson = saidaJson;
	}

	public LocalTime getChegadaJson() {
		return chegadaJson;
	}

	public void setChegadaJson(LocalTime chegadaJson) {
		this.chegadaJson = chegadaJson;
	}

	public Time getSaidaCSV() {
		return saidaCSV;
	}

	public void setSaidaCSV(Time saidaCSV) {
		this.saidaCSV = saidaCSV;
	}

	public Time getChegadaCSV() {
		return chegadaCSV;
	}

	public void setChegadaCSV(Time chegadaCSV) {
		this.chegadaCSV = chegadaCSV;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
}
