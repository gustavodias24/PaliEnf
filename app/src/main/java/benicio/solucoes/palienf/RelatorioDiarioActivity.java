package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import benicio.solucoes.palienf.model.AvaDiariaModel;

public class RelatorioDiarioActivity extends AppCompatActivity {

    private AvaDiariaModel avaliacao;
    private int somaGlasgow = 0;
    private int somaDorNaoVerbal = 0;

    private DatabaseReference refRelatorio = FirebaseDatabase.getInstance().getReference().child("relatoriodiario");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio_diario);

        avaliacao = new AvaDiariaModel();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setTitle("Avaliação Diária");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        configurarListenerGlasgow();
        configurarListenerMucosite();

        Button cadastrar = findViewById(R.id.cadastrarRelatorioDiario);

        cadastrar.setOnClickListener(view -> {

            EditText temperatura = findViewById(R.id.temperatura);
            avaliacao.setTemperatura(temperatura.getText().toString());

            EditText paEditText = findViewById(R.id.pa);
            avaliacao.setPa(paEditText.getText().toString());

            EditText fcEditText = findViewById(R.id.fc);
            avaliacao.setFc(fcEditText.getText().toString());

            EditText frEditText = findViewById(R.id.fr);
            avaliacao.setFr(frEditText.getText().toString());

            EditText satEditText = findViewById(R.id.sat);
            avaliacao.setSat(satEditText.getText().toString());

            RadioButton posOperatorio = findViewById(R.id.posOperatorio);
            avaliacao.setPosOperatorio(posOperatorio.isChecked() ? "Sim" : "Não");
            EditText posOperatorioObservacao = findViewById(R.id.posOperatorioObservacao);
            avaliacao.setPosOperatorioObservacao(posOperatorioObservacao.getText().toString());

            CheckBox amplo = findViewById(R.id.amplo);
            if (amplo.isChecked()) {
                avaliacao.getPulso().add(amplo.getText().toString());
            }
            CheckBox filiformeCheckbox = findViewById(R.id.filiforme);
            CheckBox naoPalpavelCheckbox = findViewById(R.id.naoPalpavel);

            if (filiformeCheckbox.isChecked()) {
                avaliacao.getPulso().add(filiformeCheckbox.getText().toString());
            }
            if (naoPalpavelCheckbox.isChecked()) {
                avaliacao.getPulso().add(naoPalpavelCheckbox.getText().toString());
            }

            CheckBox acordadoCheckbox = findViewById(R.id.acordado);
            CheckBox lucidoCheckbox = findViewById(R.id.lucido);
            CheckBox sonolentoCheckbox = findViewById(R.id.sonolento);
            CheckBox obnubiladoCamotosoCheckbox = findViewById(R.id.obnubiladoCamotoso);
            CheckBox sedadoCheckbox = findViewById(R.id.sedado);
            CheckBox agitadoCheckbox = findViewById(R.id.agitado);

            if (acordadoCheckbox.isChecked()) {
                avaliacao.getNivelConsciencia().add(acordadoCheckbox.getText().toString());
            }
            if (lucidoCheckbox.isChecked()) {
                avaliacao.getNivelConsciencia().add(lucidoCheckbox.getText().toString());
            }
            if (sonolentoCheckbox.isChecked()) {
                avaliacao.getNivelConsciencia().add(sonolentoCheckbox.getText().toString());
            }
            if (obnubiladoCamotosoCheckbox.isChecked()) {
                avaliacao.getNivelConsciencia().add(obnubiladoCamotosoCheckbox.getText().toString());
            }
            if (sedadoCheckbox.isChecked()) {
                avaliacao.getNivelConsciencia().add(sedadoCheckbox.getText().toString());
            }
            if (agitadoCheckbox.isChecked()) {
                avaliacao.getNivelConsciencia().add(agitadoCheckbox.getText().toString());
            }

            EditText observacaoNivelConsciencia = findViewById(R.id.observacaoNivelConsciencia);
            avaliacao.setObservacaoNivelConsciencia(observacaoNivelConsciencia.getText().toString());

            CheckBox isocoricasCheckbox = findViewById(R.id.isocoricas);
            CheckBox mioticasCheckbox = findViewById(R.id.mioticas);
            CheckBox midriaticasCheckbox = findViewById(R.id.midriaticas);
            CheckBox anisocoricaCheckbox = findViewById(R.id.anisocorica);
            CheckBox fotorreagentesCheckbox = findViewById(R.id.fotorreagentes);
            CheckBox naoFotorreagentesCheckbox = findViewById(R.id.naoFotorreagentes);

            if (isocoricasCheckbox.isChecked()) {
                avaliacao.getPupila().add(isocoricasCheckbox.getText().toString());
            }
            if (mioticasCheckbox.isChecked()) {
                avaliacao.getPupila().add(mioticasCheckbox.getText().toString());
            }
            if (midriaticasCheckbox.isChecked()) {
                avaliacao.getPupila().add(midriaticasCheckbox.getText().toString());
            }
            if (anisocoricaCheckbox.isChecked()) {
                avaliacao.getPupila().add(anisocoricaCheckbox.getText().toString());
            }
            if (fotorreagentesCheckbox.isChecked()) {
                avaliacao.getPupila().add(fotorreagentesCheckbox.getText().toString());
            }
            if (naoFotorreagentesCheckbox.isChecked()) {
                avaliacao.getPupila().add(naoFotorreagentesCheckbox.getText().toString());
            }

            EditText observacaoPupilaEditText = findViewById(R.id.observacaoPupila);
            avaliacao.setObservacaoPupila(observacaoPupilaEditText.getText().toString());

            CheckBox preservadaCheckbox = findViewById(R.id.preservada);
            CheckBox parcialmenteCheckbox = findViewById(R.id.parcialmente);
            CheckBox ausenteVisualCheckbox = findViewById(R.id.ausenteVisual);
            CheckBox exofitalmiaCheckbox = findViewById(R.id.exofitalmia);
            CheckBox enucleacaoCheckbox = findViewById(R.id.enucleacao);

            if (preservadaCheckbox.isChecked()) {
                avaliacao.getAcuidadeVisual().add(preservadaCheckbox.getText().toString());
            }
            if (parcialmenteCheckbox.isChecked()) {
                avaliacao.getAcuidadeVisual().add(parcialmenteCheckbox.getText().toString());
            }
            if (ausenteVisualCheckbox.isChecked()) {
                avaliacao.getAcuidadeVisual().add(ausenteVisualCheckbox.getText().toString());
            }
            if (exofitalmiaCheckbox.isChecked()) {
                avaliacao.getAcuidadeVisual().add(exofitalmiaCheckbox.getText().toString());
            }
            if (enucleacaoCheckbox.isChecked()) {
                avaliacao.getAcuidadeVisual().add(enucleacaoCheckbox.getText().toString());
            }

            EditText observacaoAcuidadeVisualEditText = findViewById(R.id.observacaoAcuidadeVisual);
            avaliacao.setObservacaoAcuidadeVisual(observacaoAcuidadeVisualEditText.getText().toString());


            // Para Acuidade Auditiva
            CheckBox preservadaAudioCheckbox = findViewById(R.id.preservadaAudio);
            CheckBox parcialmenteAudioCheckbox = findViewById(R.id.parcialmenteAudio);
            CheckBox ausenteAudioCheckbox = findViewById(R.id.ausenteAudio);

            if (preservadaAudioCheckbox.isChecked()) {
                avaliacao.getAcuidadeAuditiva().add(preservadaAudioCheckbox.getText().toString());
            }
            if (parcialmenteAudioCheckbox.isChecked()) {
                avaliacao.getAcuidadeAuditiva().add(parcialmenteAudioCheckbox.getText().toString());
            }
            if (ausenteAudioCheckbox.isChecked()) {
                avaliacao.getAcuidadeAuditiva().add(ausenteAudioCheckbox.getText().toString());
            }

            EditText observacaoAcuidadeAuditivaEditText = findViewById(R.id.observacaoAcuidadeAuditiva);
            avaliacao.setObservacaoAcuidadeAuditiva(observacaoAcuidadeAuditivaEditText.getText().toString());


            RadioButton simetricoCranio = findViewById(R.id.simetricoCranio);
            avaliacao.setRegiaoCranioFacial(simetricoCranio.isChecked() ? "Simétrico" : "Asimétrico");

            CheckBox integraCheckbox = findViewById(R.id.integra);
            CheckBox lesionadaCheckbox = findViewById(R.id.lesionada);
            CheckBox sangramentoCheckbox = findViewById(R.id.sangramento);
            CheckBox mucositeCheckbox = findViewById(R.id.mucosite);
            CheckBox limpaCheckbox = findViewById(R.id.limpa);
            CheckBox sujidadeCheckbox = findViewById(R.id.sujidade);

            if (integraCheckbox.isChecked()) {
                avaliacao.getCavidadeOral().add(integraCheckbox.getText().toString());
            }
            if (lesionadaCheckbox.isChecked()) {
                avaliacao.getCavidadeOral().add(lesionadaCheckbox.getText().toString());
            }
            if (sangramentoCheckbox.isChecked()) {
                avaliacao.getCavidadeOral().add(sangramentoCheckbox.getText().toString());
            }
            if (mucositeCheckbox.isChecked()) {
                avaliacao.getCavidadeOral().add(mucositeCheckbox.getText().toString());
            }
            if (limpaCheckbox.isChecked()) {
                avaliacao.getCavidadeOral().add(limpaCheckbox.getText().toString());
            }
            if (sujidadeCheckbox.isChecked()) {
                avaliacao.getCavidadeOral().add(sujidadeCheckbox.getText().toString());
            }

            EditText observacaoCavidadeOralEditText = findViewById(R.id.observacaoCavidadeOral);
            avaliacao.setObservacaoCavidadeOral(observacaoCavidadeOralEditText.getText().toString());


            // Para Ventilação
            CheckBox arCheckbox = findViewById(R.id.ar);
            CheckBox macronebulizacaoCheckbox = findViewById(R.id.macronebulizacao);
            CheckBox BIPAPCheckbox = findViewById(R.id.BIPAP);
            CheckBox cateterCheckbox = findViewById(R.id.cateter);
            CheckBox traqueostomizadoCheckbox = findViewById(R.id.traqueostomizado);
            CheckBox intubadoCheckbox = findViewById(R.id.intubado);
            CheckBox ventilacaoMecanicaCheckbox = findViewById(R.id.ventilacaoMecanica);

            if (arCheckbox.isChecked()) {
                avaliacao.getVentilacao().add(arCheckbox.getText().toString());
            }
            if (macronebulizacaoCheckbox.isChecked()) {
                avaliacao.getVentilacao().add(macronebulizacaoCheckbox.getText().toString());
            }
            if (BIPAPCheckbox.isChecked()) {
                avaliacao.getVentilacao().add(BIPAPCheckbox.getText().toString());
            }
            if (cateterCheckbox.isChecked()) {
                avaliacao.getVentilacao().add(cateterCheckbox.getText().toString());
            }
            if (traqueostomizadoCheckbox.isChecked()) {
                avaliacao.getVentilacao().add(traqueostomizadoCheckbox.getText().toString());
            }
            if (intubadoCheckbox.isChecked()) {
                avaliacao.getVentilacao().add(intubadoCheckbox.getText().toString());
            }
            if (ventilacaoMecanicaCheckbox.isChecked()) {
                avaliacao.getVentilacao().add(ventilacaoMecanicaCheckbox.getText().toString());
            }

            EditText observacaoVentilacaoEditText = findViewById(R.id.observacaoVentilacao);
            avaliacao.setObservacaoVentilacao(observacaoVentilacaoEditText.getText().toString());


            // Para Ausculta Pulmonar
            CheckBox MVUACheckbox = findViewById(R.id.MVUA);
            CheckBox MVUAdiminuDoCheckbox = findViewById(R.id.MVUAdiminuído);
            CheckBox MVUAabolidoCheckbox = findViewById(R.id.MVUAabolido);
            CheckBox estertoresCheckbox = findViewById(R.id.estertores);
            CheckBox roncosCheckbox = findViewById(R.id.roncos);
            CheckBox siblilosCheckbox = findViewById(R.id.siblilos);
            CheckBox creptaOCheckbox = findViewById(R.id.creptacao);

            if (MVUACheckbox.isChecked()) {
                avaliacao.getAuscultaPulmonar().add(MVUACheckbox.getText().toString());
            }
            if (MVUAdiminuDoCheckbox.isChecked()) {
                avaliacao.getAuscultaPulmonar().add(MVUAdiminuDoCheckbox.getText().toString());
            }
            if (MVUAabolidoCheckbox.isChecked()) {
                avaliacao.getAuscultaPulmonar().add(MVUAabolidoCheckbox.getText().toString());
            }
            if (estertoresCheckbox.isChecked()) {
                avaliacao.getAuscultaPulmonar().add(estertoresCheckbox.getText().toString());
            }
            if (roncosCheckbox.isChecked()) {
                avaliacao.getAuscultaPulmonar().add(roncosCheckbox.getText().toString());
            }
            if (siblilosCheckbox.isChecked()) {
                avaliacao.getAuscultaPulmonar().add(siblilosCheckbox.getText().toString());
            }
            if (creptaOCheckbox.isChecked()) {
                avaliacao.getAuscultaPulmonar().add(creptaOCheckbox.getText().toString());
            }

            EditText observacaoAuscultaPulmonarEditText = findViewById(R.id.observacaoAuscultaPulmonar);
            avaliacao.setObservacaoAuscultaPulmonar(observacaoAuscultaPulmonarEditText.getText().toString());


            // Para Ausculta Cardíaca
            CheckBox bulhas2Checkbox = findViewById(R.id.bulhas2);
            CheckBox bulhas3Checkbox = findViewById(R.id.bulhas3);
            CheckBox bulhas4Checkbox = findViewById(R.id.bulhas4);
            CheckBox soproCheckbox = findViewById(R.id.sopro);
            CheckBox regularCheckbox = findViewById(R.id.regular);
            CheckBox irregularCheckbox = findViewById(R.id.irregular);
            CheckBox siblilosCardiacosCheckbox = findViewById(R.id.siblilosCardiaco);

            if (bulhas2Checkbox.isChecked()) {
                avaliacao.getAuscultaCardiaca().add(bulhas2Checkbox.getText().toString());
            }
            if (bulhas3Checkbox.isChecked()) {
                avaliacao.getAuscultaCardiaca().add(bulhas3Checkbox.getText().toString());
            }
            if (bulhas4Checkbox.isChecked()) {
                avaliacao.getAuscultaCardiaca().add(bulhas4Checkbox.getText().toString());
            }
            if (soproCheckbox.isChecked()) {
                avaliacao.getAuscultaCardiaca().add(soproCheckbox.getText().toString());
            }
            if (regularCheckbox.isChecked()) {
                avaliacao.getAuscultaCardiaca().add(regularCheckbox.getText().toString());
            }
            if (irregularCheckbox.isChecked()) {
                avaliacao.getAuscultaCardiaca().add(irregularCheckbox.getText().toString());
            }
            if (siblilosCardiacosCheckbox.isChecked()) {
                avaliacao.getAuscultaCardiaca().add(siblilosCardiacosCheckbox.getText().toString());
            }

            EditText observacaoAuscultaCardiacaEditText = findViewById(R.id.observacaoAuscultaCardiaca);
            avaliacao.setObservacaoAuscultaCardiaca(observacaoAuscultaCardiacaEditText.getText().toString());


            // Para Tórax
            CheckBox simetricoCheckbox = findViewById(R.id.simetrico);
            CheckBox assimetricoCheckbox = findViewById(R.id.assimetrico);
            CheckBox escavadoCheckbox = findViewById(R.id.escavado);
            CheckBox protusoCheckbox = findViewById(R.id.protuso);

            if (simetricoCheckbox.isChecked()) {
                avaliacao.getTorax().add(simetricoCheckbox.getText().toString());
            }
            if (assimetricoCheckbox.isChecked()) {
                avaliacao.getTorax().add(assimetricoCheckbox.getText().toString());
            }
            if (escavadoCheckbox.isChecked()) {
                avaliacao.getTorax().add(escavadoCheckbox.getText().toString());
            }
            if (protusoCheckbox.isChecked()) {
                avaliacao.getTorax().add(protusoCheckbox.getText().toString());
            }

            EditText observacaoToraxEditText = findViewById(R.id.observacaoTorax);
            avaliacao.setObservacaoTorax(observacaoToraxEditText.getText().toString());


            // Para Abdome
            CheckBox globosoCheckbox = findViewById(R.id.globoso);
            CheckBox distendidoCheckbox = findViewById(R.id.distendido);
            CheckBox depressivelCheckbox = findViewById(R.id.depressivel);
            CheckBox taboaCheckbox = findViewById(R.id.taboa);
            CheckBox rigidoCheckbox = findViewById(R.id.rigido);
            CheckBox vicerapalpavelCheckbox = findViewById(R.id.vicerapalpavel);
            CheckBox piparoteMaisCheckbox = findViewById(R.id.piparoteMais);
            CheckBox peristlaseMaisCheckbox = findViewById(R.id.peristlaseMais);
            CheckBox peristalseMenosCheckbox = findViewById(R.id.peristalseMenos);
            CheckBox heperperistaltismoCheckbox = findViewById(R.id.heperperistaltismo);
            CheckBox hipoperistaltismoCheckbox = findViewById(R.id.hipoperistaltismo);

            if (globosoCheckbox.isChecked()) {
                avaliacao.getAbdome().add(globosoCheckbox.getText().toString());
            }
            if (distendidoCheckbox.isChecked()) {
                avaliacao.getAbdome().add(distendidoCheckbox.getText().toString());
            }
            if (depressivelCheckbox.isChecked()) {
                avaliacao.getAbdome().add(depressivelCheckbox.getText().toString());
            }
            if (taboaCheckbox.isChecked()) {
                avaliacao.getAbdome().add(taboaCheckbox.getText().toString());
            }
            if (rigidoCheckbox.isChecked()) {
                avaliacao.getAbdome().add(rigidoCheckbox.getText().toString());
            }
            if (vicerapalpavelCheckbox.isChecked()) {
                avaliacao.getAbdome().add(vicerapalpavelCheckbox.getText().toString());
            }
            if (piparoteMaisCheckbox.isChecked()) {
                avaliacao.getAbdome().add(piparoteMaisCheckbox.getText().toString());
            }
            if (peristlaseMaisCheckbox.isChecked()) {
                avaliacao.getAbdome().add(peristlaseMaisCheckbox.getText().toString());
            }
            if (peristalseMenosCheckbox.isChecked()) {
                avaliacao.getAbdome().add(peristalseMenosCheckbox.getText().toString());
            }
            if (heperperistaltismoCheckbox.isChecked()) {
                avaliacao.getAbdome().add(heperperistaltismoCheckbox.getText().toString());
            }
            if (hipoperistaltismoCheckbox.isChecked()) {
                avaliacao.getAbdome().add(hipoperistaltismoCheckbox.getText().toString());
            }

            EditText observacaoAbdomeEditText = findViewById(R.id.observacaoAbdome);
            avaliacao.setObservacaoAbdome(observacaoAbdomeEditText.getText().toString());


            // Para Genitália
            CheckBox integraGenitaliaCheckbox = findViewById(R.id.integraGenitalia);
            CheckBox lesionadaGenitaliaCheckbox = findViewById(R.id.lesionadaGenitalia);
            CheckBox secrecaoGenitaliaCheckbox = findViewById(R.id.secrecaoGenitalia);
            CheckBox edemaGenitaliaCheckbox = findViewById(R.id.edemaGenitalia);

            if (integraGenitaliaCheckbox.isChecked()) {
                avaliacao.getGenitalia().add(integraGenitaliaCheckbox.getText().toString());
            }
            if (lesionadaGenitaliaCheckbox.isChecked()) {
                avaliacao.getGenitalia().add(lesionadaGenitaliaCheckbox.getText().toString());
            }
            if (secrecaoGenitaliaCheckbox.isChecked()) {
                avaliacao.getGenitalia().add(secrecaoGenitaliaCheckbox.getText().toString());
            }
            if (edemaGenitaliaCheckbox.isChecked()) {
                avaliacao.getGenitalia().add(edemaGenitaliaCheckbox.getText().toString());
            }

            EditText observacaoGenitaliaEditText = findViewById(R.id.observacaoGenitalia);
            avaliacao.setObservacaoGenitalia(observacaoGenitaliaEditText.getText().toString());


            // Para Membro Superior
            CheckBox semalteracaoSuperiorCheckbox = findViewById(R.id.semalteracaoSuperior);
            CheckBox pareticoSuperiorCheckbox = findViewById(R.id.pareticoSuperior);
            CheckBox plegicoSuperiorCheckbox = findViewById(R.id.plegicoSuperior);
            CheckBox parestesiaSuperiorCheckbox = findViewById(R.id.parestesiaSuperior);

            if (semalteracaoSuperiorCheckbox.isChecked()) {
                avaliacao.getMenbroSuperior().add(semalteracaoSuperiorCheckbox.getText().toString());
            }
            if (pareticoSuperiorCheckbox.isChecked()) {
                avaliacao.getMenbroSuperior().add(pareticoSuperiorCheckbox.getText().toString());
            }
            if (plegicoSuperiorCheckbox.isChecked()) {
                avaliacao.getMenbroSuperior().add(plegicoSuperiorCheckbox.getText().toString());
            }
            if (parestesiaSuperiorCheckbox.isChecked()) {
                avaliacao.getMenbroSuperior().add(parestesiaSuperiorCheckbox.getText().toString());
            }

            EditText observacaoMenbroSuperiorEditText = findViewById(R.id.observacaoMenbroSuperior);
            avaliacao.setObservacaoMenbroSuperior(observacaoMenbroSuperiorEditText.getText().toString());


            // Para Membro Inferior
            CheckBox semalteracaoInferiorCheckbox = findViewById(R.id.semalteracaoInferior);
            CheckBox pareticoInferiorCheckbox = findViewById(R.id.pareticoInferior);
            CheckBox plegicoInferiorCheckbox = findViewById(R.id.plegicoInferior);
            CheckBox parestesiaInferiorCheckbox = findViewById(R.id.parestesiaInferior);

            if (semalteracaoInferiorCheckbox.isChecked()) {
                avaliacao.getMenbroInferior().add(semalteracaoInferiorCheckbox.getText().toString());
            }
            if (pareticoInferiorCheckbox.isChecked()) {
                avaliacao.getMenbroInferior().add(pareticoInferiorCheckbox.getText().toString());
            }
            if (plegicoInferiorCheckbox.isChecked()) {
                avaliacao.getMenbroInferior().add(plegicoInferiorCheckbox.getText().toString());
            }
            if (parestesiaInferiorCheckbox.isChecked()) {
                avaliacao.getMenbroInferior().add(parestesiaInferiorCheckbox.getText().toString());
            }

            EditText observacaoMenbroInferiorEditText = findViewById(R.id.observacaoMenbroInferior);
            avaliacao.setObservacaoMenbroInferior(observacaoMenbroInferiorEditText.getText().toString());


            RadioButton ortese = findViewById(R.id.ortese);
            avaliacao.setOrtese(ortese.isChecked() ? "Sim" : "Não");
            EditText observacaoOrtese = findViewById(R.id.observacaoOrtese);
            avaliacao.setObservacaoOrtese(observacaoOrtese.getText().toString());

            RadioButton protese = findViewById(R.id.protese);
            avaliacao.setOrtese(protese.isChecked() ? "Sim" : "Não");
            EditText observacaoProtese = findViewById(R.id.observacaoProtese);
            avaliacao.setObservacaoProtese(observacaoProtese.getText().toString());

            // Para Mucosas
            CheckBox hipocoradasCheckbox = findViewById(R.id.hipocoradas);
            CheckBox cianoticasCheckbox = findViewById(R.id.cianoticas);
            CheckBox ictéricasCheckbox = findViewById(R.id.Ictericas);
            CheckBox normocoradasCheckbox = findViewById(R.id.normocoradas);
            CheckBox hidratadasCheckbox = findViewById(R.id.hidratadas);
            CheckBox desidratadaCheckbox = findViewById(R.id.desidratada);

            if (hipocoradasCheckbox.isChecked()) {
                avaliacao.getMucosas().add(hipocoradasCheckbox.getText().toString());
            }
            if (cianoticasCheckbox.isChecked()) {
                avaliacao.getMucosas().add(cianoticasCheckbox.getText().toString());
            }
            if (ictéricasCheckbox.isChecked()) {
                avaliacao.getMucosas().add(ictéricasCheckbox.getText().toString());
            }
            if (normocoradasCheckbox.isChecked()) {
                avaliacao.getMucosas().add(normocoradasCheckbox.getText().toString());
            }
            if (hidratadasCheckbox.isChecked()) {
                avaliacao.getMucosas().add(hidratadasCheckbox.getText().toString());
            }
            if (desidratadaCheckbox.isChecked()) {
                avaliacao.getMucosas().add(desidratadaCheckbox.getText().toString());
            }

            EditText observacaoMucosasEditText = findViewById(R.id.observacaoMucosas);
            avaliacao.setObservacaoMucosas(observacaoMucosasEditText.getText().toString());


            // Para Pele
            CheckBox normocoradasPeleCheckbox = findViewById(R.id.normocoradasPele);
            CheckBox hipocoradasPeleCheckbox = findViewById(R.id.hipocoradasPele);
            CheckBox cianoticasPeleCheckbox = findViewById(R.id.cianoticasPele);
            CheckBox ictéricasPeleCheckbox = findViewById(R.id.IctericasPele);
            CheckBox hidratadasPeleCheckbox = findViewById(R.id.hidratadasPele);
            CheckBox desidratadaPeleCheckbox = findViewById(R.id.desidratadaPele);
            CheckBox petequiasPeleCheckbox = findViewById(R.id.petequiasPele);
            CheckBox hematomasPeleCheckbox = findViewById(R.id.HematomasPele);
            CheckBox equimosePeleCheckbox = findViewById(R.id.equimosePele);
            CheckBox presencaedemaequimosePeleCheckbox = findViewById(R.id.presencaedemaequimosePele);
            CheckBox anasarcaPeleCheckbox = findViewById(R.id.anasarcaPele);

            if (normocoradasPeleCheckbox.isChecked()) {
                avaliacao.getPele().add(normocoradasPeleCheckbox.getText().toString());
            }
            if (hipocoradasPeleCheckbox.isChecked()) {
                avaliacao.getPele().add(hipocoradasPeleCheckbox.getText().toString());
            }
            if (cianoticasPeleCheckbox.isChecked()) {
                avaliacao.getPele().add(cianoticasPeleCheckbox.getText().toString());
            }
            if (ictéricasPeleCheckbox.isChecked()) {
                avaliacao.getPele().add(ictéricasPeleCheckbox.getText().toString());
            }
            if (hidratadasPeleCheckbox.isChecked()) {
                avaliacao.getPele().add(hidratadasPeleCheckbox.getText().toString());
            }
            if (desidratadaPeleCheckbox.isChecked()) {
                avaliacao.getPele().add(desidratadaPeleCheckbox.getText().toString());
            }
            if (petequiasPeleCheckbox.isChecked()) {
                avaliacao.getPele().add(petequiasPeleCheckbox.getText().toString());
            }
            if (hematomasPeleCheckbox.isChecked()) {
                avaliacao.getPele().add(hematomasPeleCheckbox.getText().toString());
            }
            if (equimosePeleCheckbox.isChecked()) {
                avaliacao.getPele().add(equimosePeleCheckbox.getText().toString());
            }
            if (presencaedemaequimosePeleCheckbox.isChecked()) {
                avaliacao.getPele().add(presencaedemaequimosePeleCheckbox.getText().toString());
            }
            if (anasarcaPeleCheckbox.isChecked()) {
                avaliacao.getPele().add(anasarcaPeleCheckbox.getText().toString());
            }

            EditText observacaoPeleEditText = findViewById(R.id.observacaoPele);
            avaliacao.setObservacaoPele(observacaoPeleEditText.getText().toString());

            // todos são radioButton e EditText
            RadioButton presencaDor = findViewById(R.id.presencaDor);
            avaliacao.setPresencaDeDor(presencaDor.isChecked() ? "Sim" : "Não");
            EditText ObservacaopresencaDeDor = findViewById(R.id.ObservacaopresencaDeDor);
            avaliacao.setObservacaopresencaDeDor(ObservacaopresencaDeDor.getText().toString());
            EditText escalaDeDorEva = findViewById(R.id.escalaDeDorEva);
            avaliacao.setEscalaDeDorEva(escalaDeDorEva.getText().toString());

            RadioButton presencaLesao = findViewById(R.id.presencaLesao);
            avaliacao.setPresencaLesao(presencaLesao.isChecked() ? "Sim" : "Não");
            EditText observacaopresencaLesao = findViewById(R.id.observacaopresencaLesao);
            avaliacao.setObservacaopresencaLesao(observacaopresencaLesao.getText().toString());

            RadioButton feridaOperatoria = findViewById(R.id.feridaOperatoria);
            avaliacao.setFeridaOperatoria(feridaOperatoria.isChecked() ? "Sim" : "Não");
            EditText observacaoFeridaOperatoria = findViewById(R.id.observacaoFeridaOperatoria);
            avaliacao.setObservacaoFeridaOperatoria(observacaoFeridaOperatoria.getText().toString());

            RadioButton amputacoes = findViewById(R.id.amputacoes);
            avaliacao.setAmputacoes(amputacoes.isChecked() ? "Sim" : "Não");
            EditText observacaoAmputacoes = findViewById(R.id.observacaoAmputacoes);
            avaliacao.setObservacaoAmputacoes(observacaoAmputacoes.getText().toString());

            RadioButton historicoQueda = findViewById(R.id.historicoQueda);
            avaliacao.setHistoricoQueda(historicoQueda.isChecked() ? "Sim" : "Não");
            EditText observacaoHistoricoQueda = findViewById(R.id.observacaoHistoricoQueda);
            avaliacao.setObservacaoHistoricoQueda(observacaoHistoricoQueda.getText().toString());

            // Para Dispositivos
            CheckBox cateterPICCCheckbox = findViewById(R.id.CateterPICC);
            CheckBox acessovenosoCheckbox = findViewById(R.id.acessovenoso);
            CheckBox CVCjugularCheckbox = findViewById(R.id.CVCjugular);
            CheckBox CVCfemoralCheckbox = findViewById(R.id.CVCfemoral);
            CheckBox CVCsubclaviaCheckbox = findViewById(R.id.CVCsubclavia);
            CheckBox CVCtotalmenteCheckbox = findViewById(R.id.CVCtotalmente);
            CheckBox CVCsemiCheckbox = findViewById(R.id.CVCsemi);
            CheckBox disseccaoCheckbox = findViewById(R.id.disseccao);
            CheckBox puncaoCheckbox = findViewById(R.id.puncao);
            CheckBox drenoCheckbox = findViewById(R.id.dreno);
            CheckBox hemovacCheckbox = findViewById(R.id.hemovac);
            CheckBox JVacCheckbox = findViewById(R.id.J_vac);
            CheckBox blakeCheckbox = findViewById(R.id.blake);
            CheckBox penroseCheckbox = findViewById(R.id.penrose);
            CheckBox monitorizacaoCheckbox = findViewById(R.id.monitorizacao);
            CheckBox PICCheckbox = findViewById(R.id.PIC);
            CheckBox DVEDVPECheckbox = findViewById(R.id.DVE_DVPE);

            if (cateterPICCCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(cateterPICCCheckbox.getText().toString());
            }
            if (acessovenosoCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(acessovenosoCheckbox.getText().toString());
            }
            if (CVCjugularCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(CVCjugularCheckbox.getText().toString());
            }
            if (CVCfemoralCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(CVCfemoralCheckbox.getText().toString());
            }
            if (CVCsubclaviaCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(CVCsubclaviaCheckbox.getText().toString());
            }
            if (CVCtotalmenteCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(CVCtotalmenteCheckbox.getText().toString());
            }
            if (CVCsemiCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(CVCsemiCheckbox.getText().toString());
            }
            if (disseccaoCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(disseccaoCheckbox.getText().toString());
            }
            if (puncaoCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(puncaoCheckbox.getText().toString());
            }
            if (drenoCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(drenoCheckbox.getText().toString());
            }
            if (hemovacCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(hemovacCheckbox.getText().toString());
            }
            if (JVacCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(JVacCheckbox.getText().toString());
            }
            if (blakeCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(blakeCheckbox.getText().toString());
            }
            if (penroseCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(penroseCheckbox.getText().toString());
            }
            if (monitorizacaoCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(monitorizacaoCheckbox.getText().toString());
            }
            if (PICCheckbox.isChecked()) {
                avaliacao.getDipositivos().add(PICCheckbox.getText().toString());
            }
            if (DVEDVPECheckbox.isChecked()) {
                avaliacao.getDipositivos().add(DVEDVPECheckbox.getText().toString());
            }

            EditText observacaoDispositivosEditText = findViewById(R.id.observacaoDispositivos);
            avaliacao.setObservacaoDispositivos(observacaoDispositivosEditText.getText().toString());


            // Para Fadiga
            RadioButton naoFadigaCheckbox = findViewById(R.id.naoFadiga);
            RadioButton simFadigaCheckbox = findViewById(R.id.simFadiga);
            RadioButton leveFadigaCheckbox = findViewById(R.id.leveFadiga);
            RadioButton moderadaFadigaCheckbox = findViewById(R.id.moderadaFadiga);
            RadioButton intensaFadigaCheckbox = findViewById(R.id.intensaFadiga);

            String fadiga = "";

            if (naoFadigaCheckbox.isChecked()) {
                fadiga = naoFadigaCheckbox.getText().toString();
            } else if (simFadigaCheckbox.isChecked()) {
                fadiga = simFadigaCheckbox.getText().toString();
            } else if (leveFadigaCheckbox.isChecked()) {
                fadiga = leveFadigaCheckbox.getText().toString();
            } else if (moderadaFadigaCheckbox.isChecked()) {
                fadiga = moderadaFadigaCheckbox.getText().toString();
            } else if (intensaFadigaCheckbox.isChecked()) {
                fadiga = intensaFadigaCheckbox.getText().toString();
            }

            avaliacao.setFadiga(fadiga);

            EditText observacaoFadigaEditText = findViewById(R.id.observacaoFadiga);
            avaliacao.setObservacaoFadiga(observacaoFadigaEditText.getText().toString());


            // Para Aspectos Nutricionais
            RadioButton aspectoNormalCheckbox = findViewById(R.id.aspectonormal);
            RadioButton emagrecidoCheckbox = findViewById(R.id.emagrecido);
            RadioButton caqueticoCheckbox = findViewById(R.id.caquetico);

            String aspectosNutricionais = "";

            if (aspectoNormalCheckbox.isChecked()) {
                aspectosNutricionais = aspectoNormalCheckbox.getText().toString();
            } else if (emagrecidoCheckbox.isChecked()) {
                aspectosNutricionais = emagrecidoCheckbox.getText().toString();
            } else if (caqueticoCheckbox.isChecked()) {
                aspectosNutricionais = caqueticoCheckbox.getText().toString();
            }

            avaliacao.setAspectosNutricionais(aspectosNutricionais);

            CheckBox semAuxilioCheckbox = findViewById(R.id.semauxilio);
            CheckBox comAuxilioCheckbox = findViewById(R.id.comauxilio);
            CheckBox aceitacaoCheckbox = findViewById(R.id.aceitacao);
            CheckBox parcialCheckbox = findViewById(R.id.parcial);
            CheckBox disfagiaCheckbox = findViewById(R.id.disfagia);
            CheckBox deficitCheckbox = findViewById(R.id.deficit);
            CheckBox SNECheckbox = findViewById(R.id.SNE);
            CheckBox GTTCheckbox = findViewById(R.id.GTT);
            CheckBox retencaoCheckbox = findViewById(R.id.retencao);
            CheckBox residuoGastricoCheckbox = findViewById(R.id.residuogastrico);
            CheckBox SNGCheckbox = findViewById(R.id.SNG);
            CheckBox zeroCheckbox = findViewById(R.id.zero);
            CheckBox NPTCheckbox = findViewById(R.id.NPT);


            if (semAuxilioCheckbox.isChecked()) {
                avaliacao.getDieta().add(semAuxilioCheckbox.getText().toString());
            }
            if (comAuxilioCheckbox.isChecked()) {
                avaliacao.getDieta().add(comAuxilioCheckbox.getText().toString());
            }
            if (aceitacaoCheckbox.isChecked()) {
                avaliacao.getDieta().add(aceitacaoCheckbox.getText().toString());
            }
            if (parcialCheckbox.isChecked()) {
                avaliacao.getDieta().add(parcialCheckbox.getText().toString());
            }
            if (disfagiaCheckbox.isChecked()) {
                avaliacao.getDieta().add(disfagiaCheckbox.getText().toString());
            }
            if (deficitCheckbox.isChecked()) {
                avaliacao.getDieta().add(deficitCheckbox.getText().toString());
            }
            if (SNECheckbox.isChecked()) {
                avaliacao.getDieta().add(SNECheckbox.getText().toString());
            }
            if (GTTCheckbox.isChecked()) {
                avaliacao.getDieta().add(GTTCheckbox.getText().toString());
            }
            if (retencaoCheckbox.isChecked()) {
                avaliacao.getDieta().add(retencaoCheckbox.getText().toString());
            }
            if (residuoGastricoCheckbox.isChecked()) {
                avaliacao.getDieta().add(residuoGastricoCheckbox.getText().toString());
            }
            if (SNGCheckbox.isChecked()) {
                avaliacao.getDieta().add(SNGCheckbox.getText().toString());
            }
            if (zeroCheckbox.isChecked()) {
                avaliacao.getDieta().add(zeroCheckbox.getText().toString());
            }
            if (NPTCheckbox.isChecked()) {
                avaliacao.getDieta().add(NPTCheckbox.getText().toString());
            }

            EditText observacaoDietaEditText = findViewById(R.id.observacaoDieta);
            avaliacao.setObservacaoDieta(observacaoDietaEditText.getText().toString());


            avaliacao.setSomaEscalaGlasgow(somaGlasgow);
            avaliacao.setSomaEscalaDorNaoVerbal(somaDorNaoVerbal);

            // tabela richmond

            RadioButton Combativo = findViewById(R.id.Combativo);
            if (Combativo.isChecked()) {
                avaliacao.setRichmond(Combativo.getText().toString());
            }
            RadioButton Muitoagitado = findViewById(R.id.Muitoagitado);
            if (Muitoagitado.isChecked()) {
                avaliacao.setRichmond(Muitoagitado.getText().toString());
            }
            RadioButton Inquieto = findViewById(R.id.Inquieto);
            if (Inquieto.isChecked()) {
                avaliacao.setRichmond(Inquieto.getText().toString());
            }
            RadioButton Alerta = findViewById(R.id.Alerta);
            if (Alerta.isChecked()) {
                avaliacao.setRichmond(Alerta.getText().toString());
            }
            RadioButton Sonolento = findViewById(R.id.Sonolento);
            if (Sonolento.isChecked()) {
                avaliacao.setRichmond(Sonolento.getText().toString());
            }
            RadioButton Sedacao = findViewById(R.id.Sedacao);
            if (Sedacao.isChecked()) {
                avaliacao.setRichmond(Sedacao.getText().toString());
            }
            RadioButton moderada = findViewById(R.id.moderada);
            if (moderada.isChecked()) {
                avaliacao.setRichmond(moderada.getText().toString());
            }
            RadioButton intensa = findViewById(R.id.intensa);
            if (intensa.isChecked()) {
                avaliacao.setRichmond(intensa.getText().toString());
            }

            RadioButton Naodesperta = findViewById(R.id.Naodesperta);
            if (Naodesperta.isChecked()) {
                avaliacao.setRichmond(Naodesperta.getText().toString());
            }


            //Grau de muscosite
            RadioButton grau0 = findViewById(R.id.Grau0);
            if (grau0.isChecked()) {
                avaliacao.setGrauMucosite(grau0.getText().toString());
            }

            RadioButton grau1 = findViewById(R.id.GrauI);
            if (grau1.isChecked()) {
                avaliacao.setGrauMucosite(grau1.getText().toString());
            }

            RadioButton grau2 = findViewById(R.id.GrauII);
            if (grau2.isChecked()) {
                avaliacao.setGrauMucosite(grau2.getText().toString());
            }
            RadioButton grau3 = findViewById(R.id.GrauIII);
            if (grau3.isChecked()) {
                avaliacao.setGrauMucosite(grau3.getText().toString());
            }
            RadioButton grau4 = findViewById(R.id.GrauIV);
            if (grau4.isChecked()) {
                avaliacao.setGrauMucosite(grau4.getText().toString());
            }
            RadioButton grau5 = findViewById(R.id.GrauV);
            if (grau5.isChecked()) {
                avaliacao.setGrauMucosite(grau5.getText().toString());
            }

            CheckBox espotanea = findViewById(R.id.espontanea);
            CheckBox CVD = findViewById(R.id.CVD);
            CheckBox oliguria = findViewById(R.id.oliguria);
            CheckBox anuria = findViewById(R.id.anuria);
            CheckBox hematuria = findViewById(R.id.hematuria);
            CheckBox coloracaoanormal = findViewById(R.id.coloracaoanormal);

            if ( espotanea.isChecked() ){
                avaliacao.getDiurese().add(espotanea.getText().toString());
            }
            if ( CVD.isChecked() ){
                avaliacao.getDiurese().add(CVD.getText().toString());
            }
            if ( oliguria.isChecked() ){
                avaliacao.getDiurese().add(oliguria.getText().toString());
            }
            if ( anuria.isChecked() ){
                avaliacao.getDiurese().add(anuria.getText().toString());
            }
            if ( hematuria.isChecked() ){
                avaliacao.getDiurese().add(hematuria.getText().toString());
            }
            if ( coloracaoanormal.isChecked() ){
                avaliacao.getDiurese().add(coloracaoanormal.getText().toString());
            }

            CheckBox presente = findViewById(R.id.presente);
            CheckBox ausente = findViewById(R.id.ausente);
            CheckBox liquida = findViewById(R.id.liquida);
            CheckBox semilíquida = findViewById(R.id.semilíquida);
            CheckBox pastosa = findViewById(R.id.pastosa);
            CheckBox enrigecida = findViewById(R.id.enrigecida);
            CheckBox fecaloma = findViewById(R.id.fecaloma);
            CheckBox realizadoclister = findViewById(R.id.realizadoclister);
            CheckBox coloracaoanormalEvacuacao = findViewById(R.id.coloracaoanormalEvacuacao);

            if ( presente.isChecked() ){
                avaliacao.getEvacuacao().add(presente.getText().toString());
            }
            if ( ausente.isChecked() ){
                avaliacao.getEvacuacao().add(ausente.getText().toString());
            }
            if ( liquida.isChecked() ){
                avaliacao.getEvacuacao().add(liquida.getText().toString());
            }
            if ( semilíquida.isChecked() ){
                avaliacao.getEvacuacao().add(semilíquida.getText().toString());
            }
            if ( pastosa.isChecked() ){
                avaliacao.getEvacuacao().add(pastosa.getText().toString());
            }
            if ( enrigecida.isChecked() ){
                avaliacao.getEvacuacao().add(enrigecida.getText().toString());
            }
            if ( fecaloma.isChecked() ){
                avaliacao.getEvacuacao().add(fecaloma.getText().toString());
            }
            if ( realizadoclister.isChecked() ){
                avaliacao.getEvacuacao().add(realizadoclister.getText().toString());
            }
            if ( coloracaoanormalEvacuacao.isChecked() ){
                avaliacao.getEvacuacao().add(coloracaoanormalEvacuacao.getText().toString());
            }

            EditText observacaoEliminacoes = findViewById(R.id.observacaoEliminacoes);
            avaliacao.setObservacaoEliminacoes(observacaoEliminacoes.getText().toString());

            RadioButton tranquilo = findViewById(R.id.tranquilo);
            RadioButton ansioso = findViewById(R.id.ansioso);
            RadioButton assustado = findViewById(R.id.assustado);
            RadioButton agressivo = findViewById(R.id.agressivo);
            RadioButton agitadoEmocional = findViewById(R.id.agitadoEmocional);
            RadioButton irritado = findViewById(R.id.irritado);
            RadioButton triste = findViewById(R.id.triste);
            RadioButton choroso = findViewById(R.id.choroso);

            if ( tranquilo.isChecked() ){
                avaliacao.setEstadoEmocional(tranquilo.getText().toString());
            }
            if ( ansioso.isChecked() ){
                avaliacao.setEstadoEmocional(ansioso.getText().toString());
            }
            if ( assustado.isChecked() ){
                avaliacao.setEstadoEmocional(assustado.getText().toString());
            }
            if ( agressivo.isChecked() ){
                avaliacao.setEstadoEmocional(agressivo.getText().toString());
            }
            if ( agitadoEmocional.isChecked() ){
                avaliacao.setEstadoEmocional(agitadoEmocional.getText().toString());
            }
            if ( irritado.isChecked() ){
                avaliacao.setEstadoEmocional(irritado.getText().toString());
            }
            if ( triste.isChecked() ){
                avaliacao.setEstadoEmocional(triste.getText().toString());
            }
            if ( choroso.isChecked() ){
                avaliacao.setEstadoEmocional(choroso.getText().toString());
            }

            EditText observacaoEstadoEmocional = findViewById(R.id.observacaoEstadoEmocional);
            avaliacao.setObservacaoEstadoEmocional(observacaoEstadoEmocional.getText().toString());

            RadioButton negacao = findViewById(R.id.negacao);
            RadioButton ira = findViewById(R.id.ira);
            RadioButton barganha = findViewById(R.id.barganha);
            RadioButton depressao = findViewById(R.id.depressao);
            RadioButton aceitacaoDoenca = findViewById(R.id.aceitacaoDoenca);

            if ( negacao.isChecked() ){
                avaliacao.setEnfrentamentoDoenca(negacao.getText().toString());
            }
            if ( ira.isChecked() ){
                avaliacao.setEnfrentamentoDoenca(ira.getText().toString());
            }
            if ( barganha.isChecked() ){
                avaliacao.setEnfrentamentoDoenca(barganha.getText().toString());
            }
            if ( depressao.isChecked() ){
                avaliacao.setEnfrentamentoDoenca(depressao.getText().toString());
            }
            if ( aceitacaoDoenca.isChecked() ){
                avaliacao.setEnfrentamentoDoenca(aceitacaoDoenca.getText().toString());
            }

            CheckBox alteracao = findViewById(R.id.alteracao);
            CheckBox percepcao = findViewById(R.id.percepcao);
            CheckBox respostas = findViewById(R.id.respostas);
            CheckBox Evita = findViewById(R.id.Evita);
            CheckBox aceitacaoCorporal = findViewById(R.id.aceitacaoCorporal);

            if ( alteracao.isChecked() ){
                avaliacao.getImagemCorporal().add(alteracao.getText().toString());
            }
            if ( percepcao.isChecked() ){
                avaliacao.getImagemCorporal().add(percepcao.getText().toString());
            }
            if ( respostas.isChecked() ){
                avaliacao.getImagemCorporal().add(respostas.getText().toString());
            }
            if ( Evita.isChecked() ){
                avaliacao.getImagemCorporal().add(Evita.getText().toString());
            }
            if ( aceitacaoCorporal.isChecked() ){
                avaliacao.getImagemCorporal().add(aceitacaoCorporal.getText().toString());
            }

            CheckBox verbalizacao = findViewById(R.id.verbalizacao);
            CheckBox expressoes = findViewById(R.id.expressoes);
            CheckBox sentimentos = findViewById(R.id.sentimentos);
            CheckBox Avalia = findViewById(R.id.Avalia);


            if ( verbalizacao.isChecked() ){
                avaliacao.getAutoestima().add(verbalizacao.getText().toString());
            }
            if ( expressoes.isChecked() ){
                avaliacao.getAutoestima().add(expressoes.getText().toString());
            }
            if ( sentimentos.isChecked() ){
                avaliacao.getAutoestima().add(sentimentos.getText().toString());
            }
            if ( Avalia.isChecked() ){
                avaliacao.getAutoestima().add(Avalia.getText().toString());
            }

            RadioButton suporteRedeSocial = findViewById(R.id.suporteRedeSocial);
            avaliacao.setSuporteRedeSocial( suporteRedeSocial.isChecked() ? "Sim" : "Não");
            EditText qualSuporteRedeSocial = findViewById(R.id.qualSuporteRedeSocial);
            avaliacao.setQualSuporteRedeSocial(qualSuporteRedeSocial.getText().toString());

            RadioButton atividadeRecreativa = findViewById(R.id.atividadeRecreativa);
            avaliacao.setAtividadeRecreativa( atividadeRecreativa.isChecked() ? "Sim" : "Não");
            EditText qualAtividadeRecreativa = findViewById(R.id.qualAtividadeRecreativa);
            avaliacao.setQualAtividadeRecreativa(qualAtividadeRecreativa.getText().toString());

            EditText quantasPessoasMoram = findViewById(R.id.quantasPessoasMoram);
            avaliacao.setQuantasPessoasMoram(quantasPessoasMoram.getText().toString());

            EditText principalAconpanhante = findViewById(R.id.principalAconpanhante);
            avaliacao.setPrincipalAconpanhante(principalAconpanhante.getText().toString());

            EditText provedorRenda = findViewById(R.id.provedorRenda);
            avaliacao.setProvedorRenda(provedorRenda.getText().toString());

            EditText ondemMoram = findViewById(R.id.ondemMoram);
            avaliacao.setOndemMoram(ondemMoram.getText().toString());

            Bundle b = getIntent().getExtras();

            String id = UUID.randomUUID().toString();
            String idPaciente = b.getString("id", "");

            avaliacao.setId(id);
            avaliacao.setIdPaciente(idPaciente);

            refRelatorio.child(id).setValue(avaliacao).addOnCompleteListener( task -> {
                if ( task.isSuccessful()){
                    Toast.makeText(this, "Avaliação Cadastrada!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(this, this.getClass()));
                }
            });

        });

    }

    @SuppressLint("SetTextI18n")
    private void configurarListenerMucosite() {
        TextView valorTotalSomaDorNaoVerbal = findViewById(R.id.somaEscalaDorNaoVerbal);

        List<Integer> ids = new ArrayList<>();
        // Expressao facial

        RadioButton expressaoFacil0 = findViewById(R.id.expressaoFacil0);
        ids.add(expressaoFacil0.getId());
        expressaoFacil0.setOnClickListener(view -> {
            expressaoFacil0.setChecked(true);
            avaliacao.setExpressaoFacil(expressaoFacil0.getText().toString());
            somaDorNaoVerbal += 0;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        RadioButton expressaoFacil1 = findViewById(R.id.expressaoFacil1);
        ids.add(expressaoFacil1.getId());
        expressaoFacil1.setOnClickListener(view -> {
            expressaoFacil1.setChecked(true);
            avaliacao.setExpressaoFacil(expressaoFacil1.getText().toString());
            somaDorNaoVerbal += 1;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        RadioButton expressaoFacil2 = findViewById(R.id.expressaoFacil2);
        ids.add(expressaoFacil2.getId());
        expressaoFacil2.setOnClickListener(view -> {
            expressaoFacil2.setChecked(true);
            avaliacao.setExpressaoFacil(expressaoFacil2.getText().toString());
            somaDorNaoVerbal += 2;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });


        // atividade corpotal

        RadioButton atividadeCorporal0 = findViewById(R.id.atividadeCorporal0);
        ids.add(atividadeCorporal0.getId());
        atividadeCorporal0.setOnClickListener(view -> {
            atividadeCorporal0.setChecked(true);
            avaliacao.setAtividadeCorporal(atividadeCorporal0.getText().toString());
            somaDorNaoVerbal += 0;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        RadioButton atividadeCorporal1 = findViewById(R.id.atividadeCorporal1);
        ids.add(atividadeCorporal1.getId());
        atividadeCorporal1.setOnClickListener(view -> {
            atividadeCorporal1.setChecked(true);
            avaliacao.setAtividadeCorporal(atividadeCorporal1.getText().toString());
            somaDorNaoVerbal += 1;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        RadioButton atividadeCorporal2 = findViewById(R.id.atividadeCorporal2);
        ids.add(atividadeCorporal2.getId());
        atividadeCorporal2.setOnClickListener(view -> {
            atividadeCorporal2.setChecked(true);
            avaliacao.setAtividadeCorporal(atividadeCorporal2.getText().toString());
            somaDorNaoVerbal += 2;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });
        // defesa
        RadioButton defesal0 = findViewById(R.id.defesal0);
        ids.add(defesal0.getId());
        defesal0.setOnClickListener(view -> {
            defesal0.setChecked(true);
            avaliacao.setDefesa(defesal0.getText().toString());
            somaDorNaoVerbal += 0;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        RadioButton defesal1 = findViewById(R.id.defesal1);
        ids.add(defesal1.getId());
        defesal1.setOnClickListener(view -> {
            defesal1.setChecked(true);
            avaliacao.setDefesa(defesal1.getText().toString());
            somaDorNaoVerbal += 1;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        RadioButton defesal2 = findViewById(R.id.defesal2);
        ids.add(defesal2.getId());
        defesal2.setOnClickListener(view -> {
            defesal2.setChecked(true);
            avaliacao.setDefesa(defesal2.getText().toString());
            somaDorNaoVerbal += 2;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });
        // sinais vitais

        RadioButton sinaisVitais0 = findViewById(R.id.sinaisVitais0);
        ids.add(sinaisVitais0.getId());
        sinaisVitais0.setOnClickListener(view -> {
            sinaisVitais0.setChecked(true);
            avaliacao.setSinaisVitais(sinaisVitais0.getText().toString());
            somaDorNaoVerbal += 0;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        RadioButton sinaisVitais1 = findViewById(R.id.sinaisVitais1);
        ids.add(sinaisVitais1.getId());
        sinaisVitais1.setOnClickListener(view -> {
            sinaisVitais1.setChecked(true);
            avaliacao.setSinaisVitais(sinaisVitais1.getText().toString());
            somaDorNaoVerbal += 1;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        RadioButton sinaisVitais2 = findViewById(R.id.sinaisVitais2);
        ids.add(sinaisVitais2.getId());
        sinaisVitais2.setOnClickListener(view -> {
            sinaisVitais2.setChecked(true);
            avaliacao.setSinaisVitais(sinaisVitais2.getText().toString());
            somaDorNaoVerbal += 2;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        // alteracao respiratoria

        RadioButton alteracaoRespiratoria0 = findViewById(R.id.alteracaoRespiratoria0);
        ids.add(alteracaoRespiratoria0.getId());
        alteracaoRespiratoria0.setOnClickListener(view -> {
            alteracaoRespiratoria0.setChecked(true);
            avaliacao.setSinaisVitais(alteracaoRespiratoria0.getText().toString());
            somaDorNaoVerbal += 0;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        RadioButton alteracaoRespiratoria1 = findViewById(R.id.alteracaoRespiratoria1);
        ids.add(alteracaoRespiratoria1.getId());
        alteracaoRespiratoria1.setOnClickListener(view -> {
            alteracaoRespiratoria1.setChecked(true);
            avaliacao.setSinaisVitais(alteracaoRespiratoria1.getText().toString());
            somaDorNaoVerbal += 1;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        RadioButton alteracaoRespiratoria2 = findViewById(R.id.alteracaoRespiratoria2);
        ids.add(alteracaoRespiratoria2.getId());
        alteracaoRespiratoria2.setOnClickListener(view -> {
            alteracaoRespiratoria2.setChecked(true);
            alteracaoRespiratoria2.invalidate();
            avaliacao.setSinaisVitais(alteracaoRespiratoria2.getText().toString());
            somaDorNaoVerbal += 2;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });

        Button limparSomaDorNaoVerbal = findViewById(R.id.limparSomaDorNaoVerbal);
        limparSomaDorNaoVerbal.setOnClickListener(view -> {
            desmarcarTudo(ids);
            somaDorNaoVerbal = 0;
            valorTotalSomaDorNaoVerbal.setText("Valor Total: " + somaDorNaoVerbal);
        });
    }

    @SuppressLint("SetTextI18n")
    private void configurarListenerGlasgow() {

        TextView valorTotalGlasgow = findViewById(R.id.valorTotalGlasgow);

        List<Integer> ids = new ArrayList<>();

        // abertura ocular
        RadioButton Espotanea = findViewById(R.id.Espotanea);
        ids.add(Espotanea.getId());
        Espotanea.setOnClickListener(view -> {
            Espotanea.setChecked(true);
            avaliacao.setAberturaOcular(Espotanea.getText().toString());
            somaGlasgow += 4;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton comandoverbal = findViewById(R.id.comandoverbal);
        ids.add(comandoverbal.getId());
        comandoverbal.setOnClickListener(view -> {
            comandoverbal.setChecked(true);
            avaliacao.setAberturaOcular(comandoverbal.getText().toString());
            somaGlasgow += 3;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton dor = findViewById(R.id.dor);
        ids.add(dor.getId());
        dor.setOnClickListener(view -> {
            dor.setChecked(true);
            avaliacao.setAberturaOcular(dor.getText().toString());
            somaGlasgow += 2;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton Semabertura = findViewById(R.id.Semabertura);
        ids.add(Semabertura.getId());
        Semabertura.setOnClickListener(view -> {
            Semabertura.setChecked(true);
            avaliacao.setAberturaOcular(Semabertura.getText().toString());
            somaGlasgow += 1;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        // Resposta Verbal
        RadioButton Orientado = findViewById(R.id.Orientado);
        ids.add(Orientado.getId());
        Orientado.setOnClickListener(view -> {
            Orientado.setChecked(true);
            avaliacao.setRespostaVerbal(Orientado.getText().toString());
            somaGlasgow += 5;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton Confuso = findViewById(R.id.Confuso);
        ids.add(Confuso.getId());
        Confuso.setOnClickListener(view -> {
            Confuso.setChecked(true);
            avaliacao.setRespostaVerbal(Confuso.getText().toString());
            somaGlasgow += 4;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton Palavrasimproprias = findViewById(R.id.Palavrasimproprias);
        ids.add(Palavrasimproprias.getId());
        Palavrasimproprias.setOnClickListener(view -> {
            Palavrasimproprias.setChecked(true);
            avaliacao.setRespostaVerbal(Palavrasimproprias.getText().toString());
            somaGlasgow += 3;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton incompreensiveis = findViewById(R.id.incompreensiveis);
        ids.add(incompreensiveis.getId());
        incompreensiveis.setOnClickListener(view -> {
            incompreensiveis.setChecked(true);
            avaliacao.setRespostaVerbal(incompreensiveis.getText().toString());
            somaGlasgow += 2;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton semRespostaVerbal = findViewById(R.id.semRespostaVerbal);
        ids.add(semRespostaVerbal.getId());
        semRespostaVerbal.setOnClickListener(view -> {
            semRespostaVerbal.setChecked(true);
            avaliacao.setRespostaVerbal(semRespostaVerbal.getText().toString());
            somaGlasgow += 1;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        // Melhor Resposta Motora
        RadioButton Obedece = findViewById(R.id.Obedece);
        ids.add(Obedece.getId());
        Obedece.setOnClickListener(view -> {
            Obedece.setChecked(true);
            avaliacao.setRespostaVerbal(Obedece.getText().toString());
            somaGlasgow += 6;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton Localiza = findViewById(R.id.Localiza);
        ids.add(Localiza.getId());
        Localiza.setOnClickListener(view -> {
            Localiza.setChecked(true);
            avaliacao.setRespostaVerbal(Localiza.getText().toString());
            somaGlasgow += 5;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton afasta = findViewById(R.id.afasta);
        ids.add(afasta.getId());
        afasta.setOnClickListener(view -> {
            afasta.setChecked(true);
            avaliacao.setRespostaVerbal(afasta.getText().toString());
            somaGlasgow += 4;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton Flexao = findViewById(R.id.Flexao);
        ids.add(Flexao.getId());
        Flexao.setOnClickListener(view -> {
            Flexao.setChecked(true);
            avaliacao.setRespostaVerbal(Flexao.getText().toString());
            somaGlasgow += 3;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton Extensao = findViewById(R.id.Extensao);
        ids.add(Extensao.getId());
        Extensao.setOnClickListener(view -> {
            Extensao.setChecked(true);
            avaliacao.setRespostaVerbal(Extensao.getText().toString());
            somaGlasgow += 2;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton Semrespota = findViewById(R.id.Semrespota);
        ids.add(Semrespota.getId());
        Semrespota.setOnClickListener(view -> {
            Semrespota.setChecked(true);
            avaliacao.setRespostaVerbal(Semrespota.getText().toString());
            somaGlasgow += 1;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });
        // Reatividade Pupilar
        RadioButton Nenhuma = findViewById(R.id.Nenhuma);
        ids.add(Nenhuma.getId());
        Nenhuma.setOnClickListener(view -> {
            Nenhuma.setChecked(true);
            avaliacao.setRespostaVerbal(Nenhuma.getText().toString());
            somaGlasgow -= 2;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton Unilateral = findViewById(R.id.Unilateral);
        ids.add(Unilateral.getId());
        Unilateral.setOnClickListener(view -> {
            Unilateral.setChecked(true);
            avaliacao.setRespostaVerbal(Unilateral.getText().toString());
            somaGlasgow -= 1;
            valorTotalGlasgow.setText("Valor Total: " + somaGlasgow);
        });

        RadioButton bilateral = findViewById(R.id.bilateral);
        ids.add(bilateral.getId());
        bilateral.setOnClickListener(view -> {
            bilateral.setChecked(true);
            avaliacao.setRespostaVerbal(bilateral.getText().toString());
        });


        Button limparSomaGlasgow = findViewById(R.id.limparSomaGlasgow);

        limparSomaGlasgow.setOnClickListener(view -> {
            desmarcarTudo(ids);
            somaGlasgow = 0;
            valorTotalGlasgow.setText("Valor Total: 0");
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void desmarcarTudo(List<Integer> ids){

        for ( int i = 0 ;  i < ids.size() ; i++ ){
            RadioButton radioButton = findViewById(ids.get(i));

            if ( radioButton.isChecked() ){
                radioButton.setChecked(false);
            }
        }
    }
}