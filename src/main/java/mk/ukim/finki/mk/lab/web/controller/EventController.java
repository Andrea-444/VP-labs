package mk.ukim.finki.mk.lab.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.exceptions.EventNotFoundException;
import mk.ukim.finki.mk.lab.model.exceptions.LocationNotFoundException;
import mk.ukim.finki.mk.lab.service.EventService;
import mk.ukim.finki.mk.lab.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = {"/","/events"})
public class EventController {

    private final EventService eventService;
    private final LocationService locationService;

    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping()
    public String getEventsPage( @RequestParam(required = false) String searchText,
                                 @RequestParam(required = false) Double minRating,
                                 @RequestParam(required = false) Long locationId,
                                 @RequestParam(required = false) String error,
                                 Model model, HttpSession session) {
        List<Event> eventList = eventService.searchEvents(searchText, minRating, locationId);
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

//        @SuppressWarnings("unchecked")
//        Set<Long> likedEvents = (Set<Long>) session.getAttribute("likedEvents");
//        if (likedEvents == null) {
//            likedEvents = new HashSet<>();
//        }

        model.addAttribute("searchText", searchText);
        model.addAttribute("minRating", minRating);
        model.addAttribute("selectedLocationId", locationId);
        model.addAttribute("locations", locationService.findAll());
        model.addAttribute("events", eventList);
        model.addAttribute("error", error);
//        model.addAttribute("likedEvents", likedEvents);
        return "listEvents";
    }

    @PostMapping("/add")
    public String saveEvent(@RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationId,
                            @RequestParam double ticketsAvailable,
                            Model model) throws LocationNotFoundException, EventNotFoundException {

        try {
            eventService.saveEvent(name, description, popularityScore, locationId, (int)ticketsAvailable);
            return "redirect:/events";
        } catch (IllegalArgumentException | LocationNotFoundException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("locations", locationService.findAll());
            return "add-event";
        }
    }

    @PostMapping("/edit/{eventId}")
    public String editEvent(@PathVariable Long eventId, @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam double popularityScore,
                            @RequestParam Long locationId,
                            @RequestParam double ticketsAvailable, Model model) throws EventNotFoundException, LocationNotFoundException {

        try {
            eventService.editEvent(eventId, name, description, popularityScore, locationId, (int)ticketsAvailable);
            return "redirect:/events";
        } catch (IllegalArgumentException | EventNotFoundException | LocationNotFoundException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            model.addAttribute("locations", locationService.findAll());
            return "add-event";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }

    @GetMapping("/add-form")
    public String getAddEventPage(Model model) {
        model.addAttribute("locations", locationService.findAll());
        return "add-event";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditEventPage(@PathVariable Long id, Model model) {
        if (this.eventService.findEventById(id).isPresent()) {
            Event event = this.eventService.findEventById(id).get();
            model.addAttribute("locations", locationService.findAll());
            model.addAttribute("event", event);
            return "add-event";
        } else {
            return "redirect:/events?error=EventNotFound";
        }

    }

//    @PostMapping("/liked")
//    public String likeEvent(@RequestParam Long id, Model model, HttpSession session) throws EventNotFoundException {
//
//        @SuppressWarnings("unchecked")
//        Set<Long> likedEvents = (Set<Long>) session.getAttribute("likedEvents");
//        if (likedEvents == null) {
//            likedEvents = new HashSet<>();
//        }
//
//        likedEvents.add(id);
//        session.setAttribute("likedEvents", likedEvents);
//        this.eventService.changePopScore(id);
//        return "redirect:/events";
//    }

}
