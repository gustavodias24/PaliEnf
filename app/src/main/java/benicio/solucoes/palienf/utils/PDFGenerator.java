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
            });
        }
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


        Paragraph data = new Paragraph("Data: ")
                .setFont(font)
                .setBold()
                .setUnderline();
        Paragraph endereco = new Paragraph("Endereco: ")
                .setFont(font)
                .setBold()
                .setUnderline();
        Paragraph nome = new Paragraph("Nome: ")
                .setFont(font)
                .setBold()
                .setUnderline();
        Paragraph dataNascimento = new Paragraph("Data de Nascimento: ")
                .setFont(font)
                .setBold()
                .setUnderline();
        Paragraph prontuario = new Paragraph("Prontuário: ")
                .setFont(font)
                .setBold()
                .setUnderline();

        Paragraph sexo = new Paragraph("Sexo: ")
                .setFont(font)
                .setBold()
                .setUnderline();


//        Paragraph dataValor = new Paragraph(avaliacaoModel.getData());
        Paragraph prontuarioValor = new Paragraph(pacienteModel.getProntuário());
        Paragraph sexoValor = new Paragraph(pacienteModel.getSexo().isEmpty() ? "Não Informado" : pacienteModel.getSexo());
        Paragraph dataNascimentoValor = new Paragraph(pacienteModel.getDataNascimento());
        Paragraph nomeValor = new Paragraph(pacienteModel.getNome());

        StringBuilder enderecoBuilder = new StringBuilder();
        enderecoBuilder.append(pacienteModel.getCidade())
                .append(",").append(pacienteModel.getBairro())
                .append(",").append(pacienteModel.getCep())
                .append(",").append("Nº ").append(pacienteModel.getNumero());

        Paragraph enderecoValor = new Paragraph(
                enderecoBuilder.toString().isEmpty() ? "Não Informado" : enderecoBuilder.toString()
        );


        document.add(data);
