package fr.helpad.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class FileSystemStorageService implements StorageService {
	private final Path rootLocation;

	@Autowired
	public FileSystemStorageService() {
		this.rootLocation = Paths.get("../static/Stockage/");
	}

	@Override
	@PostConstruct
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		} catch (IOException e) {
		}
	}

	@Override
	public String store(MultipartFile[] files) throws Exception {
		String filename = "";
		for (MultipartFile file : files) {
			filename = StringUtils.cleanPath(file.getOriginalFilename());
			if (file.isEmpty()) {
				
			}
			if (filename.contains("..")) {
			}
			if (filename.endsWith(".pdf") || filename.endsWith(".jpg") || filename.endsWith(".jpeg")
					|| filename.endsWith(".png")) {
				try (InputStream inputStream = file.getInputStream()) {
					Files.copy(inputStream, this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
				}
			}
			else {
				System.out.println(filename);
			}

		}
		return filename;
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(this.rootLocation::relativize);
		} catch (IOException e) {
			return null;
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		Path file = load(filename);
		Resource resource = null;
		try {
			resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				System.out.println("File not found " + filename);
			}
		} catch (MalformedURLException e) {
		}
		return resource;
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
}
