package fr.helpad.repository;

import org.springframework.data.repository.CrudRepository;

import fr.helpad.entity.Facture;

public interface FactureRepository extends CrudRepository<Facture, Long> {

}
