package mk.finki.ukim.mk.lab4.service;

import mk.finki.ukim.mk.lab4.model.Location;

import java.util.List;

public interface LocationService {
    public List<Location> findAll();
    public List<Location> find_by_ID(long id);
}