package fr.helpad.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.helpad.entity.Allergene;

public interface AllergeneRepository extends CrudRepository<Allergene, Long> {
	public List<Allergene> findByNomAllergene(@Param(value="nomAllergene") String nom);
}
