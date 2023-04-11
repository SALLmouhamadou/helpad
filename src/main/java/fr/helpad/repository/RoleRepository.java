package fr.helpad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.helpad.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
