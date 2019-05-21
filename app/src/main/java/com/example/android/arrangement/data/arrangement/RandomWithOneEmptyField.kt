package com.example.android.arrangement.data.arrangement

import com.example.android.arrangement.*
import java.util.*

class RandomWithOneEmptyField : ChessArrangement {
    private val random = Random()
    lateinit var arrayNumbers: ArrayList<Int>
    lateinit var arrangement: Array<Array<String>>

    override fun arrangement(): Array<Array<String>> {
        arrayNumbers = ArrayList()
        arrangement = Array(HEIGHT_CHESS_FIELD) { Array(WIDTH_CHESS_FIELD) { EMPTY_FIELD } }
        repeat(2) { pasteLine() }
        return arrangement
    }

    private fun pasteLine() {
        val line = random.nextInt(HEIGHT_CHESS_FIELD)
        if (line in arrayNumbers) {
            pasteLine()
        } else {
            arrayNumbers.add(line)
            arrayNumbers.add(line - 1)
            arrayNumbers.add(line + 1)
          var rnd = random.nextInt(2)
            repeat(4) {
                arrangement[line][rnd] = FIELD_WITH_FIGURE
                rnd += 2
            }
        }
    }
}