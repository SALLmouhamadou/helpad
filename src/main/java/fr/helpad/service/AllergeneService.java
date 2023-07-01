package fr.helpad.service;

import java.util.List;
import java.util.NoSuchElementException;

import fr.helpad.entity.Allergene;

public interface AllergeneService extends BasicService<Allergene>{
	public List<Allergene> findByNom(String nom) throws NoSuchElementException;
}
