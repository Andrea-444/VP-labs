package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.Location;
import mk.ukim.finki.mk.lab.model.exceptions.EventNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    public List<Event> findAll(){

        return DataHolder.getEvents();
    }

    public List<Event> searchEvents(String text, Double minRating){
        return DataHolder.getEvents().stream()
                .filter(e -> (text == null || e.getName().contains(text) || e.getDescription().contains(text)) &&
                        (minRating == null || e.getPopularityScore() >= minRating) )
                .collect(Collectors.toList());    }

//    public List<Event> searchEventsByCategory(Category category){
//        return DataHolder.getEvents().stream()
//                .filter(e -> e.getCategory().equals(category))
//                .toList();
//    }

    public Optional<Event> save(String name, String description, double popularityScore,  Location location, int tickets) {
        Event event = new Event(name, description, popularityScore, location, tickets);
        DataHolder.getEvents().add(event);
        return Optional.of(event);

    }

    public void deleteById(long id){
        DataHolder.getEvents().removeIf(e -> e.getId().equals(id));
    }

    public Optional<Event> findById(long id){
        return DataHolder.getEvents().stream().filter(e -> e.getId() == id).findFirst();
    }

    public Optional<Event> edit(Long id, String name, String description, double popularityScore, Location location, int tickets) throws EventNotFoundException {
        Event oldEvent = findById(id).orElseThrow(()-> new EventNotFoundException(id));
        oldEvent.setName(name);
        oldEvent.setDescription(description);
        oldEvent.setPopularityScore(popularityScore);
        oldEvent.setLocation(location);
        oldEvent.setTickets(tickets);
        return Optional.of(oldEvent);
    }

    public Optional<Event> changeRating(long id) throws EventNotFoundException {
        Event oldEvent = findById(id).orElseThrow(() -> new EventNotFoundException(id));

        oldEvent.setPopularityScore(oldEvent.getPopularityScore() + 1);

        return Optional.of(oldEvent);
    }

}
