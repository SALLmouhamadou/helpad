package fr.helpad.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.helpad.entity.WebGouvMAJDate;
import fr.helpad.entity.WebGouvMedic;
import fr.helpad.entity.WebGouvSecurite;
import fr.helpad.repository.WebGouvMedicRepository;
import fr.helpad.repository.WebGouvSecuriteRepository;

@Transactional
@Service
public class WebGouvMedicService implements WebGouvMedicServiceI {

	@Autowired
	WebGouvMedicRepository repo;
	@Autowired
	WebGouvSecuriteRepository repoSecu;
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public WebGouvMedic sauvegarder(WebGouvMedic entity) throws NullPointerException {
		return repo.save(entity);
	}

	public List<WebGouvMedic> saveAll(List<WebGouvMedic> entities)
			throws IllegalArgumentException, OptimisticLockingFailureException {
		return (List<WebGouvMedic>) repo.saveAll(entities);
	}

	@Override
	public List<WebGouvMedic> listerTout() {
		return (List<WebGouvMedic>) repo.findAll();
	}

	@Override
	public WebGouvMedic get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	@Override
	public List<WebGouvMedic> findByNameLimited(String nom, Pageable pageable) {
		return repo.findByNomContainingIgnoreCaseOrderByNomDesc(nom, pageable);
	}

