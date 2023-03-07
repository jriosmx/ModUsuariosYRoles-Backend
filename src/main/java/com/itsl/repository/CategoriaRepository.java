package com.itsl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itsl.models.Categoria;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	@Query(value = "SELECT * FROM categorias WHERE categoria LIKE CONCAT('%',:categoria,'%')", 
		   nativeQuery = true)
	List<Categoria> findByNombre(@Param("categoria") String categoria);
	
	@Query(value = "SELECT id FROM categorias WHERE categoria LIKE :name", 
			   nativeQuery = true)
	Long getCategoriaIDByName(@Param("name") String name);
	
}

