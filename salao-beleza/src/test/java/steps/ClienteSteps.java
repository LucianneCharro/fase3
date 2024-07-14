package steps;

import fiap.salaobeleza.model.Cliente;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Given("que eu quero criar um novo cliente com os dados necessários")
    public void que_eu_quero_criar_um_novo_cliente_com_os_dados_necessarios() {
    }

    @When("eu faço uma requisição POST para {string} com os dados do cliente")
    public void eu_faco_uma_requisicao_POST_para_com_os_dados_do_cliente(String url) {
        Cliente cliente = new Cliente();
        cliente.setNome("Lucianne");
        cliente.setEmail("lballico@gmail.com");
        cliente.setTelefone("99999-7777");

        response = restTemplate.postForEntity(url, cliente, String.class);
    }

    @Then("o cliente deve ser criado e a resposta deve ser os detalhes do cliente criado")
    public void o_cliente_deve_ser_criado_e_a_resposta_deve_ser_os_detalhes_do_cliente_criado() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("Lucianne"));
    }
}