package com.example.android.arrangement.presentation.mvp.ui

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.android.arrangement.presentation.app.App.Companion.scope
import com.example.android.arrangement.presentation.mvp.presenter.MainPresenter
import com.example.android.arrangement.presentation.mvp.view.MainView
import com.example.android.arrangement.presentation.service.ArrangementService
import kotlinx.android.synthetic.main.activity_main.*
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var arrangementService: ArrangementService
    private var bound: Boolean = false
    private val handler = Handler()

    @Inject
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = mainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, scope)
        super.onCreate(savedInstanceState)
        setContentView(com.example.android.arrangement.R.layout.activity_main)
        button.setOnClickListener { showArrangement() }
    }

    override fun showArrangement() {
        if (bound) {
            handler.postDelayed({ matrix.text = arrangementService.getArrangements(1) }, 1000)
            handler.postDelayed({ matrix.text = arrangementService.getArrangements(2) }, 3000)
            handler.postDelayed({ matrix.text = arrangementService.getArrangements(3) }, 5000)
        }
    }

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            bound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val arrangementBinder: ArrangementService.ArrangementBind = service as ArrangementService.ArrangementBind
            arrangementService = arrangementBinder.getArrangement()
            bound = true
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, ArrangementService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onStop() {
        super.onStop()
        if (bound) {
            unbindService(connection)
            bound = false
        }
    }
}
