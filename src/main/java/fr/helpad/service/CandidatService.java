package fr.helpad.service;

import java.util.List;
import java.util.Optional;

import fr.helpad.entity.Candidat;

public interface CandidatService {
    public boolean sauveCandidat(Candidat candidat);
    public List<Candidat> findAllCandidats();
    public Candidat get(Long id);
    public void delete(Long id);
	Optional<Candidat> findByUsername(String user);
}
