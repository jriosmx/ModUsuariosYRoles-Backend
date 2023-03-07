package com.itsl.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class Categoria {
	// atributos
	
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		@Column(name = "categoria", length = 60, nullable = false)
		private String categoria;

		// Foreign Key
//		@OneToMany( mappedBy = "categoria")   
//		private List<Libro> librosList = new ArrayList<>();
		
		// Constructores
		public Categoria() {
			
		}


		public Categoria(Long id, String categoria) {
			
			this.id = id;
			this.categoria = categoria;
		}

		// getter & setters
//		public List<Libro> getLibrosList() {
//			return librosList;
//		}
//
//		public void setLibrosList(List<Libro> librosList) {
//			this.librosList = librosList;
//		}
		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getCategoria() {
			return categoria;
		}


		public void setCategoria(String categoria) {
			this.categoria = categoria;
		}
		
		
		
		

}
