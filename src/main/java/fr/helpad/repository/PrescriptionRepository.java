//package fr.helpad.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//
//import fr.helpad.entity.Prescription;
//
//public interface PrescriptionRepository extends CrudRepository<Prescription, Long> {
//	@Query(value = "SELECT c FROM Prescription c WHERE :pensionnaire = c.pensionnaire")
//	public List<Prescription> chercherParPensionnaire(@Param("pensionnaire") Long idPensionnaire);
//	@Query(value = "SELECT c FROM Prescription c WHERE :medicament = c.medicament")
//	public List<Prescription> chercherParMedicament(@Param("medicament") Long idMedicament);
//	@Query(value = "SELECT SUM(c.quantiteParPrise * c.priseParJour) FROM Prescription c WHERE c.dateFinTraitement >= CURRENT_DATE "
//			+ "AND c.medicament = :medicament")
//	public Long getSumConsoMois(@Param("medicament") Long idMedicament);
//}
