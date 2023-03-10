package fr.helpad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.helpad.entity.Prescription;

public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
	@Query(value = "SELECT c FROM Prescription c WHERE c.idPensionnaire LIKE '%' || :pensionnaire || '%'")
	public List<Prescription> chercherParPensionnaire(@Param("pensionnaire") Long idPensionnaire);
	@Query(value = "SELECT c FROM Prescription c WHERE c.idMedicament LIKE '%' || :medicament || '%'")
	public List<Prescription> chercherParMedicament(@Param("medicament") Long idMedicament);
}
