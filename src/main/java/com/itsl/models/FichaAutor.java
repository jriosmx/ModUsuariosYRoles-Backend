package com.itsl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fichaAutores")
public class FichaAutor {
	// attributes
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private Long id;
	
	@Column(name="idFichaAutor")
	private Long idFichaAutor;
	
	@Column(name="idAutor")
	private Long idAutor;
	
//	@Column(name="idLibro")
//	private Long idLibro;
	
//	 Foreign Key
//	@ManyToMany(mappedBy = "fichaAutor")
//	private Set<Autor> autor = new HashSet<Autor>();
	
////	 Foreign Key
//	@ManyToMany(mappedBy = "id")
//	private Set<Libro> libro = new HashSet<Libro>();
	
	// Constructors
	public FichaAutor() {
		
	}

	public FichaAutor(Long idFichaAutor, Long idAutor) {
		this.idFichaAutor = idFichaAutor;
		this.idAutor = idAutor;
	}

	// getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdFichaAutor() {
		return idFichaAutor;
	}

	public void setIdFichaAutor(Long idFichaAutor) {
		this.idFichaAutor = idFichaAutor;
	}

	public Long getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(Long idAutor) {
		this.idAutor = idAutor;
	}
	
	
}

