package mk.ukim.finki.wp.lab.repository.jpa;

import mk.ukim.finki.wp.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//jpa repository dava metodi sto ovozmozuvaat rabota so data base
//prviot e koj entitet go cuvame i tp na id
@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    List<Event> findAllByLocation_id(Long location_id);
    @Query("SELECT e FROM Event e WHERE e.name LIKE %:keyword%")
    List<Event> searchEvents(@Param("keyword") String keyword);
}