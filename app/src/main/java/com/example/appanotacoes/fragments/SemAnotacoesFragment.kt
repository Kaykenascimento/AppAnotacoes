package com.example.appanotacoes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.appanotacoes.R
import com.example.appanotacoes.databinding.FragmentSemAnotacoesBinding

class SemAnotacoesFragment : Fragment(R.layout.fragment_sem_anotacoes) {

    private lateinit var binding: FragmentSemAnotacoesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSemAnotacoesBinding.bind(view)

    }
}