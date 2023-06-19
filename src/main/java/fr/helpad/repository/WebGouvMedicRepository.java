package fr.helpad.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.helpad.entity.WebGouvMedic;

public interface WebGouvMedicRepository extends JpaRepository<WebGouvMedic, Long> {
	public List<WebGouvMedic> findByNomContainingIgnoreCaseOrderByNomDesc(@Param("nom") String nom, Pageable pageable);
	public List<WebGouvMedic> findByNom(@Param("nom") String nom, Pageable pageable);
	public long countByNomContainingIgnoreCase(@Param("nom") String nom);
	
	public List<WebGouvMedic> findAll();

    public default Map<Long, WebGouvMedic> findAllMap() {
        return findAll().stream().collect(Collectors.toMap(WebGouvMedic::getId, v -> v));
    }
}
