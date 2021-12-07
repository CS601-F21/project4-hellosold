package tickets;

import events.Event;

/**
 * Ticket class.
 *
 * @autor Li Liu
 */
public class Ticket {
    private Event event; // event object
    private int tickets; // number of tickets that the user has purchased

    /**
     * Constructor.
     * @param event event
     * @param tickets tickets
     */
    public Ticket(Event event, int tickets) {
        this.event = event;
        this.tickets = tickets;
    }

    /**
     * Getter of event.
     * @return event
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Setter of event.
     * @param event event
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Getter of tickets.
     * @return tickets
     */
    public int getTickets() {
        return tickets;
    }

    /**
     * setter of tickets.
     * @param tickets tickets
     */
    public void setTickets(int tickets) {
        this.tickets = tickets;
    }
}
