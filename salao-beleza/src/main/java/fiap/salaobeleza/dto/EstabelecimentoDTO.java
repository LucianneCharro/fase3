package fiap.salaobeleza.dto;

import java.util.List;
import java.util.Set;

public class EstabelecimentoDTO {

    private Long id;
    private String nome;
    private String endereco;
    private Set<Long> servicosOferecidosIds;
    private Set<Long> profissionaisDisponiveisIds;
    private String horarioFuncionamento;
    private List<String> fotos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Set<Long> getServicosOferecidosIds() {
        return servicosOferecidosIds;
    }

    public void setServicosOferecidosIds(Set<Long> servicosOferecidosIds) {
        this.servicosOferecidosIds = servicosOferecidosIds;
    }

    public Set<Long> getProfissionaisDisponiveisIds() {
        return profissionaisDisponiveisIds;
    }

    public void setProfissionaisDisponiveisIds(Set<Long> profissionaisDisponiveisIds) {
        this.profissionaisDisponiveisIds = profissionaisDisponiveisIds;
    }

    public String getHorarioFuncionamento() {
        return horarioFuncionamento;
    }

    public void setHorarioFuncionamento(String horarioFuncionamento) {
        this.horarioFuncionamento = horarioFuncionamento;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
}