package benicio.solucoes.palienf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import benicio.solucoes.palienf.databinding.ActivityDiagnosticoSelecaoBinding;

public class DiagnosticoSelecaoActivity extends AppCompatActivity {

    private ActivityDiagnosticoSelecaoBinding mainBinding;
    List<String> lista1 = new ArrayList<>();
    List<String> lista2 = new ArrayList<>();
    List<String> lista3 = new ArrayList<>();
    List<String> lista4 = new ArrayList<>();
    List<String> lista5 = new ArrayList<>();
    List<String> lista6 = new ArrayList<>();
    List<String> lista7 = new ArrayList<>();
    List<String> lista8 = new ArrayList<>();
    List<String> lista9 = new ArrayList<>();
    List<String> lista10 = new ArrayList<>();
    List<String> lista11 = new ArrayList<>();
    List<String> lista12 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityDiagnosticoSelecaoBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getSupportActionBar().setTitle("Diagnósticos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        preencherListas();

        Bundle b = getIntent().getExtras();

        List<String> listaSelecionada = new ArrayList<>();

        switch (Objects.requireNonNull(b).getInt("d", 1)) {
            case 1:
                listaSelecionada.addAll(lista1);
                break;
            case 2:
                listaSelecionada.addAll(lista2);
                break;
            case 3:
                listaSelecionada.addAll(lista3);
                break;
            case 4:
                listaSelecionada.addAll(lista4);
                break;
            case 5:
                listaSelecionada.addAll(lista5);
                break;
            case 6:
                listaSelecionada.addAll(lista6);
                break;
            case 7:
                listaSelecionada.addAll(lista7);
                break;
            case 8:
                listaSelecionada.addAll(lista8);
                break;
            case 9:
                listaSelecionada.addAll(lista9);
                break;
            case 10:
                listaSelecionada.addAll(lista10);
                break;
            case 11:
                listaSelecionada.addAll(lista11);
                break;
            case 12:
                listaSelecionada.addAll(lista12);

        }

        for (String diagnostico : listaSelecionada) {
            TextView textView = new TextView(this);
            textView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setPadding(16, 16, 16, 16); // Define o preenchimento
            textView.setText(diagnostico);
            textView.setTextSize(16); // Define o tamanho do texto
            textView.setTypeface(null, Typeface.BOLD); // Define o estilo do texto como negrito

            mainBinding.layout.addView(textView);

            View view = new View(this);
            view.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    2)); // Define a altura da linha
            view.setBackgroundColor(getResources().getColor(R.color.black)); // Define a cor da linha

