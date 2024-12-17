package mk.finki.ukim.mk.lab4.service.impl;

import mk.finki.ukim.mk.lab4.model.User;
import mk.finki.ukim.mk.lab4.model.enumarations.Role;
import mk.finki.ukim.mk.lab4.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab4.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Role role) {

        User user = new User(username, passwordEncoder.encode(password), name, surname, role);

        return  userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}