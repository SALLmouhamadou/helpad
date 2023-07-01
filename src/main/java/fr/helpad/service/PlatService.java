package fr.helpad.service;

import java.util.List;

import fr.helpad.entity.Plat;

public interface PlatService extends BasicService<Plat>{
	public List<Plat> findByNom(String nom);
}
