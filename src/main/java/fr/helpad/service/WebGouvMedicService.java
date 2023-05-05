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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import org.springframework.dao.OptimisticLockingFailureException;
import org.unbescape.html.HtmlEscape;
import org.unbescape.html.HtmlEscapeType;

import fr.helpad.entity.Medicament;
import fr.helpad.entity.WebGouvMAJDate;
import fr.helpad.entity.WebGouvMedic;
import fr.helpad.repository.WebGouvMedicRepository;

@Transactional
public class WebGouvMedicService implements WebGouvMedicServiceI {

	@Autowired
	WebGouvMedicRepository repo;

	@Override
	public WebGouvMedic sauvegarder(WebGouvMedic entity) {
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

	private void traiterFichier(String path, List<WebGouvMedic> medocs) throws IOException, NullPointerException {
		FileInputStream input = new FileInputStream(path);
		// Lire le fichier avec l'encodage ANSI (Cp1252)
		Scanner sc = new Scanner(input, "Cp1252");
		// On lit le fichier ligne par ligne (RAM safe, on ne connait pas la
		// taille que peut atteindre le fichier téléchargé en ligne)
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line == "" || line == "\n")
				continue;
		}
	}

	private List<WebGouvMedic> traiterSpecialite(File path) throws IOException, NullPointerException, FileNotFoundException {
		FileInputStream input = new FileInputStream(path);
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
			String[] speMatcher = line.split("\\t");
			boolean matchFound = speMatcher.length == 12;
			if (matchFound) {
				long id = Long.parseLong(speMatcher[0].strip());
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

//				System.out.println(line);
//				System.out.println("id: " + id);
//				System.out.println("nom: " + nom);
//				System.out.println("forme: " + forme);
//				System.out.println("voie: " + voieAdministration);
//				System.out.println("statut: " + statutAdministratif);
//				System.out.println("procedure: " + procedureAutorisation);
//				System.out.println("etat: " + etatCommercialisation);
//				System.out.println("date: " + dateAMM.toString());
//				System.out.println("statutBDM: " + statutBDM);
//				System.out.println("numeroAutorisation: " + numeroAutorisationEurope);
//				System.out.println("titulaire: " + titulaire);
//				System.out.println("surveillance: " + surveillanceRenforcee);

				WebGouvMedic medoc = new WebGouvMedic(id, nom, forme, voieAdministration, statutAdministratif,
						procedureAutorisation, etatCommercialisation, dateAMM, statutBDM, numeroAutorisationEurope,
						titulaire, surveillanceRenforcee);

				// System.out.println(medoc.toString());
				medocs.add(medoc);
			} else {
				System.out.println("Match length : " + speMatcher.length + " | text : " + line);
			}
		}
		System.out.println("Enregistrement de " + medocs.size() + " médicaments dans la BDD.");
		return medocs;
		// saveAll(medocs);
		// System.out.println("Médicaments enregistrés dans la BDD");
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
		}
		List<File> files = new ArrayList<>();
		files.add(originalFile);
		files.add(backupFile);
		return files;
	}

	private void getFileFromWeb(URL url, List<File> paths) throws IOException, FileNotFoundException, SecurityException {
		System.out.println("Récupération du fichier à l'adresse : " + url.toString());
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
		System.out.println("Fichier récupéré et écrit à l'emplacement : " + paths.get(0));
	}

	@Override
	public String setMedicaments() throws MalformedURLException, IOException, ProtocolException {
		// On s'assure que le dossier medicaments est crée
		File medicDirectory = new File("medicaments");
		if (!medicDirectory.exists())
			medicDirectory.mkdir();
		// On établit une connexion avec le serveur de medicament.gouv.fr afin d'obtenir
		// le fichier CIS_bdpm.txt contenant une liste
		// de médicaments dont le commerce est ou a été autorisé en France.
		// REF :
		// https://base-donnees-publique.medicaments.gouv.fr/docs/Contenu_et_format_des_fichiers_telechargeables_dans_la_BDM_v1.pdf
		final URL refUrl = new URL(
				"https://base-donnees-publique.medicaments.gouv.fr/docs/Contenu_et_format_des_fichiers_telechargeables_dans_la_BDM_v1.pdf");
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
		List<WebGouvMedic> medocs;
		
		boolean isNew = false;

		try {
			getFileFromWeb(specialiteUrl, medocFiles);
			// Si le fichier est différent du backup alors on le traite.
			if (medocFiles.get(0).length() != medocFiles.get(1).length()) {
				isNew = true;
				medocs = traiterSpecialite(medocFiles.get(0));
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
				getFileFromWeb(presentationUrl, presentationFiles);
				isSucces = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			if (isSucces) {
				// On traite l'original
			} else {
				// On traite le backup
			}
			isSucces = false;
			try {
				getFileFromWeb(compositionUrl, compositionFiles);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				getFileFromWeb(generiqueUrl, generiqueFiles);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				getFileFromWeb(conditionUrl, conditionFiles);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			try {
				getFileFromWeb(informationUrl, informationFiles);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		// Ensure dateMiseAJour est la date de la dernière mise à jour.
		WebGouvMAJDate.setDateMiseAJour(LocalDate.now());

		return "Medicaments récupérés";
	}

}
