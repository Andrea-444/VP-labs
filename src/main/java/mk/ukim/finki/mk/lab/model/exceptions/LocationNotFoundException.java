package mk.ukim.finki.mk.lab.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class LocationNotFoundException extends Exception {
    public LocationNotFoundException(Long id) {
        super(String.format("Location %s not found", id));
    }
}
