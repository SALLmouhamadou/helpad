package fr.helpad.service;

import java.util.List;

import fr.helpad.entity.Repas;

public interface RepasService {
	
//	public void sauveRepas(Repas repas );
//    public List<Repas> findAll();
//    public void delete(Long id);
//	List<Repas> findByDate(LocalDate date);
//	
	List<Repas> getAllRepas();

    Repas getRepasById(Long id);

    Repas saveRepas(Repas repas);

    void deleteRepas(Long id);
}
