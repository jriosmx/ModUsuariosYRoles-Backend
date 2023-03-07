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
import com.itsl.models.FichaAutor;
import com.itsl.repository.FichaAutorRepository;

@RestController
@RequestMapping("/api/fichaautor/")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FichaAutorController {
	@Autowired
	private FichaAutorRepository repository;

	// Este metodo sirve para listar todas las fichaAutores
	@GetMapping("/fichaAutores")
	public List<FichaAutor> listarTodosLasFichaAutores() {
		return repository.findAll();
	}
	
	// Este metodo sirve para obtener todos los `IDs` de los `Autores` por `IdFichaAutor`
	@GetMapping("/idAutores/{IdFichaAutor}")
	public Long[] obtenerIDsAutores(@PathVariable Long IdFichaAutor) {			
		return this.repository.getAuthorsIDs(IdFichaAutor);
	}
	
	
	// Este metodo sirve para guardar la `fichaAutores` en base a varios `IDs`
	@GetMapping("/registraFichaAutores/{IDs}")
	public Long guardaFichaAutores(@PathVariable Long[] IDs) {
		
		// Obtengo el `maximo` `id` de la tabla `FichaAutor` 
		Long id = this.repository.maxId();
		if( id == null ) {
			id = (long) 1;
		}else {
			id = id + 1;			
		}
		
//		System.out.println("id: "+id);
		
		// guardo la fichaAutores
//		List<FichaAutor> fichaAutorList = new ArrayList<>();
//		FichaAutor fichaAutor;
		for(int i=0; i<IDs.length; i++) {
			this.repository.setAuthorID(id, IDs[i]);
//			fichaAutor = new FichaAutor();
//			fichaAutor.setId(id);
//			fichaAutor.setIdAutor(IDs[i]);			
//	
//			fichaAutorList.add(fichaAutor);
//			System.out.println("IDs["+i+"]: "+IDs[i]);			
		}
		
//		this.repository.saveAll(fichaAutorList);
		
		return id;
	}
	
	// Este metodo sirve para guardar la fichaAutores
	@PostMapping("/fichaAutores")
	public FichaAutor guardaFichaAutor(@RequestBody FichaAutor fichaAutor) {
		return this.repository.save(fichaAutor);
	}
		
	// este metodo sirve para buscar una fichaAutor por `ID`
	@GetMapping("/fichaAutores/{id}")
	public ResponseEntity<FichaAutor> obtenerFichaAutorPorId(@PathVariable Long id){
		FichaAutor fichaAutor = repository.findById(id)
					          .orElseThrow(() -> new ResourceNotFoundException("No existe la FichaAutor: "+ id+", sorry!!!"));
		return ResponseEntity.ok(fichaAutor);
	}
	
	//este metodo sirve para eliminar una fichaAutor
	@DeleteMapping("/fichaAutores/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarFichaAutor(@PathVariable Long id){
		FichaAutor fichaAutor = repository.findById(id)
							  .orElseThrow(() -> new ResourceNotFoundException("No existe la FichaAutor con el ID : " + id));
					
		repository.delete(fichaAutor);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("Eliminada",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}
	
	// Este metodo sirve para guardar la `fichaAutores` en base al `id_ficha_autor` y varios `id_autor`
	public void insertIntoFichaAutores(Long id ,Long[] IDs) {
		for (int i = 0; i < IDs.length; i++) {
			this.repository.setAuthorID(id, IDs[i]);
		
		}		
	}
		
	//este metodo sirve para actualizar una fichaAutor
	@PutMapping("/fichaAutores/{idFichaAutores}")
	public ResponseEntity<Map<String,Boolean>> actualizarFichaAutor(@PathVariable Long idFichaAutores, @RequestBody Long[] authorsIDs){
		// Obtengo los `id_autor` de la tabla `fichaAutores`
//		Long id_authors[] = this.repository.getAuthorsIDs(idFichaAutores);		
//		for(int i=0; i<id_authors.length; i++) {
//			System.out.println("id_author["+i+"]: "+id_authors[i]);
//		}
		
		// Elimina los registros que son iguales con el id_ficha_autores en la tabla `FichaAutores`
		this.repository.deleteRecordsFromFichaAutores(idFichaAutores);
		//guarda varios registros en base a los IDs de los autores en la table `FichaAutores`
		this.insertIntoFichaAutores(idFichaAutores, authorsIDs);		
		
		Map<String, Boolean> respuesta = new HashMap<>();		
		respuesta.put("Eliminada",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
	}

}
