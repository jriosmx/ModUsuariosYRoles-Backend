package com.itsl.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import javax.persistence.Lob;

@Entity
@Table(name = "libros",
		uniqueConstraints = { 
				@UniqueConstraint(columnNames = "asin_isbn"),
				
	   })
public class Libro {
	// attributes
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;

	@Column(name = "asin_isbn", length = 14)
	private String asin_isbn;
	
	@Column(name = "titulo", length = 120)
	private String titulo;
	
	@Column(name = "fechaDeLanzamiento")
	@Temporal(TemporalType.DATE)
	private Date fechaDeLanzamiento;			
	
	@Column(name = "idCategoria")
	private Long idCategoria;
	
	@Column(name = "idEditorial")
	private Long idEditorial;
	
	@Column(name = "idFichaAutores")
	private Long idFichaAutores;
	
	@Column(name = "idioma", length = 60)
	private String idioma;
	
	@Column(name = "paginas", length = 5)
	private String paginas;
	
	@Column(name = "descripcion", length = 400)
	private String descripcion;
	
	@Lob
	@Column(name = "portada")
    private String portada;

	// Foreign Key
//	@ManyToMany
//	@JoinColumn(name = "idCategoria", insertable=false, updatable=false,
//	    		referencedColumnName = "id", 	    		
//	    		foreignKey = @ForeignKey(name="fk_categoria_id"))
//	@JsonBackReference("categoria")
//	private Set<Categoria> categoria = new HashSet<Categoria>();
		
	// Foreign Key
//	@ManyToMany
//	@JoinColumn(name = "idEditorial", insertable=false, updatable=false,
//	    		referencedColumnName = "id", 	    		
//	    		foreignKey = @ForeignKey(name="fk_editorial_id"))
//	@JsonBackReference("editorial")
//	private Set<Editorial> editorial = new HashSet<Editorial>();

	// Foreign Key
//	@ManyToMany
//	@JoinColumn(name="idFichaAutores", insertable=false, updatable=false,
//				referencedColumnName = "idFichaAutor", 				
//				foreignKey = @ForeignKey(name="fichaAutor_idFichaAutores"))
//	@JsonBackReference("fichaAutor")
//	private Set<FichaAutor> fichaAutor = new HashSet<FichaAutor>();
	
	// Constructors
	public Libro() {
		
	}

	public Libro(String asin_isbn, String titulo, Date fechaDeLanzamiento, Long idCategoria, Long idEditorial,
			Long idFichaAutores, String idioma, String paginas, String descripcion, String portada) {
		this.asin_isbn = asin_isbn;
		this.titulo = titulo;
		this.fechaDeLanzamiento = fechaDeLanzamiento;
		this.idCategoria = idCategoria;
		this.idEditorial = idEditorial;
		this.idFichaAutores = idFichaAutores;
		this.idioma = idioma;
		this.paginas = paginas;
		this.descripcion = descripcion;
		this.portada = portada;
	}

	// getters & setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAsin_isbn() {
		return asin_isbn;
	}

	public void setAsin_isbn(String asin_isbn) {
		this.asin_isbn = asin_isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaDeLanzamiento() {
		return fechaDeLanzamiento;
	}

	public void setFechaDeLanzamiento(Date fecha) {
		this.fechaDeLanzamiento = fecha;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getPaginas() {
		return paginas;
	}

	public void setPaginas(String paginas) {
		this.paginas = paginas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Long getIdEditorial() {
		return idEditorial;
	}

	public void setIdEditorial(Long idEditorial) {
		this.idEditorial = idEditorial;
	}

	public Long getIdFichaAutores() {
		return idFichaAutores;
	}

	public void setIdFichaAutores(Long idFichaAutores) {
		this.idFichaAutores = idFichaAutores;
	}
	
	
	
}