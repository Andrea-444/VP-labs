package mk.ukim.finki.mk.lab.service.impl;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.repository.EventBookingRepository;
import mk.ukim.finki.mk.lab.repository.EventRepository;
import mk.ukim.finki.mk.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    EventBookingRepository eventBookingRepository;

    public EventBookingServiceImpl(EventBookingRepository eventBookingRepository) {
        this.eventBookingRepository = eventBookingRepository;
    }

    @Override
    public EventBooking placeBooking(Long eventId, String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        return eventBookingRepository.placeBooking(eventId, eventName, attendeeName, attendeeAddress, numberOfTickets);
    }
}