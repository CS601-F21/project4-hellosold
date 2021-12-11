import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tickets.TransferTicket;

import static org.junit.Assert.assertEquals;

public class TransferTicketTest {

    private static TransferTicket transferTicket;
    @BeforeEach
    public void startUp() {
        transferTicket = new TransferTicket("John", "Music Show", 5);
    }

    @Test
    public void testGetToUser() {
        String toUser = "John";
        String actual = transferTicket.getToUser();
        assertEquals(toUser, actual);
    }

    @Test
    public void testGetNum() {
        int num = 5;
        int actual = transferTicket.getNum();
        assertEquals(num, actual);
    }

    @Test
    public void setNum() {
        transferTicket.setNum(6);
        int actual = transferTicket.getNum();
        assertEquals(6, actual);
    }

    @Test
    public void setToUser() {
        transferTicket.setToUser("Lily");
        String actual = transferTicket.getToUser();
        assertEquals("Lily", actual);
    }


}
