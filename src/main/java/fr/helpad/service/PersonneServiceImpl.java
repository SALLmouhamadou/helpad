package fr.helpad.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.helpad.entity.Personne;
import fr.helpad.repository.AdresseRepository;
import fr.helpad.repository.PersonneRepository;

@Service
@Transactional
public class PersonneServiceImpl implements PersonneService {
    @Autowired
    PersonneRepository personneRepository;
    @Autowired
    AdresseRepository adresseRepository;

    public void sauvePersonne(Personne personne){
        personneRepository.save(personne);
    }

    @Override
    public List<Personne> findAll() {
        return (List<Personne>) personneRepository.findAll();
    }

    public Personne get(Long id) {
        return personneRepository.findById(id).get();
    }

    public void delete(Long id) {
        personneRepository.deleteById(id);
    }

    public Personne login(String email, String password){
        return personneRepository.findByEmail(email, password);
    }
    //les getters et les setters
    public PersonneRepository getPersonneRepository() {
        return personneRepository;
    }

    public void setPersonneRepository(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }


}
