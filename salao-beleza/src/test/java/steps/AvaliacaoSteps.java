package steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fiap.salaobeleza.model.Avaliacao;
import fiap.salaobeleza.repository.AvaliacaoRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AvaliacaoSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    private ResponseEntity<String> response;
    private Avaliacao avaliacao;

    @Given("que eu quero criar uma nova avaliação com os dados necessários")
    public void que_eu_quero_criar_uma_nova_avaliacao_com_os_dados_necessarios() {

        avaliacao = new Avaliacao();
        avaliacao.setId(1L);
        avaliacao.setComentario("Excelente serviço");
        avaliacao.setNota(5);
    }

    @When("eu faço uma requisição POST para {string} com os dados da avaliação")
    public void eu_faco_uma_requisicao_POST_para_com_os_dados_da_avaliacao(String url) {
        response = restTemplate.postForEntity(url, this.avaliacao, String.class);
    }

    @Then("a avaliação deve ser criada e a resposta deve ser os detalhes da avaliação criada")
    public void a_avaliacao_deve_ser_criada_e_a_resposta_deve_ser_os_detalhes_da_avaliacao_criada() {
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Avaliacao avaliacaoResposta = convertJsonToObject(response.getBody(), Avaliacao.class);
        Assertions.assertNotNull(avaliacaoResposta.getId());
        Assertions.assertEquals("Excelente serviço", avaliacaoResposta.getComentario());
        Assertions.assertEquals(5, avaliacaoResposta.getNota());
    }

    // Método auxiliar para converter JSON em objeto
    private <T> T convertJsonToObject(String json, Class<T> clazz) {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro processar JSON", e);
        }
    }
}