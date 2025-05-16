package com.example.gerenciadoreventos

import android.os.Bundle
import android.content.Intent
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gerenciadoreventos.adapter.EventoAdapter
import com.example.gerenciadoreventos.repository.EventoRepository

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var botaoNovoEvento: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerEventos)
        botaoNovoEvento = findViewById(R.id.btnNovoEvento)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adapter que mostra os eventos e exibe a descrição ao clicar
        recyclerView.adapter = EventoAdapter(EventoRepository.listaEventos) { evento ->
            Toast.makeText(this, evento.descricao, Toast.LENGTH_LONG).show()
        }

        // Botão para abrir a tela de cadastro
        botaoNovoEvento.setOnClickListener {
            val intent = Intent(this, CadastroEventoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // Atualiza a lista toda vez que voltar para a MainActivity
        recyclerView.adapter = EventoAdapter(EventoRepository.listaEventos) { evento ->
            Toast.makeText(this, evento.descricao, Toast.LENGTH_LONG).show()
        }
    }
}