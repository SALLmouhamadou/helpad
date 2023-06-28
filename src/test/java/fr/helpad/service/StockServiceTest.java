package fr.helpad.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.helpad.entity.StockMedicament;
import fr.helpad.entity.WebGouvMedic;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {
	private static final Long id = 60234100l;
	private static final String  nom = "DOLIPRANE 1000 mg, comprimé";
	private static final short quantite = 90;
	private static final short quantiteUpper = 1001;
	private static final short quantiteMinus = -1;
	
	@Autowired
	StockMedicamentServiceI stockService;
	
	@Autowired
	WebGouvMedicServiceI medicService;
	
	@Test
	public void shouldSaveStockWithSuccess() {
		// GIVEN
		StockMedicament stock = new StockMedicament();
		stock.setCodeCis(id);
		stock.setQuantite(quantite);
		// WHEN
		StockMedicament saved = stockService.sauvegarder(stock);
		//THEN
		assertNotNull(saved);
		assertNotNull(stock);
		assertEquals(stock.getQuantite(), quantite);
		assertEquals(saved.getQuantite(), stock.getQuantite());
	}
	
	@Test
	public void shouldSaveStockWithUpperError() {
		// GIVEN
		StockMedicament stock = new StockMedicament();
		stock.setCodeCis(id);
		boolean errorSet = false;
		try {
		stock.setQuantite(quantiteUpper);
		} catch (NumberFormatException e) {
			errorSet = true;
		}
		// WHEN
		StockMedicament saved = stockService.sauvegarder(stock);
		//THEN
		assertNotNull(saved);
		assertNotNull(stock);
		assertNotEquals(stock.getQuantite(), quantiteUpper);
		assertEquals(saved.getQuantite(), stock.getQuantite());
	}
	
	@Test
	public void shouldSaveStockWithLowerError() {
		// GIVEN
		StockMedicament stock = new StockMedicament();
		stock.setCodeCis(id);
		boolean errorSet = false;
		try {
		stock.setQuantite(quantiteMinus);
		} catch (NumberFormatException e) {
			errorSet = true;
		}
		// WHEN
		StockMedicament saved = stockService.sauvegarder(stock);
		//THEN
		assertNotNull(saved);
		assertNotNull(stock);
		assertNotEquals(stock.getQuantite(), quantiteMinus);
		assertEquals(saved.getQuantite(), stock.getQuantite());
	}
	
	@Test
	public void shouldGetMedicWithSuccess() {
		// GIVEN
		StockMedicament stock = new StockMedicament();
		stock.setCodeCis(id);
		stock.setQuantite(quantite);
		// WHEN
		StockMedicament saved = stockService.sauvegarder(stock);
		WebGouvMedic medic = medicService.get(id);
		//THEN
		assertNotNull(saved);
		assertNotNull(stock);
		assertNotNull(medic);
		assertEquals(stock.getQuantite(), quantite);
		assertEquals(saved.getQuantite(), stock.getQuantite());
		assertEquals(medic.getNom(), nom);
	}
}
