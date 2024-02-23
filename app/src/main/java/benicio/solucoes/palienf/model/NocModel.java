package benicio.solucoes.palienf.model;

import java.util.ArrayList;
import java.util.List;

public class NocModel {
    String nome;
    List<String> possiveisIndicadores = new ArrayList<>();

    public NocModel(String nome, List<String> possiveisIndicadores) {
        this.nome = nome;
        this.possiveisIndicadores = possiveisIndicadores;
    }

    public NocModel() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getPossiveisIndicadores() {
        return possiveisIndicadores;
    }

    public void setPossiveisIndicadores(List<String> possiveisIndicadores) {
        this.possiveisIndicadores = possiveisIndicadores;
    }
}
