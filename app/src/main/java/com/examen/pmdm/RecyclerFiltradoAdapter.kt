package com.examen.pmdm

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.examen.pmdm.databinding.RecyclerviewBinding

class RecyclerFiltradoAdapter(var personas: MutableList<Result>) :
    RecyclerView.Adapter<RecyclerFiltradoAdapter.TextoViewHolder>() {

    private val colorrojo = Color.RED
    private val colorverde = Color.GREEN
    private var pelicula: String = ""
    fun setpelicula(personaje: String) {
        this.pelicula = personaje
    }

    class TextoViewHolder(var itemBinding: RecyclerviewBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextoViewHolder {
        val binding =
            RecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TextoViewHolder(binding)

    }

    override fun onBindViewHolder(holder: TextoViewHolder, pos: Int) {
        if (personas[pos].films.contains(pelicula))
            holder.itemBinding.name.setTextColor(colorverde)
        else
            holder.itemBinding.name.setTextColor(colorrojo)
        holder.itemBinding.name.text = personas[pos].name

    }

    override fun getItemCount(): Int {
        return personas.size
    }

}