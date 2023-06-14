package fr.helpad.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.helpad.entity.StockMedicament;

public interface StockMedicamentRepository extends CrudRepository<StockMedicament, Long> {
	public List<StockMedicament> findByQuantiteGreaterThan(@Param(value = "quantite") short quantite, Pageable pageable);
	public long countByQuantiteGreaterThan(@Param(value = "quantite") short quantite);
}
