package fr.helpad.repository;

import fr.helpad.entity.Candidat;
import fr.helpad.service.CandidatService;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatRepository extends CrudRepository<Candidat, Long> {

}
