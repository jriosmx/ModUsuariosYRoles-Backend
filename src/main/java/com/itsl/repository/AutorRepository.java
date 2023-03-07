package com.itsl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itsl.models.Autor;


@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
	
		@Query(value = "SELECT * FROM autores WHERE autor LIKE CONCAT('%',:cad,'%')", 
			   nativeQuery = true)
		List<Autor> findByNombre(@Param("cad") String cad);
		
		@Query(value = "SELECT id FROM autores WHERE autor LIKE :nombre", 
			   nativeQuery = true)
		Long getAutorIDByName(@Param("nombre") String nombre);
		
		@Query(value = "SELECT id FROM autores WHERE autor IN :nombres",
			   nativeQuery = true)
		List<Long> getAutorIDByNames(@Param("nombres") String[] nombres);
		
		@Query(value = "SELECT autor FROM autores WHERE id IN :IDs", nativeQuery = true)
		List<String> getAuthorNamesByIDs(@Param("IDs") Long[] IDs);
}
