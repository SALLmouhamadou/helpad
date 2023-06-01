package fr.helpad.service;

import java.util.NoSuchElementException;

import fr.helpad.entity.StockMedicament;
import fr.helpad.entity.WebGouvMedic;

public interface StockMedicamentServiceI extends BasicBusiness<StockMedicament> {
	public WebGouvMedic getMedicament(Long id) throws NoSuchElementException;
}
