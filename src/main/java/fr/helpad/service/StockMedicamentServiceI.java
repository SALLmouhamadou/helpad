package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Pageable;

import fr.helpad.entity.StockMedicament;
import fr.helpad.entity.WebGouvMedic;

public interface StockMedicamentServiceI extends BasicBusiness<StockMedicament> {
	public WebGouvMedic getMedicament(Long id) throws NoSuchElementException;
	public List<StockMedicament> findByQuantiteGreaterThan(short quantite, Pageable pageable);
}
