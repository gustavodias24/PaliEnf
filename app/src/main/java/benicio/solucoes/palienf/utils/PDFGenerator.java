package benicio.solucoes.palienf.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import benicio.solucoes.palienf.R;
import benicio.solucoes.palienf.model.AvaDiariaModel;
import benicio.solucoes.palienf.model.DiagnosticoPacienteModel;
import benicio.solucoes.palienf.model.IntervencaoModel;
import benicio.solucoes.palienf.model.PacienteModel;

public class PDFGenerator {
    public static void generateAndSharePDF(Activity activity, AvaDiariaModel avaliacaoModel, PacienteModel pacienteModel, List<DiagnosticoPacienteModel> diagnosticos, Dialog dialogCarregando) {

        File documentosDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File enfermaGuiaDir = new File(documentosDir, "Palienf");

        if (!enfermaGuiaDir.exists()) {
            enfermaGuiaDir.mkdirs();
        }


        String nomeArquivo = "Relatório_acompanhamento_" + pacienteModel.getNome() + ".pdf";
        File file = new File(enfermaGuiaDir, nomeArquivo);

        try {
            PdfDocument pdfDocument = createPDF(file, avaliacaoModel, pacienteModel, activity, diagnosticos, dialogCarregando);
            activity.runOnUiThread(() -> {
                Toast.makeText(activity, "PDF salvo em Documents/Palienf", Toast.LENGTH_SHORT).show();
                compartilharPDFViaWhatsApp(activity, file);
                dialogCarregando.dismiss();
            });

        } catch (IOException e) {
            activity.runOnUiThread(() -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Aviso");
                builder.setMessage(e.getMessage());
                builder.setPositiveButton("Fechar", null);
                builder.create().show();
                e.printStackTrace();
                activity.runOnUiThread(() -> dialogCarregando.dismiss());
            });
        }
    }

    public static void generateAndSharePDFIntervencoes(Activity activity, List<IntervencaoModel> listaIntervencoes) {

        File documentosDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File enfermaGuiaDir = new File(documentosDir, "Palienf");

        if (!enfermaGuiaDir.exists()) {
            enfermaGuiaDir.mkdirs();
        }


        String nomeArquivo = "diagnosticos_" + UUID.randomUUID().toString() + ".pdf";
        File file = new File(enfermaGuiaDir, nomeArquivo);

        try {
            PdfDocument pdfDocument = createPDFIntervencoes(file, listaIntervencoes);
            activity.runOnUiThread(() -> {
                Toast.makeText(activity, "PDF salvo em Documents/Palienf", Toast.LENGTH_SHORT).show();
                compartilharPDFViaWhatsApp(activity, file);
            });

        } catch (IOException e) {
            activity.runOnUiThread(() -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle("Aviso");
                builder.setMessage(e.getMessage());
                builder.setPositiveButton("Fechar", null);
                builder.create().show();
                e.printStackTrace();
            });
        }
    }

    private static PdfDocument createPDFIntervencoes(File file, List<IntervencaoModel> listaIntervencoes) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(new FileOutputStream(file)));
        Document document = new Document(pdfDocument, PageSize.A4);
        document.setMargins(50, 50, 50, 50);
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);

        Paragraph intervencoesTitle = new Paragraph("Intervenções de Enfermagem")
                .setFont(font)
                .setBold()
                .setPadding(10.0f)
                .setFontSize(19)
                .setBackgroundColor(new DeviceRgb(16, 64, 105))
                .setFontColor(new DeviceRgb(255, 255, 255))
                .setUnderline()
                .setTextAlignment(TextAlignment.CENTER);

        document.add(intervencoesTitle);

        for ( IntervencaoModel intervencao : listaIntervencoes){

            StringBuilder strIntervencaoBuilder = new StringBuilder();
            strIntervencaoBuilder.append(intervencao.getDescricao());

            if ( intervencao.isResolvido() ){
                strIntervencaoBuilder.append(" - Aprazamento: ").append(intervencao.getHoraIntervencao());
            }
            addStringToPdf(document, "Intervenção", strIntervencaoBuilder.toString(), font);


        }

        document.close();
        return pdfDocument;
    }

    private static PdfDocument createPDF(File file, AvaDiariaModel avaliacaoModel, PacienteModel pacienteModel, Context context, List<DiagnosticoPacienteModel> diagnosticoPacienteModels, Dialog dialogCarregando) throws IOException {
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(new FileOutputStream(file)));
        Document document = new Document(pdfDocument, PageSize.A4);
        document.setMargins(50, 50, 50, 50);
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);

        Paragraph titulo = new Paragraph("Relatório de acompanhamento do paciente " + pacienteModel.getNome())
                .setFont(font)
                .setBold()
                .setFontSize(16)
                .setUnderline()
                .setTextAlignment(TextAlignment.CENTER);

        document.add(titulo);


