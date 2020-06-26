package com.project.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.agenda.dao.AlunoDAO;
import com.project.agenda.R;
import com.project.agenda.model.Aluno;

import java.util.List;

import static com.project.agenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de Alunos";

    private final AlunoDAO dao = new AlunoDAO();

    private ArrayAdapter<Aluno> adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        botaoNovoCadastro();
        configuraLista();
//        Utilizados para teste não possui valor para o App.
//        dao.salva(new Aluno("Mateus", "91234321", "mateus@gmail.com"));
//        dao.salva(new Aluno("Larissa", "98761234", "larissa@hotmail.com"));

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_de_alunos_menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo =
                    (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno alunoEscolhido = adapter.getItem(menuInfo.position);
            remove(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    private void botaoNovoCadastro() {
        FloatingActionButton botaoCadastrar = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abriFormularioInsereAluno();
            }
        });
    }

    private void abriFormularioInsereAluno() {
        startActivity(new Intent(this, CadastroAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.listagem());
    }

    private void configuraLista() {
        final ListView listaAlunos = findViewById(R.id.activity_lista_de_alunos_listview);
        configuraAdapter(listaAlunos);
        configuraListanerDeCliquePorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    private void configuraListanerDeCliquePorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(position);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno aluno) {
        Intent caminhoCadastroActivity = new Intent(ListaAlunosActivity.this, CadastroAlunoActivity.class);
        caminhoCadastroActivity.putExtra(CHAVE_ALUNO, aluno);
        startActivity(caminhoCadastroActivity);
    }

    private void configuraAdapter(ListView listaAlunos) {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1);
        listaAlunos.setAdapter(adapter);
    }
}
