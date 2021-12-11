import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;
import utilities.Utilities;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LandingServeletTest {
    private String url = "https://61ec-67-169-155-8.ngrok.io/landing";

    @Test
    // Test valid xhtml file of get request when user not logged in
    public void testGetLanding() {
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
    // Test valid xhtml page of get all events request when user already logged in
    public void testContentOfGetLanding() {
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
    // Test valid xhtml page of get all events request when user already logged in
    public void testHeaderGetLanding() {
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder(new URI(url)).setHeader("cookie", Utilities.session);
            HttpRequest request = builder.GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String resBody = response.body();
            int idx = resBody.indexOf("You");
            int endIdx = resBody.indexOf("</h1>");
            String actual = resBody.substring(idx, endIdx);
            String expected = "You have already been authenticated";
            assertEquals(expected, actual);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    // Test status code of a get all events request when user logged in
    public void testStatusCodeUserLoggedIn() {
        try {
            HttpRequest.Builder builder = HttpRequest.newBuilder(new URI(url)).setHeader("cookie", Utilities.session);
            HttpRequest request = builder.GET()
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();
            assertEquals(HttpStatus.OK_200, statusCode);
        } catch (IOException | URISyntaxException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
