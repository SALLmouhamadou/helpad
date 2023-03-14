package fr.helpad.repository;

import org.springframework.data.repository.CrudRepository;

import fr.helpad.entity.Ordonnance;

public interface OrdonnanceRepository extends CrudRepository<Ordonnance, Long> {

}
