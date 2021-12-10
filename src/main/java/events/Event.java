package events;

import java.sql.Time;
import java.util.Date;

/**
 * This class is used to store events information.
 *
 * @author Li Liu
 */
public class Event {
    private int id;
    private int userId;
    private String title;
    private String sponsor;
    private String description;
    private Date date;
    private Time time;
    private String place;
    private int tickets;
    private String imagePath;

    /** Constructor. */
    public Event() {
    }

    /**
     * Constructor.
     * @param id event id
     * @param title title
     * @param sponsor host
     * @param description description
     * @param date date
     * @param time time
     * @param place place
     */
    public Event(int id, int userId, String title, String sponsor, String description, Date date, Time time,
                 String place,
                 int tickets) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.sponsor = sponsor;
        this.description = description;
        this.date = date;
        this.time = time;
        this.place = place;
        this.tickets = tickets;
    }

    /**
     * Getter of sponsor.
     * @return sponsor
     */
    public String getSponsor() {
        return sponsor;
    }

    /**
     * Getter of description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter of date.
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter of time.
     * @return time
     */
    public Time getTime() {
        return time;
    }

    /**
     * Getter of tickets
     * @return tickets
     */
    public int getTickets() {
        return tickets;
    }

    /**
     * Getter of place.
     * @return place
     */
    public String getPlace() {
        return place;
    }

    /**
     * Getter of title.
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter of title.
     * @param title title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Setter of sponsor.
     * @param sponsor sponsor
     */
    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    /**
     * Setter of description.
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter of date.
     * @param date date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Setter of time.
     * @param time time
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * Setter of place.
     * @param place place
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Setter of id.
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Setter of tickets.
     * @param tickets num of tickets
     */
    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    /**
     * Getter of id.
     * @return id
     */
    public int getId() {
        return id;
    }
    /**
     * Getter of image path.
     * @return path
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Setter of image path.
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
