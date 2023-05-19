package fr.helpad.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.helpad.entity.WebGouvMedic;

public interface WebGouvMedicRepository extends CrudRepository<WebGouvMedic, Long> {
	public List<WebGouvMedic> findByNomOrderByNomDesc(@Param("nom") String nom, Pageable pageable);
}
