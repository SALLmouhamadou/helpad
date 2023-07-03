package fr.helpad.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.helpad.entity.Plat;

public interface PlatRepository extends CrudRepository<Plat, Long> {
	public List<Plat> findByNom(@Param(value = "nom") String nom);
}
