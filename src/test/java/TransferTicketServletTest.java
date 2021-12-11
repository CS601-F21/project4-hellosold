
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import org.mockito.Mockito;
import tickets.TransferTicketServlet;
import utilities.Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TransferTicketServletTest {

    private String url = "https://61ec-67-169-155-8.ngrok.io/tickets";

    @Test
    // Test valid xhtml file of get request when user not logged in
    public void testGetRequest() {
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
    // Test transfer a ticket without enough tickets.
    public void testPostRequest() {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("id", "2");
            data.put("name", "lucy");
            data.put("email", "lliu78@dons.usfca.edu");

            // Use Mockito library to create mocked request and response
            HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
            HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
            // write output to textfile
            PrintWriter writer = new PrintWriter("somefile.txt");
            when(mockedResponse.getWriter()).thenReturn(writer);
            // We also need a mocked context to return the data structure
            ServletContext context = Mockito.mock(ServletContext.class);

            // set up the mocked request to return a correct parameter value
            when(mockedRequest.getParameter("name")).thenReturn("Sold Li");
            when(mockedRequest.getParameter("email")).thenReturn("hellosold@gmail.com");
            when(mockedRequest.getParameter("num")).thenReturn("1");
            when(mockedRequest.getParameter("title")).thenReturn("hello");
            // set the mocked request to return the mocked context
            when(mockedRequest.getServletContext()).thenReturn(context);
            // set up the mocked context to return the data structure
            when(context.getAttribute("data")).thenReturn(data);


            // Execute the mocked request
            TransferTicketServlet tts = new TransferTicketServlet();
            tts.doPost(mockedRequest, mockedResponse);
            writer.flush();

            //check output
            String rs = readFromFile("somefile.txt");
            String expect = "<p>There aren't enough tickets.</p>\n";
            assertEquals(expect, rs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPostRequestStatusCode() {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("id", "2");
            data.put("name", "lucy");
            data.put("email", "lliu78@dons.usfca.edu");

            // Use Mockito library to create mocked request and response
            HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
            HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
            // write output to textfile
            PrintWriter writer = new PrintWriter("somefile.txt");
            when(mockedResponse.getWriter()).thenReturn(writer);
            // We also need a mocked context to return the data structure
            ServletContext context = Mockito.mock(ServletContext.class);

            // set up the mocked request to return a correct parameter value
            when(mockedRequest.getParameter("name")).thenReturn("Sold Li");
            when(mockedRequest.getParameter("email")).thenReturn("hellosold@gmail.com");
            when(mockedRequest.getParameter("num")).thenReturn("1");
            when(mockedRequest.getParameter("title")).thenReturn("hello");
            // set the mocked request to return the mocked context
            when(mockedRequest.getServletContext()).thenReturn(context);
            // set up the mocked context to return the data structure
            when(context.getAttribute("data")).thenReturn(data);


            // Execute the mocked request
            TransferTicketServlet tts = new TransferTicketServlet();
            tts.doPost(mockedRequest, mockedResponse);
            writer.flush();

            //check output
            verify(mockedResponse).setStatus(HttpStatus.OK_200);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    // Test transfer a ticket with enough tickets.
    public void testPostRequest2() {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("id", "2");
            data.put("name", "lucy");
            data.put("email", "lliu78@dons.usfca.edu");

            // Use Mockito library to create mocked request and response
            HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
            HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
            // write output to textfile
            PrintWriter writer = new PrintWriter("somefile.txt");
            when(mockedResponse.getWriter()).thenReturn(writer);
            // We also need a mocked context to return the data structure
            ServletContext context = Mockito.mock(ServletContext.class);

            // set up the mocked request to return a correct parameter value
            when(mockedRequest.getParameter("name")).thenReturn("Sold Li");
            when(mockedRequest.getParameter("email")).thenReturn("hellosold@gmail.com");
            when(mockedRequest.getParameter("num")).thenReturn("1");
            when(mockedRequest.getParameter("title")).thenReturn("Lubelski at LVL 44 for the Happy Accidents album release tour!");
            // set the mocked request to return the mocked context
            when(mockedRequest.getServletContext()).thenReturn(context);
            // set up the mocked context to return the data structure
            when(context.getAttribute("data")).thenReturn(data);


            // Execute the mocked request
            TransferTicketServlet tts = new TransferTicketServlet();
            tts.doPost(mockedRequest, mockedResponse);
            writer.flush();

            //check output
            String rs = readFromFile("somefile.txt");
            int idx = rs.indexOf("Successfully");
            int eIdx = rs.indexOf("</h1>");
            String actual = rs.substring(idx, eIdx);
            String expect = "Successfully share tickets with friends!\n";
            assertEquals(expect, actual);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    // // Test transfer a ticket with enough tickets.
    public void testPostRequest2StatusCode() {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("id", "2");
            data.put("name", "lucy");
            data.put("email", "lliu78@dons.usfca.edu");

            // Use Mockito library to create mocked request and response
            HttpServletRequest mockedRequest = Mockito.mock(HttpServletRequest.class);
            HttpServletResponse mockedResponse = Mockito.mock(HttpServletResponse.class);
            // write output to textfile
            PrintWriter writer = new PrintWriter("somefile.txt");
            when(mockedResponse.getWriter()).thenReturn(writer);
            // We also need a mocked context to return the data structure
            ServletContext context = Mockito.mock(ServletContext.class);

            // set up the mocked request to return a correct parameter value
            when(mockedRequest.getParameter("name")).thenReturn("Sold Li");
            when(mockedRequest.getParameter("email")).thenReturn("hellosold@gmail.com");
            when(mockedRequest.getParameter("num")).thenReturn("1");
            when(mockedRequest.getParameter("title")).thenReturn("Live Music And Holiday Song Sing-Alongs!");
            // set the mocked request to return the mocked context
            when(mockedRequest.getServletContext()).thenReturn(context);
            // set up the mocked context to return the data structure
            when(context.getAttribute("data")).thenReturn(data);


            // Execute the mocked request
            TransferTicketServlet tts = new TransferTicketServlet();
            tts.doPost(mockedRequest, mockedResponse);
            writer.flush();

            //check status code
            verify(mockedResponse).setStatus(HttpStatus.OK_200);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (FileReader f = new FileReader(fileName);
             BufferedReader br = new BufferedReader(f);) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
        catch (IOException e) {
            System.out.println("No such file: " + fileName);
        }
        return null;
    }
}
