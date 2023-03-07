package com.itsl.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itsl.models.FichaAutor;

@Repository
public interface FichaAutorRepository extends JpaRepository<FichaAutor, Long>{
	
	@Modifying
	@Query(value = "INSERT INTO FichaAutores(idFichaAutor,idAutor) VALUES(:idFichaAutor,:idAutor)", nativeQuery = true)
	@Transactional
	void setAuthorID(@Param("idFichaAutor") Long idFichaAutor, @Param("idAutor") Long idAutor);
	
	@Query(value = "SELECT MAX(id) FROM FichaAutores", nativeQuery = true)
	Long maxId();
	
	@Query(value = "SELECT idAutor FROM FichaAutores WHERE idFichaAutor = :idFichaAutor", nativeQuery = true)
	Long[] getAuthorsIDs(@Param("idFichaAutor") Long idFichaAutor);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM FichaAutores WHERE idFichaAutor = :id", nativeQuery = true)
	public void deleteRecordsFromFichaAutores(@Param("id") Long id);

}

