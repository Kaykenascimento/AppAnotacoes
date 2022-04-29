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
import com.example.appanotacoes.databinding.ActivityCriarAnotacaoBinding
import com.example.appanotacoes.model.Anotacoes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class CriarAnotacaoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCriarAnotacaoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCriarAnotacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarCriarAnotacao)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.title = ""

        binding.toolbarCriarAnotacao.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun inserirAnotacao(){
        val db: AnotacoesDatabase = Room.databaseBuilder(this, AnotacoesDatabase::class.java, "anotacoes.db")
            .build()

        val anotacoesDao = db.anotacoesDao()

        anotacoesDao.inserirAnotacao(Anotacoes(null, binding.etTitulo.text.toString(), binding.etAnotacao.text.toString(),Date()))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_anotacao_criar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.adicionarAnotacao -> {
                CoroutineScope(Dispatchers.IO).launch{
                    inserirAnotacao()
                }
                val intent = Intent(this, MainActivity::class.java); startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if(binding.etTitulo.text.isEmpty() || binding.etAnotacao.text.isEmpty()){
            val intent = Intent(this, MainActivity::class.java); startActivity(intent)
        }
        else{
            CoroutineScope(Dispatchers.IO).launch {
                inserirAnotacao()
            }
            val intent = Intent(this, MainActivity::class.java); startActivity(intent)
        }
    }
}