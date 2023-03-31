package fr.helpad.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {
	private final Path rootLocation;
	@Autowired
	  public FileSystemStorageService() {
	    this.rootLocation = Paths.get("./Stockage/");
	   }
	@Override
	@PostConstruct
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		    } catch (IOException e) {}
	}

	@Override
	public String store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());
		  try {
		  if (file.isEmpty()) {}  
		  if (filename.contains("..")) { }
		   try (InputStream inputStream = file.getInputStream()) {
		    Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
		    }
		   } catch (IOException e) {	}
		   return filename;
	}

}
