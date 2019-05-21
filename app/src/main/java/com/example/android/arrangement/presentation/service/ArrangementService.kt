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

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    class ArrangementBind : Binder() {
        fun getArrangement(): ArrangementService {
            return ArrangementService()
        }
    }

    fun getArrangements(id: Int): String {
        val task = Callable<String> { interactor.getArrangement(id) }
        val executor = Executors.newFixedThreadPool(1)
        val future = executor.submit(task)
        val result = future.get()
        executor.shutdown()
        return result
    }
}
