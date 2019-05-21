package com.example.android.arrangement.presentation.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import com.example.android.arrangement.interactor.Interactor
import com.example.android.arrangement.presentation.app.App
import toothpick.Toothpick
import javax.inject.Inject

class ArrangementService : Service() {

    init {
        Toothpick.inject(this, App.scope)
    }

    private val binder: IBinder = ArrangementBind()
    private val handler = Handler()
    @Inject
    lateinit var interactor: Interactor

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    class ArrangementBind : Binder() {
        fun getArrangement(): ArrangementService {
            return ArrangementService()
        }
    }

    fun getFirstArrangement(): String? {
        return interactor.getArrangement(1)
       /* var arr: String? = null
        handler.postDelayed({ arr = interactor.getArrangement(1) }, 3000)
                return arr*/
    }

    fun getSecondArrangement(): String? {
        return interactor.getArrangement(2)
       /* var arr: String? = null
        handler.postDelayed({ arr = interactor.getArrangement(2) }, 5000)
        return arr*/
    }

    fun getThirdArrangement(): String? {
        return interactor.getArrangement(3)
       /* var arr: String? = null
        handler.postDelayed({ arr = interactor.getArrangement(3) }, 7000)
        return arr*/
    }
}
