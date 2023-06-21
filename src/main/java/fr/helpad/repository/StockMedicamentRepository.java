package fr.helpad.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.helpad.entity.StockMedicament;

public interface StockMedicamentRepository extends CrudRepository<StockMedicament, Long> {
	public static final short s = 0;

	public List<StockMedicament> findByQuantiteGreaterThan(@Param(value = "quantite") short quantite,
			Pageable pageable);

	public long countByQuantiteGreaterThan(@Param(value = "quantite") short quantite);

	public default Map<Long, StockMedicament> findAllPositiveMap() {
		return findByQuantiteGreaterThan(s, PageRequest.of(0, 20000)).stream()
				.collect(Collectors.toMap(StockMedicament::getCodeCis, v -> v));
	}
}