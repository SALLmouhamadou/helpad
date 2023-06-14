package fr.helpad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.helpad.entity.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {

}
