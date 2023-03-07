package com.itsl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "editoriales")
public class Editorial {
	// atributos
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private Long id;

	@Column(name = "editorial", length = 60, nullable = false)
	private String editorial;

	// Foreign Key
//	@OneToMany( mappedBy = "editorial")   
//	private List<Libro> librosList = new ArrayList<>();
	
	//	constructores
	public Editorial() {
		
	}

	public Editorial( String editorial) {			
		this.editorial = editorial;
	}

	// getters & setters
//	public List<Libro> getLibrosList() {
//		return librosList;
//	}
//
//	public void setLibrosList(List<Libro> librosList) {
//		this.librosList = librosList;
//	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	
	
	
}