//        document.add(dataValor);

        document.add(prontuario);
        document.add(prontuarioValor);

        document.add(dataNascimento);
        document.add(dataNascimentoValor);

        document.add(nome);
        document.add(nomeValor);

        document.add(sexo);
        document.add(sexoValor);

        document.add(endereco);
        document.add(enderecoValor);

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

        document.add(new Paragraph("Temperatura: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getTemperatura())
                .setFont(font));

        document.add(new Paragraph("PA: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getPa())
                .setFont(font));

        document.add(new Paragraph("FC: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getFc())
                .setFont(font));

        document.add(new Paragraph("FR: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getFr())
                .setFont(font));

        document.add(new Paragraph("Sat: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getSat())
                .setFont(font));

        document.add(new Paragraph("Pós-operatório: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getPosOperatorio())
                .setFont(font));

        document.add(new Paragraph("Observação Pós-operatória: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getPosOperatorioObservacao())
                .setFont(font));

        document.add(new Paragraph("Pulso: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getPulso()))
                .setFont(font));

        document.add(new Paragraph("Nível de Consciência: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getNivelConsciencia()))
                .setFont(font));

        document.add(new Paragraph("Observação Nível de Consciência: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoNivelConsciencia())
                .setFont(font));

        document.add(new Paragraph("Pupila: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getPupila()))
                .setFont(font));

        document.add(new Paragraph("Observação Pupila: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoPupila())
                .setFont(font));

        document.add(new Paragraph("Acuidade Visual: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getAcuidadeVisual()))
                .setFont(font));

        document.add(new Paragraph("Observação Acuidade Visual: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoAcuidadeVisual())
                .setFont(font));

        document.add(new Paragraph("Acuidade Auditiva: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getAcuidadeAuditiva()))
                .setFont(font));

        document.add(new Paragraph("Observação Acuidade Auditiva: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoAcuidadeAuditiva())
                .setFont(font));
        document.add(new Paragraph("Região Craniofacial: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getRegiaoCranioFacial())
                .setFont(font));

        document.add(new Paragraph("Cavidade Oral: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getCavidadeOral()))
                .setFont(font));

        document.add(new Paragraph("Observação Cavidade Oral: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoCavidadeOral())
                .setFont(font));

        document.add(new Paragraph("Ventilação: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getVentilacao()))
                .setFont(font));

        document.add(new Paragraph("Observação Ventilação: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoVentilacao())
                .setFont(font));

        document.add(new Paragraph("Ausculta Pulmonar: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getAuscultaPulmonar()))
                .setFont(font));

        document.add(new Paragraph("Observação Ausculta Pulmonar: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoAuscultaPulmonar())
                .setFont(font));

        document.add(new Paragraph("Ausculta Cardíaca: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getAuscultaCardiaca()))
                .setFont(font));

        document.add(new Paragraph("Observação Ausculta Cardíaca: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoAuscultaCardiaca())
                .setFont(font));

        document.add(new Paragraph("Tórax: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getTorax()))
                .setFont(font));

        document.add(new Paragraph("Observação Tórax: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoTorax())
                .setFont(font));

        document.add(new Paragraph("Abdome: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getAbdome()))
                .setFont(font));

        document.add(new Paragraph("Observação Abdome: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoAbdome())
                .setFont(font));

        document.add(new Paragraph("Genitália: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getGenitalia()))
                .setFont(font));

        document.add(new Paragraph("Observação Genitália: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoGenitalia())
                .setFont(font));

        document.add(new Paragraph("Membro Superior: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getMenbroSuperior()))
                .setFont(font));

        document.add(new Paragraph("Observação Membro Superior: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoMenbroSuperior())
                .setFont(font));

        document.add(new Paragraph("Membro Inferior: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getMenbroInferior()))
                .setFont(font));

        document.add(new Paragraph("Observação Membro Inferior: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoMenbroInferior())
                .setFont(font));

        document.add(new Paragraph("Órtese: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getOrtese())
                .setFont(font));

        document.add(new Paragraph("Observação Órtese: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoOrtese())
                .setFont(font));

        document.add(new Paragraph("Prótese: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getProtese())
                .setFont(font));

        document.add(new Paragraph("Observação Prótese: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoProtese())
                .setFont(font));

        document.add(new Paragraph("Mucosas: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getMucosas()))
                .setFont(font));

        document.add(new Paragraph("Observação Mucosas: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoMucosas())
                .setFont(font));

        document.add(new Paragraph("Pele: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getPele()))
                .setFont(font));

        document.add(new Paragraph("Observação Pele: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoPele())
                .setFont(font));

        document.add(new Paragraph("Presença de Dor: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getPresencaDeDor())
                .setFont(font));

        document.add(new Paragraph("Observação Presença de Dor: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getPresencaDeDor())
                .setFont(font));

        document.add(new Paragraph("Escala de Dor Eva: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getEscalaDeDorEva())
                .setFont(font));

        document.add(new Paragraph("Presença de Lesão: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getPresencaLesao())
                .setFont(font));

        document.add(new Paragraph("Observação Presença de Lesão: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaopresencaLesao())
                .setFont(font));

        document.add(new Paragraph("Ferida Operatória: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getFeridaOperatoria())
                .setFont(font));

        document.add(new Paragraph("Observação Ferida Operatória: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoFeridaOperatoria())
                .setFont(font));

        document.add(new Paragraph("Amputações: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getAmputacoes())
                .setFont(font));

        document.add(new Paragraph("Observação Amputações: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoAmputacoes())
                .setFont(font));

        document.add(new Paragraph("Histórico de Queda: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getHistoricoQueda())
                .setFont(font));

        document.add(new Paragraph("Observação Histórico de Queda: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoHistoricoQueda())
                .setFont(font));

        document.add(new Paragraph("Risco de Queda: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getRiscoQueda())
                .setFont(font));
        document.add(new Paragraph("Observação Risco de Queda: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoRiscoQueda())
                .setFont(font));

        document.add(new Paragraph("Dispositivos: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getDipositivos()))
                .setFont(font));

        document.add(new Paragraph("Observação Dispositivos: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoDispositivos())
                .setFont(font));

        document.add(new Paragraph("Fadiga: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getFadiga())
                .setFont(font));

        document.add(new Paragraph("Observação Fadiga: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoFadiga())
                .setFont(font));

        document.add(new Paragraph("Aspectos Nutricionais: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getAspectosNutricionais())
                .setFont(font));

        document.add(new Paragraph("Observação Aspectos Nutricionais: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoAspectosNutricionais())
                .setFont(font));

        document.add(new Paragraph("Dieta: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getDieta()))
                .setFont(font));

        document.add(new Paragraph("Observação Dieta: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoDieta())
                .setFont(font));


        document.add(escalas);

        addImage(context, document, R.drawable.table1);

        document.add(new Paragraph("Abertura Ocular: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getAberturaOcular())
                .setFont(font));

        document.add(new Paragraph("Resposta Verbal: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getRespostaVerbal())
                .setFont(font));

        document.add(new Paragraph("Resposta Motora: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getRespostaMotora())
                .setFont(font));

        document.add(new Paragraph("Reatividade Pupilar: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getReatividadePupilar())
                .setFont(font));

        document.add(new Paragraph("Soma Escala Glasgow: ")
                .setFont(font)
                .setBold()
                .add(String.valueOf(avaliacaoModel.getSomaEscalaGlasgow()))
                .setFont(font));

        addImage(context, document, R.drawable.table2);

        document.add(new Paragraph("Expressão Facial: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getExpressaoFacil())
                .setFont(font));

        document.add(new Paragraph("Atividade Corporal: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getAtividadeCorporal())
                .setFont(font));

        document.add(new Paragraph("Defesa: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getDefesa())
                .setFont(font));

        document.add(new Paragraph("Sinais Vitais: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getSinaisVitais())
                .setFont(font));

        document.add(new Paragraph("Alteração Respiratória: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getAlteracaoRespiratoria())
                .setFont(font));

        document.add(new Paragraph("Soma Escala Dor Não Verbal: ")
                .setFont(font)
                .setBold()
                .add(String.valueOf(avaliacaoModel.getSomaEscalaDorNaoVerbal()))
                .setFont(font));

        addImage(context, document, R.drawable.table3);

        document.add(new Paragraph("Escala Richmond: ")
                .setFont(font)
                .setBold()
                .add(String.valueOf(avaliacaoModel.getRichmond()))
                .setFont(font));

        addImage(context, document, R.drawable.table4);

        document.add(new Paragraph("Escala de Mucosite: ")
                .setFont(font)
                .setBold()
                .add(String.valueOf(avaliacaoModel.getRichmond()))
                .setFont(font));

        document.add(eliminacoes);

        document.add(new Paragraph("Diurese: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getDiurese()))
                .setFont(font));

        document.add(new Paragraph("Evacuação: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getEvacuacao()))
                .setFont(font));

        document.add(new Paragraph("Observação Eliminações: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoEliminacoes())
                .setFont(font));

        document.add(necessidadesPRE);

        document.add(new Paragraph("Estado Emocional: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getEstadoEmocional())
                .setFont(font));

        document.add(new Paragraph("Observação Estado Emocional: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getObservacaoEstadoEmocional())
                .setFont(font));

        document.add(new Paragraph("Enfrentamento da Doença: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getEnfrentamentoDoenca())
                .setFont(font));

        document.add(new Paragraph("Imagem Corporal: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getImagemCorporal()))
                .setFont(font));

        document.add(new Paragraph("Autoestima: ")
                .setFont(font)
                .setBold()
                .add(formatListToString(avaliacaoModel.getAutoestima()))
                .setFont(font));

        document.add(new Paragraph("Suporte Rede Social: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getSuporteRedeSocial())
                .setFont(font));

        document.add(new Paragraph("Qual Suporte Rede Social: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getQualSuporteRedeSocial())
                .setFont(font));

        document.add(new Paragraph("Atividade Recreativa: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getAtividadeRecreativa())
                .setFont(font));

        document.add(new Paragraph("Qual Atividade Recreativa: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getQualAtividadeRecreativa())
                .setFont(font));

        document.add(new Paragraph("Quantas Pessoas Moram: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getQuantasPessoasMoram())
                .setFont(font));

        document.add(new Paragraph("Principal Acompanhante: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getPrincipalAconpanhante())
                .setFont(font));

        document.add(new Paragraph("Provedor de Renda: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getProvedorRenda())
                .setFont(font));

        document.add(new Paragraph("Onde Moram: ")
                .setFont(font)
                .setBold()
                .add(avaliacaoModel.getOndemMoram())
                .setFont(font));


        document.add(diagnosticus);

        for (DiagnosticoPacienteModel diagnostico : diagnosticoPacienteModels) {
            document.add(new Paragraph(diagnostico.getTitulo() + "( " + diagnostico.getDataCriacao() + " )")
                    .setFont(font)
                    .setBold());

            document.add(new Paragraph(diagnostico.getSubTitulo())
                    .add(avaliacaoModel.getOndemMoram())
                    .setItalic()
                    .setFont(font));

            String diagnosticoResolvido = "Não";

            if (!diagnostico.isAtivo()) {
                diagnosticoResolvido = "Sim";
            }

            document.add(new Paragraph("Diagnóstico Resolvido? ")
                    .setFont(font)
                    .setBold()
                    .add(diagnosticoResolvido)
                    .setFont(font));

            document.add(new Paragraph("Noc's Selecionadas: ")
                    .setFont(font)
                    .setBold()
                    .add(formatListToString(diagnostico.getNocSelecionadas()))
                    .setFont(font));

            for (IntervencaoModel intervencaoModel : diagnostico.getIntervencoes()) {

                document.add(new Paragraph("Intervenção: ")
                        .setFont(font)
                        .setBold()
                        .add(intervencaoModel.getDescricao())
                        .setFont(font));

                String dataPrazamento = "Pendente";

                if (intervencaoModel.isResolvido()) {
                    dataPrazamento = intervencaoModel.getHoraIntervencao();
                    if (dataPrazamento.isEmpty()) {
                        dataPrazamento = "Pendente";
                    }
                }
                document.add(new Paragraph("Data do Aprazamento: ")
                        .setFont(font)
                        .setBold()
                        .add(dataPrazamento)
                        .setFont(font));
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

}