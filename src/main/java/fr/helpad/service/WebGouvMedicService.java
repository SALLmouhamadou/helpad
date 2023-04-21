package fr.helpad.service;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import fr.helpad.entity.Medicament;
import fr.helpad.entity.WebGouvMedic;
import fr.helpad.repository.WebGouvMedicRepository;

public class WebGouvMedicService implements WebGouvMedicServiceI {
	
	private static final Pattern specialitesPattern = Pattern.compile("(\\d*)	((.*), (.*)|(.*))	(.*)" + 
			"	(Autorisation .*)	(.*)	(.*)	(.*)	(.*)	(.*)	(.*)	(Oui|Non)\\n", Pattern.CASE_INSENSITIVE);
	
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
		final URL url = new URL("https://base-donnees-publique.medicaments.gouv.fr/telechargement.php?fichier=CIS_bdpm.txt");
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.setRequestMethod("HEAD");
		long removeFileSize = httpConnection.getContentLengthLong();
		
		try (BufferedInputStream in = new BufferedInputStream(url.openStream());
				  FileOutputStream fileOutputStream = new FileOutputStream("specialites.txt")) {
				    byte dataBuffer[] = new byte[1024];
				    int bytesRead;
				    while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				        fileOutputStream.write(dataBuffer, 0, bytesRead);
				    }
				} catch (IOException e) {
					e.printStackTrace();
				    return false;
				}
		
		return true;
	}

}
