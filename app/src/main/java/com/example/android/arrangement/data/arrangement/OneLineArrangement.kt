package com.example.android.arrangement.data.arrangement

import com.example.android.arrangement.*
import java.util.*

class OneLineArrangement: ChessArrangement {
    override fun arrangement(): Array<Array<String>> {
val arrangement: Array<Array<String>> = Array( heightChessField) { Array(widthChessField) {emptyField} }
        val random = Random()
        arrangement[random.nextInt(heightChessField - 1)] = Array(widthChessField) {fieldWithFigure}
        return arrangement
    }
}