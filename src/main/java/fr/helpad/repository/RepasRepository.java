package fr.helpad.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.helpad.entity.Allergene;
import fr.helpad.entity.Plat;
import fr.helpad.entity.Repas;

public interface RepasRepository extends JpaRepository<Repas, Long> {
//	List<Repas> findByDate(LocalDate date);

//	@Query("SELECT r FROM Repas r WHERE r.dateRepas >= :dateRepas")
//    List<Repas> findByDate(@Param("dateRepas") LocalDate dateRepas);

    List<Plat> findByNomContaining(String nom);

    List<Plat> findByAllergenes(Allergene allergene);
}
