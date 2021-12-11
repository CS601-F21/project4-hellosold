import events.Event;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tickets.Ticket;

import java.sql.Date;
import java.sql.Time;

import static org.junit.Assert.assertEquals;

public class TicketTest {
    private static Ticket ticket;

    @BeforeEach
    public void startUp() {
        Event event = new Event(1, 2, "Hello From 2021", "Tina Tian", "Have Fun", Date.valueOf("2021-12-10"),
                Time.valueOf("20:20:00"), "Fremont", 2);
        ticket = new Ticket(event, 3);
    }

    @Test
    public void testTickets() {
        int numTickets = ticket.getTickets();
        int expect = 3;
        assertEquals(expect, numTickets);
    }

    @Test
    public void testGetTitle() {
        String title = ticket.getEvent().getTitle();
        String expect = "Hello From 2021";
        assertEquals(expect, title);
    }

    @Test
    public void testGetSponsor() {
        String sponsor = ticket.getEvent().getSponsor();
        String expect = "Tina Tian";
        assertEquals(expect, sponsor);
    }

    @Test
    public void testGetDescription() {
        String description = ticket.getEvent().getDescription();
        String expect = "Have Fun";
        assertEquals(expect, description);
    }

    @Test
    public void testGetDate() {
        java.util.Date date = ticket.getEvent().getDate();
        Date expect = Date.valueOf("2021-12-10");
        assertEquals(expect, date);
    }

    @Test
    public void testGetTime() {
        Time time = ticket.getEvent().getTime();
        Time expect = Time.valueOf("20:20:00");
        assertEquals(expect, time);
    }


}
