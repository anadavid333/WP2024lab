package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface LocationService {
    List<Location> listAll();
    Optional<Location> findById(long id);
}
