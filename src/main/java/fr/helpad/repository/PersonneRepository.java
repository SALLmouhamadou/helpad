package fr.helpad.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.helpad.entity.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {

	public Optional<Personne> findByUsername(String username);

}