            mainBinding.layout.addView(view);


        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void preencherListas() {

        lista1.add("DIAGNÓSTICO –1- PROTEÇÃO INEFICAZ\n" +
                "Diminuição na capacidade de se proteger de ameaças internas ou externas, como doenças ou lesões.");

        lista2.add("DIAGNÓSTICO- 2-NUTRIÇÃO DESIQUILIBRADA: MENOR DO QUE AS NECESSIDADES CORPORAIS\nIngestão de nutrientes insuficiente para satisfazer as necessidades metabólicas.");
        lista2.add("DIAGNÓSTICO- 3-RISCO DE FUNÇÃO HEPÁTICA PREJUDICADA\nSuscetibilidade à diminuição na função hepática que pode comprometer a saúde.");
        lista2.add("DIAGNÓSTICO- 4-RISCO DE GLICEMIA INSTÁVEL\nSuscetibilidade à variação dos níveis séricos de glicose em relação à faixa normal que pode comprometer a saúde.");
        lista2.add("DIAGNÓSTICO- 5-RISCO DE DESEQUILÍBRIO ELETROLÍTICO\nSuscetibilidade a mudanças nos níveis de eletrólitos séricos que pode comprometer a saúde.");
        lista2.add("DIAGNÓSTICO- 6-VOLUME DE LÍQUIDO DEFICIENTE\nDiminuição do líquido intravascular, intersticial e/ou intracelular. Refere-se à desidratação, perda de água apenas, sem mudança no sódio.");
        lista2.add("DIAGNÓSTICO- 7-VOLUME DE LÍQUIDOS EXCESSIVO\nEntrada excessiva e/ou retenção de líquidos.");

        lista3.add("DIAGNÓSTICO –8- ELIMINAÇÃO URINÁRIA PREJUDICADA\nDisfunção da eliminação urinária.\nRetenção urinária\nMúltiplas causas \nDano sensório motor\nBexigoma (1)\nComatoso (32)\nParesia em membros superiores e inferiores (2)\nParestesia direita (4)\nTetraplégico (3)");
        lista3.add("DIAGNÓSTICO –9- RETENÇÃO URINÁRIA\nIncapacidade de esvaziar completamente a bexiga.\nAusência de eliminação urinária\nEliminação urinária em pequena quantidade\nBloqueio do trato urinário\nBexigoma (1)\nOligúrico (16)");
        lista3.add("DIAGNÓSTICO – 10-CONSTIPAÇÃO\nDiminuição na frequência normal de evacuação, acompanhada por eliminação difícil ou incompleta de fezes e/ou eliminação de fezes excessivamente duras e secas.\nAbdome distendido\nDor abdominal\nFezes formadas endurecidas\nMassa abdominal palpável\nRuídos intestinais hipoativos\nVômito\nDesidratação\nAlteração nos hábitos alimentares\nPrejuízo neurológico\nAbdome distendido (40)\nAbdome globoso (58)\nAbdome doloroso (6)\nEvacuação com fecaloma (1)\nAbdome rígido com presença de massa à palpação (29)\nPeristalse reduzida (14)\nEpisódio emético (2)\nAceitando dieta oral de forma parcial (6)\nDesidratado (3)\nDesorientado (2)\nComatoso  (32)");
        lista3.add("DIAGNÓSTICO – 11-RISCO DE CONSTIPAÇÃO\nSuscetibilidade à diminuição na frequência normal de evacuação, acompanhada por eliminação difícil ou incompleta de fezes, que pode comprometer a saúde.\nDesidratação\nConfusão\nAlteração nos hábitos alimentares\nIngesta de fibras insuficiente\nMotilidade gastrintestinal diminuída\nPrejuízo neurológico\nDesidratado (3)\nDesorientado (2)\nDieta zero (57)\nAceitando dieta oral de forma parcial (6)\nPeristalse reduzida (14) \nComatoso (32)");
        lista3.add("DIAGNÓSTICO –12- CONSTIPAÇÃO FUNCIONAL CRÔNICA\nEvacuação de fezes infrequente ou difícil, presente há pelo menos 3 dos 12 meses anteriores.\nAbdome distendido\nImpactação fecal\nMassa abdominal palpável\nDesidratação\nBaixa ingesta calórica\nAbdome distendido (40)\nAbdome globoso (58)\nEvacuação com fecaloma (1)\nAbdome rígido com presença de massa à palpação (29)\nDesidratado (3)\nDieta zero (57)\nAceitando dieta oral de forma parcial (6)\nRecebendo morfina (20)\nRealizado clister (1)");
        lista3.add("DIAGNÓSTICO – 13-RISCO DE CONSTIPAÇÃO FUNCIONAL CRÔNICA\nSuscetibilidade à evacuação de fezes infrequente ou difícil, presente em quase 3 dos 12 meses anteriores, que pode comprometer a saúde.\nBaixa ingestão calórica\nDesidratação\nIngestão alimentar insuficiente\nIngestão alimentar reduzida\nMobilidade prejudicada\nCâncer \nDieta zero (57)\nAceitando dieta oral de forma parcial (6)\nDesidratado (3)\nTetraplégico (3)\nParesia em membros superiores e inferiores (2)\nParestesia direita (4)\nRestrito ao leito (17)");
        lista3.add("DIAGNÓSTICO –14- DIARREIA\nEliminação de fezes soltas e não formadas.\nDor abdominal\nEvacuação de fezes líquidas\nRuídos intestinais hipoativos\nAbuso de substâncias\nAbdome doloroso (6)\nEvacuação líquida (9)\nPeristalse reduzida (14)\nPeristalse ausente (4)\nDieta enteral (80)");
        lista3.add("DIAGNÓSTICO –15- MOTILIDADE GASTRINTESTINAL DISFUNCIONAL\nAtividade peristáltica aumentada, diminuída, ineficaz ou ausente no sistema gastrointestinal.\nAbdome distendido\nDiarreia\nDor abdominal\nFezes formadas endurecidas \nMudança nos ruídos intestinais \nVômito \nAlteração dos hábitos alimentares\nImobilidade\nAlimentação enteral\nAbdome distendido (40)\nAbdome globoso (58)\nEvacuação líquida (9)\nAbdome doloroso (6)\nEvacuação com fecaloma (1)\nPeristalse reduzida (14)\nPeristalse ausente (4)\nDieta zero (57)\nDieta enteral (80)\nEpisódio emético (2) \nRestrito ao leito (17)\nTetraplégico (3)");
        lista3.add("DIAGNÓSTICO – 16-RISCO DE MOTILIDADE GASTRINTESTINAL DISFUNCIONAL\nSuscetibilidade à atividade peristáltica aumentada, diminuída, ineficaz ou ausente no sistema gastrointestinal, a qual pode comprometer a saúde.");
        lista3.add("DIAGNÓSTICO – 17-TROCA DE GASES PREJUDICADA\nExcesso ou éficit na oxigenação e/ou na eliminação de dióxido de carbono na membrana alveolocapilar.");

        lista4.add("DIAGNÓSTICO – 18-PRIVAÇÃO DO SONO\nPeríodos prolongados de tempo sem suspensão sustentada natural e periódica do estado de consciência relativa que proporciona o descanso.\nAgitação\nConfusão\nEstado de sonolência\nIrritabilidade\nDesconforto prolongado\nRegime de tratamento\nAgitação psicomotora (8)\nCriança chorosa ao manuseio (17)\nDesorientado (2)\nCriança sonolenta (18)\nRespirando com auxílio de BIPAP (16)\nRetração de fúrcula (2)\nGemente (2)");
        lista4.add("DIAGNÓSTICO – 19-CAPACIDADE DE TRANSFERÊNCIA PREJUDICADA\nLimitação de movimento independente entre duas superfícies próximas.\nCapacidade prejudicada de transferir-se entre a cama e a cadeira\nCapacidade prejudicada de transferir-se entre a cama e a posição em pé\nDor\nEquilíbrio prejudicado\nForça muscular insuficiente\nAlteração da função cognitiva\nPrejuízo neuromuscular\nRestrito ao leito (17)\nQueixando-se de dor (25)\nParesia em membros superiores e inferiores (2)\nParestesia direita (4)\nTetraplégico (3)\nRebaixamento do nível de consciência (1)\nHemiplegia (1)");
        lista4.add("DIAGNÓSTICO -20- MOBILIDADE FÍSICA PREJUDICADA\nLimitação no movimento independente e voluntário do corpo ou de uma ou mais extremidades.\nDispneia \nRedução da amplitude dos movimentos\nRedução das habilidades motoras\nControle muscular diminuído\nDor\nForça muscular diminuída\nRigidez articular\nPrejuízo neuromuscular\nDispneico (4)\nParesia em membros superiores e inferiores (2)\nParestesia direita (4)\nTetraplegia (3)\nQueixando-se de dor (25)\nDesarticulação de membro inferior (4)\nHemiplegia (1)");
        lista4.add("DIAGNÓSTICO –21- MOBILIDADE NO LEITO PREJUDICADA\nLimitação de movimento independente de uma posição para outra no leito. \nCapacidade prejudicada para virar-se de um lado para o outro\nCapacidade prejudicada de reposicionar-se na cama\nDor\nForça muscular insuficiente\nPrejuízo neuromuscular\nParesia em membros superiores e inferiores (2)\nParestesia direita (4)\nTetraplegia (3)\nQueixando-se de dor (25)\nRecebendo infusão de sedativos (86)\nHemiplegia (1)");
        lista4.add("DIAGNÓSTICO –22- SENTAR-SE PREJUDICADO\nLimitação da capacidade para, de maneira independente e intencional, atingir e/ou manter uma posição de descanso apoiada pelas nádegas e coxas com o torso ereto. \nCapacidade prejudicada de manter o torso em posição equilibrada\nDor\nEnergia insuficiente\nForça muscular insuficiente\nParesia em membros superiores e inferiores (2)\nParestesia direita (4)\nTetraplegia (3)\nQueixando-se de dor (25)\nEsforço respiratório (3)\nRetração de fúrcula (2)");
        lista4.add("DIAGNÓSTICO –23- RISCO DE SÍNDROME DO DESUSO\nSuscetibilidade à deterioração de sistemas do corpo como resultado de inatividade musculoesquelética prescrita ou inevitável que pode comprometer a saúde.\nDor \nParalisia \nQueixando de dor (25)\nQueixando-se de dor no membro esquerdo (1)");
        lista4.add("DIAGNÓSTICO –24- DÉBITO CARDÍACO DIMINUÍDO\nVolume de sangue bombeado pelo coração inadequado para atender às demandas metabólicas do organismo.\nFrequência cardíaca alterada\nEdema\nSopro cardíaco\nAlteração da pressão arterial \nDispneia\nOligúria\nPulso diminuído\nPresença de 3º bulha\nTaquicárdico (27)\nBradicárdico (4)\nAusculta cardíaca com presença de três bulhas (1)\nAusculta cardíaca com presença de sopro (1)\nDispneico (4)\nOligúrico (16)\nPulso filiforme (4)");
        lista4.add("DIAGNÓSTICO –25- PADRÃO RESPIRATÓRIO INEFICAZ\nInspiração e/ou expiração que não proporciona ventilação adequada.\nBatimento de asa de nariz\nBradipneia\nTaquipneia\nUso da musculatura acessória para respirar\nDor \nDeformidade na parede do tórax\nBatimento de asa de nariz (1)\nBradicárdica (4)\nBradipneico (1)\nRetração de fúrcula (2)\nQueixando-se de dor (25)\nTórax abaulado (1)");
        lista4.add("DIAGNÓSTICO –26- RISCO DE PERFUSÃO TISSULAR CARDÍACA DIMINUÍDA\nSuscetibilidade a uma redução na circulação cardíaca (coronariana) que pode comprometer a saúde.\nAbuso de substância\nHipertensão\nHipóxia \nHipoxemia\nRecebendo infusão de vasoativos (34)\nHipertenso (2)\nDesaturando (5)\nCianose central (2)");
        lista4.add("DIAGNÓSTICO –27- RISCO DE PERFUSÃO TISSULAR CEREBRAL INEFICAZ\nSuscetibilidade a uma redução na circulação do tecido cerebral que pode comprometer a saúde. \nHipertensão\nCoagulopatia\nLesão encefálica\nHipertenso (2)\nRecebeu plaquetas (2)\nRecebeu crioprecipitado (1)\nPlaquetopênica (1)\nPortador de leucemia (2)\nPele com petéquias (1)\nCrise convulsiva (4)");
        lista4.add("DIAGNÓSTICO –28- PERFUSÃO TISSULAR PERIFÉRICA INEFICAZ\nRedução da circulação sanguínea para a periferia que pode comprometer a saúde.\nEdema\nParestesia\nPulsos diminuídos \nAlteração da função motora\nDor em extremidades\nHipertensão\nEdema de membros superiores e inferiores (37)\nEdema de membros inferiores (20)\nPulso filiforme (4)\nHipertenso (2)\nParesia de membros superiores e inferiores (2)\nQueixando-se de dor em membros inferiores (1)");
        lista4.add("DIAGNÓSTICO –29- RESPOSTA DISFUNCIONAL AO DESMAME VENTILATÓRIO\nIncapacidade de ajustar-se a níveis diminuídos de suporte ventilatório mecânico que interrompe e prolonga o processo de desmame.\nDesconforto respiratório\nCor da pele anormal\nAgitação\nAumento da frequência cardíaca\nAumento da frequência respiratória \nRuídos adventícios respiratórios \nUso da musculatura acessória respiratória \nDor\nNutrição inadequada\nEsforço respiratório (3)\nHipocorada (128)\nAgitação psicomotora (8)\nTaquicardia (27)\nTaquidispneica (16)\nAusculta pulmonar: estertores difusos (4)\nAusculta pulmonar com presença de sibilos (4)\nQueixando-se de dor (25)\nDieta zero (57)");
        lista4.add("DIAGNÓSTICO –30- VENTILAÇÃO ESPONTÂNEA PREJUDICADA\nIncapacidade de iniciar e/ou manter respiração independente que seja adequada para sustentação da vida.\nDiminuição da saturação de oxigênio \nDispneia\nInquietação\nUso aumentado da musculatura acessória\nAumento da frequência cardíaca \nDesaturando (5)\nDispneico (4)\nCriança chorosa (17)\nRetração de fúrcula (2)\nTaquicárdica (27)\nRespirando em macronebulização (18)");
        lista4.add("DIAGNÓSTICO –31- DEFICIT NO AUTOCUIDADO PARA ALIMENTAÇÃO\nIncapacidade de alimentar-se de forma independente.\nCapacidade prejudicada de alimentar-se de uma refeição inteira\nCapacidade prejudicada de manusear os utensílios \nDor \nAlteração da junção cognitiva\nPrejuízo musculoesquelético\nAceitando dieta oral de forma parcial (6)\nHemiplegia (1)\nParesia de membros superiores e inferiores (2)\nQueixando-se de dor (25)\nRebaixamento do nível de consciência (1)");
        lista4.add("DIAGNÓSTICO –32- DEFICIT NO AUTOCUIDADO PARA BANHO\nIncapacidade de completar as atividades de limpeza do corpo de forma independente.\nCapacidade prejudicada de acessar o banheiro\nDor \nAlteração da função cognitiva \nPrejuízo neuromuscular\nCapacidade prejudicada de perceber uma parte do corpo\nParesia de membros superiores e inferiores (2)\nQueixando-se de dor (25)\nRebaixamento do nível de consciência (1)\nHemiplegian (1)");
        lista4.add("DIAGNÓSTICO –33- DEFICIT NO AUTOCUIDADO PARA HIGIENE ÍNTIMA\nIncapacidade de realizar tarefas associadas à eliminação vesical e intestinal de forma independente.");

        lista5.add("DIAGNÓSTICO –34- CONFUSÃO AGUDA\nDistúrbios reversíveis de consciência, atenção, cognição e percepção que surgem em um período de tempo breve, com duração inferior a 3 meses.\nAgitação\nAlteração da função psicomotora \nAlteração da função cognitiva\nAlteração do nível de consciência \nAbuso de substâncias\nDesidratação\nDor\nMobilidade prejudicada\nRetenção urinária\nInfecção \nAgitação psicomotora (8)\nDesorientado (2)\nRebaixamento do nível de consciência (1)\nRecebendo morfina (20)\nRecebendo infusão de sedativos (86)\nDesidratado (1)\nQueixando-se de dor (25)\nHemiplégia (1)\nBexigoma (1)\nSecreção traqueal purulenta (5)");
        lista5.add("DIAGNÓSTICO –35- RISCO DE CONFUSÃO AGUDA\nSuscetibilidade a distúrbios reversíveis de consciência, atenção, cognição e percepção que surgem em um período de tempo breve e que podem comprometer a saúde.\nAbuso de substâncias \nDesidratação\nDor\nMobilidade prejudicada\nRetenção urinária\nRecebendo morfina (20)\nRecebendo infusão de sedativos (86)\nQueixando-se de dor (25)\nParesia em membros superiores e inferiores (2)\nBexigoma (1)");
        lista5.add("DIAGNÓSTICO – 36-MEMÓRIA PREJUDICADA\nIncapacidade persistente de recordar ou recuperar partes de informações ou habilidades.\nAlteração no volume de líquidos\nHipóxia\nPrejuízo cognitivo leve\nAnemia  \nLesão encefálica\nEdema de membros superiores e inferiores (37)\nAnasarca (16)\nDesidratação (3)\nCianose central (2)\nRecebeu hemácia (2)\nRecebendo morfina (20)\nPupilas anisocóricas (10)");
        lista5.add("DIAGNÓSTICO – 37-COMUNICAÇÃO VERBAL PREJUDICADA\nCapacidade diminuída, retardada ou ausente para receber, processar, transmitir e/ou usar um sistema de símbolos.");

        lista6.add("DIAGNÓSTICO –38- RISCO DE BAIXA AUTOESTIMA SITUACIONAL\nSuscetibilidade ao desenvolvimento de uma percepção negativa sobre o seu próprio valor em resposta a uma situação atual que pode comprometer a saúde.\nAlteração da imagem corporal\nDoença física\nDesarticulação de membro inferior (4)\nLesões fúngicas na pele (6)\nOlho enucleado (6)\nPortador de leucemia (2)");
        lista6.add("DIAGNÓSTICO –39- DISTÚRBIO NA IMAGEM CORPORAL\nConfusão na imagem mental do eu físico");

        lista7.add("DIAGNÓSTICO – 40-RISCO DE TENSÃO DO PAPEL DE CUIDADOS\nSuscetibilidade a dificuldade para atender à responsabilidade, expectativas e/ou comportamentos de cuidados relacionados à família ou a pessoas significativas que pode comprometer a saúde.");

        lista8.add("DIAGNÓSTICO –41- ANSIEDADE\nSentimento vago e incômodo de desconforto ou temor, acompanhado por resposta autonômica (a fonte é frequentemente não específica ou desconhecida para o indivíduo); sentimento de apreensão causada pela antecipação de perigo. É um sinal de alerta que chama a atenção para um perigo iminente e permite ao indivíduo tomar medidas para lidar com a ameaça.\nDiarreia\nExcitação cardiovascular\nPupilas dilatadas \nDiminuição da pressão arterial\nDor abdominal\nAmeaça de morte\nEvacuação líquida (9)\nTaquicárdica (27)\nPupilar midriática (5)\nHipotensa (23)\nAbdome doloroso (58)\nFora de possibilidades curativas atuais (12)");
        lista8.add("DIAGNÓSTICO – 42-ANSIEDADE RELACIONADA À MORTE\nSentimento vago e incômodo de desconforto ou temor gerado por percepções de uma ameaça real ou imaginária à própria existência.\nMedo do processo de morrer \nDoença terminal \nFora de possibilidades curativas atuais (12)\nConverso com a equipe sobre necessidade de amparo psicológico (1)\nConverso com a mãe sobre medidas de conforto (3)\nRealizados cuidados de fim de vida (2)\nRealizados cuidados paliativos (3)\nRealizadas medidas de conforto (10)");
        lista8.add("DIAGNÓSTICO –43- MEDO\nResposta a uma ameaça percebida que é conscientemente reconhecida como perigo.\nEstado de agitação\nPalidez\nPupilas dilatadas \nVômito\nDiarreia \nDispneia\nDeficit sensorial\nAgitação psicomotora (8)\nCriança chorosa ao manuseio (17)\nHipocorada (128)\nEpisódio emético (2)\nEvacuação líquida (9)\nDispneico (4)\nRebaixamento do nível de consciência (1)");
        lista8.add("DIAGNÓSTICO –44- REGULAÇÃO DO HUMOR PREJUDICADA\nEstado mental caracterizado por mudanças no humor ou no afeto e que abarca uma série de manifestações afetivas, cognitivas, somáticas e/ou fisiológicas, variando de leves a graves.\nAgitação psicomotora\nIrritabilidade\nMudança no comportamento verbal\nAlteração do apetite\nDor\nMudança no peso\nDoença crônica\nAgitação psicomotora (8)\nCriança chorosa ao manuseio (17)\nCriança desanimada (3)\nNão verbaliza (1)\nQueixando-se de dor (25)\nEmagrecido (9)\nPortador de leucemia (2)\nMãe encontra-se muito entristecida (1)");
        lista8.add("DIAGNÓSTICO – 45-CAPACIDADE ADAPTATIVA INTRACRANIANA DIMINUÍDA\nOs mecanismos da dinâmica dos fluidos intracranianos que normalmente compensam os aumentos nos volumes intracranianos estão comprometidos, resultando em repetidos aumentos desproporcionais na pressão intracraniana (PIC) em resposta a uma variedade de estímulos nocivos e não nocivos.\nAumento desproporcional da pressão intracraniana \nLesão encefálica\nHidrocefalia (2)\nDerivação ventricular externa (4)\nDerivação ventricular peritoneal externa (6)\nPupilas anisocóricas (10)");
        lista8.add("DIAGNÓSTICO –46- RISCO DE DISREFLEXIA AUTONÔMICA\nSuscetibilidade a uma resposta não inibida do sistema nervoso simpático, que representa uma ameaça à vida, pós-choque medular, em indivíduos com lesão de medula espinhal ou lesão na sexta vértebra torácica (T6) ou acima (foi demostrada em pacientes com lesão da sétima vértebra torácica [T8]), que pode comprometer a saúde.");

        lista9.add("DIAGNÓSTICO – 47-RISCO DE RELIGIOSIDADE PREJUDICADA\nSuscetibilidade de capacidade prejudicada de confiar em crenças e/ou participar de rituais de alguma fé religiosa, o que pode comprometer a saúde.");

        lista10.add("DIAGNÓSTICO –48- RISCO DE INFECÇÃO\nSuscetibilidade à invasão e multiplicação de organismos patogênicos que pode comprometer a saúde.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nAlteração da integridade da pele\nAlteração do peristaltismo\nProcedimento invasivo\nDoença crônica\nPele ressecada e descamativa (1)\nPele friável (2)\nPeristalse reduzida (14)\nPeristalse ausente (4)\nCateter venoso central – femoral (46)\nTraqueostomizado em ventilação mecânica (51)\nPortador de leucemia (2)");
        lista10.add("DIAGNÓSTICO – 49-RISCO DE ASPIRAÇÃO\nSuscetibilidade à entrada de secreções gastrintestinais, secreções orofaríngeas, sólidos ou líquidos nas vias traqueobrônquicas que pode comprometer a saúde.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nMotilidade gastrintestinal diminuída\nAlimentação enteral\nEsvaziamento gástrico retardado\nNível de consciência diminuído\nPresença de sonda nasal\nTrauma oral\nPeristalse reduzida (14)\nDieta enteral (80)\nRetendo dieta por sonda nasoenteral (1)\nRebaixamento do nível de consciência (1)\nSonda nasoenteral (97)\nSangramento em cavidade oral (5)");
        lista10.add("DIAGNÓSTICO –50- RISCO DE BOCA SECA\nSuscetibilidade a desconforto ou dano à mucosa oral devido à quantidade reduzida ou à qualidade da saliva para hidratar a mucosa que pode comprometer a saúde.");
        lista10.add("DIAGNÓSTICO –51- RISCO DE CHOQUE\nSuscetibilidade a fluxo sanguíneo inadequado para os tecidos do corpo, podendo levar à disfunção celular que ameaça a vida, que pode comprometer a saúde.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nHipotensão\nHipoxemia\nHipóxia\nInfecção\nHipovolemia\nHipotenso (23)\nCianose central (2)\nDesaturando (5)\nPerfusão ruim (5)\nSecreção traqueal purulenta (2)\nAbscesso em ambas as axilas (4)\nCurativo de cateter com presença de secreção (1)\nDesidratado (3)");
        lista10.add("DIAGNÓSTICO – 52-DESOBSTRUÇÃO INEFICAZ DAS VIAS AÉREAS\nIncapacidade de eliminar secreções ou obstruções do trato respiratório para manter a via aérea desobstruída.\nCD\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nAlteração da frequência respiratória\nAlteração do padrão respiratório\nCianose\nDispneia\nSons respiratórios diminuídos\nAusência de tosse\nRuídos adventícios respiratórios\nCorpo estranho na via aérea\nExsudato nos alvéolos\nTaquidispneica (16)\nEsforço respiratório (3)\nCianose central (2)\nDispneica (4)\nAusculta pulmonar com MVUA diminuídos (24)\nAusculta pulmonar MVUA abolidos em hemetórax D (1)\nAusculta pulmonar com presença de sibilos (4)\nSem reflexo de tosse (6)\nTamponamento nasal para conter sangramento (2)\nSecreção traqueal espessa (15)");
        lista10.add("DIAGNÓSTICO –53- INTEGRIDADE DA MEMBRANA MUCOSA ORAL PREJUDICADA\nLesão em lábio, tecidos moles, cavidade oral e/ou orofaringe.\nCD\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nLesão oral\nSangramento\nExsudatos oronasais purulentos\nDesidratação\nHigiene oral inadequada\nNutrição inadequada\nQuimioterapia\nCavidade oral ferida pelo TOT (9)\nMucosite em cavidade oral (1)\nSangramento em cavidade oral (5)\nCavidade oral com sujidade (1)\nDesidratado (3)\nDieta zero (57)\nSecreção de orofaringe purulenta (5)\nRecebendo quimioterapia paliativa (1)");
        lista10.add("DIAGNÓSTICO – 54-RISCO DE INTEGRIDADE DA MEMBRANA ORAL PREJUDICADA\nSuscetibilidade à lesão em lábios, tecidos moles, cavidade oral e/ou orofaringe que pode comprometer a saúde.\nCD \n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS");
        lista10.add("DIAGNÓSTICO-55- INTEGRIDADE DA PELE PREJUDICADA\nEpiderme e/ou derme alterada.\nCD\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nDor\nHematoma\nSangramento\nAlteração na integridade da pele\nVermelhidão\nHipotermia\nNutrição inadequada\nExcreções \nSecreções\nQueixando-se de dor (25)\nHematoma em região inguinal (1)\nPele com hematomas (1)\nSangramento ativo em lesão por pressão sacra (1)\nPele friável (2)\nHipotérmica (8)\nDieta zero (57)\nSecreção de lesão esverdeada (2)\nSecreção em óstio de gastrostomia (1)");
        lista10.add("DIAGNÓSTICO –56- RISCO DE INTEGRIDADE DA PELE PREJUDICADA\nSuscetibilidade à alteração na epiderme e/ou derme que pode comprometer a saúde.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nExcreções\nHipotermia\nAlteração no volume de líquidos \nNutrição inadequada\nPunção arterial\nEvacuações líquidas (9)\nHipotérmico (8)\nDesidratado (3)\nAnasarca (16)\nDieta zero (57)\nCateter de pressão arterial invasiva (18)");
        lista10.add("DIAGNÓSTICO –57- INTEGRIDADE TISSULAR PREJUDICADA\nDano em membrana mucosa, córnea, sistema tegumentar, fáscia muscular, músculo, tendão, osso, cartilagem, cápsula articular e/ou ligamento.\nCD\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nDano tecidual\nDor aguda\nHematoma\nSangramento\nTecido destruído\nVolume de líquido deficiente\nVolume de líquido excessivo \nMobilidade prejudicada\nLesão de septo nasal (1)\nLesão de panturrilha (1)\nFace de dor (6)\nHematoma em olho esquerdo (3)\nSecreção peniana sanguinolenta (3)\nDesidratado (3)\nAnasarca (16)\nTetraplégico (3)");
        lista10.add("DIAGNÓSTICO –58- RISCO DE INTEGRIDADE TISSULAR PREJUDICADA\nSuscetibilidade a dano em membrana mucosa, córnea, sistema tegumentar, fáscia muscular, músculo, tendão, osso, cartilagem, cápsula articular e/ou ligamento que pode comprometer a saúde.");
        lista10.add("DIAGNÓSTICO –59- RISCO DE LESÃO\nSuscetibilidade à lesão física por condições ambientais que interagem com os recursos adaptativos e defensivos do indivíduo que pode comprometer a saúde.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nExposição a patógenos\nAlteração da função cognitiva\nAlteração da função psicomotora\nHipóxia tissular\nPerfil sanguíneo anormal\nDesorientado (2)\nAgitação psicomotora (8)\nCianose central (2)\nPlaquetopênica (1)\nRecebeu hemácias (2)\nIsolamento de contato (5)");
        lista10.add("DIAGNÓSTICO –60- RISCO DE LESÃO DO TRATO URINÁRIO\nSuscetibilidade a dano às estruturas do trato urinário em decorrência de uso de cateteres que pode comprometer a saúde.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nConfusão\nUso prolongado de cateter urinário\nVariação anatômica em órgãos pélvicos\nDesorientação (2)\nAgitação psicomotora (8)\nDiurese por cateter vesical de demora (122)\nEdema de genitália (19)");
        lista10.add("DIAGNÓSTICO – 61-RISCO DE LESÃO NA CÓRNEA\nSuscetibilidade à infecção ou lesão inflamatória no tecido da córnea que pode afetar camadas superficiais ou profundas e que pode comprometer a saúde.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nEdema periorbital\nIntubação\nOxigenioterapia\nTraqueostomia\nVentilação mecânica\nEdema de órbita ocular (5)\nEdema conjuntival (1)\nIntubado em ventilação mecânica (82)\nTraqueostomizado em ventilação mecânica (51)\nRespirando em macronebulização (18)\nRespirando com auxílio de máscara de ventury (7)\nRespirando com auxílio de BIPAP (16)");
        lista10.add("DIAGNÓSTICO –62- RISCO DE LESÃO POR PRESSÃO\nSuscetibilidade à lesão localizada da pele e/ou tecido subjacente, normalmente sobre saliência óssea, em consequência de pressão, ou pressão combinada com forças de cisalhamento (NPUAP, 2007).");
        lista10.add("DIAGNÓSTICO –63- RISCO DE QUEDAS\nSuscetibilidade aumentada a quedas que pode causar dano físico e comprometer a saúde.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nDiarreia\nMobilidade prejudicada\nAlteração na função cognitiva\nAnemia\nNeoplasia\nPrejuízo da audição\nVisão prejudicada\nEvacuação líquida (9)\nParestesia direita (4)\nDesorientado (2)\nRecebeu hemácia (2)\nPortador de leucemia (2)\nDeficiente auditivo (1)");
        lista10.add("DIAGNÓSTICO –64- RECUPERAÇÃO CIRÚRGICA RETARDADA\nExtensão do número de dias de pós-operatório necessários para iniciar e desempenhar atividade que mantém a vida, a saúde e o bem-estar.\nCD\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nEvidência de interrupção da cicatrização da área cirúrgica\nDor\nContaminação do sítio cirúrgico\nMobilidade prejudicada\nDeiscência de sutura/ secreção amarelada (1)\nLesão em sítio de derivação ventricular externa (1)\nHiperemia de ferida operatória (1)\nQueixando-se de dor (25)\nRestrito ao leito (17)");
        lista10.add("DIAGNÓSTICO –65- RISCO DE RESSECAMENTO OCULAR\nSuscetibilidade a desconforto ocular ou dano à córnea e à conjuntiva devido à quantidade reduzida ou à qualidade das lágrimas para hidratar o olho que pode comprometer a saúde.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nDano da superfície ocular\nLesão neurológica por perda de reflexo sensorial ou motor\nRegime de tratamento\nVentilação mecânica\nHiperemia ocular (2)\nHematoma em olho E (3)\nGlobo ocular com equimose (3)\nSem reflexo córneo-palpebral (2)\nRecebendo infusão de sedativos (86)\nIntubado em ventilação mecânica (82)");
        lista10.add("DIAGNÓSTICO –66- RISCO DE SANGRAMENTO\nSuscetibilidade à redução no volume de sangue que pode comprometer a saúde.");
        lista10.add("DIAGNÓSTICO –67- RISCO DE TROMBOEMBOLISMO VENOSO\nSuscetibilidade ao desenvolvimento de coágulos sanguíneos em veia profunda, geralmente na coxa, panturrilha ou extremidade superior, que pode se romper e alojar-se em outro vaso, o que pode comprometer a saúde.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nDesidratação\nMobilidade prejudicada\nComorbidade médica significativa\nDiagnóstico atual de câncer\nDesidratado (3)\nTetraplégico (3)\nHipertensão intracraniana (1)\nPortador de leucemia (2)");
        lista10.add("DIAGNÓSTICO –68- RISCO DE SUICÍDIO\nSuscetibilidade à lesão autoinfligida que ameaça a vida.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nDoença física\nDoença terminal\nTranstorno psiquiátrico\nOlho enucleado (6)\nDesarticulação de membro inferior (4)\nFora de possibilidade curativas atuais (12)\nAgitação psicomotora (8)");
        lista10.add("DIAGNÓSTICO –69- CONTAMINAÇÃO\nExposição a contaminantes ambientais em doses suficientes para causar efeitos adversos à saúde.");
        lista10.add("DIAGNÓSTICO – 70-RISCO DE REAÇÃO ALÉRGICA\nSuscetibilidade a uma reação ou resposta imunológica exagerada a substâncias que pode comprometer a saúde. \nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nExposição à substância química tóxica\nExposição a alérgeno\nRecebendo quimioterapia paliativa (1)\nRecebeu hemácia (1)");
        lista10.add("DIAGNÓSTICO –71- HIPERTERMIA\nTemperatura corporal central acima dos parâmetros diurnos normais devido à falha na termorregulação.\nCD\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nComa\nConvulsão\nHipotensão\nTaquicardia\nTaquipneia\nDesidratação\nIsquemia\nComatosa (32)\nCrise convulsiva (4)\nHipotenso (23)\nTaquicárdico (27)\nTaquidispneico (16)\nDesidratado (3)\nCianose central (2)");
        lista10.add("DIAGNÓSTICO – 72-HIPOTERMIA\nTemperatura corporal central abaixo dos parâmetros diurnos normais devido à falha na termorregulação.");
        lista10.add("DIAGNÓSTICO –73- RISCO DE HIPOTERMIA\nSuscetibilidade a uma queda inadvertida na temperatura corporal central abaixo de 36ºC, que ocorre no período entre 1 hora antes até 24 horas após cirurgia, que pode comprometer a saúde.\nCD\n(não se aplica)\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nInatividade\nRadioterapia\nDano ao hipotálamo\nControle vascular ineficaz\nNão tem resposta motora (3)\nTratamento radioterápico paliativo (1)\nSem reflexo córnio-palpebral (2)\nSem reflexo de tronco (3)\nSem reflexo de tosse (6)\nHipotenso (23)\nPerfusão ruim (5)");
        lista10.add("DIAGNÓSTICO – 74-TERMOREGULAÇÃO INEFICAZ\nOscilação da temperatura entre hipotermia e hipertermia.\nCD\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nAumento da frequência respiratória\nAumento da temperatura corporal acima dos parâmetros normais\nConvulsão\nHipertensão\nPalidez\nPele fria\nTaquicardia\nDesidratação\nLesão cefálica\nSedação\nTaquidispneico (16)\nFebril (14)\nCrise convulsiva (4)\nHipertenso (2)\nHipocorada (128)\nExtremidades frias (9)\nTaquicárdico (27)\nDesidratado (3)\nSem reflexo córneo-palpebral (2)\nRecebendo infusão de sedativos (86)");
        lista10.add("DIAGNÓSTICO –75- RISCO DE TERMOREGULAÇAO INEFICAZ\nSuscetibilidade à oscilação da temperatura entre hipotermia e hipertermia que pode comprometer a saúde.");

        lista11.add("DIAGNÓSTICO –76- CONFORTO PREJUDICADO\nPercepção de falta de conforto, de alívio e de transcendência nas dimensões física, psicoespiritual, ambiental, cultural e/ou social.\nCD\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nChoro\nRegime de tratamento\nSintomas relacionados à doença\nCriança chorosa ao manuseio (17)\nRespirando com auxílio de BIPAP (16)\nEsforço respiratório (3)");
        lista11.add("DIAGNÓSTICO –77- DOR AGUDA\nExperiência sensorial e emocional desagradável associada à lesão tissular real ou potencial, ou descrita em termos de tal lesão (International Association for the Study of Pain); ínicio súbito ou lento, de intensidade leve a intensa, com término antecipado ou previsível e com duração menor que 3 meses.\nCD\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nAlteração no apetite\nAutorrelato de dor\nExpressão facial de dor\nPupilas dilatadas\nAgente físico lesivo\nAceitando dieta oral de forma parcial (6)\nQueixando-se de dor (25)\nFace de dor (6)\nPupilas midriáticas (5)\nDreno de tórax (33)");
        lista11.add("DIAGNÓSTICO –78- DOR CRÔNICA\nExperiência sensorial e emocional desagradável associada à lesão tissular real ou potencial, ou descrita em termos de tal lesão (International Association for the Study of Pain); início súbito ou lento, de intensidade leve a intensa, constante ou recorrente, sem término antecipado ou previsível e com duração maior que 3 meses.");
        lista11.add("DIAGNÓSTICO –79- NÁUSEA\nFenômeno subjetivo de uma sensação desagradável na parte de trás da garganta e do estômago que pode ou não resultar em vômito.\nCD\nFR\nCA\nTERMOS ENCONTRADOS NOS PRONTUÁRIOS\nSalivação aumentada\nÂnsia de vômito\nPressão intracraniana elevada\nRegime de tratamento\nTumores intra-abdominais\nSialorreia (6)\nEpisódio emético (2)\nHipertensão intracraniana (1)\nRecebendo quimioterapia paliativa (1)\nAbdome rígido com presença de massa à palpação (29)");

        lista12.add("DIAGNÓSTICO –80- RISCO DE DESENVOLVIMENTO ATRASADO\nSuscetibilidade a atraso de 25% ou mais em uma ou mais áreas do comportamento social ou autorregulador, ou em habilidades cognitivas, de linguagem e motoras grossas ou finas, que pode comprometer a saúde.");

    }
}