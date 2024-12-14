package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Event {
//    @Column(unique = true)
    String name;
    String description;
    double popularityScore;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @OneToMany

    @ManyToOne
//    @JoinColumn(unique = true)
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
//        this.id = (long) (Math.random() * 1000);
        this.location = location;
        this.tickets = tickets;

    }

}
