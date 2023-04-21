package fr.helpad.repository;

<<<<<<< HEAD
import java.util.List;
=======
import org.springframework.data.repository.CrudRepository;
>>>>>>> refs/remotes/origin/main

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

import fr.helpad.entity.Plat;
=======
>>>>>>> refs/remotes/origin/main
import fr.helpad.entity.Repas;

public interface RepasRepository extends CrudRepository<Repas, Long> {

<<<<<<< HEAD
//	@Query("SELECT r FROM Repas r WHERE r.dateRepas >= :dateRepas")
//    List<Repas> findByDate(@Param("dateRepas") LocalDate dateRepas);

    List<Plat> findByNomContaining(String nom);

//    List<Plat> findByAllergene(Allergene allergene);
=======
>>>>>>> refs/remotes/origin/main
}
