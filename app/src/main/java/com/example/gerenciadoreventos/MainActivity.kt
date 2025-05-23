package com.example.gerenciadoreventos

import android.os.Bundle
import android.content.Intent
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gerenciadoreventos.adapter.EventoAdapter
import com.example.gerenciadoreventos.data.EventoDBHelper
import com.example.gerenciadoreventos.model.Evento

class MainActivity : ComponentActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var botaoNovoEvento: Button
    private lateinit var dbHelper: EventoDBHelper
    private lateinit var adapter: EventoAdapter
    private var eventos: List<Evento> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = EventoDBHelper(this)

        recyclerView = findViewById(R.id.recyclerEventos)
        botaoNovoEvento = findViewById(R.id.btnNovoEvento)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Botão para abrir a tela de cadastro
        botaoNovoEvento.setOnClickListener {
            val intent = Intent(this, CadastroEventoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        atualizarLista()
    }

    private fun atualizarLista() {
        eventos = dbHelper.listar()
        adapter = EventoAdapter(eventos) { evento ->
            Toast.makeText(this, evento.descricao, Toast.LENGTH_LONG).show()
        }
        recyclerView.adapter = adapter
    }
}