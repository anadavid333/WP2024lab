package mk.finki.ukim.mk.lab4.service.impl;

import mk.finki.ukim.mk.lab4.model.Location;
import mk.finki.ukim.mk.lab4.repository.jpa.LocationRepository;
import mk.finki.ukim.mk.lab4.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private  LocationRepository locationRepository;
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> find_by_ID(long id) {
        return locationRepository.findById(id);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }
}
