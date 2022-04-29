package com.example.appanotacoes.extensions

fun String.limitarCaracter(tamanho: Int) : String{
    return if(this.length > tamanho){
        "${this.substring(0, tamanho)}..."
    }
    else{
        this
    }
}