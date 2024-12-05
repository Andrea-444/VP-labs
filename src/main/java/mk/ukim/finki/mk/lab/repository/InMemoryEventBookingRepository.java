package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryEventBookingRepository {
    public Optional<Event> findById(long id){
        return DataHolder.getEvents().stream().filter(e -> e.getId() == id).findFirst();
    }

    public EventBooking placeBooking(Long eventId, String eventName, String attendeeName, String attendeeAddress, int numberOfTickets){
        Event event = findById(eventId).get();
        if(event.getTickets()>=numberOfTickets) {
            event.setTickets(event.getTickets() - numberOfTickets);
        }
        else{
            throw new IllegalArgumentException("Nema dovolno karti!");
        }
        return new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
    }
}
