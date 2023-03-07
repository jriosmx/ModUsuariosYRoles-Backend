package com.itsl.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.itsl.models.ERole;
import com.itsl.models.Role;
import com.itsl.models.User;
import com.itsl.payload.request.SignupRequest;
import com.itsl.payload.response.MessageResponse;
import com.itsl.repository.RoleRepository;
import com.itsl.repository.UserRepository;
import com.itsl.repository.UserWithRoles;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	RoleRepository roleRepository;
	
	
	@Autowired
	PasswordEncoder encoder;
	
	// Este metodo sirve para listar todos los `usuarios` con sus `Roles`
	@GetMapping("/allUsersWithRoles")
	public List<UserWithRoles> listarTodosLosUsuariosConRoles() {
		return repository.findAllUsersWithRoles();
	}
	
	// Este metodo sirve para listar todos los `usuarios`
	@GetMapping("/all")
	public List<User> listarTodosLosUsuarios() {
		return repository.findAll();
	}

	// Este metodo sirve para guardar un `usuario`
	@PostMapping("/register")
	public User guardaUsuario(@RequestBody User user) {

		return repository.save(user);
	}

	// este metodo sirve para buscar un `usuario` por `ID`
	@GetMapping("/getUserByID/{id}")
		public ResponseEntity<User> obtenerUserPorId(@PathVariable Long id){
			User user = repository.findById(id)
					  .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario: "+ id+", sorry!!!"));
			
			
			return ResponseEntity.ok(user);
		}
		
		// este metodo sirve para buscar libros por `Valor`
//		@GetMapping("/libros/{titulo}")
//		public List<Libro> obtenerLibrosPorValor(@PathVariable String titulo){		
//			return repository.findByTitulo(titulo);
//		}
		
		//este metodo sirve para eliminar un `usuario`
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Map<String,Boolean>> eliminarUsuario(@PathVariable Long id){
			User user = repository.findById(id)
							        .orElseThrow(() -> new ResourceNotFoundException("No existe el `usuario` con el ID : " + id));
					
			this.repository.delete(user);
			
			Map<String, Boolean> respuesta = new HashMap<>();
			respuesta.put("Eliminado",Boolean.TRUE);
			return ResponseEntity.ok(respuesta);
		}
		
		//este metodo sirve para `actualizar` un `usuario`
		@PutMapping("/update/{id}")
		public ResponseEntity<?> actualizarUsuario(@PathVariable Long id, @RequestBody SignupRequest signUpRequest){
			User user = repository.findById(id)
						        .orElseThrow(() -> new ResourceNotFoundException("No existe el Usuario con el ID : " + id));
			
			user.setName(      signUpRequest.getName()     );
			user.setLastname(  signUpRequest.getLastname() );
			user.setEmail(     signUpRequest.getEmail()    );
			user.setUsername(  signUpRequest.getUsername() );
			
			
			System.out.println("<----- detallesUsuario.getRoles(): "+signUpRequest.getRole() );
			
			Set<String> strRoles = signUpRequest.getRole();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found. 1"));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found. 2"));
						roles.add(adminRole);

						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found. 3"));
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found. 4"));
						roles.add(userRole);
					}
				});
			}

			user.setRoles(roles);
			repository.save(user);
			
			return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
		}
		
		//este metodo sirve para `actualizar` un `usuario`
		@PutMapping("/updatePasswd/{id}")
		public ResponseEntity<?> actualizaPassword(@PathVariable Long id, @RequestBody String passwd) {
			User user = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("No existe el Usuario con el ID : " + id));

//			System.out.println("passwd: "+passwd );
			passwd = encoder.encode(passwd);
			repository.updatePasswd(id, passwd);

			return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
		}

}
