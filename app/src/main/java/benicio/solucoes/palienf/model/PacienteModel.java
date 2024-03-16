package benicio.solucoes.palienf.model;

public class PacienteModel {
    String
    id,
    nome,
    dataNascimento,
    cpf,
    prontuário,
    cidade,
    bairro,
    cep,
    numero,
    pontoReferencia, sexo;


    boolean necessidadeFinanceira;
    String descriNecessidadeFinanceira;

    boolean necessidadeMenbroFamilia;
    String descriNecessidadeMenbroFamilia;

    boolean crencaReligiao;
    String descriCrencaReligiao;

    boolean crencaVistirarLider;

    boolean crencaRitoEspiritual;
    String descriRitoEspiritual;


    String
    alergias,
    internacaoRecente,
    doencaOncológica,
    procedencia,
    motidaInternacao,
    nomeCompanhante,
    peso,
    altura,
    sc, imc, numeroLeito;

    public PacienteModel() {
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getProntuário() {
        return prontuário;
    }

    public void setProntuário(String prontuário) {
        this.prontuário = prontuário;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public boolean isNecessidadeFinanceira() {
        return necessidadeFinanceira;
    }

    public void setNecessidadeFinanceira(boolean necessidadeFinanceira) {
        this.necessidadeFinanceira = necessidadeFinanceira;
    }

    public String getDescriNecessidadeFinanceira() {
        return descriNecessidadeFinanceira;
    }

    public void setDescriNecessidadeFinanceira(String descriNecessidadeFinanceira) {
        this.descriNecessidadeFinanceira = descriNecessidadeFinanceira;
    }

    public boolean isNecessidadeMenbroFamilia() {
        return necessidadeMenbroFamilia;
    }

    public void setNecessidadeMenbroFamilia(boolean necessidadeMenbroFamilia) {
        this.necessidadeMenbroFamilia = necessidadeMenbroFamilia;
    }

    public String getDescriNecessidadeMenbroFamilia() {
        return descriNecessidadeMenbroFamilia;
    }

    public void setDescriNecessidadeMenbroFamilia(String descriNecessidadeMenbroFamilia) {
        this.descriNecessidadeMenbroFamilia = descriNecessidadeMenbroFamilia;
    }

    public boolean isCrencaReligiao() {
        return crencaReligiao;
    }

    public void setCrencaReligiao(boolean crencaReligiao) {
        this.crencaReligiao = crencaReligiao;
    }

    public String getDescriCrencaReligiao() {
        return descriCrencaReligiao;
    }

    public void setDescriCrencaReligiao(String descriCrencaReligiao) {
        this.descriCrencaReligiao = descriCrencaReligiao;
    }

    public boolean isCrencaVistirarLider() {
        return crencaVistirarLider;
    }

    public void setCrencaVistirarLider(boolean crencaVistirarLider) {
        this.crencaVistirarLider = crencaVistirarLider;
    }

    public boolean isCrencaRitoEspiritual() {
        return crencaRitoEspiritual;
    }

    public void setCrencaRitoEspiritual(boolean crencaRitoEspiritual) {
        this.crencaRitoEspiritual = crencaRitoEspiritual;
    }

    public String getDescriRitoEspiritual() {
        return descriRitoEspiritual;
    }

    public void setDescriRitoEspiritual(String descriRitoEspiritual) {
        this.descriRitoEspiritual = descriRitoEspiritual;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(String alergias) {
        this.alergias = alergias;
    }

    public String getInternacaoRecente() {
        return internacaoRecente;
    }

    public void setInternacaoRecente(String internacaoRecente) {
        this.internacaoRecente = internacaoRecente;
    }

    public String getDoencaOncológica() {
        return doencaOncológica;
    }

    public void setDoencaOncológica(String doencaOncológica) {
        this.doencaOncológica = doencaOncológica;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getMotidaInternacao() {
        return motidaInternacao;
    }

    public void setMotidaInternacao(String motidaInternacao) {
        this.motidaInternacao = motidaInternacao;
    }

    public String getNomeCompanhante() {
        return nomeCompanhante;
    }

    public void setNomeCompanhante(String nomeCompanhante) {
        this.nomeCompanhante = nomeCompanhante;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getNumeroLeito() {
        return numeroLeito;
    }

    public void setNumeroLeito(String numeroLeito) {
        this.numeroLeito = numeroLeito;
    }
}