//        Paragraph data = new Paragraph("Data: ")
//                .setFont(font)
//                .setBold()
//                .setUnderline();
//        Paragraph endereco = new Paragraph("Endereco: ")
//                .setFont(font)
//                .setBold()
//                .setUnderline();
//        Paragraph nome = new Paragraph("Nome: ")
//                .setFont(font)
//                .setBold()
//                .setUnderline();
//        Paragraph dataNascimento = new Paragraph("Data de Nascimento: ")
//                .setFont(font)
//                .setBold()
//                .setUnderline();
//        Paragraph prontuario = new Paragraph("Prontuário: ")
//                .setFont(font)
//                .setBold()
//                .setUnderline();
//
//        Paragraph sexo = new Paragraph("Sexo: ")
//                .setFont(font)
//                .setBold()
//                .setUnderline();


//        Paragraph dataValor = new Paragraph(avaliacaoModel.getData().replace("_", "/"));
//        Paragraph prontuarioValor = new Paragraph(pacienteModel.getProntuário());
//        Paragraph sexoValor = new Paragraph(pacienteModel.getSexo().isEmpty() ? "Não Informado" : pacienteModel.getSexo());
//        Paragraph dataNascimentoValor = new Paragraph(pacienteModel.getDataNascimento());
//        Paragraph nomeValor = new Paragraph(pacienteModel.getNome());

        StringBuilder enderecoBuilder = new StringBuilder();
        enderecoBuilder.append(pacienteModel.getCidade())
                .append(",").append(pacienteModel.getBairro())
                .append(",").append(pacienteModel.getCep())
                .append(",").append("Nº ").append(pacienteModel.getNumero());

        Paragraph enderecoValor = new Paragraph(
                enderecoBuilder.toString().isEmpty() ? "Não Informado" : enderecoBuilder.toString()
        );


