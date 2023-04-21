package fr.helpad.service;

import java.util.Optional;

import fr.helpad.entity.Personne;

public interface PersonneService {
    public Optional<Personne> findByUsername(String user);
	public void save(Personne user);
	
}
