package com.example.desafiotegra.api.repository.voo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.example.desafiotegra.api.model.Voo;
import com.example.desafiotegra.api.repository.filter.VooFilter;


public class VooRepositoryImpl implements VooRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Voo> findByFiltro(final VooFilter vooFilter) {

		final StringBuilder hqlBuilder = new StringBuilder();

		hqlBuilder.append("select distinct voo from Voo voo ");
		hqlBuilder.append("where voo.origem = :origem ");
		hqlBuilder.append("and voo.saida >= :data ");
		hqlBuilder.append("and voo.saida <= :dataFinal ");
		hqlBuilder.append("or voo.destino = :destino ");
		hqlBuilder.append("and voo.saida >= :data ");
		hqlBuilder.append("and voo.saida <= :dataFinal ");
		hqlBuilder.append("order by voo.saida ");

		Query query = manager.createQuery(hqlBuilder.toString());
		query.setParameter("origem", vooFilter.getOrigem());
		query.setParameter("destino", vooFilter.getDestino());
		query.setParameter("data", vooFilter.getData().atStartOfDay());
		query.setParameter("dataFinal", vooFilter.getData().atTime(23, 59));

		return query.getResultList();
	}

}
