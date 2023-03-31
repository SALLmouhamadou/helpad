package fr.helpad.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import fr.helpad.entity.PriseMedicament;

public interface PriseMedicamentService extends BasicBusiness<PriseMedicament> {
	public List<PriseMedicament> chercherParPensionnaire(@Param("pensionnaire") Long Pensionnaire);
	public List<PriseMedicament> chercherParMedicament(@Param("medicament") Long idMedicament);
}
