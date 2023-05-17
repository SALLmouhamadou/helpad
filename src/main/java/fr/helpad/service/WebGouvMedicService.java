package fr.helpad.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IllegalFormatConversionException;
import java.util.IllegalFormatException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.unbescape.html.HtmlEscapeType;

import fr.helpad.entity.Medicament;
import fr.helpad.entity.WebGouvGenerique;
import fr.helpad.entity.WebGouvMAJDate;
import fr.helpad.entity.WebGouvMedic;
import fr.helpad.entity.WebGouvSecurite;
import fr.helpad.repository.WebGouvGeneriqueRepository;
import fr.helpad.repository.WebGouvMedicRepository;
import fr.helpad.repository.WebGouvSecuriteRepository;

@Transactional
@Service
public class WebGouvMedicService implements WebGouvMedicServiceI {

	@Autowired
	WebGouvMedicRepository repo;
	@Autowired
	WebGouvGeneriqueRepository repoGenerique;
	@Autowired
	WebGouvSecuriteRepository repoSecu;

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
	
	public Class getClassById(Long id) throws NoSuchElementException {
		if (repo.findById(id).isPresent())
			return WebGouvMedic.class;
		if (repoGenerique.findById(id).isPresent())
			return WebGouvGenerique.class;
		return null;
	}

	@Override
	public WebGouvMedic get(Long id) throws NoSuchElementException {
		return repo.findById(id).get();
	}

	@Override
	public void supprimer(Long id) throws IllegalArgumentException {
		repo.deleteById(id);
	}

	public List<WebGouvMedic> findByNameLimited(String nom, Pageable pageable) {
		return repo.findByNomOrderByNomDesc(nom, pageable);
	}

	public long count() {
		return repo.count();
	}

