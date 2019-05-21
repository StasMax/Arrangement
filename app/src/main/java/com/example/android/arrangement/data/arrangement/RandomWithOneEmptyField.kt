package com.example.android.arrangement.data.arrangement

import com.example.android.arrangement.*
import java.util.*

class RandomWithOneEmptyField : ChessArrangement {
    private val random = Random()
    lateinit var arrayNumbers: ArrayList<Int>
    lateinit var arrangement: Array<Array<String>>
    private var rnd: Int = 0

    override fun arrangement(): Array<Array<String>> {
        arrayNumbers = ArrayList()
        arrangement = Array(heightChessField) { Array(widthChessField) { emptyField } }
        repeat(2) { pasteLine() }
        return arrangement
    }

    private fun pasteLine() {
        val line = random.nextInt(heightChessField)
        if (line in arrayNumbers) {
            pasteLine()
        } else {
            arrayNumbers.add(line)
        }
        if (line != 0) {
            arrayNumbers.add(line - 1)
        }
        if (line != widthChessField - 1) {
            arrayNumbers.add(line + 1)
        }
        rnd = random.nextInt(2)
        repeat(widthChessField / 2) {
            arrangement[line][rnd] = fieldWithFigure
            rnd += 2
        }
    }
}