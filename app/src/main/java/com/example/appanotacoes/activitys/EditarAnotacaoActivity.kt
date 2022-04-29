package com.example.appanotacoes.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.room.Room
import com.example.appanotacoes.R
import com.example.appanotacoes.database.AnotacoesDatabase
import com.example.appanotacoes.databinding.ActivityEditarAnotacaoBinding
import com.example.appanotacoes.model.Anotacoes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class EditarAnotacaoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditarAnotacaoBinding
    private var codigo: Int = 0
    private var titulo: String = ""
    private var anotacao: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarAnotacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarAnotacaoEditar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.title = ""

        val bundle: Bundle? = this.intent.extras

        codigo = bundle!!.getInt("codigo")
        titulo = bundle.getString("titulo")!!
        anotacao = bundle.getString("anotacao")!!

        binding.etTituloEdit.setText(titulo)
        binding.etAnotacaoEdit.setText(anotacao)

        binding.toolbarAnotacaoEditar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun alterarAnotacao(){
        val db: AnotacoesDatabase = Room.databaseBuilder(this, AnotacoesDatabase::class.java, "anotacoes.db")
            .build()

        val anotacoesDao = db.anotacoesDao()

        anotacoesDao.atualizarAnotacao(Anotacoes(codigo, binding.etTituloEdit.text.toString(), binding.etAnotacaoEdit.text.toString(), Date()))
    }

    private fun deletarAnotacao(anotacoes: Anotacoes){
        val db: AnotacoesDatabase = Room.databaseBuilder(this, AnotacoesDatabase::class.java, "anotacoes.db")
            .build()

        val anotacoesDao = db.anotacoesDao()

        anotacoesDao.deletarAnotacao(anotacoes)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_anotacao_editar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deletarAnotacao -> {
                val anotacoes = Anotacoes(
                    codigo = codigo,
                    titulo = binding.etTituloEdit.text.toString(),
                    anotacao = binding.etAnotacaoEdit.text.toString(),
                    data = Date()
                )
                CoroutineScope(Dispatchers.IO).launch {
                    deletarAnotacao(anotacoes)
                }
                val intent = Intent(this, MainActivity::class.java); startActivity(intent)
            }
            R.id.alterarAnotacao -> {
                CoroutineScope(Dispatchers.IO).launch{
                    alterarAnotacao()
                }
                val intent = Intent(this, MainActivity::class.java); startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        CoroutineScope(Dispatchers.IO).launch {
            alterarAnotacao()
        }
        val intent = Intent(this, MainActivity::class.java); startActivity(intent)
    }
}