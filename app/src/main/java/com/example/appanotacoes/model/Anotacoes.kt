package com.example.appanotacoes.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Anotacoes(
    @PrimaryKey(autoGenerate = true) val codigo: Int?,
    @ColumnInfo(name = "titulo") val titulo: String?,
    @ColumnInfo(name = "anotacao") val anotacao: String?,
    @ColumnInfo(name = "data") val data: Date
)


