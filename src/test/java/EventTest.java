import events.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private static Event event;
    @BeforeEach
    public void startUp() {
        event = new Event();
    }


    @Test
    public void testGetId() {
        event.setId(1);
        int except = 1;
        int actual = event.getId();
        assertEquals(except, actual);
    }

    @Test
    public void testGetUserId() {
        event.setUserId(2);
        int except = 2;
        int actual = event.getUserId();
        assertEquals(except, actual);
    }

    @Test
    public void testGetTitle() {
        event.setTitle("Hello From 2021");
        String except = "Hello From 2021";
        String actual = event.getTitle();
        assertEquals(except, actual);
    }

    @Test
    public void testGetSponsor() {
        event.setSponsor("Lucy Tian");
        String except = "Lucy Tian";
        String actual = event.getSponsor();
        assertEquals(except, actual);
    }

    @Test
    public void testGetDescription() {
        event.setDescription("Have Fun");
        String except = "Have Fun";
        String actual = event.getDescription();
        assertEquals(except, actual);
    }

    @Test
    public void testGetDate() {
        Date date = Date.valueOf("2021-12-10");
        event.setDate(date);
        java.util.Date actual = event.getDate();
        assertEquals(date, actual);
    }

    @Test
    public void testGetTime() {
        Time time = Time.valueOf("20:00:00");
        event.setTime(time);
        Time actual = event.getTime();
        assertEquals(time, actual);
    }

    @Test
    public void testGetPlace() {
        event.setPlace("Fremont");
        String except = "Fremont";
        String actual = event.getPlace();
        assertEquals(except, actual);
    }

    @Test
    public void testGetTickets() {
        event.setTickets(4);
        int actual = event.getTickets();
        assertEquals(4, actual);
    }

    @Test
    public void testGetImagePath() {
        event.setImagePath("image.jpeg");
        String except = "image.jpeg";
        String actual = event.getImagePath();
        assertEquals(except, actual);
    }

}