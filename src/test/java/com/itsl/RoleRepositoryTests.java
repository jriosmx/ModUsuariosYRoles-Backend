package com.itsl;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;


import com.itsl.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
	
	@Autowired
    private RoleRepository repo;
    
    @Test
    public void testCreateRoles() {
//        ERole user = new ERole("ROLE_USER");
//        ERole admin = new ERole("ROLE_ADMIN");
////        final Role customer = new Role("Customer");
//        this.repo.saveAll((Iterable)List.of(user, admin));
//        final List<Role> listRoles = (List<Role>)this.repo.findAll();
//        
//        Assertions.assertThat(listRoles.size()).isEqualTo(2);
    }
	
}
