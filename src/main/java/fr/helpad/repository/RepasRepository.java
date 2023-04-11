package fr.helpad.repository;

import org.springframework.data.repository.CrudRepository;

import fr.helpad.entity.Repas;

public interface RepasRepository extends CrudRepository<Repas, Long> {

}
