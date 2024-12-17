package mk.finki.ukim.mk.lab4.service.impl;

import mk.finki.ukim.mk.lab4.model.User;
import mk.finki.ukim.mk.lab4.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab4.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).get();
    }
}