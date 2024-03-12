package benicio.solucoes.palienf.model;

import java.util.ArrayList;
import java.util.List;

import benicio.solucoes.palienf.R;

public class DiagnosticoModel {
    String nome, descricao , dominio;
    List<NocModel> nocs = new ArrayList<>();
    List<IntervencaoModel> intervencoes = new ArrayList<>();
    int tabelaNoc = R.drawable.noc_1;

    public DiagnosticoModel(String nome, String descricao, String dominio, List<NocModel> nocs, List<IntervencaoModel> intervencoes) {
        this.nome = nome;
        this.descricao = descricao;
        this.dominio = dominio;
        this.nocs = nocs;
        this.intervencoes = intervencoes;
    }

    public DiagnosticoModel() {
    }

    public int getTabelaNoc() {
        return tabelaNoc;
    }

    public void setTabelaNoc(int tabelaNoc) {
        this.tabelaNoc = tabelaNoc;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDominio() {
        return dominio;
    }

    public void setDominio(String dominio) {
        this.dominio = dominio;
    }

    public List<NocModel> getNocs() {
        return nocs;
    }

    public void setNocs(List<NocModel> nocs) {
        this.nocs = nocs;
    }

    public List<IntervencaoModel> getIntervencoes() {
        return intervencoes;
    }

    public void setIntervencoes(List<IntervencaoModel> intervencoes) {
        this.intervencoes = intervencoes;
    }
}
