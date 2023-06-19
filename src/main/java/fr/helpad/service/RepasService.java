package fr.helpad.service;

import java.time.LocalDate;
import java.util.List;

import fr.helpad.entity.Repas;

public interface RepasService extends BasicBusiness<Repas> {
	
	List<Repas> findByDateRepas(LocalDate dr);
}
