//package mk.ukim.finki.mk.lab.web;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import mk.ukim.finki.mk.lab.model.Event;
//import mk.ukim.finki.mk.lab.service.EventBookingService;
//import mk.ukim.finki.mk.lab.service.EventService;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring6.SpringTemplateEngine;
//import org.thymeleaf.web.IWebExchange;
//import org.thymeleaf.web.servlet.JakartaServletWebApplication;
//
//import java.io.IOException;
//import java.util.List;
//
//
//@WebServlet(name = "event-list-servlet", urlPatterns = "/event-list-servlet")
//public class EventListServlet extends HttpServlet {
//
//    private final EventService eventService;
//    private final EventBookingService eventBookingService;
//    private final SpringTemplateEngine springTemplateEngine;
//
//    public EventListServlet(EventService eventService, EventBookingService eventBookingService, SpringTemplateEngine springTemplateEngine) {
//        this.eventService = eventService;
//        this.eventBookingService = eventBookingService;
//        this.springTemplateEngine = springTemplateEngine;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        IWebExchange webExchange = JakartaServletWebApplication
//                .buildApplication(getServletContext())
//                .buildExchange(req, resp);
//
//        String searchTxt = req.getParameter("searchTxt");
//        String rating = req.getParameter("rating");
//
//        String category = req.getParameter("category");
//
//        Double minRating = rating != null && !rating.isEmpty() ? Double.valueOf(rating) : null;
//
//        List<Event> events;
//        if (((searchTxt != null) && !searchTxt.isEmpty()) || minRating != null) {
//            events = eventService.searchEvents(searchTxt, minRating);
//        } else {
//            events = eventService.listAll();
//        }
//        WebContext context = new WebContext(webExchange);
//        context.setVariable("events", events);
//
//        springTemplateEngine.process("listEvents.html", context, resp.getWriter());
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.sendRedirect("/eventBooking");
//    }
//}
