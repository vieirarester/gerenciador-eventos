package com.example.gerenciadoreventos

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.example.gerenciadoreventos.model.Evento
import com.example.gerenciadoreventos.repository.EventoRepository

class CadastroEventoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_evento)

        val editNome = findViewById<EditText>(R.id.editNome)
        val editData = findViewById<EditText>(R.id.editData)
        val editLocal = findViewById<EditText>(R.id.editLocal)
        val editDescricao = findViewById<EditText>(R.id.editDescricao)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener {
            val evento = Evento(
                editNome.text.toString(),
                editData.text.toString(),
                editLocal.text.toString(),
                editDescricao.text.toString()
            )

            EventoRepository.listaEventos.add(evento)
            Toast.makeText(this, "Evento salvo com sucesso!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

            finish()
        }
    }
}
