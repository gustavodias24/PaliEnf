package benicio.solucoes.palienf.model;

import java.util.ArrayList;
import java.util.List;

public class AvaDiariaModel {
    String id, idPaciente;
    String data;

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

    String temperatura = "Não Informado", pa= "Não Informado", fc= "Não Informado", fr= "Não Informado", sat= "Não Informado";

    String posOperatorio = "Não";
    String posOperatorioObservacao= "Não Informado";

    List<String> pulso = new ArrayList<>();

    List<String> nivelConsciencia = new ArrayList<>();
    String observacaoNivelConsciencia= "Não Informado";

    List<String> pupila = new ArrayList<>();
    String observacaoPupila= "Não Informado";

    List<String> acuidadeVisual = new ArrayList<>();
    String observacaoAcuidadeVisual= "Não Informado";

    List<String> acuidadeAuditiva = new ArrayList<>();
    String observacaoAcuidadeAuditiva= "Não Informado";

    String regiaoCranioFacial = "assimetrico";

    List<String> cavidadeOral = new ArrayList<>();
    String observacaoCavidadeOral= "Não Informado";

    List<String> ventilacao = new ArrayList<>();
    String observacaoVentilacao= "Não Informado";

    List<String> auscultaPulmonar = new ArrayList<>();
    String observacaoAuscultaPulmonar= "Não Informado";

    List<String> auscultaCardiaca = new ArrayList<>();
    String observacaoAuscultaCardiaca= "Não Informado";

    List<String> torax = new ArrayList<>();
    String observacaoTorax= "Não Informado";

    List<String> abdome = new ArrayList<>();
    String observacaoAbdome= "Não Informado";

    List<String> genitalia = new ArrayList<>();
    String observacaoGenitalia= "Não Informado";

    List<String> menbroSuperior = new ArrayList<>();
    String observacaoMenbroSuperior= "Não Informado";

    List<String> menbroInferior = new ArrayList<>();
    String observacaoMenbroInferior= "Não Informado";

    String ortese = "Não";
    String observacaoOrtese= "Não Informado";
    String protese = "Não";
    String observacaoProtese= "Não Informado";


    List<String> mucosas = new ArrayList<>();
    String observacaoMucosas= "Não Informado";


    List<String> pele = new ArrayList<>();
    String observacaoPele= "Não Informado";

    String presencaDeDor = "Não";
    String ObservacaopresencaDeDor= "Não Informado";
    String escalaDeDorEva= "Não Informado";

    String presencaLesao = "Não";
    String observacaopresencaLesao= "Não Informado";

    String feridaOperatoria = "Não";
    String observacaoFeridaOperatoria= "Não Informado";

    String amputacoes = "Não";
    String observacaoAmputacoes= "Não Informado";

    String historicoQueda = "Não";
    String observacaoHistoricoQueda= "Não Informado";

    String riscoQueda = "Não";
    String observacaoRiscoQueda= "Não Informado";

    List<String> dipositivos = new ArrayList<>();
    String observacaoDispositivos= "Não Informado";

    String fadiga = "Não";
    String observacaoFadiga= "Não Informado";

    String aspectosNutricionais= "Não Informado";
    String observacaoAspectosNutricionais= "Não Informado";

    List<String> dieta = new ArrayList<>();
    String observacaoDieta= "Não Informado";

    String aberturaOcular= "Não Informado";
    String respostaVerbal= "Não Informado";
    String respostaMotora= "Não Informado";
    String reatividadePupilar= "Não Informado";

    int somaEscalaGlasgow;


    String expressaoFacil= "Não Informado";
    String atividadeCorporal= "Não Informado";
    String defesa= "Não Informado";
    String sinaisVitais= "Não Informado";
    String alteracaoRespiratoria= "Não Informado";

    int somaEscalaDorNaoVerbal;


    String richmond= "Não Informado";
    String grauMucosite= "Não Informado";

