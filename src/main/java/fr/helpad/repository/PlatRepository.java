package fr.helpad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.helpad.entity.Allergene;
import fr.helpad.entity.Plat;

@Repository
public interface PlatRepository extends JpaRepository<Plat, Long> {

    List<Plat> findByNomContaining(String nom);

    List<Plat> findByAllergenes(Allergene allergene);

}