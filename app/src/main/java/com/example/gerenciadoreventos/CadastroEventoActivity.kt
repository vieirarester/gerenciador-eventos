package com.example.gerenciadoreventos

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity
import com.example.gerenciadoreventos.data.EventoDBHelper
import com.example.gerenciadoreventos.model.Evento
import java.util.*
import android.app.DatePickerDialog
import android.app.TimePickerDialog

class CadastroEventoActivity : ComponentActivity() {

    private lateinit var dbHelper: EventoDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_evento)

        val editNome = findViewById<EditText>(R.id.editNome)
        val editData = findViewById<EditText>(R.id.editData)
        val editDescricao = findViewById<EditText>(R.id.editDescricao)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        dbHelper = EventoDBHelper(this)

        editData.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // Primeiro abre o DatePicker
            val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                // Depois de escolher a data, abre o TimePicker
                val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                    // Formata a data e hora juntas
                    val dataHora = String.format(
                        "%02d/%02d/%04d %02d:%02d",
                        selectedDay,
                        selectedMonth + 1,
                        selectedYear,
                        selectedHour,
                        selectedMinute
                    )
                    editData.setText(dataHora)
                }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true) // formato 24h

                timePickerDialog.show()
            }, year, month, day)

            datePickerDialog.show()
        }

        btnSalvar.setOnClickListener {
            val nome = editNome.text.toString()
            val data = editData.text.toString()
            val descricao = editDescricao.text.toString()

            if (nome.isNotEmpty() && data.isNotEmpty()) {
                val evento = Evento(nome, data, descricao)
                dbHelper.inserir(evento)
                Toast.makeText(this, "Evento salvo com sucesso!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Preencha o nome e data", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
