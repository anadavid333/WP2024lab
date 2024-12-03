package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Event;
import mk.ukim.finki.wp.lab.service.EventService;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name ="eventListServlet", urlPatterns = "/event")
@Component
public class EventListSevlet extends HttpServlet {

    private final EventService eventService;//za komunikacija so biznis layer
    private final SpringTemplateEngine springTemplateEngine;

    public EventListSevlet(EventService eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        // da se inicijalizira userviews koga se inicijalizira servletot
        getServletContext().setAttribute("userViews", 0);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        List<Event> events = eventService.listAll();

        //search del
        String searchname=req.getParameter("searchname");
        String rating=req.getParameter("rating");
        String category=req.getParameter("category");
        if (searchname != null || rating != null) {

            double minRatingValue;
            if (rating != null && !rating.isEmpty()) {
                minRatingValue = Double.parseDouble(rating);//ako ima vneseno rejtingkonvertiraj go vo double
            } else {
                minRatingValue = 0.0;
            }

            events = events.stream()
                    .filter(event -> (searchname == null || searchname.isEmpty()
                            || event.getName().toLowerCase().contains(searchname.toLowerCase()))
                            && (rating == null || rating.isEmpty() || event.getPopularityScore() >= minRatingValue)
                    )
                    .collect(Collectors.toList());
        }


        context.setVariable("events", events);
        context.setVariable("searchname",searchname);
        context.setVariable("rating",rating);
        context.setVariable("category",category);



        //prvo treb da se inicijalizira userviews atributot
        Integer userViews=(Integer) getServletContext().getAttribute("userViews");
        if (userViews ==null) {
            userViews = 0;
        }


        getServletContext().setAttribute("userViews",userViews + 1);
        context.setVariable("userViews",userViews + 1);

        springTemplateEngine.process("listEvents.html",context,resp.getWriter());
    }
}