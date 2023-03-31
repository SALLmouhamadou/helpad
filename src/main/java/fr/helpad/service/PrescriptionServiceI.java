package fr.helpad.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import fr.helpad.entity.Medicament;
import fr.helpad.entity.Pensionnaire;
import fr.helpad.entity.Prescription;

public interface PrescriptionServiceI extends BasicBusiness<Prescription> {
	public List<Prescription> chercherParPensionnaire(@Param("pensionnaire") Pensionnaire pensionnaire);
	public List<Prescription> chercherParMedicament(@Param("medicament") Medicament medicament);
}
