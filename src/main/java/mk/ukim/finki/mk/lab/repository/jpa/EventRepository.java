package mk.ukim.finki.mk.lab.repository.jpa;

import mk.ukim.finki.mk.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation_Id(Long locationId);
    List<Event> findByPopularityScoreGreaterThanEqual(Double popularityScore);
    List<Event> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    List<Event> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(String name, String description, Double popularityScore);
    List<Event> findByNameContainingIgnoreCase(String name);
    List<Event> findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqualAndLocation_Id(
            String name,
            Double popularityScore,
            Long locationId
    );
    List<Event> findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(
            String name,
            Double popularityScore
    );
    List<Event> findByPopularityScoreGreaterThanEqualAndLocation_Id(
            Double popularityScore,
            Long locationId
    );
    List<Event> findByNameContainingIgnoreCaseAndLocation_Id(
            String name,
            Long locationId
    );
}

