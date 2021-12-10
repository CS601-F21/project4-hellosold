import editProfile.EditProfileServlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import utilities.Utilities;

import java.io.IOException;
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


public class EditProfileServletTest {
    private static String url = "https://61ec-67-169-155-8.ngrok.io/editprofile";

    @Test
    // Test valid xhtml file of a get request without user log in
    public void testGetNotLoggedIn() {
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
    // Test status code of a get request without user log in
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
    // Test valid xhtml file of a get request when user already logged in
    public void testGetWithLoggedIn() {
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder(new URI(url)).setHeader("cookie", Utilities.session);
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
    // Test correctly change user name
    public void testEditCorrectly() {
        try {
            String expected = "Li Liu";
            Map<String, String> data = new HashMap<>();
            data.put("id", "2");
            data.put("name", "lucy");
            data.put("email", "lliu78@dons.usfca.edu");

            // Use Mockito library to create mocked request and response
            HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
            HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
            // We also need a mocked context to return the data structure
            ServletContext context = Mockito.mock(ServletContext.class);

            // set up the mocked request to return a correct parameter value
            when(mockedRequest.getParameter("name")).thenReturn(expected);
            // set the mocked request to return the mocked context
            when(mockedRequest.getServletContext()).thenReturn(context);
            // set up the mocked context to return the data structure
            when(context.getAttribute("data")).thenReturn(data);

            // Execute the mocked request
            EditProfileServlet ts = new EditProfileServlet();
            ts.doPost(mockedRequest, mockedResponse);
            // The mocked request returned null for the parameter value
            // so no data should have been added to the data structure.
            Assertions.assertEquals(data.get("name"), expected);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    // Test status code of changing user name
    public void testStatusCodeOfEditCorrectly() {
        try {
            String expected = "Li Liu";
            Map<String, String> data = new HashMap<>();
            data.put("id", "2");
            data.put("name", "lucy");
            data.put("email", "lliu78@dons.usfca.edu");

            // Use Mockito library to create mocked request and response
            HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
            HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
            // We also need a mocked context to return the data structure
            ServletContext context = Mockito.mock(ServletContext.class);

            // set up the mocked request to return a correct parameter value
            when(mockedRequest.getParameter("name")).thenReturn(expected);
            // set the mocked request to return the mocked context
            when(mockedRequest.getServletContext()).thenReturn(context);
            // set up the mocked context to return the data structure
            when(context.getAttribute("data")).thenReturn(data);

            // Execute the mocked request
            EditProfileServlet ts = new EditProfileServlet();
            ts.doPost(mockedRequest, mockedResponse);
            verify(mockedResponse).setStatus(HttpStatus.OK_200);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}