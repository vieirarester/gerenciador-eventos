package com.example.gerenciadoreventos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerenciadoreventos.R
import com.example.gerenciadoreventos.model.Evento

class EventoAdapter(
    private val eventos: List<Evento>,
    private val onItemClick: (Evento) -> Unit
) : RecyclerView.Adapter<EventoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nome: TextView = itemView.findViewById(R.id.txtNome)
        private val data: TextView = itemView.findViewById(R.id.txtData)
        private val descricao: TextView = itemView.findViewById(R.id.txtDescricao)

        fun bind(evento: Evento) {
            nome.text = evento.nome
            data.text = evento.data
            descricao.text = evento.descricao
            itemView.setOnClickListener { onItemClick(evento) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_evento, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(eventos[position])
    }

    override fun getItemCount(): Int = eventos.size
}
