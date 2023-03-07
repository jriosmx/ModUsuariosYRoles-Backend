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
import com.itsl.models.Autor;
import com.itsl.repository.AutorRepository;

@RestController
@RequestMapping("/api/autor")
@CrossOrigin(origins = "http://localhost:4200")
public class AutorController {
	@Autowired
	private AutorRepository repository;

	// Este metodo sirve para listar todos los autores
	@GetMapping("/autores")
	public List<Autor> listarTodosLosAutores() {		
		return repository.findAll();
	}
	
	// Este metodo sirve para guardar el autor
	@PostMapping("/autores")
	public Autor guardaAutor(@RequestBody Autor autor) {
		return this.repository.save(autor);
	}
	
	// Este metodo sirve para listar todas las autores
	@GetMapping("/autoresPorNombre/{cad}")
	public List<Autor> listarTodosLosAutoresPorNombre(@PathVariable String cad) {
		return repository.findByNombre(cad);
	}
	
	// este metodo sirve para buscar los `Ids` de una lista de `autores`
	@GetMapping("/autorIdPorNombres/{nombres}")
	public List<Long> obtenerAutorIdPorNombres(@PathVariable String[] nombres){
		
			List<Long> id = repository.getAutorIDByNames(nombres);
						  
		return id;
	}		
	
	// este metodo sirve para buscar un `ID` por `nombre`
	@GetMapping("/autorIdPorNombre/{nombre}")
	public Long obtenerAutorIdPorNombre(@PathVariable String nombre){
	
		Long id = repository.getAutorIDByName(nombre);
					  
	return id;
	}	
	
	// este metodo sirve para buscar los `nombres` de los `autores` por `ID[]`
	@GetMapping("/autoresNombres/{IDs}")
	public List<String> obtenerAutorNombresPorIDs(@PathVariable Long[] IDs) {

		List<String> names = this.repository.getAuthorNamesByIDs(IDs);
		
//		System.out.println("this.repository.getAuthorNamesByIDs(IDs): "+this.repository.getAuthorNamesByIDs(IDs));

		return names;
	}
	
	// este metodo sirve para buscar un autor por `ID`
	@GetMapping("/autores/{id}")
	public ResponseEntity<Autor> obtenerAutorPorId(@PathVariable Long id){
	Autor autor = repository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("No existe el autor: "+ id+", sorry!!!"));
		return ResponseEntity.ok(autor);
	}
	
	//este metodo sirve para eliminar un autor
	@DeleteMapping("/autores/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarAutor(@PathVariable Long id){
		Autor autor = repository.findById(id)
						        .orElseThrow(() -> new ResourceNotFoundException("No existe el autor con el ID : " + id));
				
		repository.delete(autor);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("Eliminado",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
	
	//este metodo sirve para actualizar autor
	@PutMapping("/autores/{id}")
	public ResponseEntity<Autor> actualizarEmpleado(@PathVariable Long id, @RequestBody Autor detallesAutor){
		Autor autor = repository.findById(id)
					        .orElseThrow(() -> new ResourceNotFoundException("No existe el autor con el ID : " + id));
			
		autor.setAutor(detallesAutor.getAutor());
		
			
		Autor empleadoActualizado = repository.save(autor);
		return ResponseEntity.ok(empleadoActualizado);
	}
}
