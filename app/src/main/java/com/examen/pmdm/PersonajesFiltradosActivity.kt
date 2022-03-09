package com.examen.pmdm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.examen.pmdm.databinding.ActivityPersonajefiltradoBinding


var dataPelicula: String? = ""

class PersonajesFiltradosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonajefiltradoBinding
    private val viewModel: ActivityPersonajeFiltradoViewModel by viewModels()
    private var lista: MutableList<Result>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonajefiltradoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataPelicula = intent.getStringExtra("pelicula")
        //println(data)
        viewModel.descargarPersonas()
        initObserver()
    }

    private fun initObserver() {
        viewModel.isVisible.observe(this) { isVisible ->
            if (isVisible) setVisible() else setGone()
        }

        viewModel.responseText.observe(this) { responseText ->
            showToast(responseText)
        }

        viewModel.responseList.observe(this) {
            setRecycler(it as MutableList<Result>)
        }
    }

    private fun setVisible() {
        binding.pbDownloading.visibility = View.VISIBLE
    }

    private fun setGone() {
        binding.pbDownloading.visibility = View.GONE
    }

    private fun showToast(text: String) {
        Toast.makeText(this@PersonajesFiltradosActivity, text, Toast.LENGTH_SHORT).show()

    }

    private fun setRecycler(lista: MutableList<Result>) {

        val adapter = RecyclerFiltradoAdapter(lista)

        adapter.setpelicula(dataPelicula!!)
        if (this.lista == null)
            this.lista = lista
        binding.recycler.layoutManager = LinearLayoutManager(this@PersonajesFiltradosActivity)
        binding.recycler.adapter = adapter
    }
}