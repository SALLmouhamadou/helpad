package fr.helpad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Allergene;
import fr.helpad.repository.AllergeneRepository;

@Service
public class AllergeneServiceImpl implements AllergeneService {

    @Autowired
    private AllergeneRepository allergeneRepository;

    @Override
    public List<Allergene> getAllAllergenes() {
        return (List<Allergene>) allergeneRepository.findAll();
    }

    @Override
    public Allergene getAllergeneById(Long id) {
        return allergeneRepository.findById(id).get();
    }

    @Override
    public Allergene saveAllergene(Allergene allergene) {
        return allergeneRepository.save(allergene);
    }

    @Override
    public void deleteAllergene(Long id) {
        allergeneRepository.deleteById(id);
    }
}