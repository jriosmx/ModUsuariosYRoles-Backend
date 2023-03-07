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
import com.itsl.models.Editorial;
import com.itsl.repository.EditorialRepository;

@RestController
@RequestMapping("/api/editorial")
@CrossOrigin(origins = "http://localhost:4200")
public class EditorialController {
	
	@Autowired
	private EditorialRepository repository;

	// Este metodo sirve para listar todos las editoriales
	@GetMapping("/editoriales")
	public List<Editorial> listarTodosLasEditoriales() {
		return repository.findAll();
	}
		
	// Este metodo sirve para guardar la editorial
	@PostMapping("/editoriales")
	public Editorial guardaEditorial(@RequestBody Editorial editorial) {
		return this.repository.save(editorial);
	}
	
	// este metodo sirve para buscar una editorial por `ID`
		@GetMapping("/editoriales/{id}")
		public ResponseEntity<Editorial> obtenerEditorialPorId(@PathVariable Long id){
			Editorial editorial = repository.findById(id)
						          .orElseThrow(() -> new ResourceNotFoundException("No existe la editorial: "+ id+", sorry!!!"));
			return ResponseEntity.ok(editorial);
		}
	
	// Este metodo sirve para listar todos las categorias
	@GetMapping("/editorialesPorNombre/{nombre}")
	public List<Editorial> listarTodasLasEditorialesPorNombre(@PathVariable String nombre) {
		return repository.findByNombre(nombre);
	}
	
	// Este metodo sirve para obtener el id de la editorial dado el nombre
	@GetMapping("/getEditorialIDByName/{name}")
	public Long getEditorialIDByName(@PathVariable String name) {

		Long id = repository.getEditorialIDByName(name);

		return id;
	}
		
	//este metodo sirve para eliminar una editorial
	@DeleteMapping("/editoriales/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarEditorial(@PathVariable Long id){
		Editorial editorial = repository.findById(id)
							  .orElseThrow(() -> new ResourceNotFoundException("No existe la editorial con el ID : " + id));
					
		repository.delete(editorial);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("Eliminada",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
		
	//este metodo sirve para actualizar editorial
	@PutMapping("/editoriales/{id}")
	public ResponseEntity<Editorial> actualizarEditorial(@PathVariable Long id, @RequestBody Editorial detallesEditorial){
		Editorial editorial = repository.findById(id)
						       .orElseThrow(() -> new ResourceNotFoundException("No existe la editorial con el ID : " + id));
				
		editorial.setEditorial(detallesEditorial.getEditorial());
			
				
		Editorial editorialActualizada = repository.save(editorial);
		return ResponseEntity.ok(editorialActualizada);
	}
	
}