    String escalaDeMucosite= "Não Informado";

    List<String> diurese = new ArrayList<>();
    List<String> evacuacao = new ArrayList<>();
    String observacaoEliminacoes= "Não Informado";

    String estadoEmocional= "Não Informado";
    String observacaoEstadoEmocional= "Não Informado";

    String enfrentamentoDoenca= "Não Informado";

    List<String> imagemCorporal = new ArrayList<>();
    List<String> autoestima = new ArrayList<>();

    String suporteRedeSocial = "Não";
    String qualSuporteRedeSocial= "Não Informado";

    String atividadeRecreativa = "Não";
    String qualAtividadeRecreativa= "Não Informado";

    String quantasPessoasMoram= "Não Informado";
    String principalAconpanhante= "Não Informado";
    String provedorRenda= "Não Informado";
    String ondemMoram= "Não Informado";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public AvaDiariaModel() {
    }

    public String getTemperatura() {
        return temperatura;
    }

    public String getGrauMucosite() {
        return grauMucosite;
    }

    public void setGrauMucosite(String grauMucosite) {
        this.grauMucosite = grauMucosite;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getPa() {
        return pa;
    }

    public void setPa(String pa) {
        this.pa = pa;
    }

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getPosOperatorio() {
        return posOperatorio;
    }

    public void setPosOperatorio(String posOperatorio) {
        this.posOperatorio = posOperatorio;
    }

    public String getPosOperatorioObservacao() {
        return posOperatorioObservacao;
    }

    public void setPosOperatorioObservacao(String posOperatorioObservacao) {
        this.posOperatorioObservacao = posOperatorioObservacao;
    }

    public List<String> getPulso() {
        return pulso;
    }

    public void setPulso(List<String> pulso) {
        this.pulso = pulso;
    }

    public List<String> getNivelConsciencia() {
        return nivelConsciencia;
    }

    public void setNivelConsciencia(List<String> nivelConsciencia) {
        this.nivelConsciencia = nivelConsciencia;
    }

    public String getObservacaoNivelConsciencia() {
        return observacaoNivelConsciencia;
    }

    public void setObservacaoNivelConsciencia(String observacaoNivelConsciencia) {
        this.observacaoNivelConsciencia = observacaoNivelConsciencia;
    }

    public List<String> getPupila() {
        return pupila;
    }

    public void setPupila(List<String> pupila) {
        this.pupila = pupila;
    }

    public String getObservacaoPupila() {
        return observacaoPupila;
    }

    public void setObservacaoPupila(String observacaoPupila) {
        this.observacaoPupila = observacaoPupila;
    }

    public List<String> getAcuidadeVisual() {
        return acuidadeVisual;
    }

    public void setAcuidadeVisual(List<String> acuidadeVisual) {
        this.acuidadeVisual = acuidadeVisual;
    }

    public String getObservacaoAcuidadeVisual() {
        return observacaoAcuidadeVisual;
    }

    public void setObservacaoAcuidadeVisual(String observacaoAcuidadeVisual) {
        this.observacaoAcuidadeVisual = observacaoAcuidadeVisual;
    }

    public List<String> getAcuidadeAuditiva() {
        return acuidadeAuditiva;
    }

    public void setAcuidadeAuditiva(List<String> acuidadeAuditiva) {
        this.acuidadeAuditiva = acuidadeAuditiva;
    }

    public String getObservacaoAcuidadeAuditiva() {
        return observacaoAcuidadeAuditiva;
    }

    public void setObservacaoAcuidadeAuditiva(String observacaoAcuidadeAuditiva) {
        this.observacaoAcuidadeAuditiva = observacaoAcuidadeAuditiva;
    }

    public String getRegiaoCranioFacial() {
        return regiaoCranioFacial;
    }

    public void setRegiaoCranioFacial(String regiaoCranioFacial) {
        this.regiaoCranioFacial = regiaoCranioFacial;
    }

    public List<String> getCavidadeOral() {
        return cavidadeOral;
    }

    public void setCavidadeOral(List<String> cavidadeOral) {
        this.cavidadeOral = cavidadeOral;
    }

    public String getObservacaoCavidadeOral() {
        return observacaoCavidadeOral;
    }

    public void setObservacaoCavidadeOral(String observacaoCavidadeOral) {
        this.observacaoCavidadeOral = observacaoCavidadeOral;
    }

    public List<String> getVentilacao() {
        return ventilacao;
    }

    public void setVentilacao(List<String> ventilacao) {
        this.ventilacao = ventilacao;
    }

    public String getObservacaoVentilacao() {
        return observacaoVentilacao;
    }

    public void setObservacaoVentilacao(String observacaoVentilacao) {
        this.observacaoVentilacao = observacaoVentilacao;
    }

    public List<String> getAuscultaPulmonar() {
        return auscultaPulmonar;
    }

    public void setAuscultaPulmonar(List<String> auscultaPulmonar) {
        this.auscultaPulmonar = auscultaPulmonar;
    }

    public String getObservacaoAuscultaPulmonar() {
        return observacaoAuscultaPulmonar;
    }

    public void setObservacaoAuscultaPulmonar(String observacaoAuscultaPulmonar) {
        this.observacaoAuscultaPulmonar = observacaoAuscultaPulmonar;
    }

    public List<String> getAuscultaCardiaca() {
        return auscultaCardiaca;
    }

    public void setAuscultaCardiaca(List<String> auscultaCardiaca) {
        this.auscultaCardiaca = auscultaCardiaca;
    }

    public String getObservacaoAuscultaCardiaca() {
        return observacaoAuscultaCardiaca;
    }

    public void setObservacaoAuscultaCardiaca(String observacaoAuscultaCardiaca) {
        this.observacaoAuscultaCardiaca = observacaoAuscultaCardiaca;
    }

    public List<String> getTorax() {
        return torax;
    }

    public void setTorax(List<String> torax) {
        this.torax = torax;
    }

    public String getObservacaoTorax() {
        return observacaoTorax;
    }

    public void setObservacaoTorax(String observacaoTorax) {
        this.observacaoTorax = observacaoTorax;
    }

    public List<String> getAbdome() {
        return abdome;
    }

    public void setAbdome(List<String> abdome) {
        this.abdome = abdome;
    }

    public String getObservacaoAbdome() {
        return observacaoAbdome;
    }

    public void setObservacaoAbdome(String observacaoAbdome) {
        this.observacaoAbdome = observacaoAbdome;
    }

    public List<String> getGenitalia() {
        return genitalia;
    }

    public void setGenitalia(List<String> genitalia) {
        this.genitalia = genitalia;
    }

    public String getObservacaoGenitalia() {
        return observacaoGenitalia;
    }

    public void setObservacaoGenitalia(String observacaoGenitalia) {
        this.observacaoGenitalia = observacaoGenitalia;
    }

    public List<String> getMenbroSuperior() {
        return menbroSuperior;
    }

    public void setMenbroSuperior(List<String> menbroSuperior) {
        this.menbroSuperior = menbroSuperior;
    }

    public String getObservacaoMenbroSuperior() {
        return observacaoMenbroSuperior;
    }

    public void setObservacaoMenbroSuperior(String observacaoMenbroSuperior) {
        this.observacaoMenbroSuperior = observacaoMenbroSuperior;
    }

    public List<String> getMenbroInferior() {
        return menbroInferior;
    }

    public void setMenbroInferior(List<String> menbroInferior) {
        this.menbroInferior = menbroInferior;
    }

    public String getObservacaoMenbroInferior() {
        return observacaoMenbroInferior;
    }

    public void setObservacaoMenbroInferior(String observacaoMenbroInferior) {
        this.observacaoMenbroInferior = observacaoMenbroInferior;
    }

    public String getOrtese() {
        return ortese;
    }

    public void setOrtese(String ortese) {
        this.ortese = ortese;
    }

    public String getObservacaoOrtese() {
        return observacaoOrtese;
    }

    public void setObservacaoOrtese(String observacaoOrtese) {
        this.observacaoOrtese = observacaoOrtese;
    }

    public String getProtese() {
        return protese;
    }

    public void setProtese(String protese) {
        this.protese = protese;
    }

    public String getObservacaoProtese() {
        return observacaoProtese;
    }

    public void setObservacaoProtese(String observacaoProtese) {
        this.observacaoProtese = observacaoProtese;
    }

    public List<String> getMucosas() {
        return mucosas;
    }

    public void setMucosas(List<String> mucosas) {
        this.mucosas = mucosas;
    }

    public String getObservacaoMucosas() {
        return observacaoMucosas;
    }

    public void setObservacaoMucosas(String observacaoMucosas) {
        this.observacaoMucosas = observacaoMucosas;
    }

    public List<String> getPele() {
        return pele;
    }

    public void setPele(List<String> pele) {
        this.pele = pele;
    }

    public String getObservacaoPele() {
        return observacaoPele;
    }

    public void setObservacaoPele(String observacaoPele) {
        this.observacaoPele = observacaoPele;
    }

    public String getPresencaDeDor() {
        return presencaDeDor;
    }

    public void setPresencaDeDor(String presencaDeDor) {
        this.presencaDeDor = presencaDeDor;
    }

    public String getObservacaopresencaDeDor() {
        return ObservacaopresencaDeDor;
    }

    public void setObservacaopresencaDeDor(String observacaopresencaDeDor) {
        ObservacaopresencaDeDor = observacaopresencaDeDor;
    }

    public String getEscalaDeDorEva() {
        return escalaDeDorEva;
    }

    public void setEscalaDeDorEva(String escalaDeDorEva) {
        this.escalaDeDorEva = escalaDeDorEva;
    }

    public String getPresencaLesao() {
        return presencaLesao;
    }

    public void setPresencaLesao(String presencaLesao) {
        this.presencaLesao = presencaLesao;
    }

    public String getObservacaopresencaLesao() {
        return observacaopresencaLesao;
    }

    public void setObservacaopresencaLesao(String observacaopresencaLesao) {
        this.observacaopresencaLesao = observacaopresencaLesao;
    }

    public String getFeridaOperatoria() {
        return feridaOperatoria;
    }

    public void setFeridaOperatoria(String feridaOperatoria) {
        this.feridaOperatoria = feridaOperatoria;
    }

    public String getObservacaoFeridaOperatoria() {
        return observacaoFeridaOperatoria;
    }

    public void setObservacaoFeridaOperatoria(String observacaoFeridaOperatoria) {
        this.observacaoFeridaOperatoria = observacaoFeridaOperatoria;
    }

    public String getAmputacoes() {
        return amputacoes;
    }

    public void setAmputacoes(String amputacoes) {
        this.amputacoes = amputacoes;
    }

    public String getObservacaoAmputacoes() {
        return observacaoAmputacoes;
    }

    public void setObservacaoAmputacoes(String observacaoAmputacoes) {
        this.observacaoAmputacoes = observacaoAmputacoes;
    }

    public String getHistoricoQueda() {
        return historicoQueda;
    }

    public void setHistoricoQueda(String historicoQueda) {
        this.historicoQueda = historicoQueda;
    }

    public String getObservacaoHistoricoQueda() {
        return observacaoHistoricoQueda;
    }

    public void setObservacaoHistoricoQueda(String observacaoHistoricoQueda) {
        this.observacaoHistoricoQueda = observacaoHistoricoQueda;
    }

    public String getRiscoQueda() {
        return riscoQueda;
    }

    public void setRiscoQueda(String riscoQueda) {
        this.riscoQueda = riscoQueda;
    }

    public String getObservacaoRiscoQueda() {
        return observacaoRiscoQueda;
    }

    public void setObservacaoRiscoQueda(String observacaoRiscoQueda) {
        this.observacaoRiscoQueda = observacaoRiscoQueda;
    }

    public List<String> getDipositivos() {
        return dipositivos;
    }

    public void setDipositivos(List<String> dipositivos) {
        this.dipositivos = dipositivos;
    }

    public String getObservacaoDispositivos() {
        return observacaoDispositivos;
    }

    public void setObservacaoDispositivos(String observacaoDispositivos) {
        this.observacaoDispositivos = observacaoDispositivos;
    }

    public String getFadiga() {
        return fadiga;
    }

    public void setFadiga(String fadiga) {
        this.fadiga = fadiga;
    }

    public String getObservacaoFadiga() {
        return observacaoFadiga;
    }

    public void setObservacaoFadiga(String observacaoFadiga) {
        this.observacaoFadiga = observacaoFadiga;
    }

    public String getAspectosNutricionais() {
        return aspectosNutricionais;
    }

    public void setAspectosNutricionais(String aspectosNutricionais) {
        this.aspectosNutricionais = aspectosNutricionais;
    }

    public String getObservacaoAspectosNutricionais() {
        return observacaoAspectosNutricionais;
    }

    public void setObservacaoAspectosNutricionais(String observacaoAspectosNutricionais) {
        this.observacaoAspectosNutricionais = observacaoAspectosNutricionais;
    }

    public List<String> getDieta() {
        return dieta;
    }

    public void setDieta(List<String> dieta) {
        this.dieta = dieta;
    }

    public String getObservacaoDieta() {
        return observacaoDieta;
    }

    public void setObservacaoDieta(String observacaoDieta) {
        this.observacaoDieta = observacaoDieta;
    }

    public int getSomaEscalaGlasgow() {
        return somaEscalaGlasgow;
    }

    public void setSomaEscalaGlasgow(int somaEscalaGlasgow) {
        this.somaEscalaGlasgow = somaEscalaGlasgow;
    }

    public String getAberturaOcular() {
        return aberturaOcular;
    }

    public void setAberturaOcular(String aberturaOcular) {
        this.aberturaOcular = aberturaOcular;
    }

    public String getRespostaVerbal() {
        return respostaVerbal;
    }

    public void setRespostaVerbal(String respostaVerbal) {
        this.respostaVerbal = respostaVerbal;
    }

    public String getRespostaMotora() {
        return respostaMotora;
    }

    public void setRespostaMotora(String respostaMotora) {
        this.respostaMotora = respostaMotora;
    }

    public String getReatividadePupilar() {
        return reatividadePupilar;
    }

    public void setReatividadePupilar(String reatividadePupilar) {
        this.reatividadePupilar = reatividadePupilar;
    }

    public int getSomaEscalaDorNaoVerbal() {
        return somaEscalaDorNaoVerbal;
    }

    public void setSomaEscalaDorNaoVerbal(int somaEscalaDorNaoVerbal) {
        this.somaEscalaDorNaoVerbal = somaEscalaDorNaoVerbal;
    }

    public String getExpressaoFacil() {
        return expressaoFacil;
    }

    public void setExpressaoFacil(String expressaoFacil) {
        this.expressaoFacil = expressaoFacil;
    }

    public String getAtividadeCorporal() {
        return atividadeCorporal;
    }

    public void setAtividadeCorporal(String atividadeCorporal) {
        this.atividadeCorporal = atividadeCorporal;
    }

    public String getDefesa() {
        return defesa;
    }

    public void setDefesa(String defesa) {
        this.defesa = defesa;
    }

    public String getSinaisVitais() {
        return sinaisVitais;
    }

    public void setSinaisVitais(String sinaisVitais) {
        this.sinaisVitais = sinaisVitais;
    }

    public String getAlteracaoRespiratoria() {
        return alteracaoRespiratoria;
    }

    public void setAlteracaoRespiratoria(String alteracaoRespiratoria) {
        this.alteracaoRespiratoria = alteracaoRespiratoria;
    }

    public String getRichmond() {
        return richmond;
    }

    public void setRichmond(String richmond) {
        this.richmond = richmond;
    }

    public String getEscalaDeMucosite() {
        return escalaDeMucosite;
    }

    public void setEscalaDeMucosite(String escalaDeMucosite) {
        this.escalaDeMucosite = escalaDeMucosite;
    }

    public List<String> getDiurese() {
        return diurese;
    }

    public void setDiurese(List<String> diurese) {
        this.diurese = diurese;
    }

    public List<String> getEvacuacao() {
        return evacuacao;
    }

    public void setEvacuacao(List<String> evacuacao) {
        this.evacuacao = evacuacao;
    }

    public String getObservacaoEliminacoes() {
        return observacaoEliminacoes;
    }

    public void setObservacaoEliminacoes(String observacaoEliminacoes) {
        this.observacaoEliminacoes = observacaoEliminacoes;
    }

    public String getEstadoEmocional() {
        return estadoEmocional;
    }

    public void setEstadoEmocional(String estadoEmocional) {
        this.estadoEmocional = estadoEmocional;
    }

    public String getObservacaoEstadoEmocional() {
        return observacaoEstadoEmocional;
    }

    public void setObservacaoEstadoEmocional(String observacaoEstadoEmocional) {
        this.observacaoEstadoEmocional = observacaoEstadoEmocional;
    }

    public String getEnfrentamentoDoenca() {
        return enfrentamentoDoenca;
    }

    public void setEnfrentamentoDoenca(String enfrentamentoDoenca) {
        this.enfrentamentoDoenca = enfrentamentoDoenca;
    }

    public List<String> getImagemCorporal() {
        return imagemCorporal;
    }

    public void setImagemCorporal(List<String> imagemCorporal) {
        this.imagemCorporal = imagemCorporal;
    }

    public List<String> getAutoestima() {
        return autoestima;
    }

    public void setAutoestima(List<String> autoestima) {
        this.autoestima = autoestima;
    }

    public String getSuporteRedeSocial() {
        return suporteRedeSocial;
    }

    public void setSuporteRedeSocial(String suporteRedeSocial) {
        this.suporteRedeSocial = suporteRedeSocial;
    }

    public String getQualSuporteRedeSocial() {
        return qualSuporteRedeSocial;
    }

    public void setQualSuporteRedeSocial(String qualSuporteRedeSocial) {
        this.qualSuporteRedeSocial = qualSuporteRedeSocial;
    }

    public String getAtividadeRecreativa() {
        return atividadeRecreativa;
    }

    public void setAtividadeRecreativa(String atividadeRecreativa) {
        this.atividadeRecreativa = atividadeRecreativa;
    }

    public String getQualAtividadeRecreativa() {
        return qualAtividadeRecreativa;
    }

    public void setQualAtividadeRecreativa(String qualAtividadeRecreativa) {
        this.qualAtividadeRecreativa = qualAtividadeRecreativa;
    }

    public String getQuantasPessoasMoram() {
        return quantasPessoasMoram;
    }

    public void setQuantasPessoasMoram(String quantasPessoasMoram) {
        this.quantasPessoasMoram = quantasPessoasMoram;
    }

    public String getPrincipalAconpanhante() {
        return principalAconpanhante;
    }

    public void setPrincipalAconpanhante(String principalAconpanhante) {
        this.principalAconpanhante = principalAconpanhante;
    }

    public String getProvedorRenda() {
        return provedorRenda;
    }

    public void setProvedorRenda(String provedorRenda) {
        this.provedorRenda = provedorRenda;
    }

    public String getOndemMoram() {
        return ondemMoram;
    }

    public void setOndemMoram(String ondemMoram) {
        this.ondemMoram = ondemMoram;
    }
}