	@Override
	public Page<WebGouvMedic> findLimited(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public long count() {
		return repo.count();
	}

	private void traiterInformation(File path) throws IOException, NullPointerException, NumberFormatException {
		System.out.println(LocalDateTime.now().toString() + " Début traitement informations.");
		FileInputStream input = new FileInputStream(path);
		// Lire le fichier avec l'encodage ANSI (Cp1252)
		Scanner sc = new Scanner(input, "Cp1252");
		// On lit le fichier ligne par ligne (RAM safe, on ne connait pas la
		// taille que peut atteindre le fichier téléchargé en ligne)
		Map<Long, WebGouvSecurite> secus = repoSecu.findAllMap();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line == "" || line == "\n")
				continue;
			String[] speMatcher = line.split("\\t", -1);
			boolean matchFound = speMatcher.length == 4;
			if (!matchFound) {
				System.out.println("[Information] Erreur de match " + speMatcher.length + " : " + line);
				continue;
			} else {
				long id = Long.parseLong(speMatcher[0].strip());
				LocalDate dateDebutInformation = LocalDate.MIN;
				try {
					dateDebutInformation = LocalDate.parse(speMatcher[1].strip(),
							DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				} catch (DateTimeParseException ex) {
					System.out.println("Date incorrecte : " + speMatcher[1] + " à dateDebutInformation");
				}
				LocalDate dateFinInformation = LocalDate.MIN;
				try {
					dateFinInformation = LocalDate.parse(speMatcher[2].strip(),
							DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				} catch (DateTimeParseException ex) {
					System.out.println("Date incorrecte : " + speMatcher[2] + " à dateFinInformation");
				}
				String infoLienInformationSecu = speMatcher[3].strip();

				try {
					WebGouvSecurite secu;
					if (secus.get(id) != null)
						secu = secus.get(id);
					else
						secu = new WebGouvSecurite();
					secu.setDebutInfoSecurite(dateDebutInformation);
					secu.setFinInfoSecurite(dateFinInformation);
					secu.setInformationSecurite(infoLienInformationSecu);
					secu.setCodeCis(id);
					secus.put(id, secu);
				} catch (NoSuchElementException ex) {
					System.out.println("[WebGouvSecurite] Le médicament avec l'ID : " + id + " est introuvable.");
					continue;
				}
			}
		}
		System.out.println(LocalDateTime.now().toString() + " [Securite] Enregistrement de " + secus.size()
				+ " fiches de sécurité de médicaments dans la BDD.");
		repoSecu.saveAll(secus.values());
		sc.close();
		input.close();
		System.out.println(LocalDateTime.now().toString() + " [Sécurité] Fin traitement informations.");
	}

	private void traiterMedicaments(File medicPath, File generiquePath, File conditionPath, File compositionPath,
			File presentationPath) throws IOException {
		FileInputStream medicInput = new FileInputStream(medicPath);
		Scanner sc = new Scanner(medicInput, "Cp1252");
		Map<Long, WebGouvMedic> medicaments = repo.findAllMap();

		// Traitement des spécialités
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line == "" || line == "\n")
				continue;
			// On traite la ligne
			String[] speMatcher = line.split("\\t", -1);
			if (speMatcher.length != 12) {
				System.out.println("[Spécialité] Erreur de match " + speMatcher.length + " : " + line);
			} else {
				Long id;
				try {
					id = Long.parseLong(speMatcher[0].strip());
				} catch (NumberFormatException ex) {
					System.out.println("[Specialite] L'ID : " + speMatcher[0] + " n'est pas parsable.");
					continue;
				}
				String nom = speMatcher[1].strip();
				String forme = speMatcher[2].strip();
				String voieAdministration = speMatcher[3].strip();
				String statutAdministratif = speMatcher[4].strip();
				String procedureAutorisation = speMatcher[5].strip();
				boolean etatCommercialisation = (speMatcher[6].strip() == "Commercialisée" ? true : false);
				LocalDate dateAMM = LocalDate.MIN;
				try {
					dateAMM = LocalDate.parse(speMatcher[7].strip(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				} catch (DateTimeParseException ex) {
					System.out.println("Date incorrecte : " + speMatcher[7]);
				}
				String statutBDM = speMatcher[8].strip();
				String numeroAutorisationEurope = speMatcher[9].strip();
				String titulaire = speMatcher[10].strip();
				boolean surveillanceRenforcee = (speMatcher[11].strip() == "Non" ? false : true);

				WebGouvMedic medoc;
				if (medicaments.get(id) != null) {
					medoc = medicaments.get(id);
					medoc.setNom(nom);
					medoc.setFormePharmaceutique(forme);
					medoc.setVoieAdministration(voieAdministration);
					medoc.setStatutBdm(statutBDM);
					medoc.setProcedureAdministrative(statutAdministratif);
					medoc.setProcedureAdministrative(procedureAutorisation);
					medoc.setCommercialise(etatCommercialisation);
					medoc.setDateCommercialisation(dateAMM);
					medoc.setNumeroAutorisationEuropeenne(numeroAutorisationEurope);
					medoc.setTitulaire(titulaire);
					medoc.setSurveillanceRenforcee(surveillanceRenforcee);
				} else {
					medoc = new WebGouvMedic(id, nom, forme, voieAdministration, statutAdministratif,
							procedureAutorisation, etatCommercialisation, dateAMM, statutBDM, numeroAutorisationEurope,
							titulaire, surveillanceRenforcee);
				}
				// medoc = sauvegarder(medoc);
				medicaments.put(id, medoc);
			}
		}
		sc.close();
		medicInput.close();

		// Traitement des génériques

		FileInputStream generiqueInput = new FileInputStream(generiquePath);
		Scanner generiqueSc = new Scanner(generiqueInput, "Cp1252");

		while (generiqueSc.hasNextLine()) {
			String line = generiqueSc.nextLine();
			if (line == "" || line == "\n")
				continue;
			String[] speMatcher = line.split("\\t", -1);
			boolean matchFound = speMatcher.length == 6;
			if (!matchFound) {
				System.out.println("[Generique] Erreur de match " + speMatcher.length + " : " + line);
			} else {
				Long id;
				try {
					id = Long.parseLong(speMatcher[2].strip());
				} catch (NumberFormatException ex) {
					System.out.println("L'ID : " + speMatcher[2] + " n'est pas parsable.");
					continue;
				}
				String generiqueId = speMatcher[0].strip();
				String libelleGroupeGenerique = speMatcher[1].strip();
				String typeGenerique = (speMatcher[3].strip().charAt(0) == '0' ? "princeps" : "")
						+ (speMatcher[3].strip().charAt(0) == '1' ? "générique" : "")
						+ (speMatcher[3].strip().charAt(0) == '2' ? "générique par complémentarité posologique" : "")
						+ (speMatcher[3].strip().charAt(0) == '4' ? "générique substituable" : "");
				String numeroElement = speMatcher[4].strip();

				WebGouvMedic generique;
				if (medicaments.get(id) != null)
					generique = medicaments.get(id);
				else {
					generique = new WebGouvMedic();
					generique.setId(id);
				}
				generique.setIdentifiantGroupeGenerique(generiqueId);
				generique.setLibelleGenerique(libelleGroupeGenerique);
				generique.setNumeroTri(numeroElement);
				generique.setTypeGenerique(typeGenerique);
				generique.setNom(libelleGroupeGenerique);
				// repo.save(generique);
				medicaments.put(id, generique);
			}
		}
		generiqueSc.close();
		generiqueInput.close();

		FileInputStream conditionInput = new FileInputStream(conditionPath);
		// Lire le fichier avec l'encodage ANSI (Cp1252)
		Scanner conditionSc = new Scanner(conditionInput, "Cp1252");

		while (conditionSc.hasNextLine()) {
			String line = conditionSc.nextLine();
			if (line == "" || line == "\n")
				continue;
			String[] speMatcher = line.split("\\t", -1);
			boolean matchFound = speMatcher.length == 2;
			if (!matchFound) {
				System.out.println("[Condition] Erreur de match " + speMatcher.length + " : " + line);
				continue;
			} else {
				long id = Long.parseLong(speMatcher[0].strip());
				String conditionPrescription = speMatcher[1].strip();

				WebGouvMedic optMedic = medicaments.get(id);
				if (optMedic == null)
					continue;

				optMedic.setConditionPrescriptionDelivrance(conditionPrescription);
			}
		}

		conditionSc.close();
		conditionInput.close();

		FileInputStream compositionInput = new FileInputStream(compositionPath);
		// Lire le fichier avec l'encodage ANSI (Cp1252)
		Scanner compositionSc = new Scanner(compositionInput, "Cp1252");
		while (compositionSc.hasNextLine()) {
			String line = compositionSc.nextLine();
			if (line == "" || line == "\n")
				continue;
			String[] speMatcher = line.split("\\t", -1);
			boolean matchFound = speMatcher.length == 9;
			if (!matchFound) {
				System.out.println("[Composition] Erreur de match " + speMatcher.length + " : " + line);
			} else {
				long id = Long.parseLong(speMatcher[0].strip());
				String elementPharmaceutique = speMatcher[1].strip();
				String codeSubstance = speMatcher[2].strip();
				String nomSubstance = speMatcher[3].strip();
				String dosageSubstance = speMatcher[4].strip();
				String referenceDosage = speMatcher[5].strip();
				String natureComposant = (speMatcher[6].strip() == "SA" ? "principe actif" : " fraction thérapeutique");
				String numeroSubstance = speMatcher[7].strip();

				WebGouvMedic medoc = medicaments.get(id);
				if (medoc == null)
					continue;
				medoc.setElementPharmaceutique(elementPharmaceutique);
				medoc.setCodeSubstance(codeSubstance);
				medoc.setNomSubstance(nomSubstance);
				medoc.setDosageSubstance(dosageSubstance);
				medoc.setReferenceDosage(referenceDosage);
				medoc.setNatureComposant(natureComposant);
				medoc.setNumeroLiaisonSubstances(numeroSubstance);
			}
		}
		compositionSc.close();
		compositionInput.close();

		FileInputStream presentationInput = new FileInputStream(presentationPath);
		Scanner presentationSc = new Scanner(presentationInput, "Cp1252");

		while (presentationSc.hasNextLine()) {
			String line = presentationSc.nextLine();
			if (line == "" || line == "\n")
				continue;
			String[] speMatcher = line.split("\\t", -1);
			boolean matchFound = speMatcher.length == 13;
			long idMedic = Long.parseLong(speMatcher[0].strip());
			String libellePresentation = speMatcher[2].strip();
			;
			String etatCommercialisation = speMatcher[4].strip();
			LocalDate dateDeclarationCommercialisation = LocalDate.MIN;
			try {
				dateDeclarationCommercialisation = LocalDate.parse(speMatcher[5].strip(),
						DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			} catch (DateTimeParseException ex) {
				System.out.println("Date incorrecte : " + speMatcher[5]);
			}
			String tauxRemboursement = "";
			String prix = "";
			String droitRemboursement = "";

			if (!matchFound) {
				if (speMatcher.length == 8) {

				} else {
					System.out.println("[Présentation] Erreur de match " + speMatcher.length + " : " + line);
					for (String s : speMatcher)
						System.out.println(s);
				}
			} else {
				tauxRemboursement = speMatcher[8].strip();
				prix = speMatcher[9].strip();
				droitRemboursement = speMatcher[10].strip();

//					System.out.println("prix : " + prix + " tx rmb : " + tauxRemboursement + " libelle Presentation : "
//							+ libellePresentation);
			}
			WebGouvMedic medoc = medicaments.get(idMedic);
			if (medoc == null)
				continue;
			medoc.setLibellePresentation(libellePresentation);
			medoc.setDateCommercialisation(dateDeclarationCommercialisation);
			medoc.setEtatCommercialisation(etatCommercialisation);
			medoc.setTauxRemboursement(tauxRemboursement);
			medoc.setPrix(prix);
			medoc.setIndicationDroitRemboursement(droitRemboursement);
		}
		repo.saveAll(medicaments.values());
		System.out.println(LocalDateTime.now().toString() + " [Medicaments] " + medicaments.size()
				+ " médicaments ont été enregistrés dans la BDD.");
		presentationSc.close();
		presentationInput.close();
	}

	// Fait une backup du fichier original et le détruit.
	private List<File> backup(String path) throws IOException {
		File originalFile = new File(path);
		File backupFile = new File(path + ".old");
		if (originalFile.exists()) {
			if (backupFile.exists()) {
				// On vérifie que la taille du fichier bakup est inférieur ou égale au fichier
				// pour éviter des I/O inutiles et de perdre des données.
				if (backupFile.length() <= originalFile.length() && originalFile.length() > 0) {
					backupFile.delete();
					originalFile.renameTo(backupFile);
					// Files.copy(originalFile.toPath(), backupFile.toPath());
				}
			}
			// On renomme le fichier original pour télécharger le nouveau contenu à son
			// emplacement.
			if (originalFile.exists())
				originalFile.delete();
			System.out.println("Fichier de backup crée à  l'emplacement : " + backupFile.getAbsolutePath());
		}
		List<File> files = new ArrayList<>();
		files.add(originalFile);
		files.add(backupFile);
		return files;
	}

	// Télécharge des fichiers binaires externes vers un path.
	private boolean getFileFromWeb(URL url, List<File> paths)
			throws IOException, FileNotFoundException, SecurityException {
		System.out.println(LocalDateTime.now().toString() + " Récupération du fichier à l'adresse : " + url.toString());
		BufferedInputStream in = new BufferedInputStream(url.openStream());
		FileOutputStream fileOutputStream = new FileOutputStream(paths.get(0));
		int bufferSize = 1024;
		byte dataBuffer[] = new byte[bufferSize];
		int bytesRead;
		while ((bytesRead = in.read(dataBuffer, 0, bufferSize)) != -1) {
			// Tant que le fichier n'est pas télécharger, on écrit dans le fichier à la
			// suite.
			fileOutputStream.write(dataBuffer, 0, bytesRead);
		}
		fileOutputStream.close();
		System.out.println(
				LocalDateTime.now().toString() + " Fichier récupéré et écrit à l'emplacement : " + paths.get(0));
		return true;
	}

	@Override
	public String setMedicaments() throws MalformedURLException, IOException, ProtocolException {
		LocalDateTime execTimer = LocalDateTime.now();
		// On limite le nombre de requêtes vers le site du gouvernement pour ne pas être
		// considété comme attaque DDOS;
		if (WebGouvMAJDate.getDateMiseAJour() != null) {
			// On vérifie si la BDD a été mise à jour aujourd'hui
			if (WebGouvMAJDate.getDateMiseAJour().isBefore(LocalDate.now().minus(6, ChronoUnit.HOURS))) {
				return "La base de donnée des médicaments ne peut être mise à jour qu'une fois toutes les 6 heures.";
			}
		}
		// On s'assure que le dossier medicaments est crée
		File medicDirectory = new File("medicaments");
		if (!medicDirectory.exists())
			medicDirectory.mkdir();
		// On établit une connexion avec le serveur de medicament.gouv.fr afin d'obtenir
		// le fichier CIS_bdpm.txt contenant une liste
		// de médicaments dont le commerce est ou a été autorisé en France.
		// REF :
		// https://base-donnees-publique.medicaments.gouv.fr/docs/Contenu_et_format_des_fichiers_telechargeables_dans_la_BDM_v1.pdf
		final URL specialiteUrl = new URL(
				"https://base-donnees-publique.medicaments.gouv.fr/telechargement.php?fichier=CIS_bdpm.txt");
		final URL presentationUrl = new URL(
				"https://base-donnees-publique.medicaments.gouv.fr/telechargement.php?fichier=CIS_CIP_bdpm.txt");
		final URL compositionUrl = new URL(
				"https://base-donnees-publique.medicaments.gouv.fr/telechargement.php?fichier=CIS_COMPO_bdpm.txt");
		final URL generiqueUrl = new URL(
				"https://base-donnees-publique.medicaments.gouv.fr/telechargement.php?fichier=CIS_GENER_bdpm.txt");
		final URL conditionUrl = new URL(
				"https://base-donnees-publique.medicaments.gouv.fr/telechargement.php?fichier=CIS_CPD_bdpm.txt");
		final URL informationUrl = new URL(
				"https://base-donnees-publique.medicaments.gouv.fr/telechargement.php?fichier=CIS_InfoImportantes.txt");

		// Liste des emplacements des fichiers originaux et de backup
		List<File> medocFiles = backup("medicaments/specialites.txt");
		List<File> presentationFiles = backup("medicaments/presentations.txt");
		List<File> compositionFiles = backup("medicaments/compositions.txt");
		List<File> generiqueFiles = backup("medicaments/generiques.txt");
		List<File> conditionFiles = backup("medicaments/conditions.txt");
		List<File> informationFiles = backup("medicaments/informations.txt");

		CompletableFuture.supplyAsync(() -> {
			try {
				return getFileFromWeb(informationUrl, informationFiles);
			} catch (SecurityException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}).exceptionally(ex -> {
			System.out.println("Oups! Exception dans getInformations - " + ex.getMessage());
			return false;
		}).thenRunAsync(() -> {
			try {
				if (informationFiles.get(0).length() != informationFiles.get(1).length()
						&& informationFiles.get(0).length() > 0) {
					traiterInformation(informationFiles.get(0));
				} else if (informationFiles.get(0).length() == 0) {
					System.out.println(
							"[Informations] Le fichier téléchargé était vide. Procédure de récupération par backup");
					traiterInformation(informationFiles.get(1));
				}
			} catch (NumberFormatException | NullPointerException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}).exceptionally(ex -> {
			System.out.println("Oops! Exception dans le traitement d'informations - " + ex.getMessage());
			return null;
		});

		CompletableFuture.supplyAsync(() -> {
			try {
				return getFileFromWeb(specialiteUrl, medocFiles);
			} catch (SecurityException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}).thenRunAsync(() -> {
			if (medocFiles.get(0).length() != medocFiles.get(1).length() && medocFiles.get(0).length() > 0
					|| count() == 0) {

				CompletableFuture<Boolean> futureGenerique = CompletableFuture.supplyAsync(() -> {
					try {
						return getFileFromWeb(generiqueUrl, generiqueFiles);
					} catch (SecurityException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				}).exceptionally(ex -> {
					System.out.println("Oups! Exception dans getPresentations - " + ex.getMessage());
					return false;
				});

				ArrayList<CompletableFuture<Boolean>> futures = new ArrayList<>();

				futures.add(futureGenerique);

				CompletableFuture<Boolean> futurePresentation = CompletableFuture.supplyAsync(() -> {
					try {
						return getFileFromWeb(presentationUrl, presentationFiles);
					} catch (SecurityException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				}).exceptionally(ex -> {
					System.out.println("Oups! Exception dans getPresentations - " + ex.getMessage());
					return false;
				});

				futures.add(futurePresentation);

				CompletableFuture<Boolean> futureComposition = CompletableFuture.supplyAsync(() -> {
					try {
						return getFileFromWeb(compositionUrl, compositionFiles);
					} catch (SecurityException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				}).exceptionally(ex -> {
					System.out.println("Oups! Exception dans getPresentations - " + ex.getMessage());
					return false;
				});

				futures.add(futureComposition);

				CompletableFuture<Boolean> futureCondition = CompletableFuture.supplyAsync(() -> {
					try {
						return getFileFromWeb(conditionUrl, conditionFiles);
					} catch (SecurityException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return false;
					}
				}).exceptionally(ex -> {
					System.out.println("Oups! Exception dans getPresentations - " + ex.getMessage());
					return false;
				});

				futures.add(futureCondition);

				// wrapper future completes when all futures have completed
				CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();

				CompletableFuture.runAsync(() -> {
					System.out.println("Entrée dans le traitement des médicaments");
					try {
						if (medocFiles.get(0).length() > 0 && generiqueFiles.get(0).length() > 0
								&& conditionFiles.get(0).length() > 0 && compositionFiles.get(0).length() > 0
								&& presentationFiles.get(0).length() > 0) {
							traiterMedicaments(medocFiles.get(0), generiqueFiles.get(0), conditionFiles.get(0),
									compositionFiles.get(0), presentationFiles.get(0));
						} else if (medocFiles.get(0).length() != medocFiles.get(1).length()) {
							traiterMedicaments(medocFiles.get(1), generiqueFiles.get(1), conditionFiles.get(1),
									compositionFiles.get(1), presentationFiles.get(1));
						}
					} catch (NumberFormatException | NullPointerException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}).exceptionally(ex -> {
					System.out
							.println("Oups! Exception dans setSpecialites - " + ex);
					return null;
				});
			} else if (medocFiles.get(0).length() == medocFiles.get(1).length()) {
				System.out.println("Aucune modification détectée pour les médicaments.");
			} else if (medocFiles.get(0).length() == 0) {
				try {
					traiterMedicaments(medocFiles.get(1), generiqueFiles.get(1), conditionFiles.get(1),
							compositionFiles.get(1), presentationFiles.get(1));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Ensure dateMiseAJour est la date de la dernière mise à jour.
		WebGouvMAJDate.setDateMiseAJour(LocalDate.now());
		System.out.println(LocalDateTime.now().toString() + " Temps d'exécution de setMedicament() : "
				+ execTimer.until(LocalDateTime.now(), ChronoUnit.MILLIS) + "ms");
		return "Medicaments récupérés";
	}

	@Transactional
	public void truncateSecus() {
		final String sql = "DROP TABLE IF EXISTS :web_gouv_securite";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

	@Transactional
	public void truncateMeds() {
		final String sql = "DROP TABLE IF EXISTS :web_gouv_medic";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

}
