package com.cbellmont.ejemplorepaso

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PreguntaAdapter : RecyclerView.Adapter<PreguntaAdapter.PreguntaViewHolder>() {

    var listaPreguntas: List<Pregunta> = listOf()

    class PreguntaViewHolder(val root: View, val textViewPregunta: TextView) :
        RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreguntaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter, parent, false)
        val tvPregunta = view.findViewById<TextView>(R.id.tvPregunta)
        return PreguntaViewHolder(view, tvPregunta)
    }

    override fun getItemCount(): Int {
        return listaPreguntas.size
    }

    override fun onBindViewHolder(holder: PreguntaViewHolder, position: Int) {
        holder.textViewPregunta.text = listaPreguntas[position].pregunta
    }

    fun updatePreguntas(newData: List<Pregunta>) {
        listaPreguntas = newData
        notifyDataSetChanged()
    }
}