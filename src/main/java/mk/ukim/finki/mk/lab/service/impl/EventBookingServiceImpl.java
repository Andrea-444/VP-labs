package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.model.User;
//import mk.ukim.finki.mk.lab.repository.InMemoryEventBookingRepository;
import mk.ukim.finki.mk.lab.repository.jpa.BookingRepository;
import mk.ukim.finki.mk.lab.repository.jpa.UserRepository;
import mk.ukim.finki.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final UserRepository userRepository;
    BookingRepository eventBookingRepository;

    public EventBookingServiceImpl(BookingRepository eventBookingRepository, UserRepository userRepository) {
        this.eventBookingRepository = eventBookingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public EventBooking placeBooking(Long eventId, String eventName, String attendeeName, String attendeeAddress, int numberOfTickets,String username) {
//        EventBooking eventB = eventBookingRepository.findById(eventId).get();
//        if(event.getTickets()>=numberOfTickets) {
//            event.setTickets(event.getTickets() - numberOfTickets);
//        }
//        else{
//            throw new IllegalArgumentException("Nema dovolno karti!");
//        }
        User newUser = userRepository.findById(username)
                .orElse(new User(username, attendeeName));
        userRepository.save(newUser);

        EventBooking newBooking = new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets, newUser);
        return eventBookingRepository.save(newBooking);     }
}