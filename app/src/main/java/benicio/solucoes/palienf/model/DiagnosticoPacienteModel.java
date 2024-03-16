package benicio.solucoes.palienf.model;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticoPacienteModel {

    String dataHoraResolucaoIntervencao = "Não Informado";
    String id, idPaciente, dataCriacao;
    String titulo = "Não Informado", subTitulo = "Não Informado";
    boolean ativo = true;
    List<NocModel> nocs = new ArrayList<>();
    List<IntervencaoModel> intervencoes = new ArrayList<>();
    List<String> nocSelecionadas = new ArrayList<>();

    public String getDataHoraResolucaoIntervencao() {
        return dataHoraResolucaoIntervencao;
    }

    public void setDataHoraResolucaoIntervencao(String dataHoraResolucaoIntervencao) {
        this.dataHoraResolucaoIntervencao = dataHoraResolucaoIntervencao;
    }

    @Override
    public String toString() {
        StringBuilder intervencoes = new StringBuilder();
        for (IntervencaoModel intervencao : this.intervencoes) {
            if (intervencao.isSelecionado()) {
                intervencoes.append(intervencao.getDescricao()).append("\n");
            }
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

    public List<String> getNocSelecionadas() {
        return nocSelecionadas;
    }

    public void setNocSelecionadas(List<String> nocSelecionadas) {
        this.nocSelecionadas = nocSelecionadas;
    }
}
