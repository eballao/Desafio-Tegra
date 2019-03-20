package com.example.desafiotegra.api.repository.voo;

import java.util.List;

import com.example.desafiotegra.api.model.Voo;
import com.example.desafiotegra.api.repository.filter.VooFilter;

public interface VooRepositoryQuery {

	public List<Voo> findByFiltro(VooFilter vooFilter);
	
}
