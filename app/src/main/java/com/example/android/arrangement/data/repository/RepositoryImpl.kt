package com.example.android.arrangement.data.repository

import com.example.android.arrangement.data.arrangement.AllLinesWithOneFigure
import com.example.android.arrangement.data.arrangement.ChessArrangement
import com.example.android.arrangement.data.arrangement.OneLineArrangement
import com.example.android.arrangement.data.arrangement.RandomWithOneEmptyField
import java.lang.StringBuilder
import javax.inject.Inject

class RepositoryImpl @Inject constructor() : Repository {
    override fun getArrangementById(id: Int): String {
        return when (id) {
            1 -> getOneLineArrangement()
            2 -> getAllLinesWithOneFigure()
            3 -> getRandomWithOneEmptyField()
            else -> {
                getOneLineArrangement()
            }
        }
    }

    override fun getOneLineArrangement(): String {
        val arr: ChessArrangement = OneLineArrangement()
        val builder = StringBuilder()
        arr.arrangement().forEach { builder.append(it.contentToString()).append("\n") }
        return builder.toString()
    }

    override fun getAllLinesWithOneFigure(): String {
        val arr: ChessArrangement = AllLinesWithOneFigure()
        val builder = StringBuilder()
        arr.arrangement().forEach { builder.append(it.contentToString()).append("\n") }
        return builder.toString()
    }

    override fun getRandomWithOneEmptyField(): String {
        val arr: ChessArrangement = RandomWithOneEmptyField()
        val builder = StringBuilder()
        arr.arrangement().forEach { builder.append(it.contentToString()).append("\n") }
        return builder.toString()
    }
}