//        document.add(data);
//        document.add(dataValor);
//
//        document.add(prontuario);
//        document.add(prontuarioValor);
//
//        document.add(dataNascimento);
//        document.add(dataNascimentoValor);
//
//        document.add(nome);
//        document.add(nomeValor);
//
//        document.add(sexo);
//        document.add(sexoValor);
//
//        document.add(endereco);
//        document.add(enderecoValor);


        document.add(new Paragraph("Nome: ")
                .setFont(font)
                .setBold()
                .add(pacienteModel.getNome())
                .setFont(font));

        document.add(new Paragraph("Data de Nascimento: ")
                .setFont(font)
                .setBold()
                .add(pacienteModel.getDataNascimento() != null ? pacienteModel.getDataNascimento() : "Não Informado")
                .setFont(font));

        document.add(new Paragraph("CPF: ")
                .setFont(font)
                .setBold()
                .add(pacienteModel.getCpf())
                .setFont(font));

        document.add(new Paragraph("Prontuário: ")
                .setFont(font)
                .setBold()
                .add(pacienteModel.getProntuário() != null ? pacienteModel.getProntuário() : "Não Informado")
                .setFont(font));

        document.add(new Paragraph("Cidade: ")
                .setFont(font)
                .setBold()
                .add(pacienteModel.getCidade())
                .setFont(font));

        document.add(new Paragraph("Bairro: ")
                .setFont(font)
                .setBold()
                .add(pacienteModel.getBairro())
                .setFont(font));

        document.add(new Paragraph("CEP: ")
                .setFont(font)
                .setBold()
                .add(pacienteModel.getCep())
                .setFont(font));

        document.add(new Paragraph("Número: ")
                .setFont(font)
                .setBold()
                .add(pacienteModel.getNumero())
                .setFont(font));

        document.add(new Paragraph("Ponto de Referência: ")
                .setFont(font)
                .setBold()
                .add(pacienteModel.getPontoReferencia())
                .setFont(font));

        document.add(new Paragraph("Sexo: ")
                .setFont(font)
                .setBold()
                .add(pacienteModel.getSexo() != null ? pacienteModel.getSexo() : "Não Informado")
                .setFont(font));

        addBooleanToPdf(document, "Existe necessidades financeiras para prover insumos mínimos?", pacienteModel.isNecessidadeFinanceira(), pacienteModel.getDescriNecessidadeFinanceira(), font);
        addBooleanToPdf(document, "Gostaria que outros membros da família estejam presentes quando a equipe de saúde for passar informações sobre a saúde da criança/adolescente?", pacienteModel.isNecessidadeMenbroFamilia(), pacienteModel.getDescriNecessidadeMenbroFamilia(), font);
        addBooleanToPdf(document, "Crença/religião ?", pacienteModel.isCrencaReligiao(), pacienteModel.getDescriCrencaReligiao(), font);
        addBooleanToPdf(document, "Você gostaria de receber a visita do seu líder espiritual?", pacienteModel.isCrencaVistirarLider(), null, font);
        addBooleanToPdf(document, "Existe algum rito espiritual que você gostaria de realizar no hospital?", pacienteModel.isCrencaRitoEspiritual(), pacienteModel.getDescriRitoEspiritual(), font);

        addStringToPdf(document, "Descrição Geral", pacienteModel.getAlergias(), font);
        addStringToPdf(document, "Internação Recente", pacienteModel.getInternacaoRecente(), font);
        addStringToPdf(document, "Doença Oncológica", pacienteModel.getDoencaOncológica(), font);
        addStringToPdf(document, "Procedência", pacienteModel.getProcedencia(), font);
        addStringToPdf(document, "Motivo Internação", pacienteModel.getMotidaInternacao(), font);
        addStringToPdf(document, "Nome do Acompanhante", pacienteModel.getNomeCompanhante(), font);
        addStringToPdf(document, "Peso", pacienteModel.getPeso(), font);
        addStringToPdf(document, "Altura", pacienteModel.getAltura(), font);
        addStringToPdf(document, "SC", pacienteModel.getSc(), font);
        addStringToPdf(document, "IMC", pacienteModel.getImc(), font);
        addStringToPdf(document, "Número do Leito", pacienteModel.getNumeroLeito(), font);


        String dataDaAvaliacao = "( " + avaliacaoModel.getData().replace("_", "/") + " )";
        Paragraph avaliacaoFisica = new Paragraph("Avaliação Física " + dataDaAvaliacao)
                .setFont(font)
                .setBold()
                .setPadding(10.0f)
                .setFontSize(19)
                .setBackgroundColor(new DeviceRgb(16, 64, 105))
                .setFontColor(new DeviceRgb(255, 255, 255))
                .setUnderline()
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph escalas = new Paragraph("Escalas" + dataDaAvaliacao)
                .setFont(font)
                .setBold()
                .setPadding(10.0f)
                .setFontSize(19)
                .setBackgroundColor(new DeviceRgb(16, 64, 105))
                .setFontColor(new DeviceRgb(255, 255, 255))
                .setUnderline()
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph eliminacoes = new Paragraph("Eliminações " + dataDaAvaliacao)
                .setFont(font)
                .setBold()
                .setPadding(10.0f)
                .setFontSize(19)
                .setBackgroundColor(new DeviceRgb(16, 64, 105))
                .setFontColor(new DeviceRgb(255, 255, 255))
                .setUnderline()
                .setTextAlignment(TextAlignment.CENTER);
        Paragraph necessidadesPRE = new Paragraph("Necessidades Psicossociais/Religiosidade e espiritualidade " + dataDaAvaliacao)
                .setFont(font)
                .setBold()
                .setPadding(10.0f)
                .setFontSize(19)
                .setBackgroundColor(new DeviceRgb(16, 64, 105))
                .setFontColor(new DeviceRgb(255, 255, 255))
                .setUnderline()
                .setTextAlignment(TextAlignment.CENTER);

        Paragraph diagnosticus = new Paragraph("Diagnósticos " + dataDaAvaliacao)
                .setFont(font)
                .setBold()
                .setPadding(10.0f)
                .setFontSize(19)
                .setBackgroundColor(new DeviceRgb(16, 64, 105))
                .setFontColor(new DeviceRgb(255, 255, 255))
                .setUnderline()
                .setTextAlignment(TextAlignment.CENTER);


        document.add(avaliacaoFisica);

        addStringToPdf(document, "Temperatura", avaliacaoModel.getTemperatura(), font);
        addStringToPdf(document, "PA", avaliacaoModel.getPa(), font);

        addStringToPdf(document, "FC", avaliacaoModel.getFc(), font);
        addStringToPdf(document, "FR", avaliacaoModel.getFr(), font);
        addStringToPdf(document, "Sat", avaliacaoModel.getSat(), font);

        addStringToPdf(document, "Pós-operatório", avaliacaoModel.getPosOperatorio(), font);
        addStringToPdf(document, "Pós-operatório obs", avaliacaoModel.getPosOperatorioObservacao(), font);
        addStringToPdf(document, "Pulso", formatListToString(avaliacaoModel.getPulso()), font);
        addStringToPdf(document, "Nível de Consciência", formatListToString(avaliacaoModel.getNivelConsciencia()), font);
        addStringToPdf(document, "Nível de Consciência obs", avaliacaoModel.getObservacaoNivelConsciencia(), font);


        addStringToPdf(document, "Pupila", formatListToString(avaliacaoModel.getPupila()), font);
        addStringToPdf(document, "Pupila obs", avaliacaoModel.getObservacaoPupila(), font);
        addStringToPdf(document, "Acuidade Visual", formatListToString(avaliacaoModel.getAcuidadeVisual()), font);
        addStringToPdf(document, "Acuidade Visual obs", avaliacaoModel.getObservacaoAcuidadeVisual(), font);
        addStringToPdf(document, "Acuidade Auditiva", formatListToString(avaliacaoModel.getAcuidadeAuditiva()), font);
        addStringToPdf(document, "Acuidade Auditiva obs", avaliacaoModel.getObservacaoAcuidadeAuditiva(), font);
        addStringToPdf(document, "Região Craniofacial", avaliacaoModel.getRegiaoCranioFacial(), font);
        addStringToPdf(document, "Cavidade Oral", formatListToString(avaliacaoModel.getCavidadeOral()), font);
        addStringToPdf(document, "Cavidade Oral obs", avaliacaoModel.getObservacaoCavidadeOral(), font);
        addStringToPdf(document, "Ventilação", formatListToString(avaliacaoModel.getVentilacao()), font);
        addStringToPdf(document, "Ventilação obs", avaliacaoModel.getObservacaoVentilacao(), font);
        addStringToPdf(document, "Ausculta Pulmonar", formatListToString(avaliacaoModel.getAuscultaPulmonar()), font);
        addStringToPdf(document, "Ausculta Pulmonar obs", avaliacaoModel.getObservacaoAuscultaPulmonar(), font);
        addStringToPdf(document, "Ausculta Cardíaca", formatListToString(avaliacaoModel.getAuscultaCardiaca()), font);
        addStringToPdf(document, "Ausculta Cardíaca obs", avaliacaoModel.getObservacaoAuscultaCardiaca(), font);
        addStringToPdf(document, "Tórax", formatListToString(avaliacaoModel.getTorax()), font);
        addStringToPdf(document, "Tórax obs", avaliacaoModel.getObservacaoTorax(), font);
        addStringToPdf(document, "Abdome", formatListToString(avaliacaoModel.getAbdome()), font);
        addStringToPdf(document, "Abdome obs", avaliacaoModel.getObservacaoAbdome(), font);


        addStringToPdf(document, "Genitália", formatListToString(avaliacaoModel.getGenitalia()), font);
        addStringToPdf(document, "Genitália obs", avaliacaoModel.getObservacaoGenitalia(), font);
        addStringToPdf(document, "Membro Superior", formatListToString(avaliacaoModel.getMenbroSuperior()), font);
        addStringToPdf(document, "Membro Superior obs", avaliacaoModel.getObservacaoMenbroSuperior(), font);
        addStringToPdf(document, "Membro Inferior", formatListToString(avaliacaoModel.getMenbroInferior()), font);
        addStringToPdf(document, "Membro Inferior obs", avaliacaoModel.getObservacaoMenbroInferior(), font);
        addStringToPdf(document, "Órtese", avaliacaoModel.getOrtese(), font);
        addStringToPdf(document, "Órtese obs", avaliacaoModel.getObservacaoOrtese(), font);
        addStringToPdf(document, "Prótese", avaliacaoModel.getProtese(), font);
        addStringToPdf(document, "Prótese obs", avaliacaoModel.getObservacaoProtese(), font);


        addStringToPdf(document, "Mucosas", formatListToString(avaliacaoModel.getMucosas()), font);
        addStringToPdf(document, "Mucosas obs", avaliacaoModel.getObservacaoMucosas(), font);
        addStringToPdf(document, "Pele", formatListToString(avaliacaoModel.getPele()), font);
        addStringToPdf(document, "Pele obs", avaliacaoModel.getObservacaoPele(), font);
        addStringToPdf(document, "Presença de Dor", avaliacaoModel.getPresencaDeDor(), font);
        addStringToPdf(document, "Presença de Dor obs", avaliacaoModel.getPresencaDeDor(), font);
        addStringToPdf(document, "Escala de Dor Eva", avaliacaoModel.getEscalaDeDorEva(), font);
        addStringToPdf(document, "Presença de Lesão", avaliacaoModel.getPresencaLesao(), font);
        addStringToPdf(document, "Presença de Lesão obs", avaliacaoModel.getObservacaopresencaLesao(), font);


        addStringToPdf(document, "Ferida Operatória", avaliacaoModel.getFeridaOperatoria(), font);
        addStringToPdf(document, "Ferida Operatória obs", avaliacaoModel.getObservacaoFeridaOperatoria(), font);
        addStringToPdf(document, "Amputações", avaliacaoModel.getAmputacoes(), font);
        addStringToPdf(document, "Amputações obs", avaliacaoModel.getObservacaoAmputacoes(), font);
        addStringToPdf(document, "Histórico de Queda", avaliacaoModel.getHistoricoQueda(), font);
        addStringToPdf(document, "Histórico de Queda obs", avaliacaoModel.getObservacaoHistoricoQueda(), font);
        addStringToPdf(document, "Risco de Queda", avaliacaoModel.getRiscoQueda(), font);
        addStringToPdf(document, "Risco de Queda obs", avaliacaoModel.getObservacaoRiscoQueda(), font);
        addStringToPdf(document, "Dispositivos", formatListToString(avaliacaoModel.getDipositivos()), font);
        addStringToPdf(document, "Dispositivos obs", avaliacaoModel.getObservacaoDispositivos(), font);
        addStringToPdf(document, "Fadiga", avaliacaoModel.getFadiga(), font);
        addStringToPdf(document, "Fadiga obs", avaliacaoModel.getObservacaoFadiga(), font);
        addStringToPdf(document, "Aspectos Nutricionais", avaliacaoModel.getAspectosNutricionais(), font);
        addStringToPdf(document, "Aspectos Nutricionais obs", avaliacaoModel.getObservacaoAspectosNutricionais(), font);
        addStringToPdf(document, "Dieta", formatListToString(avaliacaoModel.getDieta()), font);
        addStringToPdf(document, "Dieta obs", avaliacaoModel.getObservacaoDieta(), font);

        addStringToPdf(document, "Condutas", avaliacaoModel.getCondutas(), font);



        document.add(escalas);

