package com.itsl.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itsl.models.Libro;


@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{
	
	@Query(value = "SELECT * FROM libros WHERE titulo LIKE CONCAT('%',:titulo,'%')", nativeQuery = true)
	List<Libro> findByTitulo(@Param("titulo") String titulo);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM fichaAutores WHERE idFichaAutor = :id", nativeQuery = true)
	public void deleteFichaAutores(@Param("id") Long id);
	
	@Query(value = "SELECT \n"
			+ "	libros.id, libros.titulo, autores.autor \n"
			+ "FROM \n"
			+ "	libros,fichaAutores,autores\n"
			+ "WHERE \n"
			+ "	libros.idFichaAutores = fichaAutores.idFichaAutor\n"
			+ "AND\n"
			+ "	(libros.titulo LIKE CONCAT('%',:cad,'%')\n"
			+ "OR\n"
			+ "	autores.autor LIKE CONCAT('%',:cad,'%'))", nativeQuery = true)
	List<BooksListByTitleAndAuthor> getBooksListByTitleAndAuthor(@Param("cad") String cad);
	
}
