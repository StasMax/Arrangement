package com.example.android.arrangement.data.arrangement

import com.example.android.arrangement.EMPTY_FIELD
import com.example.android.arrangement.FIELD_WITH_FIGURE
import com.example.android.arrangement.HEIGHT_CHESS_FIELD
import com.example.android.arrangement.WIDTH_CHESS_FIELD
import java.util.*
import kotlin.collections.ArrayList

class AllLinesWithOneFigure : ChessArrangement {
    val random = Random()
    lateinit var arrayNumbers: ArrayList<Int>
    var numberArray: Int = 0

    override fun arrangement(): Array<Array<String>> {
        arrayNumbers = ArrayList()
        val arrangement: Array<Array<String>> = Array(HEIGHT_CHESS_FIELD) { Array(WIDTH_CHESS_FIELD) { EMPTY_FIELD } }
        for (i in 0 until HEIGHT_CHESS_FIELD) arrangement[i][randomIndex()] = FIELD_WITH_FIGURE
        return arrangement
    }

    private fun randomIndex(): Int {
        numberArray = random.nextInt(HEIGHT_CHESS_FIELD)
        if (numberArray in arrayNumbers) {
            randomIndex()
        } else {
            arrayNumbers.add(numberArray)
        }
        return numberArray
    }
}