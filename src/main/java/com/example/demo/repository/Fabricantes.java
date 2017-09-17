package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Fabricante;

public interface Fabricantes extends JpaRepository<Fabricante, Long> {
	
	@Query(value = "select * from fabricante f order by f.nome", nativeQuery = true)
	List<Fabricante> findAllFabricantesinOrder();

}