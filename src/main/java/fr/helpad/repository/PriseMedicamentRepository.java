package fr.helpad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.helpad.entity.PriseMedicament;

public interface PriseMedicamentRepository extends CrudRepository<PriseMedicament, Long>  {
	@Query(value = "SELECT c FROM PriseMedicament c WHERE c.idPensionnaire LIKE '%' || :pensionnaire || '%'")
	public List<PriseMedicament> chercherParPensionnaire(@Param("pensionnaire") Long idPensionnaire);
	@Query(value = "SELECT c FROM PriseMedicament c WHERE c.idMedicament LIKE '%' || :medicament || '%'")
	public List<PriseMedicament> chercherParMedicament(@Param("medicament") Long idMedicament);
}
