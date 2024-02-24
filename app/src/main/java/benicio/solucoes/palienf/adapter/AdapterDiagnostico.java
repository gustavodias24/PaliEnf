package benicio.solucoes.palienf.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.solucoes.palienf.R;
import benicio.solucoes.palienf.databinding.LayoutDiagnosticoBinding;
import benicio.solucoes.palienf.databinding.LayoutNocBinding;
import benicio.solucoes.palienf.model.DiagnosticoModel;
import benicio.solucoes.palienf.model.IntervencaoModel;
import benicio.solucoes.palienf.model.NocModel;

public class AdapterDiagnostico extends RecyclerView.Adapter<AdapterDiagnostico.MyViewHolder> {
    List<DiagnosticoModel> diagnosticos;
    Activity c;
    Dialog dialogNoc;

    public AdapterDiagnostico(List<DiagnosticoModel> diagnosticos, Activity c) {
        this.diagnosticos = diagnosticos;
        this.c = c;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_diagnostico, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DiagnosticoModel d = diagnosticos.get(position);
        holder.itemView.getRootView().setClickable(false);

        holder.titulo_diagnostico_text.setText(d.getNome());
        holder.descricao_diagnostico_text.setText(d.getDescricao());

        holder.botao_noc.setOnClickListener(view -> {

            AlertDialog.Builder b = new AlertDialog.Builder(c);
            LayoutNocBinding diagnosticoBinding = LayoutNocBinding.inflate(c.getLayoutInflater());

            for (NocModel noc : d.getNocs()) {
                RadioGroup radioGroup = new RadioGroup(c);
                radioGroup.setLayoutParams(new RadioGroup.LayoutParams(
                        RadioGroup.LayoutParams.MATCH_PARENT,
                        RadioGroup.LayoutParams.WRAP_CONTENT));
                radioGroup.setOrientation(RadioGroup.VERTICAL);

                TextView textView = new TextView(c);
                textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                textView.setText(noc.getNome());
                radioGroup.addView(textView);

                for (String indicador : noc.getPossiveisIndicadores()) {
                    RadioButton radioButton = new RadioButton(c);
                    radioButton.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    radioButton.setText(indicador);
                    radioGroup.addView(radioButton);
                }
                diagnosticoBinding.layoutNocs.addView(radioGroup);

            }

            Button button = new Button(c);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setBackgroundResource(R.color.azul);
            button.setText("Próximo");
            button.setAllCaps(true);
            button.setTextColor(Color.WHITE);
            button.setTypeface(null, Typeface.BOLD);

            diagnosticoBinding.layoutNocs.addView(button);

            button.setOnClickListener(itenversoesView -> {
                AlertDialog.Builder b2 = new AlertDialog.Builder(c);
                LayoutNocBinding diagnosticoBinding2 = LayoutNocBinding.inflate(c.getLayoutInflater());

                diagnosticoBinding2.textView10.setText("Intervenções");
                for (IntervencaoModel intervencao : d.getIntervencoes()) {
                    CheckBox checkBox = new CheckBox(c);
                    checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    checkBox.setText(intervencao.getDescricao());
                    diagnosticoBinding2.layoutNocs.addView(checkBox);
                }

                Button buttonConcluir = new Button(c);
                buttonConcluir.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                buttonConcluir.setBackgroundResource(R.color.azul);
                buttonConcluir.setText("Concluir");
                buttonConcluir.setAllCaps(true);
                buttonConcluir.setTextColor(Color.WHITE);
                buttonConcluir.setTypeface(null, Typeface.BOLD);
                diagnosticoBinding2.layoutNocs.addView(buttonConcluir);


                b2.setView(diagnosticoBinding2.getRoot());
                dialogNoc.dismiss();
                b2.create().show();
            });

            // TODO
            b.setView(diagnosticoBinding.getRoot());
            dialogNoc = b.create();
            dialogNoc.show();
        });
    }

    @Override
    public int getItemCount() {
        return diagnosticos.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo_diagnostico_text, descricao_diagnostico_text;
        Button botao_noc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo_diagnostico_text = itemView.findViewById(R.id.titulo_diagnostico_text);
            descricao_diagnostico_text = itemView.findViewById(R.id.descricao_diagnostico_text);
            botao_noc = itemView.findViewById(R.id.botao_noc);

        }
    }
}
