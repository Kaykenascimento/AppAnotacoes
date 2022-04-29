package com.example.appanotacoes.dao

import androidx.room.*
import com.example.appanotacoes.model.Anotacoes

@Dao
interface RoomAnotacoesDAO {

    @Insert
    fun inserirAnotacao(anotacoes: Anotacoes)

    @Query("SELECT * FROM Anotacoes")
    fun carregarAnotacoes(): MutableList<Anotacoes>

    @Update
    fun atualizarAnotacao(anotacoes: Anotacoes)

    @Delete
    fun deletarAnotacao(anotacoes: Anotacoes)

    @Query("DELETE FROM Anotacoes WHERE codigo > :codigoAnotacao")
    fun deletarAnotacaoComCodigo(codigoAnotacao: Int)

}