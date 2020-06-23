package com.project.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.agenda.dao.AlunoDAO;
import com.project.agenda.R;

public class ListaAlunosActivity extends AppCompatActivity {

    public static  final String TITULO_APPBAR = "Lista de Alunos";

    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        botaoNovoCadastro();
    }

    private void botaoNovoCadastro() {
        FloatingActionButton botaoCadastrar = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirNovoFormulario();
            }
        });
    }

    private void abrirNovoFormulario() {
        startActivity(new Intent(this, CadastroAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_lista_de_alunos_listview);
        listaAlunos.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.listagem()));
    }
}
