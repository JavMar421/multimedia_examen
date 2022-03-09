package com.examen.pmdm

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.examen.pmdm.databinding.ActivityPeliculasBinding

var data: String? = ""

class PeliculasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPeliculasBinding
    private val viewModel: ActivityPeliculasViewModel by viewModels()
    private var lista: MutableList<ResultPelis>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url="https://swapi.dev/api/films/"
        binding = ActivityPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = intent.getStringExtra("personaje")
        println(data)
        viewModel.descargarPelis(url)
        initObserver()
    }

    private fun initObserver() {
        viewModel.isVisible.observe(this) { isVisible ->
            if (isVisible) setVisible() else setGone()
        }

        viewModel.responseText.observe(this) { responseText ->
            showToast(responseText)
        }

        viewModel.responsePelisList.observe(this) {
            setRecycler(it as MutableList<ResultPelis>)
        }
    }

    private fun setVisible() {
        binding.pbDownloading.visibility = View.VISIBLE
    }

    private fun setGone() {
        binding.pbDownloading.visibility = View.GONE
    }

    private fun showToast(text: String) {
        Toast.makeText(this@PeliculasActivity, text, Toast.LENGTH_SHORT).show()

    }

    private fun setRecycler(lista: MutableList<ResultPelis>) {

        val adapter = RecyclerPelisAdapter(lista)
        adapter.setpersonaje(data!!)

        if (this.lista == null)
            this.lista = lista
        binding.recycler.layoutManager = LinearLayoutManager(this@PeliculasActivity)
        binding.recycler.adapter = adapter
    }
}