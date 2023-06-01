package fr.helpad.repository;

import org.springframework.data.repository.CrudRepository;

import fr.helpad.entity.StockMedicament;

public interface StockMedicamentRepository extends CrudRepository<StockMedicament, Long> {
	
}
