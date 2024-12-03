package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.model.Location;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class DataHolder {
    public static List<Event> eventList=new ArrayList<>();
    public static List<Location> locationList=new ArrayList<>();

   // @PostConstruct
    public void init(){
        locationList.add(new Location("City Park", "City Park Avenue", "10000", "Open Space"));
        locationList.add(new Location( "Youth Cultural Center", "13th November Quay", "800", "Cultural Center"));
        locationList.add(new Location( "Universal Hall", "Ilinden Boulevard", "3000", "Sports Arena"));
        locationList.add(new Location("City Fortress", "Samoilova Street", "5000", "Historical Monument"));
        locationList.add(new Location( "Boris Trajkovski Arena", "8th September Blvd", "60000", "Sports Arena"));


        eventList.add(new Event("PhotoGallery","Photo gallery event",7.3,locationList.get(1)));
        eventList.add(new Event("LocalFoodFair","Community gathering with food",7.8,locationList.get(2)));
        eventList.add(new Event("WeekendBookSale","Used book market ",6.4,locationList.get(3)));
        eventList.add(new Event("PetDayOut","Family-friendly pet adoption and activities event ",8.2,locationList.get(1)));
        eventList.add(new Event("MovieinthePark","Outdoor film screening with snack vendors",7.5,locationList.get(4)));
        eventList.add(new Event("CraftBeerTasting","Local brewery sampling event ",7.9,locationList.get(2)));
        eventList.add(new Event("HolidayCraftFair","Seasonal handmade gifts and decorations market",7.7,locationList.get(1)));
        eventList.add(new Event("SundayFarmersMarket","Weekly fresh produce and handmade goods market",8.0,locationList.get(2)));
        eventList.add(new Event("CommunityYardSale","Neighborhood-wide garage sale day ",7.0,locationList.get(4)));
        eventList.add(new Event("Kids Fun Day","Children's activities, games, and entertainment",8.3,locationList.get(3)));
    }
}
