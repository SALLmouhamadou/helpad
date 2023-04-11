package fr.helpad.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.helpad.entity.Repas;
import fr.helpad.repository.RepasRepository;

@Service
public class RepasServiceImpl implements RepasService {

	
//	@Override
//	public void sauveRepas(Repas repas) {
//		repasRepo.save(repas);
//	}
//
//	@Override
//	public List<Repas> findAll() {
//		return (List<Repas>) repasRepo.findAll();
//	}
//
//	public List<Repas> findByDate(LocalDate date) {
//		return (List<Repas>) repasRepo.findByDate(date);
//	}
//
//	@Override
//	public void delete(Long id) {
//		repasRepo.deleteById(id);
//		
//	}
	
	@Autowired
    private RepasRepository repasRepository;

    @Override
    public List<Repas> getAllRepas() {
        return repasRepository.findAll();
    }

    @Override
    public Repas getRepasById(Long id) {
        return repasRepository.findById(id).orElse(null);
    }

    @Override
    public Repas saveRepas(Repas repas) {
        return repasRepository.save(repas);
    }

    @Override
    public void deleteRepas(Long id) {
        repasRepository.deleteById(id);
    }



}
