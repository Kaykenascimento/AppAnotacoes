package com.example.appanotacoes.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatarData(): String? {
    val formatoBrasileiro = "dd/MM/yyyy"
    val formato = SimpleDateFormat(formatoBrasileiro)
    return formato.format(this.time)
}

fun Date.formatarHora(): String?{
    val formatoHora = "HH:mm"
    val formato = SimpleDateFormat(formatoHora)
    return formato.format(this.time)
}

fun Date.formatarDataEHora(): String?{
    val formatoDataEHora = "dd/MM/yyyy - HH:mm"
    val formato = SimpleDateFormat(formatoDataEHora)
    return formato.format(this.time)
}