package fr.helpad.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.helpad.entity.Adresse;

@Repository
public interface AdresseRepository extends CrudRepository<Adresse, Long> {
}
