package fr.helpad.service;

import java.util.List;

import fr.helpad.entity.Adresse;
import fr.helpad.entity.Personne;

public interface PersonneService {
    public void sauvePersonne(Personne personne );
    public List<Personne> findAll();
    public Personne get(Long id);
    public void delete(Long id);
    public Personne login(String email, String password);
}
