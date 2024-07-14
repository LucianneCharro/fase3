package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EspecialidadeSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Given("the system has especialidades")
    public void the_system_has_especialidades() {
    }

    @When("the client calls {string}")
    public void the_client_calls(String url) {
        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(int statusCode) {
        assertEquals(statusCode, response.getStatusCodeValue());
    }}