package com.itsl.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itsl.exceptions.ResourceNotFoundException;
import com.itsl.models.Libro;
import com.itsl.repository.BooksListByTitleAndAuthor;
import com.itsl.repository.LibroRepository;

@RestController
@RequestMapping("/api/libro")
@CrossOrigin(origins = "http://localhost:4200")
public class LibroController {
	@Autowired
	private LibroRepository repository;			

	// Este metodo sirve para listar todos los libros
	@GetMapping("/getLibros")
	public List<Libro> listarTodosLosLibros() {
		return repository.findAll();
	}
	
	// Este metodo sirve para listar todos los libros
	@GetMapping("/getBooksListByTitleAndAuthor/{cad}")
	public List<BooksListByTitleAndAuthor> listarTodosLosLibrosPorTituloYAutor(@PathVariable String cad) {
		return repository.getBooksListByTitleAndAuthor(cad);
	}
	
	// Este metodo sirve para guardar un libro
	@PostMapping("/register")
	public Libro guardaLibro(@RequestBody Libro libro) {		
		
		return repository.save(libro);
	}
	
	// este metodo sirve para buscar un libro por `ID`
	@GetMapping("/getLibro/{id}")
	public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id){
		Libro libro = repository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("No existe el Libro: "+ id+", sorry!!!"));
		return ResponseEntity.ok(libro);
	}
	
	// este metodo sirve para buscar libros por `Valor`
	@GetMapping("/libros/{titulo}")
	public List<Libro> obtenerLibrosPorValor(@PathVariable String titulo){		
		return repository.findByTitulo(titulo);
	}
	
	//este metodo sirve para eliminar un libro
	@DeleteMapping("/libros/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarLibro(@PathVariable Long id){
		Libro libro = repository.findById(id)
						        .orElseThrow(() -> new ResourceNotFoundException("No existe el Libro con el ID : " + id));
				
		//borra los registros de `fichaAutores` con el `idFichaAutor` igual al `id`
		this.repository.deleteFichaAutores( libro.getIdFichaAutores() );								
		//borra el registro de `Lbros`
		this.repository.delete(libro);
		
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("Eliminado",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
	
	//este metodo sirve para actualizar un libro
	@PutMapping("/libros/{id}")
	public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro detallesLibro){
		Libro libro = repository.findById(id)
					        .orElseThrow(() -> new ResourceNotFoundException("No existe el Libro con el ID : " + id));
		
		libro.setAsin_isbn(          detallesLibro.getAsin_isbn()          );
		libro.setTitulo(             detallesLibro.getTitulo()             );
		libro.setFechaDeLanzamiento( detallesLibro.getFechaDeLanzamiento() );
		libro.setIdCategoria(        detallesLibro.getIdCategoria()        );
		libro.setIdEditorial(        detallesLibro.getIdEditorial()        );
		libro.setIdioma(             detallesLibro.getIdioma()             );
		libro.setPaginas(            detallesLibro.getPaginas()            );
		libro.setDescripcion(        detallesLibro.getDescripcion()        );
		libro.setPortada(            detallesLibro.getPortada()            );
		
			
		Libro libroActualizado = repository.save(libro);
		
		return ResponseEntity.ok(libroActualizado);
	}

}