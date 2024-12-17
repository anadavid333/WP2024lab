package mk.finki.ukim.mk.lab4.service;

import mk.finki.ukim.mk.lab4.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(long id);
}