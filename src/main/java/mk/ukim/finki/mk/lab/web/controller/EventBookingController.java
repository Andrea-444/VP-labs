package mk.ukim.finki.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.mk.lab.model.EventBooking;
import mk.ukim.finki.mk.lab.service.EventBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/eventBooking")
public class EventBookingController {

    private final EventBookingService eventBookingService;

    public EventBookingController(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }

    @GetMapping
    public String getBookingPage() {
        return "bookingConfirmation";
    }

    @PostMapping
    public String placeBooking(@RequestParam Long eventId,
                                @RequestParam String eventName,
                               @RequestParam String attendeeName,
                               @RequestParam String attendeeAddress,
                               @RequestParam int numTickets,
                               HttpServletRequest request,
                               Model model) {
        String clientIp = request.getRemoteAddr();
        model.addAttribute("clientIp", clientIp);

        EventBooking booking = eventBookingService.placeBooking(eventId,eventName, attendeeName, attendeeAddress, numTickets);
        model.addAttribute("booking", booking);

        return "bookingConfirmation";
    }

}