	private void traiterInformation(File path) throws IOException, NullPointerException, NumberFormatException {
		FileInputStream input = new FileInputStream(path);
		// Lire le fichier avec l'encodage ANSI (Cp1252)
		Scanner sc = new Scanner(input, "Cp1252");
		// On lit le fichier ligne par ligne (RAM safe, on ne connait pas la
		// taille que peut atteindre le fichier téléchargé en ligne)
		List<WebGouvSecurite> secus = new LinkedList<>();
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

				Optional<WebGouvMedic> optMedic = repo.findById(id);

				try {
					WebGouvMedic medoc = optMedic.get();
					WebGouvSecurite secu = new WebGouvSecurite();
					secu.setDebutInfoSecurite(dateFinInformation);
					secu.setFinInfoSecurite(dateFinInformation);
					secu.setInformationSecurite(infoLienInformationSecu);
					secu.setMedicament(medoc);
					secus.add(secu);
				} catch (NoSuchElementException ex) {
					// System.out.println("[WebGouvSecurite] Le médicament avec l'ID : " + id + "
					// est introuvable.");
					continue;
				}
			}
		}
		System.out.println(
				"[Securite] Enregistrement de " + secus.size() + " fiches de sécurité de médicaments dans la BDD.");
		repoSecu.saveAll(secus);
		sc.close();
		input.close();
	}

	private void traiterCondition(File path) throws IOException, NullPointerException, NumberFormatException {
		FileInputStream input = new FileInputStream(path);
		// Lire le fichier avec l'encodage ANSI (Cp1252)
		Scanner sc = new Scanner(input, "Cp1252");
		// On lit le fichier ligne par ligne (RAM safe, on ne connait pas la
		// taille que peut atteindre le fichier téléchargé en ligne)
		List<WebGouvMedic> medocs = new LinkedList<>();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
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

				Optional<WebGouvMedic> optMedic = repo.findById(id);

				try {
					WebGouvMedic medoc = optMedic.get();
					medoc.setConditionPrescriptionDelivrance(conditionPrescription);
					medocs.add(medoc);
				} catch (NoSuchElementException ex) {
					System.out.println("[WebGouvCondition] Le médicament avec l'ID : " + id + " est introuvable.");
				}
			}
		}
		System.out.println("[Condition] Modification de " + medocs.size() + " médicaments dans la BDD.");
		repo.saveAll(medocs);
		sc.close();
		input.close();
	}

	private void traiterGenerique(File path) throws IOException, NullPointerException, NumberFormatException {
		FileInputStream input = new FileInputStream(path);
		// Lire le fichier avec l'encodage ANSI (Cp1252)
		Scanner sc = new Scanner(input, "Cp1252");
		// On lit le fichier ligne par ligne (RAM safe, on ne connait pas la
		// taille que peut atteindre le fichier téléchargé en ligne)
		List<WebGouvGenerique> generiques = new LinkedList<>();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
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

				Optional<WebGouvMedic> optMedic = repo.findById(id);

				WebGouvGenerique generique = new WebGouvGenerique();
				generique.setId(id);
				generique.setIdentifiantGroupeGenerique(generiqueId);
				generique.setLibelleGenerique(libelleGroupeGenerique);
				generique.setNumeroTri(numeroElement);
				generique.setTypeGenerique(typeGenerique);
				generiques.add(generique);
			}
		}

		System.out.println("[Generique] Modification de " + generiques.size() + " médicaments génériques dans la BDD.");
		repoGenerique.saveAll(generiques);
		sc.close();
		input.close();
	}

	private void traiterComposition(File path) throws IOException, NullPointerException, NumberFormatException {
		FileInputStream input = new FileInputStream(path);
		// Lire le fichier avec l'encodage ANSI (Cp1252)
		Scanner sc = new Scanner(input, "Cp1252");
		// On lit le fichier ligne par ligne (RAM safe, on ne connait pas la
		// taille que peut atteindre le fichier téléchargé en ligne)
		List<WebGouvMedic> medocs = new LinkedList<>();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
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

				Optional<WebGouvMedic> optMedic = repo.findById(id);

				try {
					WebGouvMedic medoc = optMedic.get();
					medoc.setElementPharmaceutique(elementPharmaceutique);
					medoc.setCodeSubstance(codeSubstance);
					medoc.setNomSubstance(nomSubstance);
					medoc.setDosageSubstance(dosageSubstance);
					medoc.setReferenceDosage(referenceDosage);
					medoc.setNatureComposant(natureComposant);
					medoc.setNumeroLiaisonSubstances(numeroSubstance);
					medocs.add(medoc);
				} catch (NoSuchElementException ex) {
					System.out.println("[WebGouvComposition] Le médicament avec l'ID : " + id + " est introuvable.");
				}
			}
		}
		System.out.println("[Composition] Modification de " + medocs.size() + " médicaments dans la BDD.");
		repo.saveAll(medocs);
		sc.close();
		input.close();
	}

	private void traiterPresentation(File path) throws IOException, NullPointerException, NumberFormatException {
		FileInputStream input = new FileInputStream(path);
		try (// Lire le fichier avec l'encodage ANSI (Cp1252)
				Scanner sc = new Scanner(input, "Cp1252")) {
			// On lit le fichier ligne par ligne (RAM safe, on ne connait pas la
			// taille que peut atteindre le fichier téléchargé en ligne)
			List<WebGouvMedic> medocs = new LinkedList<>();
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line == "" || line == "\n")
					continue;
				String[] speMatcher = line.split("\\t", -1);
				boolean matchFound = speMatcher.length == 13;
				long id = 0;
				String libellePresentation = "";
				String etatCommercialisation = "";
				LocalDate dateDeclarationCommercialisation = LocalDate.MIN;
				String tauxRemboursement = "";
				String prix = "";
				String droitRemboursement = "";
				
				if (!matchFound) {
					if (speMatcher.length == 8) {
						id = Long.parseLong(speMatcher[0].strip());
						libellePresentation = speMatcher[2].strip();
						etatCommercialisation = speMatcher[4].strip();
						try {
							dateDeclarationCommercialisation = LocalDate.parse(speMatcher[5].strip(),
									DateTimeFormatter.ofPattern("dd/MM/yyyy"));
						} catch (DateTimeParseException ex) {
							System.out.println("Date incorrecte : " + speMatcher[5]);
						}
					} else {
						System.out.println("[Présentation] Erreur de match " + speMatcher.length + " : " + line);
						for (String s : speMatcher)
							System.out.println(s);
					}
				} else {
					id = Long.parseLong(speMatcher[0].strip());
					libellePresentation = speMatcher[2].strip();
					etatCommercialisation = speMatcher[4].strip();
					try {
						dateDeclarationCommercialisation = LocalDate.parse(speMatcher[5].strip(),
								DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					} catch (DateTimeParseException ex) {
						System.out.println("Date incorrecte : " + speMatcher[5]);
					}
					tauxRemboursement = speMatcher[8].strip();
					prix = speMatcher[9].strip();
					droitRemboursement = speMatcher[10].strip();

//					System.out.println("prix : " + prix + " tx rmb : " + tauxRemboursement + " libelle Presentation : "
//							+ libellePresentation);
				}
				try {
					Optional<WebGouvMedic> optMedic = repo.findById(id);
					WebGouvMedic medoc = optMedic.get();
					medoc.setLibellePresentation(libellePresentation);
					medoc.setDateCommercialisation(dateDeclarationCommercialisation);
					medoc.setEtatCommercialisation(etatCommercialisation);
					medoc.setTauxRemboursement(tauxRemboursement);
					medoc.setPrix(prix);
					medoc.setIndicationDroitRemboursement(droitRemboursement);
					medocs.add(medoc);
				} catch (NoSuchElementException ex) {
					System.out
							.println("[WebGouvPresentation] Le médicament avec l'ID : " + id + " est introuvable.");
				}
			}
			System.out.println("[Presentation] Modification de " + medocs.size() + " médicaments dans la BDD.");
			repo.saveAll(medocs);
			sc.close();
			input.close();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void traiterSpecialite(File path)
			throws IOException, NullPointerException, FileNotFoundException, NumberFormatException {
		FileInputStream input = new FileInputStream(path);
		System.out.println(LocalDateTime.now().toString() + " Début de traitement des spécialités.");
		// Lire le fichier avec l'encodage ANSI (Cp1252)
		Scanner sc = new Scanner(input, "Cp1252");
		// On lit le fichier ligne par ligne (RAM safe, on ne connait pas la
		// taille que peut atteindre le fichier téléchargé en ligne)
		List<WebGouvMedic> medocs = new LinkedList<WebGouvMedic>();
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line == "" || line == "\n")
				continue;
			// On traite la ligne
			String[] speMatcher = line.split("\\t", -1);
			boolean matchFound = speMatcher.length == 12;
			if (!matchFound) {
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

				WebGouvMedic medoc = new WebGouvMedic(id, nom, forme, voieAdministration, statutAdministratif,
						procedureAutorisation, etatCommercialisation, dateAMM, statutBDM, numeroAutorisationEurope,
						titulaire, surveillanceRenforcee);
				// medoc = sauvegarder(medoc);
				medocs.add(medoc);
			}
		}
		System.out.println(LocalDateTime.now().toString() + " [Specialites] Enregistrement de " + medocs.size() + " médicaments dans la BDD.");
		saveAll(medocs);
		sc.close();
		input.close();
	}

	// Fait une backup du fichier original et le détruit.
	private List<File> backup(String path) throws IOException {
		File originalFile = new File(path);
		File backupFile = new File(path + ".old");
		if (originalFile.exists()) {
			if (backupFile.exists()) {
				// On vérifie que la taille du fichier bakup est inférieur ou égale au fichier
				// pour éviter des I/O inutiles et de perdre des données.
				if (backupFile.length() <= originalFile.length()) {
					backupFile.delete();
					// Files.copy(originalFile.toPath(), backupFile.toPath());
				}
			}
			// On renomme le fichier original pour télécharger le nouveau contenu à son
			// emplacement.
			originalFile.renameTo(backupFile);
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
		System.out.println(LocalDateTime.now().toString() + " Fichier récupéré et écrit à l'emplacement : " + paths.get(0));
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

		boolean isNew = false;

		try {
			getFileFromWeb(specialiteUrl, medocFiles);
			// Si le fichier est différent du backup ou que la BDD est vide alors on traite
			// le fichier.
			if (medocFiles.get(0).length() != medocFiles.get(1).length() || count() == 0) {
				isNew = true;
				traiterSpecialite(medocFiles.get(0));
			} else {
				WebGouvMAJDate.setDateMiseAJour(LocalDate.now());
				return "Les médicaments étaient à jour, aucune modification n'a été apportée.";
			}
		} catch (SecurityException e) {
			e.printStackTrace();
			return "Erreur de droit d'accès aux fichiers";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (isNew) {
			boolean isSucces = false;

			try {
				isSucces = getFileFromWeb(presentationUrl, presentationFiles);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (isSucces) {
				traiterPresentation(presentationFiles.get(0));
			} else {
				traiterPresentation(presentationFiles.get(1));
			}
			isSucces = false;

			try {
				isSucces = getFileFromWeb(compositionUrl, compositionFiles);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (isSucces) {
				traiterComposition(compositionFiles.get(0));
			} else {
				traiterComposition(compositionFiles.get(1));
			}
			isSucces = false;

			try {
				isSucces = getFileFromWeb(generiqueUrl, generiqueFiles);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (isSucces) {
				traiterGenerique(generiqueFiles.get(0));
			} else {
				traiterGenerique(generiqueFiles.get(1));
			}
			isSucces = false;

			try {
				isSucces = getFileFromWeb(conditionUrl, conditionFiles);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (isSucces) {
				traiterCondition(conditionFiles.get(0));
			} else {
				traiterCondition(conditionFiles.get(1));
			}
			isSucces = false;

			try {
				isSucces = getFileFromWeb(informationUrl, informationFiles);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (isSucces) {
				traiterInformation(informationFiles.get(0));
			} else {
				traiterInformation(informationFiles.get(1));
			}
			isSucces = false;
		}

		// Ensure dateMiseAJour est la date de la dernière mise à jour.
		WebGouvMAJDate.setDateMiseAJour(LocalDate.now());
		System.out.println("Temps d'exécution de setMedicament() : " + execTimer.until(LocalDateTime.now(), ChronoUnit.MILLIS) + "ms");
		return "Medicaments récupérés";
	}

}
