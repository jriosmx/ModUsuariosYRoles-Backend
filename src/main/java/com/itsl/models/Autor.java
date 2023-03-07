package com.itsl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {
	
	// atributos
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	@Column(name = "id")
	private Long id;

	@Column(name = "autor", length = 60, nullable = false)
	private String autor;
	
	// Foreign Key
//	@ManyToMany
//	@JoinColumn(name="fichaAutor_autorID", 
//				referencedColumnName = "idAutor", 				
//				foreignKey = @ForeignKey(name="fk_fichaAutor_autorId"))
//	private Set<FichaAutor> fichaAutor = new HashSet<FichaAutor>();
//	private FichaAutor fichaAutor;

	// Constructores
	public Autor() {
		
	}


	public Autor(String autor) {
		this.autor = autor;
		
	}

//	getter & setters
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}

	
}

