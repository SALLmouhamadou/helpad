package fr.helpad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import fr.helpad.entity.Etage;

public interface EtageRepository extends JpaRepository<Etage, Long> {

}
