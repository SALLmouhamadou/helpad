package fr.helpad.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import fr.helpad.entity.Medicament;
import fr.helpad.entity.WebGouvMedic;
import fr.helpad.repository.WebGouvMedicRepository;

public class WebGouvMedicService implements WebGouvMedicServiceI {

	@Autowired
	WebGouvMedicRepository repo;

	@Override
	public WebGouvMedic sauvegarder(WebGouvMedic entity) {
		return repo.save(entity);
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

	private void traiterFichier(String path) throws IOException {
		FileInputStream input = new FileInputStream(path);
		// Lire le fichier avec l'encodage ANSI (Cp1252)
		Scanner sc = new Scanner(input, "Cp1252");
		// On lit le fichier ligne par ligne (RAM safe, on ne connait pas la
		// taille que peut atteindre le fichier téléchargé en ligne)
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if (line == "<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">") {
				break;
			}
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
				}
				catch (DateTimeParseException ex) {
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
				
//				System.out.println(medoc.toString());
			} else {
				System.out.println(line);
			}
		}
	}

	@Override
	public boolean setMedicaments() throws MalformedURLException, IOException, ProtocolException {
		// On établit une connexion avec le serveur de medicament.gouv.fr afin d'obtenir
		// le fichier CIS_bdpm.txt contenant une liste
		// de médicaments dont le commerce est ou a été autorisé en France.
		final URL url = new URL(
				"https://base-donnees-publique.medicaments.gouv.fr/telechargement.php?fichier=CIS_bdpm.txt");
		System.out.println("Etablissement de la connexion à " + url.toString());
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		// On obtient la taille du fichier à télécharger
		System.out.println("HEADER : " + httpConnection.getHeaderFields().get("content-length"));
		long htppFileSize = httpConnection.getContentLengthLong();
		System.out.println("Taille du fichier à récupérer : " + htppFileSize);
		boolean chargerLocal = false;
		
		File medicDirectory = new File("medicaments");
		if (!medicDirectory.exists())
			medicDirectory.mkdir();

		File medocFile = new File("medicaments/specialites.txt");
		boolean pareil = false;

		if (medocFile.exists()) {
			pareil = medocFile.length() == htppFileSize;
			System.out.println("Le fichier est-il identique ? : " + pareil);
		}

		// On vérifie que la taille du fichier à télécharger est inférieur à 100Mb
		if (htppFileSize < 102400000 && !chargerLocal) {

			if (pareil) {
				// TODO
				System.out.println(
						"Les médicaments sont déjà téléchargés," + " vérification de la conformité des données.");
				return false;
			} else {
				// On crée une copie du fichier s'il existe, on l'utilisera en cas d'erreur.
				File medocBackup = new File("medicaments/specialites.txt.old");

				if (medocFile.exists()) {
					if (medocBackup.exists())
						medocBackup.delete();
					Files.copy(medocFile.toPath(), medocBackup.toPath());
					// On supprime le contenu de fileMedoc pour télécharger le nouveau contenu.
					medocFile.delete();
				}

				boolean webResult = false;

				// On télécharge le fichier et on l'écrit à la racine de notre application dans
				// specialites.txt.
				BufferedInputStream in = new BufferedInputStream(url.openStream());
				FileOutputStream fileOutputStream = new FileOutputStream("medicaments/specialites.txt");
				int bufferSize = 1024;
				byte dataBuffer[] = new byte[bufferSize];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, bufferSize)) != -1) {
					// Tant que le fichier n'est pas télécharger, on écrit dans le fichier à la
					// suite.
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
				webResult = true;

				traiterFichier((webResult ? "medicaments/specialites.txt" : "medicaments/specialites.txt.old"));
			}

			// Ensure dateMiseAJour est la date de la dernière mise à jour.
			WebGouvMedic.setDateMiseAJour(LocalDate.now());
		} else if (chargerLocal) {
			System.out.println("Traitement local du fichier de médicaments.");
			traiterFichier("medicaments/specialites.txt");
		}

		return true;
	}

}
