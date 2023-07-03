package fr.helpad.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import fr.helpad.entity.Personne;

public interface PersonneService {
    public Optional<Personne> findByUsername(String user);
	public void save(Personne user) throws SQLIntegrityConstraintViolationException, ConstraintViolationException;
	
}
