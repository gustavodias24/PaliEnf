package benicio.solucoes.palienf.model;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import benicio.solucoes.palienf.R;

public class AvaDiariaModel {


    // variaveis nova implementacao

    String condutas = "";
    String obsEnfrentamentoDoenda = "";
    String obsImagemCorporal = "";
    String obsAutoestima = "";

    public String getObsEnfrentamentoDoenda() {
        return obsEnfrentamentoDoenda;
    }

    public void setObsEnfrentamentoDoenda(String obsEnfrentamentoDoenda) {
        this.obsEnfrentamentoDoenda = obsEnfrentamentoDoenda;
    }

    public String getObsImagemCorporal() {
        return obsImagemCorporal;
    }

    public void setObsImagemCorporal(String obsImagemCorporal) {
        this.obsImagemCorporal = obsImagemCorporal;
    }

    public String getObsAutoestima() {
        return obsAutoestima;
    }

    public void setObsAutoestima(String obsAutoestima) {
        this.obsAutoestima = obsAutoestima;
    }

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

    String temperatura = "", pa = "", fc = "", fr = "", sat = "";

    String posOperatorio = "Não";
    String posOperatorioObservacao = "";

    List<String> pulso = new ArrayList<>();

    List<String> nivelConsciencia = new ArrayList<>();
    String observacaoNivelConsciencia = "";

    List<String> pupila = new ArrayList<>();
    String observacaoPupila = "";

    List<String> acuidadeVisual = new ArrayList<>();
    String observacaoAcuidadeVisual = "";

    List<String> acuidadeAuditiva = new ArrayList<>();
    String observacaoAcuidadeAuditiva = "";

    String regiaoCranioFacial = "assimetrico";

    List<String> cavidadeOral = new ArrayList<>();
    String observacaoCavidadeOral = "";

    List<String> ventilacao = new ArrayList<>();
    String observacaoVentilacao = "";

    List<String> auscultaPulmonar = new ArrayList<>();
    String observacaoAuscultaPulmonar = "";

    List<String> auscultaCardiaca = new ArrayList<>();
    String observacaoAuscultaCardiaca = "";

    List<String> torax = new ArrayList<>();
    String observacaoTorax = "";

    List<String> abdome = new ArrayList<>();
    String observacaoAbdome = "";

    List<String> genitalia = new ArrayList<>();
    String observacaoGenitalia = "";

    List<String> menbroSuperior = new ArrayList<>();
    String observacaoMenbroSuperior = "";

    List<String> menbroInferior = new ArrayList<>();
    String observacaoMenbroInferior = "";

    String ortese = "Não";
    String observacaoOrtese = "";
    String protese = "Não";
    String observacaoProtese = "";


    List<String> mucosas = new ArrayList<>();
    String observacaoMucosas = "";


    List<String> pele = new ArrayList<>();
    String observacaoPele = "";

    String presencaDeDor = "Não";
    String ObservacaopresencaDeDor = "";
    String escalaDeDorEva = "";

    String presencaLesao = "Não";
    String observacaopresencaLesao = "";

    String feridaOperatoria = "Não";
    String observacaoFeridaOperatoria = "";

    String amputacoes = "Não";
    String observacaoAmputacoes = "";

    String historicoQueda = "Não";
    String observacaoHistoricoQueda = "";

    String riscoQueda = "Não";
    String observacaoRiscoQueda = "";

    List<String> dipositivos = new ArrayList<>();
    String observacaoDispositivos = "";

    String fadiga = "Não";
    String observacaoFadiga = "";

    String aspectosNutricionais = "";
    String observacaoAspectosNutricionais = "";

    List<String> dieta = new ArrayList<>();
    String observacaoDieta = "";

    String aberturaOcular = "";
    String respostaVerbal = "";
    String respostaMotora = "";
    String reatividadePupilar = "";

    int somaEscalaGlasgow;


