package com.example.appanotacoes.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.appanotacoes.R
import com.example.appanotacoes.database.AnotacoesDatabase
import com.example.appanotacoes.databinding.ActivityMainBinding
import com.example.appanotacoes.fragments.AnotacoesCarregadasFragment
import com.example.appanotacoes.fragments.SemAnotacoesFragment
import com.example.appanotacoes.model.Anotacoes

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checarAnotacoes()

        binding.fbaCriarAnotacao.setOnClickListener {
            criarAnotacao()
        }
    }

     private fun checarAnotacoes(){
        val db: AnotacoesDatabase = Room.databaseBuilder(this, AnotacoesDatabase::class.java, "anotacoes.db")
            .allowMainThreadQueries()
            .build()

        val anotacoesDao = db.anotacoesDao()
        val anotacoes: List<Anotacoes> = anotacoesDao.carregarAnotacoes()

        if(anotacoes.isEmpty()){
            fragmentSemAnotacoes()
        }
        else{
            fragmentComAnotacoes()
        }
    }

    private fun criarAnotacao(){
        val intent = Intent(this, CriarAnotacaoActivity::class.java); startActivity(intent)
    }

    private fun fragmentComAnotacoes(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayoutPrincipal, AnotacoesCarregadasFragment())
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    private fun fragmentSemAnotacoes(){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayoutPrincipal, SemAnotacoesFragment())
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}