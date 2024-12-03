package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import mk.ukim.finki.wp.lab.service.EventBookingService;
import mk.ukim.finki.wp.lab.service.impl.EventServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import org.springframework.stereotype.Component;
//za sekoj nastan da moze da se dodade categorija i da moze da se bara spored taa kategorija(kako klasa)
import java.io.IOException;

@WebServlet(name = "EventBookingServlet", urlPatterns = {"/eventBooking"})
@Component
public class EventBookingServlet extends HttpServlet {

    private final EventServiceImpl eventService;
    private final SpringTemplateEngine springTemplateEngine;

    public EventBookingServlet(EventServiceImpl eventService, SpringTemplateEngine springTemplateEngine) {
        this.eventService = eventService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventName = req.getParameter("eventName");
        String numTickets = req.getParameter("numTickets");
        String clientName = req.getParameter("clientName");
        String clientIpAddress = req.getRemoteAddr();

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(iWebExchange);
        context.setVariable("eventName", eventName);
        context.setVariable("numTickets", numTickets);
        context.setVariable("clientName", clientName);
        context.setVariable("clientIpAddress", clientIpAddress);

        resp.setContentType("text/html");
        springTemplateEngine.process("bookingConfirmation.html", context, resp.getWriter());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/event"); //ako go pristame so /booking go redirektira na osnovnata /event
    }
}