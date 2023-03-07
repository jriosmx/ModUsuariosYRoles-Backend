package com.itsl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itsl.models.Editorial;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long>{

	@Query(value = "SELECT id FROM editoriales WHERE editorial LIKE :name", 
			   nativeQuery = true)
	Long getEditorialIDByName(@Param("name") String name);
	
	@Query(value = "SELECT * FROM editoriales WHERE editorial LIKE CONCAT('%',:editorial,'%')", 
			   nativeQuery = true)
	List<Editorial> findByNombre(@Param("editorial") String editorial);
	
}
