package fr.helpad.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.repository.CrudRepository;

import fr.helpad.entity.WebGouvSecurite;

public interface WebGouvSecuriteRepository extends CrudRepository<WebGouvSecurite, Long> {
	public List<WebGouvSecurite> findAll();

    public default Map<Long, WebGouvSecurite> findAllMap() {
        return findAll().stream().collect(Collectors.toMap(WebGouvSecurite::getId, v -> v));
    }
}
