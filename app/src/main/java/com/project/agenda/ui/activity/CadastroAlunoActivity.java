package com.project.agenda.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.agenda.dao.AlunoDAO;
import com.project.agenda.R;
import com.project.agenda.model.Aluno;

public class CadastroAlunoActivity extends AppCompatActivity {

    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;

    private final AlunoDAO dao = new AlunoDAO();

    public static  final String TITULO_APPBAR = "Novo aluno";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        botaoSalvarCadastro();
    }

    private void botaoSalvarCadastro() {
        Button botaoSalvar = findViewById(R.id.activity_btn_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Aluno novoAluno = cadastroAluno();
                salva(novoAluno);
            }
        });
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_aluno_nome);
        campoTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        campoEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salva(Aluno novoAluno) {
        dao.salva(novoAluno);

        finish();
    }

    private Aluno cadastroAluno() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        return new Aluno(nome, telefone, email);
    }
}