package fr.helpad.service;

import java.util.List;

import fr.helpad.entity.Allergene;
import fr.helpad.entity.Plat;

public interface PlatService {
	
	List<Plat> getAllPlats();

    Plat getPlatById(Long id);

    Plat savePlat(Plat plat);

    void deletePlat(Long id);

	List<Plat> getPlatsByAllergene(Allergene allergene);

	List<Plat> searchPlats(String query);

}
