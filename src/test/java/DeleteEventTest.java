import events.GetAEventServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.mockito.Mockito;
import utilities.Utilities;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteEventTest {
    @Test
    // Test status code
    public void testPostRequestStatusCode() {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("id", "2");
            data.put("name", "lucy");
            data.put("email", "lliu78@dons.usfca.edu");

            // Use Mockito library to create mocked request and response
            HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
            HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
            // write output to text file
            PrintWriter writer = new PrintWriter("somefile.txt");
            when(mockedResponse.getWriter()).thenReturn(writer);
            // We also need a mocked context to return the data structure
            ServletContext context = Mockito.mock(ServletContext.class);

            // set up the mocked request to return a correct parameter value
            when(mockedRequest.getPathInfo()).thenReturn("/delete/10");
            // set the mocked request to return the mocked context
            when(mockedRequest.getServletContext()).thenReturn(context);
            // set up the mocked context to return the data structure
            when(context.getAttribute("data")).thenReturn(data);

            // Execute the mocked request
            GetAEventServlet ges = new GetAEventServlet();
            ges.doPost(mockedRequest, mockedResponse);
            writer.flush();

            // Verify status code
            verify(mockedResponse).setStatus(HttpStatus.OK_200);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    // Test status code
    public void testPostRequestValidXHtml() {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("id", "2");
            data.put("name", "lucy");
            data.put("email", "lliu78@dons.usfca.edu");

            // Use Mockito library to create mocked request and response
            HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
            HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
            // write output to text file
            PrintWriter writer = new PrintWriter("somefile.txt");
            when(mockedResponse.getWriter()).thenReturn(writer);
            // We also need a mocked context to return the data structure
            ServletContext context = Mockito.mock(ServletContext.class);

            // set up the mocked request to return a correct parameter value
            when(mockedRequest.getPathInfo()).thenReturn("/delete/10");
            // set the mocked request to return the mocked context
            when(mockedRequest.getServletContext()).thenReturn(context);
            // set up the mocked context to return the data structure
            when(context.getAttribute("data")).thenReturn(data);

            // Execute the mocked request
            GetAEventServlet ges = new GetAEventServlet();
            ges.doPost(mockedRequest, mockedResponse);
            writer.flush();

            // Verify status code
            String rs = TransferTicketServletTest.readFromFile("somefile.txt");
            boolean actual = Utilities.isValid(rs);
            assertTrue(actual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
