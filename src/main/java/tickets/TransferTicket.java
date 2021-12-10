package tickets;

public class TransferTicket {
    private String toUser;
    private String eventTitle;
    private int num;

    public TransferTicket(String toUser, String eventTitle, int num) {
        this.toUser = toUser;
        this.eventTitle = eventTitle;
        this.num = num;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
