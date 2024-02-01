package benicio.solucoes.palienf.model;

public class UsuarioModel {

    String id, nome, email, nr;

    public UsuarioModel(String id, String nome, String email, String nr) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.nr = nr;
    }

    public UsuarioModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }
}
