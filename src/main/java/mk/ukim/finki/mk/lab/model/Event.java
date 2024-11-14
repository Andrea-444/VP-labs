package mk.ukim.finki.mk.lab.model;

import lombok.Data;

@Data
public class Event {
    String name;
    String description;
    double popularityScore;
    private Long id;
    private Location location;
    int tickets;
//
//    public Event() {
//        this.id = (long) (Math.random() * 1000);
//    }

    public Event(String name, String description, double popularityScore,  Location location, int tickets) {
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.id = (long) (Math.random() * 1000);
        this.location = location;
        this.tickets = tickets;

    }

}
