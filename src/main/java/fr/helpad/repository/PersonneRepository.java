package fr.helpad.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import fr.helpad.entity.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
	public Optional<Personne> findByEmail(String email);

}
