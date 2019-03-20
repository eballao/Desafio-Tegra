package com.example.desafiotegra.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "aeroporto")
public class Aeroporto {

	@Id
	@Column(length = 3)
	@Size(min = 3, max = 3)
	private String aeroporto;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cidade")
	private String cidade;
	
	public String getAeroporto() {
		return aeroporto;
	}

	public void setAeroporto(String aeroporto) {
		this.aeroporto = aeroporto;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aeroporto == null) ? 0 : aeroporto.hashCode());
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
		Aeroporto other = (Aeroporto) obj;
		if (aeroporto == null) {
			if (other.aeroporto != null)
				return false;
		} else if (!aeroporto.equals(other.aeroporto))
			return false;
		return true;
	}

}
