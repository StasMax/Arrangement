package com.example.android.arrangement.data.repository

interface Repository {
    fun getArrangementById(id: Int): String

    fun getOneLineArrangement(): String

    fun getAllLinesWithOneFigure(): String

    fun getRandomWithOneEmptyField(): String
}