package benicio.solucoes.palienf.model;

public class IntervencaoModel {
    String descricao = "Não Informado";
    String horaIntervencao = "Não Informado";
    boolean resolvido = false;
    boolean selecionado = false;

    public IntervencaoModel() {
    }

    public String getHoraIntervencao() {
        return horaIntervencao;
    }

    public void setHoraIntervencao(String horaIntervencao) {
        this.horaIntervencao = horaIntervencao;
    }

    public boolean isResolvido() {
        return resolvido;
    }

    public void setResolvido(boolean resolvido) {
        this.resolvido = resolvido;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public IntervencaoModel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
