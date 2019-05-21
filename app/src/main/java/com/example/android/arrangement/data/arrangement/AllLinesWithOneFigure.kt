package com.example.android.arrangement.data.arrangement

import com.example.android.arrangement.emptyField
import com.example.android.arrangement.fieldWithFigure
import com.example.android.arrangement.heightChessField
import com.example.android.arrangement.widthChessField
import java.util.*
import kotlin.collections.ArrayList

class AllLinesWithOneFigure : ChessArrangement {
    val random = Random()
    lateinit var arrayNumbers: ArrayList<Int>
    var numberArray: Int = 0

    override fun arrangement(): Array<Array<String>> {
        arrayNumbers = ArrayList()
        val arrangement: Array<Array<String>> = Array(heightChessField) { Array(widthChessField) { emptyField } }
        for (i in 0 until heightChessField) arrangement[i][randomIndex()] = fieldWithFigure
        return arrangement
    }

    private fun randomIndex(): Int {
        numberArray = random.nextInt(heightChessField)
        if (numberArray in arrayNumbers) {
            randomIndex()
        } else {
            arrayNumbers.add(numberArray)
        }
        return numberArray
    }
}