package fr.helpad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.helpad.entity.Candidature;

@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
	
	@Query("SELECT c  FROM Candidature c WHERE c.id = :idCandidat")
	public List<Candidature> findAllCandidatureById(@Param("idCandidat")Long idCandidat);
}