    String expressaoFacil = "";
    String atividadeCorporal = "";
    String defesa = "";
    String sinaisVitais = "";
    String alteracaoRespiratoria = "";

    int somaEscalaDorNaoVerbal;


    String richmond = "";
    String grauMucosite = "";

    String escalaDeMucosite = "";

    List<String> diurese = new ArrayList<>();
    List<String> evacuacao = new ArrayList<>();
    String observacaoEliminacoes = "";

    String estadoEmocional = "";
    String observacaoEstadoEmocional = "";

    String enfrentamentoDoenca = "";

    List<String> imagemCorporal = new ArrayList<>();
    List<String> autoestima = new ArrayList<>();

    String suporteRedeSocial = "Não";
    String qualSuporteRedeSocial = "";

    String atividadeRecreativa = "Não";
    String qualAtividadeRecreativa = "";

    public String getCondutas() {
        return condutas;
    }

    public void setCondutas(String condutas) {
        this.condutas = condutas;
    }

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

    @Override
    public String toString() {
        return
                "Temperatura: " + temperatura + '\n' +
                        "PA: " + pa + '\n' +
                        "FC: " + fc + '\n' +
                        "FR: " + fr + '\n' +
                        "Saturação: " + sat + '\n' +
                        "Pós-operatório: " + posOperatorio + '\n' +
                        "Obs Pós-operatório: " + posOperatorioObservacao + '\n' +
                        "Condutas: " + condutas + '\n' +
                        "Obs Enfrentamento Doença: " + obsEnfrentamentoDoenda + '\n' +
                        "Obs Imagem Corporal: " + obsImagemCorporal + '\n' +
                        "Obs Autoestima: " + obsAutoestima + '\n' +
                        "Data: " + data + '\n' +
                        "Pulso: " + pulso + '\n' +
                        "Nível de Consciência: " + nivelConsciencia + '\n' +
                        "Obs Nível de Consciência: " + observacaoNivelConsciencia + '\n' +
                        "Pupila: " + pupila + '\n' +
                        "Obs Pupila: " + observacaoPupila + '\n' +
                        "Acuidade Visual: " + acuidadeVisual + '\n' +
                        "Obs Acuidade Visual: " + observacaoAcuidadeVisual + '\n' +
                        "Acuidade Auditiva: " + acuidadeAuditiva + '\n' +
                        "Obs Acuidade Auditiva: " + observacaoAcuidadeAuditiva + '\n' +
                        "Região Crânio-facial: " + regiaoCranioFacial + '\n' +
                        "Cavidade Oral: " + cavidadeOral + '\n' +
                        "Obs Cavidade Oral: " + observacaoCavidadeOral + '\n' +
                        "Ventilação: " + ventilacao + '\n' +
                        "Obs Ventilação: " + observacaoVentilacao + '\n' +
                        "Ausculta Pulmonar: " + auscultaPulmonar + '\n' +
                        "Obs Ausculta Pulmonar: " + observacaoAuscultaPulmonar + '\n' +
                        "Ausculta Cardíaca: " + auscultaCardiaca + '\n' +
                        "Obs Ausculta Cardíaca: " + observacaoAuscultaCardiaca + '\n' +
                        "Tórax: " + torax + '\n' +
                        "Obs Tórax: " + observacaoTorax + '\n' +
                        "Abdome: " + abdome + '\n' +
                        "Obs Abdome: " + observacaoAbdome + '\n' +
                        "Genitália: " + genitalia + '\n' +
                        "Obs Genitália: " + observacaoGenitalia + '\n' +
                        "Membro Superior: " + menbroSuperior + '\n' +
                        "Obs Membro Superior: " + observacaoMenbroSuperior + '\n' +
                        "Membro Inferior: " + menbroInferior + '\n' +
                        "Obs Membro Inferior: " + observacaoMenbroInferior + '\n' +
                        "Órtese: " + ortese + '\n' +
                        "Obs Órtese: " + observacaoOrtese + '\n' +
                        "Prótese: " + protese + '\n' +
                        "Obs Prótese: " + observacaoProtese + '\n' +
                        "Mucosas: " + mucosas + '\n' +
                        "Obs Mucosas: " + observacaoMucosas + '\n' +
                        "Pele: " + pele + '\n' +
                        "Obs Pele: " + observacaoPele + '\n' +
                        "Presença de Dor: " + presencaDeDor + '\n' +
                        "Obs Presença de Dor: " + ObservacaopresencaDeDor + '\n' +
                        "Escala de Dor EVA: " + escalaDeDorEva + '\n' +
                        "Presença de Lesão: " + presencaLesao + '\n' +
                        "Obs Presença de Lesão: " + observacaopresencaLesao + '\n' +
                        "Ferida Operatória: " + feridaOperatoria + '\n' +
                        "Obs Ferida Operatória: " + observacaoFeridaOperatoria + '\n' +
                        "Amputações: " + amputacoes + '\n' +
                        "Obs Amputações: " + observacaoAmputacoes + '\n' +
                        "Histórico de Queda: " + historicoQueda + '\n' +
                        "Obs Histórico de Queda: " + observacaoHistoricoQueda + '\n' +
                        "Risco de Queda: " + riscoQueda + '\n' +
                        "Obs Risco de Queda: " + observacaoRiscoQueda + '\n' +
                        "Dispositivos: " + dipositivos + '\n' +
                        "Obs Dispositivos: " + observacaoDispositivos + '\n' +
                        "Fadiga: " + fadiga + '\n' +
                        "Obs Fadiga: " + observacaoFadiga + '\n' +
                        "Aspectos Nutricionais: " + aspectosNutricionais + '\n' +
                        "Obs Aspectos Nutricionais: " + observacaoAspectosNutricionais + '\n' +
                        "Dieta: " + dieta + '\n' +
                        "Obs Dieta: " + observacaoDieta + '\n' +
                        "Abertura Ocular: " + aberturaOcular + '\n' +
                        "Resposta Verbal: " + respostaVerbal + '\n' +
                        "Resposta Motora: " + respostaMotora + '\n' +
                        "Reatividade Pupilar: " + reatividadePupilar + '\n' +
                        "Soma Escala Glasgow: " + somaEscalaGlasgow + '\n' +
                        "Expressão Facial: " + expressaoFacil + '\n' +
                        "Atividade Corporal: " + atividadeCorporal + '\n' +
                        "Defesa: " + defesa + '\n' +
                        "Sinais Vitais: " + sinaisVitais + '\n' +
                        "Alteração Respiratória: " + alteracaoRespiratoria + '\n' +
                        "Soma Escala Dor Não Verbal: " + somaEscalaDorNaoVerbal + '\n' +
                        "Richmond: " + richmond + '\n' +
                        "Grau de Mucosite: " + grauMucosite + '\n' +
                        "Escala de Mucosite: " + escalaDeMucosite + '\n' +
                        "Diurese: " + diurese + '\n' +
                        "Evacuação: " + evacuacao + '\n' +
                        "Obs Eliminações: " + observacaoEliminacoes + '\n' +
                        "Estado Emocional: " + estadoEmocional + '\n' +
                        "Obs Estado Emocional: " + observacaoEstadoEmocional + '\n' +
                        "Enfrentamento da Doença: " + enfrentamentoDoenca + '\n' +
                        "Imagem Corporal: " + imagemCorporal + '\n' +
                        "Autoestima: " + autoestima + '\n' +
                        "Suporte Rede Social: " + suporteRedeSocial + '\n' +
                        "Qual Suporte Rede Social: " + qualSuporteRedeSocial + '\n' +
                        "Atividade Recreativa: " + atividadeRecreativa + '\n' +
                        "Qual Atividade Recreativa: " + qualAtividadeRecreativa;

    }
}
