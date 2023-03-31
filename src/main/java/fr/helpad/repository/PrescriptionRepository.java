package fr.helpad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.helpad.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	@Query(value = "SELECT c FROM Prescription c WHERE c.idPensionnaire LIKE '%' || :pensionnaire || '%'")
	public List<Prescription> chercherParPensionnaire(@Param("pensionnaire") Long idPensionnaire);
	@Query(value = "SELECT c FROM Prescription c WHERE c.idMedicament LIKE '%' || :medicament || '%'")
	public List<Prescription> findByMedicament(@Param("medicament") Long idMedicament);
	@Query(value = "SELECT SUM(c.quantiteParPrise * c.priseParJour) FROM Prescription c WHERE c.dateDebutTraitement >= CURRENT_DATE "
			+ "AND c.idMedicament = medicament")
	public Long getSumConsoMois(@Param("medicament") Long idMedicament);
}
