package benicio.solucoes.palienf.model;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticoPacienteModel {
    String id, idPaciente, dataCriacao;
    String titulo, subTitulo;
    boolean ativo = true;
    List<NocModel> nocs = new ArrayList<>();
    List<IntervencaoModel> intervencoes = new ArrayList<>();
    List<String> intervensoeSelecionadas = new ArrayList<>();
    List<String> nocSelecionadas = new ArrayList<>();

    @Override
    public String toString() {
        StringBuilder intervencoes = new StringBuilder();
        for (String intervencao : this.intervensoeSelecionadas) {
            intervencoes.append(intervencao).append("\n");
        }

        StringBuilder nocs = new StringBuilder();
        for (String noc : this.nocSelecionadas) {
            nocs.append(noc).append("\n");
        }
        return "Criado em: " + dataCriacao + '\n' +
                "Noc's: \n" + nocs +
                "Intervenções: \n" + intervencoes;
    }

    public List<IntervencaoModel> getIntervencoes() {
        return intervencoes;
    }

    public void setIntervencoes(List<IntervencaoModel> intervencoes) {
        this.intervencoes = intervencoes;
    }

    public List<NocModel> getNocs() {
        return nocs;
    }

    public void setNocs(List<NocModel> nocs) {
        this.nocs = nocs;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public DiagnosticoPacienteModel() {
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<String> getIntervensoeSelecionadas() {
        return intervensoeSelecionadas;
    }

    public void setIntervensoeSelecionadas(List<String> intervensoeSelecionadas) {
        this.intervensoeSelecionadas = intervensoeSelecionadas;
    }

    public List<String> getNocSelecionadas() {
        return nocSelecionadas;
    }

    public void setNocSelecionadas(List<String> nocSelecionadas) {
        this.nocSelecionadas = nocSelecionadas;
    }
}
