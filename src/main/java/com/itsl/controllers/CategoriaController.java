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
import com.itsl.models.Categoria;
import com.itsl.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
	@Autowired
	private CategoriaRepository repository;

	// Este metodo sirve para listar todos las categorias
	@GetMapping("/categorias")
	public List<Categoria> listarTodosLasCategorias() {
		return repository.findAll();
	}
	
	// Este metodo sirve para listar todas las categorias
	@GetMapping("/categoriaPorNombre/{nombre}")
	public List<Categoria> listarTodasLasCategoriasPorNombre(@PathVariable String nombre) {
		return repository.findByNombre(nombre);
	}
	
	// Este metodo sirve para obtener el id de la cateforia dado el nombre
	@GetMapping("/getCategoriaIDByName/{name}")
	public Long getCategoriaIDByName(@PathVariable String name) {
		
		Long id = repository.getCategoriaIDByName(name);
				
		return id;
	}
	
	// Este metodo sirve para guardar la categoria
	@PostMapping("/categorias")
	public Categoria guardaCategoria(@RequestBody Categoria categoria) {
		return this.repository.save(categoria);
	}
	
	// este metodo sirve para buscar un categoria por `ID`
	@GetMapping("/categorias/{id}")
	public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id){
		Categoria categoria = repository.findById(id)
				  .orElseThrow(() -> new ResourceNotFoundException("No existe la categoria: "+ id+", sorry!!!"));
		return ResponseEntity.ok(categoria);
	}
	
	//este metodo sirve para eliminar una categoria
	@DeleteMapping("/categorias/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarCategoria(@PathVariable Long id){
		Categoria categoria = repository.findById(id)
						        .orElseThrow(() -> new ResourceNotFoundException("No existe la categoria con el ID : " + id));
				
		repository.delete(categoria);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("Eliminada",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
	
	//este metodo sirve para actualizar una categoria
	@PutMapping("/categorias/{id}")
	public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria detallesCategoria){
		Categoria categoria = repository.findById(id)
					        .orElseThrow(() -> new ResourceNotFoundException("No existe al categoria con el ID : " + id));
			
		categoria.setCategoria(detallesCategoria.getCategoria());
		
			
		Categoria categoriaActualizado = repository.save(categoria);
		return ResponseEntity.ok(categoriaActualizado);
	}
}

