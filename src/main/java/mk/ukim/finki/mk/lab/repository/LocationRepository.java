package mk.ukim.finki.mk.lab.repository;

import mk.ukim.finki.mk.lab.bootstrap.DataHolder;
import mk.ukim.finki.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class LocationRepository {
    public List<Location> findAll() {
        return DataHolder.getLocations();
    }

    public Optional<Location> findById(Long id) {
        return DataHolder.getLocations().stream().filter(location -> Objects.equals(location.getId(), id)).findFirst();
    }

}
