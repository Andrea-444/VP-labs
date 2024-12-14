//package mk.ukim.finki.mk.lab.service.impl;
////
//import mk.ukim.finki.mk.lab.model.Event;
//import mk.ukim.finki.mk.lab.model.Location;
//import mk.ukim.finki.mk.lab.model.exceptions.EventNotFoundException;
//import mk.ukim.finki.mk.lab.model.exceptions.LocationNotFoundException;
//import mk.ukim.finki.mk.lab.repository.InMemoryEventRepository;
//import mk.ukim.finki.mk.lab.repository.InMemoryLocationRepository;
//import mk.ukim.finki.mk.lab.service.EventService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class EventServiceImpl implements EventService {
//    private final InMemoryEventRepository eventRepository;
//    private final InMemoryLocationRepository locationRepository;
//
//    public EventServiceImpl(InMemoryEventRepository eventRepository, InMemoryLocationRepository locationRepository) {
//        this.eventRepository = eventRepository;
//        this.locationRepository = locationRepository;
//    }
//    @Override
//    public List<Event> listAll() {
//        return eventRepository.findAll();
//    }
//
//    @Override
//    public List<Event> searchEvents(String text, Double minRating) {
//        return eventRepository.searchEvents(text,minRating);
//    }
//
////    @Override
////    public List<Event> searchEventsByCategory(Category category) {
////        return eventRepository.searchEventsByCategory(category);
////    }
//
//    @Override
//    public Optional<Event> saveEvent(String name, String description, double popularityScore, Long locationId, int tickets) throws LocationNotFoundException {
//        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
//        return eventRepository.save(name,description,popularityScore,location, tickets);
//
//    }
//
//    @Override
//    public Optional<Event> editEvent(Long eventId, String name, String description, double popularityScore, Long locationId, int tickets) throws LocationNotFoundException, EventNotFoundException {
//        Location location = locationRepository.findById(locationId).orElseThrow(() -> new LocationNotFoundException(locationId));
//        return eventRepository.edit(eventId,name,description,popularityScore,location, tickets);
//    }
//
//    @Override
//    public void deleteEvent(Long id) {
//        eventRepository.deleteById(id);
//    }
//
//    @Override
//    public Optional<Event> findEventById(Long id) {
//        return eventRepository.findById(id);
//    }
//
//    @Override
//    public Optional<Event> changePopScore(Long eventId) throws EventNotFoundException {
//        return this.eventRepository.changeRating(eventId);
//    }
//
//
//}

package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.Location;
import mk.ukim.finki.mk.lab.model.exceptions.EventNotFoundException;
import mk.ukim.finki.mk.lab.model.exceptions.LocationNotFoundException;
import mk.ukim.finki.mk.lab.repository.InMemoryEventRepository;
import mk.ukim.finki.mk.lab.repository.InMemoryLocationRepository;
import mk.ukim.finki.mk.lab.repository.jpa.EventRepository;
import mk.ukim.finki.mk.lab.repository.jpa.LocationRepository;
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
    public List<Event> searchEvents(String text, Double minRating, Long locationId) {
        if ((text == null || text.isEmpty()) && minRating == null && locationId == null) {
            return eventRepository.findAll();
        }
        if ((text == null || text.isEmpty()) && minRating == null) {
            return eventRepository.findAllByLocation_Id(locationId);
        }
        if (text != null && !text.isEmpty() && minRating == null && locationId == null) {
            return eventRepository.findByNameContainingIgnoreCase(text);
        }
        if ((text == null || text.isEmpty()) && locationId == null) {
            return eventRepository.findByPopularityScoreGreaterThanEqual(minRating);
        }
        if (text != null && !text.isEmpty() && minRating != null && locationId == null) {
            return eventRepository.findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqual(text, minRating);
        }
        if (text != null && !text.isEmpty() && minRating == null) {
            return eventRepository.findByNameContainingIgnoreCaseAndLocation_Id(text, locationId);
        }
        if (text == null || text.isEmpty()) {
            return eventRepository.findByPopularityScoreGreaterThanEqualAndLocation_Id(minRating, locationId);
        }
        return eventRepository.findByNameContainingIgnoreCaseAndPopularityScoreGreaterThanEqualAndLocation_Id(
                text, minRating, locationId);
    }

    @Override
    public List<Event> findAllByLocation_Id(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);    }


    @Override
    public Optional<Event> saveEvent(String name, String description, double popularityScore, Long locationId, int tickets) throws LocationNotFoundException {
//        if (!eventRepository.findByNameContainingIgnoreCase(name).isEmpty()) {
//            throw new IllegalArgumentException("An event with this name already exists.");
//        }
//        if (!eventRepository.findAllByLocation_Id(locationId).isEmpty()) {
//            throw new IllegalArgumentException("An event with this location ID already exists.");
//        }
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setPopularityScore(popularityScore);

        Optional<Location> location = locationRepository.findById(locationId);

        if (location.isPresent()) {

            event.setLocation(location.get());
            return Optional.of(eventRepository.save(event));
        }
        throw new IllegalArgumentException("Invalid location ID");
    }

    @Override
    public Optional<Event> editEvent(Long eventId, String name, String description, double popularityScore, Long locationId, int tickets) throws LocationNotFoundException, EventNotFoundException {
//        if (eventRepository.findByNameContainingIgnoreCase(name).stream()
//                .anyMatch(event -> !event.getId().equals(eventId))) {
//            throw new IllegalArgumentException("An event with this name already exists.");
//        }
//
//        if (eventRepository.findAllByLocation_Id(locationId).stream()
//                .anyMatch(event -> !event.getId().equals(eventId))) {
//            throw new IllegalArgumentException("An event with this location ID already exists.");
//        }
        Optional<Event> eventOptional = eventRepository.findById(eventId);
        if (eventOptional.isPresent()) {
            Event event = eventOptional.get();
            event.setName(name);
            event.setDescription(description);
            event.setPopularityScore(popularityScore);
            event.setTickets(tickets);

            Optional<Location> location = locationRepository.findById(locationId);
            if (location.isPresent()) {

                event.setLocation(location.get());
                event.setLocation(location.get());
                return Optional.of(eventRepository.save(event));
            }
        }
        return Optional.empty();}

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Optional<Event> findEventById(Long id) {
        return eventRepository.findById(id);
    }

//    @Override
//    public Optional<Event> changePopScore(Long eventId) throws EventNotFoundException {
//        return this.eventRepository.changeRating(eventId);
//    }


}
