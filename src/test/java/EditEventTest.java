import editProfile.EditProfileServlet;
import events.AddEventServlet;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EditEventTest {
    private String url = "https://61ec-67-169-155-8.ngrok.io/events/edit/1";

    @Test
    // Test valid xhtml file of get request when user not logged in
    public void testGetEditEvent() {
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder(new URI(url));
            HttpRequest request = builder.GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String resBody = response.body();

            boolean actual = Utilities.isValid(resBody);
            assertTrue(actual);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    // Test status code of a valid get request when user not logged in
    public void testGetStatusCode() {
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder(new URI(url));
            HttpRequest request = builder.GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            assertEquals(HttpStatus.OK_200, statusCode);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    // Test valid xhtml page of a get request
    public void testGetValidXHtml() {
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder(new URI(url)).setHeader("cookie", Utilities.session);
            HttpRequest request = builder.GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String resBody = response.body();
            assertTrue(Utilities.isValid(resBody));
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    // Test valid xhtml page of a get request
    public void testGetStatusCodeLoggedIn() {
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder(new URI(url)).setHeader("cookie", Utilities.session);
            HttpRequest request = builder.GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            assertEquals(HttpStatus.OK_200, statusCode);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }

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
            when(mockedRequest.getParameter("title")).thenReturn("Hello");
            when(mockedRequest.getParameter("date")).thenReturn("2021-12-12");
            when(mockedRequest.getParameter("time")).thenReturn("12:30:40");
            when(mockedRequest.getParameter("place")).thenReturn("Fremont");
            when(mockedRequest.getParameter("ticket")).thenReturn("10");
            when(mockedRequest.getParameter("description")).thenReturn("Live Music And Holiday Song Sing-Alongs!");
            when(mockedRequest.getPathInfo()).thenReturn("/edit/10");
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
}
