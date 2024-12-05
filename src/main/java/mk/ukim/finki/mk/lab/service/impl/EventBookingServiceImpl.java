package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.repository.InMemoryEventBookingRepository;
import mk.ukim.finki.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    InMemoryEventBookingRepository eventBookingRepository;

    public EventBookingServiceImpl(InMemoryEventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public EventBooking placeBooking(Long eventId, String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return eventBookingRepository.placeBooking(eventId, eventName, attendeeName, attendeeAddress, numberOfTickets);
    }
}