package fr.helpad.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.helpad.entity.Plat;
import fr.helpad.entity.Repas;

public interface RepasRepository extends CrudRepository<Repas, Long> {

	List<Repas> findByDateRepas(LocalDate dr);

//	@Query("SELECT r FROM Repas r WHERE r.dateRepas >= :dateRepas")
//    List<Repas> findByDate(@Param("dateRepas") LocalDate dateRepas);

//    List<Plat> findByNomContaining(String nom);

//    List<Plat> findByAllergene(Allergene allergene);
}
