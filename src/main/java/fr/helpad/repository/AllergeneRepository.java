package fr.helpad.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.helpad.entity.Allergene;

public interface AllergeneRepository extends CrudRepository<Allergene, Long> {

	List<Allergene> findByNomContaining(String nom);

}
