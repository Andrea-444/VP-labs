package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.Location;
import mk.ukim.finki.mk.lab.model.exceptions.EventNotFoundException;
import mk.ukim.finki.mk.lab.model.exceptions.LocationNotFoundException;
import mk.ukim.finki.mk.lab.repository.EventRepository;
import mk.ukim.finki.mk.lab.repository.LocationRepository;
import mk.ukim.finki.mk.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }
    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text, Double minRating) {
        return eventRepository.searchEvents(text,minRating);
    }

//    @Override
//    public List<Event> searchEventsByCategory(Category category) {
//        return eventRepository.searchEventsByCategory(category);
//    }

    @Override
    public Optional<Event> saveEvent(String name, String description, double popularityScore, Long locationId, int tickets) throws LocationNotFoundException {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        return eventRepository.save(name,description,popularityScore,location, tickets);

    }

    @Override
    public Optional<Event> editEvent(Long eventId, String name, String description, double popularityScore, Long locationId, int tickets) throws LocationNotFoundException, EventNotFoundException {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
        return eventRepository.edit(eventId,name,description,popularityScore,location, tickets);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public Optional<Event> changePopScore(Long eventId) throws EventNotFoundException {
        return this.eventRepository.changeRating(eventId);
    }


}
