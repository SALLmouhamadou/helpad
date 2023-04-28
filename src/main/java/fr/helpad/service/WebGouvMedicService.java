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
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import fr.helpad.entity.Medicament;
import fr.helpad.entity.WebGouvMedic;
import fr.helpad.repository.WebGouvMedicRepository;

public class WebGouvMedicService implements WebGouvMedicServiceI {

	private static final Pattern specialitesPattern = Pattern.compile(
			"(\\d*)	((.*), (.*)|(.*))	(.*)"
					+ "	(Autorisation .*)	(.*)	(.*)	(.*)	(.*)	(.*)	(.*)	(Oui|Non)\\n",
			Pattern.CASE_INSENSITIVE);

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

	@Override
	public boolean setMedicaments() throws MalformedURLException, IOException, ProtocolException {
		// On établit une connexion avec le serveur de medicament.gouv.fr afin d'obtenir
		// le fichier CIS_bdpm.txt contenant une liste
		// de médicaments dont le commerce est ou a été autorisé en France.
		final URL url = new URL(
				"https://base-donnees-publique.medicaments.gouv.fr/telechargement.php?fichier=CIS_bdpm.txt");
		System.out.println("Etablissement de la connexion à " + url.toString());
//		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
//		// On obtient la taille du fichier à télécharger
//		httpConnection.setRequestMethod("HEAD");
//		//System.out.println(httpConnection.getResponseMessage());
//		httpConnection.getInputStream();
		long htppFileSize = 0; //httpConnection.getContentLengthLong();
//		System.out.println("Taille du fichier à récupérer : " + htppFileSize);

		File medocFile = new File("specialites.txt");
		boolean pareil = false;

		if (medocFile.exists()) {
			pareil = medocFile.length() == htppFileSize;
			System.out.println("Le fichier est-il identique ? : " + pareil);
		}

		// On vérifie que la taille du fichier à télécharger est inférieur à 100Mb
		if (htppFileSize < 102400000) {

			if (pareil) {
				// TODO
				System.out.println(
						"Les médicaments sont déjà téléchargés," + " vérification de la conformité des données.");
				return false;
			} else {
				// On crée une copie du fichier s'il existe, on l'utilisera en cas d'erreur.
				File medocBackup = new File("specialites.txt.old");

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
				FileOutputStream fileOutputStream = new FileOutputStream("specialites.txt");
				int bufferSize = 1024;
				byte dataBuffer[] = new byte[bufferSize];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, bufferSize)) != -1) {
					// Tant que le fichier n'est pas télécharger, on écrit dans le fichier à la
					// suite.
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
				webResult = true;

				FileInputStream input = new FileInputStream((webResult ? "specialites.txt" : "specialites.txt.old"));
				Scanner sc = new Scanner(input, "UTF-8");
				// On lit le fichier ligne par ligne (RAM safe, on ne connait pas la
				// taille que peut atteindre le fichier téléchargé en ligne)
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					// On traite la ligne
					Matcher speMatcher = specialitesPattern.matcher(line);
					boolean matchFound = speMatcher.find();
					if (matchFound) {
						long id = Long.parseLong(speMatcher.group(1));
						String nom = speMatcher.group(2);
						String forme = speMatcher.group(3);
						String voieAdministration = speMatcher.group(4);
						String statutAdministratif = speMatcher.group(5);
						String procedureAutorisation = speMatcher.group(6);
						boolean etatCommercialisation = (speMatcher.group(7)=="Commercialisée"?true:false);
						LocalDate dateAMM = LocalDate.parse(speMatcher.group(8));
						String statutBDM = speMatcher.group(9);
						String numeroAutorisationEurope = speMatcher.group(10);
						String titulaire = speMatcher.group(11);
						String surveillanceRenforcee = speMatcher.group(12);
//						WebGouvMedic medoc = new WebGouvMedic(id, nom, forme, voieAdministration, statutAdministratif, 
//								procedureAutorisation, etatCommercialisation, dateAMM, statutBDM, numeroAutorisationEurope, 
//								titulaire, surveillanceRenforcee);
					} else {
						System.out.println("La ligne suivante ne retourne pas de médicament : " + line);
					}
				}
			}

			// Ensure dateMiseAJour est la date de la dernière mise à jour.
			WebGouvMedic.setDateMiseAJour(LocalDate.now());
		}

		return true;
	}

}
