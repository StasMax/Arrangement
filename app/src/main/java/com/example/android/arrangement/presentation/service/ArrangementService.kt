package com.example.android.arrangement.presentation.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.android.arrangement.interactor.Interactor
import com.example.android.arrangement.presentation.app.App
import toothpick.Toothpick
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import javax.inject.Inject

class ArrangementService : Service() {

    init {
        Toothpick.inject(this, App.scope)
    }

    private val binder: IBinder = ArrangementBind()
    @Inject
    lateinit var interactor: Interactor
    var task1 = Callable<String> { interactor.getArrangement(1) }
    var task2 = Callable<String> { interactor.getArrangement(2) }
    var task3 = Callable<String> { interactor.getArrangement(3) }
    var executor = Executors.newFixedThreadPool(1)
    var future1 = executor.submit(task1)
    var future2 = executor.submit(task2)
    var future3 = executor.submit(task3)

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    class ArrangementBind : Binder() {
        fun getArrangement(): ArrangementService {
            return ArrangementService()
        }
    }

    fun getFirstArrangement(): String? {
        return future1.get()
    }

    fun getSecondArrangement(): String? {
        return future2.get()
    }

    fun getThirdArrangement(): String? {
        return future3.get()
    }
}