//        addImage(context, document, R.drawable.table1);

//        document.add(new Paragraph("Abertura Ocular: ")
//                .setFont(font)
//                .setBold()
//                .add(avaliacaoModel.getAberturaOcular())
//                .setFont(font));
//
//        document.add(new Paragraph("Resposta Verbal: ")
//                .setFont(font)
//                .setBold()
//                .add(avaliacaoModel.getRespostaVerbal())
//                .setFont(font));
//
//        document.add(new Paragraph("Resposta Motora: ")
//                .setFont(font)
//                .setBold()
//                .add(avaliacaoModel.getRespostaMotora())
//                .setFont(font));
//
//        document.add(new Paragraph("Reatividade Pupilar: ")
//                .setFont(font)
//                .setBold()
//                .add(avaliacaoModel.getReatividadePupilar())
//                .setFont(font));

        document.add(new Paragraph("Soma Escala Glasgow: ")
                .setFont(font)
                .setBold()
                .add(String.valueOf(avaliacaoModel.getSomaEscalaGlasgow()))
                .setFont(font));

//        addImage(context, document, R.drawable.table2);

//        document.add(new Paragraph("Expressão Facial: ")
//                .setFont(font)
//                .setBold()
//                .add(avaliacaoModel.getExpressaoFacil())
//                .setFont(font));
//
//        document.add(new Paragraph("Atividade Corporal: ")
//                .setFont(font)
//                .setBold()
//                .add(avaliacaoModel.getAtividadeCorporal())
//                .setFont(font));
//
//        document.add(new Paragraph("Defesa: ")
//                .setFont(font)
//                .setBold()
//                .add(avaliacaoModel.getDefesa())
//                .setFont(font));
//
//        document.add(new Paragraph("Sinais Vitais: ")
//                .setFont(font)
//                .setBold()
//                .add(avaliacaoModel.getSinaisVitais())
//                .setFont(font));
//
//        document.add(new Paragraph("Alteração Respiratória: ")
//                .setFont(font)
//                .setBold()
//                .add(avaliacaoModel.getAlteracaoRespiratoria())
//                .setFont(font));

        document.add(new Paragraph("Soma Escala Dor Não Verbal: ")
                .setFont(font)
                .setBold()
                .add(String.valueOf(avaliacaoModel.getSomaEscalaDorNaoVerbal()))
                .setFont(font));

