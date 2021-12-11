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
}
