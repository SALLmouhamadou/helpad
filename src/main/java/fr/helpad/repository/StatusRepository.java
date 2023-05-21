package fr.helpad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.helpad.entity.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{
	Status findByLibelle(String libelle);
}
