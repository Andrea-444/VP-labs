package mk.ukim.finki.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import mk.ukim.finki.mk.lab.model.Event;
import mk.ukim.finki.mk.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    @Getter
    public static List<Event> events = new ArrayList<>();
    @Getter
    public static List<Location>locations = new ArrayList<>();

    @PostConstruct
    public void init() {
        locations.add(new Location("Skopje", "Sk1", "100", "Opera i balet"));
        locations.add(new Location("Prilep", "Pp1", "10", "City Hall"));
        locations.add(new Location("Minsk", "Mn1", "200", "Concert Hall"));
        locations.add(new Location("Vienna", "Vn1", "40", "Arena"));
        locations.add(new Location("Milan", "Mi1", "60", "Movie Hall"));

        events.add(new Event("Opera - Skopje","Carmen",10,  locations.get(0),15));
        events.add(new Event("Concert - Minsk","Nkeeei",9.5, locations.get(2),20));
        events.add(new Event("Ballet - Skopje","Swan lake",3,  locations.get(0),30));
        events.add(new Event("Festival - Berlin","Octoberfest",4.5, locations.get(1),25));
        events.add(new Event("Concert - Vienna","Harry Styles",5.6, locations.get(3),50));
        events.add(new Event("Concert - Skopje","Funk Shui",6, locations.get(3),40));
        events.add(new Event("Opera - Sofia","Tosca",7.9, locations.get(0),45));
        events.add(new Event("Movie premiere - Los Angeles","Avatar",8.4, locations.get(4),55));
        events.add(new Event("Movie premiere - Milan","Whiplash",9, locations.get(4),20));
        events.add(new Event("Festival - Prilep","Beerfest",3.6, locations.get(1),15));
    }

}