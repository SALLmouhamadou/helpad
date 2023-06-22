//package fr.helpad.service;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import fr.helpad.entity.Pensionnaire;
//import fr.helpad.entity.Prescription;
//import fr.helpad.entity.WebGouvMedic;
//import fr.helpad.repository.PrescriptionRepository;
//
//@Service("prescrptionBusiness")
//public class PrescriptionService implements PrescriptionServiceI {
//
//	@Autowired
//	PrescriptionRepository repo;
//	
//	@Autowired
//	WebGouvMedicServiceI medic;
//
//	@Override
//	public Prescription sauvegarder(Prescription entity) {
//		return repo.save(entity);
//	}
//
//	@Override
//	public List<Prescription> listerTout() {
//		return (List<Prescription>) repo.findAll();
//	}
//
//	@Override
//	public void supprimer(Long id) throws IllegalArgumentException, NoSuchElementException {
//		repo.deleteById(id);
//	}
//
//	@Override
//	public Prescription get(Long id) throws NoSuchElementException {
//		return repo.findById(id).get();
//	}
//	
//	public List<Prescription> chercherParPensionnaire(Pensionnaire pensionnaire) {
//		return repo.chercherParPensionnaire(pensionnaire.getIdPersonne());
//	}
//
//	public List<Prescription> chercherParMedicament(WebGouvMedic medicament) {
//		return repo.chercherParMedicament(medicament.getId());
//	}
//
//	@Override
//	public Long getConsoMois(WebGouvMedic medicament) {
//		return repo.getSumConsoMois(medicament.getId());
//	}
//}
