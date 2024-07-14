 package steps;

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
public class ProfissionalSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Given("que eu quero ver todos os profissionais cadastrados")
    public void que_eu_quero_ver_todos_os_profissionais_cadastrados() {
    }

    @When("eu faço uma requisição GET para {string}")
    public void eu_faco_uma_requisicao_GET_para(String url) {
        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("a resposta deve ser uma lista de profissionais")
    public void a_resposta_deve_ser_uma_lista_de_profissionais() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("nome_do_profissional"));
    }

    @Given("que eu quero obter informações de um profissional específico pelo seu ID")
    public void que_eu_quero_obter_informacoes_de_um_profissional_especifico_pelo_seu_id() {
    }

    @Then("a resposta deve ser os detalhes do profissional correspondente")
    public void a_resposta_deve_ser_os_detalhes_do_profissional_correspondente() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("id_do_profissional"));
    }

    @Given("que eu quero criar um novo profissional com os dados necessários")
    public void que_eu_quero_criar_um_novo_profissional_com_os_dados_necessarios() {
    }

    @Then("o profissional deve ser criado e a resposta deve ser os detalhes do profissional criado")
    public void o_profissional_deve_ser_criado_e_a_resposta_deve_ser_os_detalhes_do_profissional_criado() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("nome_do_profissional_criado"));
    }

    @Given("que eu quero atualizar os dados de um profissional existente pelo seu ID")
    public void que_eu_quero_atualizar_os_dados_de_um_profissional_existente_pelo_seu_id() {
    }

    @Then("os dados do profissional devem ser atualizados e a resposta deve ser os detalhes do profissional atualizado")
    public void os_dados_do_profissional_devem_ser_atualizados_e_a_resposta_deve_ser_os_detalhes_do_profissional_atualizado() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("nome_do_profissional_atualizado"));
        assertTrue(response.getBody().contains("detalhe_especifico_atualizado"));
    }}