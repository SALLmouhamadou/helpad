package fr.helpad.service;

import java.util.List;

import fr.helpad.entity.Allergene;

public interface AllergeneService {
	
	List<Allergene> getAllAllergenes();

    Allergene getAllergeneById(Long id);

    Allergene saveAllergene(Allergene allergene);

    void deleteAllergene(Long id);
}
