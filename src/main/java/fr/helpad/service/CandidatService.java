package fr.helpad.service;

import java.util.List;
import java.util.Optional;

import fr.helpad.entity.Candidat;

public interface CandidatService {
    public Candidat sauveCandidat(Candidat candidat);
    public List<Candidat> findAllCandidats();
    public Candidat get(Long id);
    public void delete(Long id);
	Candidat findByUsername(String user);
}
