package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import fr.helpad.entity.Medicament;

public interface MedicamentServiceI extends BasicBusiness<Medicament> {
	public List<Medicament> getByNom(String nom) throws NoSuchElementException;
}
