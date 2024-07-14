package steps;

import fiap.salaobeleza.model.PacoteServicos;
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
public class PacoteServicosSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Given("que eu quero ver todos os pacotes de serviços cadastrados")
    public void que_eu_quero_ver_todos_os_pacotes_de_servicos_cadastrados() {
    }

    @When("eu faço uma requisição GET para {string}")
    public void eu_faco_uma_requisicao_GET_para(String url) {
        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("a resposta deve ser uma lista de pacotes de serviços")
    public void a_resposta_deve_ser_uma_lista_de_pacotes_de_servicos() {
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("lista_de_pacotes"));
    }

    @Given("que eu quero obter informações de um pacote de serviços específico pelo seu ID")
    public void que_eu_quero_obter_informacoes_de_um_pacote_de_servicos_especifico_pelo_seu_id() {
    }

    @When("eu faço uma requisição GET para \"/pacoteservicos/{id}\"")
    public void eu_faco_uma_requisicao_GET_para_pacoteservicos_id(String url) {
        response = restTemplate.getForEntity(url, String.class);
    }

    @Then("a resposta deve ser os detalhes do pacote de serviços correspondente")
    public void a_resposta_deve_ser_os_detalhes_do_pacote_de_servicos_correspondente() {
        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("detalhes_do_pacote"));
    }

    @Given("que eu quero criar um novo pacote de serviços com os dados necessários")
    public void que_eu_quero_criar_um_novo_pacote_de_servicos_com_os_dados_necessarios() {
    }

    @When("eu faço uma requisição POST para \"/pacoteservicos\" com os dados do pacote de serviços")
    public void eu_faco_uma_requisicao_POST_para_pacoteservicos_com_os_dados_do_pacote_de_servicos(PacoteServicos pacoteServicos) {
        response = restTemplate.postForEntity("/pacoteservicos", pacoteServicos, String.class);
    }

    @Then("o pacote de serviços deve ser criado e a resposta deve ser os detalhes do pacote de serviços criado")
    public void o_pacote_de_servicos_deve_ser_criado_e_a_resposta_deve_ser_os_detalhes_do_pacote_de_servicos_criado() {
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(response.getBody().contains("detalhes_do_pacote_criado"));
    }

    @Given("que eu quero atualizar os dados de um pacote de serviços existente pelo seu ID")
    public void que_eu_quero_atualizar_os_dados_de_um_pacote_de_servicos_existente_pelo_seu_id() {
    }

    @When("eu faço uma requisição PUT para \"/pacoteservicos/{id}\" com os novos dados do pacote de serviços")
    public void eu_faco_uma_requisicao_PUT_para_pacoteservicos_id_com_os_novos_dados_do_pacote_de_servicos(String url, PacoteServicos pacoteServicosAtualizado) {
        restTemplate.put(url, pacoteServicosAtualizado);
    }

    @Then("os dados do pacote de serviços devem ser atualizados e a resposta deve ser os detalhes do pacote de serviços atualizado")
    public void os_dados_do_pacote_de_servicos_devem_ser_atualizados_e_a_resposta_deve_ser_os_detalhes_do_pacote_de_servicos_atualizado() {
        assertEquals(HttpStatus.OK, response.getStatusCode());
}}