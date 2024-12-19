package mk.ukim.finki.mk.lab.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "app_users")
public class User {

    @Id
    private String username;

    private String name;

    @OneToMany
    private List<EventBooking> bookings;

    public User(String username, String name) {
        this.username = username;
        this.name = name;
        this.bookings = new ArrayList<>();
    }

}

