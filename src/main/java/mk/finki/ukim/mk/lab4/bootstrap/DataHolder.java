package mk.finki.ukim.mk.lab4.bootstrap;

import jakarta.annotation.PostConstruct;

import mk.finki.ukim.mk.lab4.model.Category;
import mk.finki.ukim.mk.lab4.model.Event;
import mk.finki.ukim.mk.lab4.model.Location;
import mk.finki.ukim.mk.lab4.model.User;
import mk.finki.ukim.mk.lab4.model.enumarations.Role;
import mk.finki.ukim.mk.lab4.repository.jpa.EventRepository;
import mk.finki.ukim.mk.lab4.repository.jpa.LocationRepository;
import mk.finki.ukim.mk.lab4.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab4.repository.jpa.CategoryRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// In-memory data holder
@Component
public class DataHolder {
    public static List<User> users = null;
    public static List<Location> locations=null;
    public static List<Category> categories=null;
    public static List<Event> eventList;

    private final CategoryRepository categoryRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    private final PasswordEncoder passwordEncoder;

    public DataHolder(CategoryRepository categoryRepository, UserRepository userRepository,PasswordEncoder passwordEncoder,LocationRepository locationRepository,EventRepository eventRepository) {
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.locationRepository=locationRepository;
        this.eventRepository = eventRepository;
    }

    // On application startup, initialize the categories list
    // On each startup, the list will be initialized with the same values and the previous values will be lost
    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        locations= new ArrayList<>();
        categories=new ArrayList<>();
        eventList=new ArrayList<>();
        if (this.userRepository.count() == 0) {
            users.add(new User("novica", passwordEncoder.encode("nc"), "Elena", "Atanasoska", Role.ROLE_USER));
            users.add(new User("darko.sasanski", passwordEncoder.encode("ds"), "Darko", "Sasanski", Role.ROLE_USER));
            users.add(new User("ana.todorovska", passwordEncoder.encode("at"), "Ana", "Todorovska", Role.ROLE_USER));
            users.add(new User("admin", passwordEncoder.encode("admin"), "admin", "admin", Role.ROLE_ADMIN));
            this.userRepository.saveAll(users);
        }
        if(locationRepository.count()==0){
            locations.add(new Location("AAAA","200","BBBB","DAOSDASKODK"));
            locations.add(new Location("FSDFDS","120","FASDFDS","DASDSADD"));
            locationRepository.saveAll(locations);
        }
        if(categoryRepository.count()==0){
            categories.add(new Category("Festival"));
            categories.add(new Category("Panagur"));
            categoryRepository.saveAll(categories);
        }
        if(eventRepository.count()==0){
            Event tmp= new Event("event 1","doma mi",30.00);
            tmp.setCategory(categories.get(0));
            tmp.setLocation(locations.get(0));
            eventList.add(tmp);
            eventRepository.saveAll(eventList);
        }
    }
}