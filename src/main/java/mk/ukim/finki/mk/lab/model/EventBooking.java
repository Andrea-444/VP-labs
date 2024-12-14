package mk.ukim.finki.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name = "event_booking")
@NoArgsConstructor
public class EventBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eventName;
    private String attendeeName;
    private String attendeeAddress;
    private Long numberOfTickets;
    @ManyToOne
    private User user;

    public EventBooking(String eventName, String attendeeName, String attendeeAddress, Long numberOfTickets, User user) {
        this.eventName = eventName;
        this.attendeeName = attendeeName;
        this.attendeeAddress = attendeeAddress;
        this.numberOfTickets = numberOfTickets;
        this.user = user;
    }
}
