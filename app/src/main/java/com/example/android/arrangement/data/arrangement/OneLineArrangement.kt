package com.example.android.arrangement.data.arrangement

import com.example.android.arrangement.*
import java.util.*

class OneLineArrangement: ChessArrangement {
    override fun arrangement(): Array<Array<String>> {
val arrangement: Array<Array<String>> = Array( HEIGHT_CHESS_FIELD) { Array(WIDTH_CHESS_FIELD) {EMPTY_FIELD} }
        val random = Random()
        arrangement[random.nextInt(HEIGHT_CHESS_FIELD - 1)] = Array(WIDTH_CHESS_FIELD) {FIELD_WITH_FIGURE}
        return arrangement
    }
}