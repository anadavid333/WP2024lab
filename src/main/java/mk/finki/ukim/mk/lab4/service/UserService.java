package mk.finki.ukim.mk.lab4.service;

import mk.finki.ukim.mk.lab4.model.User;
import mk.finki.ukim.mk.lab4.model.enumarations.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, String surname, Role role);
}