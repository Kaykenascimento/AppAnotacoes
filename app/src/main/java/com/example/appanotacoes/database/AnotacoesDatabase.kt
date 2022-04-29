package com.example.appanotacoes.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appanotacoes.converters.Converters
import com.example.appanotacoes.dao.RoomAnotacoesDAO
import com.example.appanotacoes.model.Anotacoes

@Database(entities = [Anotacoes::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)

abstract class AnotacoesDatabase : RoomDatabase(){
    abstract fun anotacoesDao(): RoomAnotacoesDAO
}