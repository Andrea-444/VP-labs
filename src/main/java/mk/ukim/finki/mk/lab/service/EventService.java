package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.exceptions.EventNotFoundException;
import mk.ukim.finki.mk.lab.model.exceptions.LocationNotFoundException;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text, Double minRating,Long locationId);
    //List<Event> searchEventsByCategory(Category category);
    List<Event> findAllByLocation_Id(Long locationId);
    Optional<Event> saveEvent(String name, String description, double popularityScore, Long id, int tickets) throws LocationNotFoundException;

    Optional<Event> editEvent(Long eventId, String name, String description, double popularityScore, Long id, int tickets) throws LocationNotFoundException, EventNotFoundException;

    void deleteEvent(Long id);
    Optional<Event> findEventById(Long id);

    //Optional<Event> changePopScore(Long eventId) throws EventNotFoundException;
}