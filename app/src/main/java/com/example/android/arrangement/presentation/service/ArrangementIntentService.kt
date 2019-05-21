package com.example.android.arrangement.presentation.service

import android.app.IntentService
import android.content.Intent
import com.example.android.arrangement.BROADCAST_ACTION
import com.example.android.arrangement.PARAM_ARR
import com.example.android.arrangement.interactor.Interactor
import com.example.android.arrangement.presentation.app.App
import toothpick.Toothpick
import javax.inject.Inject

class ArrangementIntentService : IntentService("ArrangementIntentService") {

    init {
        Toothpick.inject(this, App.scope)
    }

    @Inject
    lateinit var interactor: Interactor

    override fun onHandleIntent(intent: Intent?) {
        val id = intent?.getIntExtra(PARAM_ARR, 0)
        val arrangement = id?.let { interactor.getArrangement(it) }
        val newIntent = Intent(BROADCAST_ACTION)
        newIntent.putExtra(PARAM_ARR, arrangement)
        sendBroadcast(newIntent)
    }
}
