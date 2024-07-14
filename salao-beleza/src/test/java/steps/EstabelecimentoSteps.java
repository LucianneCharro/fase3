package steps;

import fiap.salaobeleza.dto.EstabelecimentoDTO;
import fiap.salaobeleza.model.Estabelecimento;
import fiap.salaobeleza.service.EstabelecimentoService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EstabelecimentoSteps {

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @MockBean
    private EstabelecimentoService mockEstabelecimentoService;

    private EstabelecimentoDTO estabelecimentoDTO;

    private List<Estabelecimento> estabelecimentos;

    @Dado("que eu quero ver todos os estabelecimentos cadastrados")
    public void que_eu_quero_ver_todos_os_estabelecimentos_cadastrados() {
        List<EstabelecimentoDTO> dtos = Arrays.asList(new EstabelecimentoDTO(), new EstabelecimentoDTO());
        List<Estabelecimento> estabelecimentosMock = dtos.stream().map(dto -> convertToEntity(dto)).collect(Collectors.toList());
        Mockito.when(mockEstabelecimentoService.listarTodos()).thenReturn(estabelecimentosMock);
    }

    @Quando("eu solicito a lista de estabelecimentos")
    public void eu_solicito_a_lista_de_estabelecimentos() {
        estabelecimentos = mockEstabelecimentoService.listarTodos();
    }

    @Então("todos os estabelecimentos devem ser retornados")
    public void todos_os_estabelecimentos_devem_ser_retornados() {
        assertNotNull(estabelecimentos);
        assertFalse(estabelecimentos.isEmpty());
    }

    @Dado("que eu quero adicionar um novo estabelecimento")
    public void que_eu_quero_adicionar_um_novo_estabelecimento() {
        estabelecimentoDTO = new EstabelecimentoDTO();
    }

    private Estabelecimento convertToEntity(EstabelecimentoDTO dto) {
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(dto.getId());
        estabelecimento.setNome(dto.getNome());
        estabelecimento.setEndereco(dto.getEndereco());
        estabelecimento.setHorarioFuncionamento(dto.getHorarioFuncionamento());
        estabelecimento.setFotos(dto.getFotos());
        return estabelecimento;
    }
}