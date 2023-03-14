package fr.helpad.repository;

import org.springframework.data.repository.CrudRepository;

import fr.helpad.entity.Candidature;

public interface CandidatureRepository extends CrudRepository<Candidature, Long> {

}
