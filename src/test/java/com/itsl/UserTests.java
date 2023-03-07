package com.itsl;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.itsl.exceptions.ResourceNotFoundException;
import com.itsl.models.ERole;
import com.itsl.models.Role;
import com.itsl.models.User;
import com.itsl.repository.RoleRepository;
import com.itsl.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserTests {
	
	@Autowired
    private UserRepository repo;
	
	@Autowired
    private RoleRepository roleRepo;
	
	@Autowired
	PasswordEncoder encoder;
    
    @Test
    public void testCreateUser() {
    	
    	User user = new User("Juan","de las Cuerdas", "Juanito", "juanito@gmail.com","Cuenta123");
    	
        this.repo.save(user);
        final List<User> listUser = (List<User>)this.repo.findAll();
   
        Assertions.assertThat(listUser.size()).isEqualTo(3);
    }
    
    @Test
    public void testAddRoleToNewUser() {
    	User user = new User("Juan","de las Cuerdas", "Juanito", "juanito@gmail.com", "Cuenta123");
    	
    	Role userRole = roleRepo.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found. 2"));
    	
    	user.addRole(userRole);
    	
    	User savedUser = repo.save(user);
    	
    	Assertions.assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }
    
    @Test
    public void testUpdateUser() {
    	Long id = (long) 18;
    	User userData = new User("Juan N.","de las Cuerdas", "Juanito", "juanito@gmail.com","Cuenta123");
    	
    	
    	User user = repo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("No existe el Usuario con el ID : " + id));
    	
    	user.setName(      userData.getName()     );
		user.setLastname(  userData.getLastname() );
		user.setEmail(     userData.getEmail()    );
		user.setUsername(  userData.getUsername() );
		user.setPassword(  userData.getPassword() );
    	
    	User savedUser = repo.save(user);
    	
    	Assertions.assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }
    
    
}
