package com.example.appanotacoes.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.room.Room
import com.example.appanotacoes.R
import com.example.appanotacoes.activitys.EditarAnotacaoActivity
import com.example.appanotacoes.adapter.AdapterAnotacoes
import com.example.appanotacoes.database.AnotacoesDatabase
import com.example.appanotacoes.databinding.FragmentAnotacoesCarregadasBinding
import com.example.appanotacoes.model.Anotacoes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AnotacoesCarregadasFragment : Fragment(R.layout.fragment_anotacoes_carregadas) {

    private lateinit var binding: FragmentAnotacoesCarregadasBinding
    private lateinit var listaAnotacoes: MutableList<Anotacoes>
    private lateinit var adapterAnotacoes: AdapterAnotacoes

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAnotacoesCarregadasBinding.bind(view)

        carregarAnotacoes()

        binding.listViewAnotacoes.setOnItemClickListener { parent, view, position, id ->
            val posicao = listaAnotacoes[position]
            val intent = Intent(context, EditarAnotacaoActivity::class.java)
            intent.putExtra("codigo", posicao.codigo)
            intent.putExtra("titulo", posicao.titulo)
            intent.putExtra("anotacao", posicao.anotacao)
            intent.putExtra("data", posicao.data)
            startActivity(intent)
        }

        binding.listViewAnotacoes.setOnItemLongClickListener { parent, view, position, id ->
            val posicao = listaAnotacoes[position]
            val anotacoes = Anotacoes(
                codigo = posicao.codigo,
                titulo = posicao.titulo,
                anotacao = posicao.anotacao,
                data = posicao.data)
                deletarAnotacao(anotacoes)
            true
        }
    }

    private fun carregarAnotacoes(){
        val db: AnotacoesDatabase = Room.databaseBuilder(requireContext(), AnotacoesDatabase::class.java, "anotacoes.db")
            .allowMainThreadQueries()
            .build()

        val anotacoesDao = db.anotacoesDao()

        listaAnotacoes = anotacoesDao.carregarAnotacoes()

        adapterAnotacoes = AdapterAnotacoes(requireContext(), listaAnotacoes)
        binding.listViewAnotacoes.adapter = adapterAnotacoes
    }

    private fun deletarAnotacao(anotacoes: Anotacoes){
        val db: AnotacoesDatabase = Room.databaseBuilder(requireContext(), AnotacoesDatabase::class.java, "anotacoes.db")
            .build()

        val anotacoesDao = db.anotacoesDao()

        CoroutineScope(Dispatchers.IO).launch {
            anotacoesDao.deletarAnotacao(anotacoes)
        }
        limparLista()
    }

    private fun chamarFragmentSemAnotacao(){
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayoutPrincipal, SemAnotacoesFragment())
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    private fun limparLista(){
        listaAnotacoes.clear()
        carregarAnotacoes()
        if(listaAnotacoes.isEmpty()){
            chamarFragmentSemAnotacao()
        }
    }
}