//        addImage(context, document, R.drawable.table3);

        document.add(new Paragraph("Escala RASS: ")
                .setFont(font)
                .setBold()
                .add(String.valueOf(avaliacaoModel.getRichmond()))
                .setFont(font));

//        addImage(context, document, R.drawable.table4);

        document.add(new Paragraph("Escala de Mucosite: ")
                .setFont(font)
                .setBold()
                .add(String.valueOf(avaliacaoModel.getGrauMucosite()))
                .setFont(font));

        document.add(eliminacoes);

        addStringToPdf(document, "Diurese", formatListToString(avaliacaoModel.getDiurese()), font);
        addStringToPdf(document, "Evacuação", formatListToString(avaliacaoModel.getEvacuacao()), font);
        addStringToPdf(document, "Eliminações obs", avaliacaoModel.getObservacaoEliminacoes(), font);


        document.add(necessidadesPRE);

        addStringToPdf(document, "Estado Emocional", avaliacaoModel.getEstadoEmocional(), font);
        addStringToPdf(document, "Estado Emocional obs", avaliacaoModel.getObservacaoEstadoEmocional(), font);
        addStringToPdf(document, "Enfrentamento da Doença", avaliacaoModel.getEnfrentamentoDoenca(), font);
        addStringToPdf(document, "Enfrentamento da Doença obs", avaliacaoModel.getObsEnfrentamentoDoenda(), font);
        addStringToPdf(document, "Imagem Corporal", formatListToString(avaliacaoModel.getImagemCorporal()), font);
        addStringToPdf(document, "Imagem Corporal obs", avaliacaoModel.getObsImagemCorporal(), font);
        addStringToPdf(document, "Autoestima", formatListToString(avaliacaoModel.getAutoestima()), font);
        addStringToPdf(document, "Autoestima obs", avaliacaoModel.getObsAutoestima(), font);
        addStringToPdf(document, "Suporte Rede Social", avaliacaoModel.getSuporteRedeSocial(), font);
        addStringToPdf(document, "Qual Suporte Rede Social", avaliacaoModel.getQualSuporteRedeSocial(), font);
        addStringToPdf(document, "Atividade Recreativa", avaliacaoModel.getAtividadeRecreativa(), font);
        addStringToPdf(document, "Qual Atividade Recreativa", avaliacaoModel.getQualAtividadeRecreativa(), font);


        addStringToPdf(document, "Quantas Pessoas Moram", pacienteModel.getQuantasPessoasMoram(), font);
        addStringToPdf(document, "Principal Acompanhante", pacienteModel.getPrincipalAconpanhante(), font);
        addStringToPdf(document, "Provedor de Renda", pacienteModel.getProvedorRenda(), font);
        addStringToPdf(document, "Onde Moram", pacienteModel.getOndemMoram(), font);


        document.add(diagnosticus);

        for (DiagnosticoPacienteModel diagnostico : diagnosticoPacienteModels) {

            if (diagnostico.isAtivo()) {
                document.add(new Paragraph(diagnostico.getTitulo() + "( " + diagnostico.getDataCriacao() + " )")
                        .setFont(font)
                        .setBold());

//            document.add(new Paragraph(diagnostico.getSubTitulo())
//                    .add(avaliacaoModel.getOndemMoram())
//                    .setItalic()
//                    .setFont(font));

                String diagnosticoResolvido = "Não";

                if (!diagnostico.isAtivo()) {
                    diagnosticoResolvido = "Sim";
                }

                document.add(new Paragraph("Diagnóstico Resolvido? ")
                        .setFont(font)
                        .setBold()
                        .add(diagnosticoResolvido)
                        .setFont(font));

                document.add(new Paragraph("Noc's Selecionadas:\n ")
                        .setFont(font)
                        .setBold()
                        .add(formatListToString(diagnostico.getNocSelecionadas()).replace(",", "\n").replace("\n\n", "\n"))
                        .setFont(font));

                for (IntervencaoModel intervencaoModel : diagnostico.getIntervencoes()) {

                    if (intervencaoModel.isSelecionado()) {
                        document.add(new Paragraph("Intervenção: ")
                                .setFont(font)
                                .setBold()
                                .add(intervencaoModel.getDescricao())
                                .setFont(font));


                        if (intervencaoModel.isResolvido()) {
                            addStringToPdf(document, "Hora da Resolução: ", intervencaoModel.getHoraIntervencao(), font);

                        }
                    }
                }
            }

        }

        document.close();
        return pdfDocument;
    }

    private static void addImage(Context context, Document document, int id) {
        Drawable d = context.getDrawable(id);
        Bitmap bitmap = ((BitmapDrawable) d).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bimapData = stream.toByteArray();
        ImageData imageData = ImageDataFactory.create(bimapData);
        Image image = new Image(imageData);

        document.add(image);
    }

    private static void compartilharPDFViaWhatsApp(Activity activity, File file) {
        Uri contentUri = FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", file);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("application/pdf");
        intent.putExtra(Intent.EXTRA_STREAM, contentUri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        activity.startActivity(intent);
    }

    private static String formatListToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));
            if (i < list.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    private static void addBooleanToPdf(Document document, String label, boolean value, String description, PdfFont font) {
        Paragraph paragraph = new Paragraph(label + ": ")
                .setFont(font)
                .setBold()
                .add(value ? "Sim" : "Não")
                .setFont(font);

        if (description != null && !description.isEmpty()) {
            paragraph.add(new Paragraph( ", " + description)
                    .setFont(font)
                    .setBold()
                    .setFont(font));
        }

        document.add(paragraph);
    }

    private static void addStringToPdf(Document document, String label, String value, PdfFont font) {
        if (value != null && !value.isEmpty()) {
            document.add(new Paragraph(label + ": ")
                    .setFont(font)
                    .setBold()
                    .add(value != null ? value : "Não Informado")
                    .setFont(font));
        }
    }

}