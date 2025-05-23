package com.example.gerenciadoreventos.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor
import com.example.gerenciadoreventos.model.Evento

class EventoDBHelper(context: Context) : SQLiteOpenHelper(context, "eventos.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val sql = """
            CREATE TABLE eventos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nome TEXT NOT NULL,
                data TEXT NOT NULL,
                descricao TEXT
            );
        """.trimIndent()
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS eventos")
        onCreate(db)
    }

    fun inserir(evento: Evento) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("nome", evento.nome)
            put("data", evento.data)
            put("descricao", evento.descricao)
        }
        db.insert("eventos", null, values)
    }

    fun listar(): List<Evento> {
        val lista = mutableListOf<Evento>()
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM eventos", null)

        if (cursor.moveToFirst()) {
            do {
                val evento = Evento(
                    nome = cursor.getString(cursor.getColumnIndexOrThrow("nome")),
                    data = cursor.getString(cursor.getColumnIndexOrThrow("data")),
                    descricao = cursor.getString(cursor.getColumnIndexOrThrow("descricao"))
                )
                lista.add(evento)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }
}
