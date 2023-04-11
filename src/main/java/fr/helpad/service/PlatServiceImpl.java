package fr.helpad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Allergene;
import fr.helpad.entity.Plat;
import fr.helpad.entity.Repas;
import fr.helpad.repository.PlatRepository;

@Service
public class PlatServiceImpl implements PlatService {

    @Autowired
    private PlatRepository platRepository;

    @Override
    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    @Override
    public Plat getPlatById(Long id) {
        return platRepository.findById(id).get();
    }

    @Override
    public Plat savePlat(Plat plat) {
        return platRepository.save(plat);
    }

    @Override
    public void deletePlat(Long id) {
        platRepository.deleteById(id);
    }

    @Override
    public List<Plat> searchPlats(String query) {
        return platRepository.findByNomContaining(query);
    }

    @Override
    public List<Plat> getPlatsByAllergene(Allergene allergene) {
        return platRepository.findByAllergenes(allergene);
    }
}