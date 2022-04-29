package com.example.appanotacoes.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.appanotacoes.R
import com.example.appanotacoes.extensions.formatarData
import com.example.appanotacoes.extensions.formatarDataEHora
import com.example.appanotacoes.extensions.limitarCaracter
import com.example.appanotacoes.model.Anotacoes

class AdapterAnotacoes(private val context: Context,
                       private val lista: List<Anotacoes>) : BaseAdapter() {

    private val limitacao: Int = 70

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.adapter_anotacoes, null)

        val titulo: TextView = view.findViewById(R.id.tvTituloAnotacao)
        val anotacao: TextView = view.findViewById(R.id.tvAnotacao)
        val data: TextView = view.findViewById(R.id.tvDataAnotacao)

        val anotacoes: Anotacoes = lista[position]

        titulo.text = anotacoes.titulo
        anotacao.text = anotacoes.anotacao!!.limitarCaracter(limitacao)
        data.text = anotacoes.data!!.formatarData()

        return view
    }

    override fun getItem(position: Int): Any {
        return lista[position]
    }

    override fun getCount(): Int {
        return lista.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}