package tickets;

/**
 * This is class is used to store transfer tickets servlet.
 *
 * @author Li Liu
 */
public class TransferTicket {
    private String toUser;
    private String eventTitle;
    private int num;

    /**
     * Construction
     * @param toUser to user
     * @param eventTitle event title
     * @param num num of tickets to transfer
     */
    public TransferTicket(String toUser, String eventTitle, int num) {
        this.toUser = toUser;
        this.eventTitle = eventTitle;
        this.num = num;
    }

    /**
     * Getter of toUser
     * @return toUser
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * Setter od toUser.
     * @param toUser toUser
     */
    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    /**
     * Event title.
     * @return event title
     */
    public String getEventTitle() {
        return eventTitle;
    }

    /**
     * Setter of event title.
     * @param eventTitle event title
     */
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    /**
     * Getter of num of tickets.
     * @return num of tickets
     */
    public int getNum() {
        return num;
    }

    /**
     * Setter of num of tickets.
     * @param num num
     */
    public void setNum(int num) {
        this.num = num;
    }
}
