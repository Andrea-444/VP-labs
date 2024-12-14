package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(String id);
}
