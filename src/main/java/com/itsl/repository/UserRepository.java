package com.itsl.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itsl.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	@Query(value = "SELECT \n"
			+ "	users.id, users.name, users.lastname, users.email, users.username, \n"
			+ "    IF(user_roles.role_id=1, 'Usuario', 'Administrador') as role\n"
			+ "	FROM \n"
			+ "		users, user_roles \n"
			+ "	WHERE \n"
			+ "		users.id = user_roles.user_id", nativeQuery = true)
	List<UserWithRoles> findAllUsersWithRoles();
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET password=:passwd WHERE id=:id", nativeQuery = true)
	public void updatePasswd(@Param("id") Long id, @Param("passwd") String passwd);
}
