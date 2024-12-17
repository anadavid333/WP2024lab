package mk.finki.ukim.mk.lab4.repository.jpa;

import mk.finki.ukim.mk.lab4.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findById(long id);
}