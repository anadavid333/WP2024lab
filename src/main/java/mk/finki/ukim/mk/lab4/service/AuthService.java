package mk.finki.ukim.mk.lab4.service;

import mk.finki.ukim.mk.lab4.model.User;

public interface AuthService {
    User login(String username, String password);
}