package com.example.desafiotegra.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.desafiotegra.api.model.Voo;
import com.example.desafiotegra.api.repository.voo.VooRepositoryQuery;

public interface VooRepository extends JpaRepository<Voo, Long>, VooRepositoryQuery {

}
