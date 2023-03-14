package fr.helpad.service;

import java.util.List;

import fr.helpad.entity.Candidat;
import fr.helpad.entity.Candidature;

public interface CandidatService {
    public Candidat sauveCandidat(Candidat candidat);
    public List<Candidat> findAll();
    public Candidat get(Long id);
    public void delete(Long id);
}
