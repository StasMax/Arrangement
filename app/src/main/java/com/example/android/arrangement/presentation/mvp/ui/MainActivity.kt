package com.example.android.arrangement.presentation.mvp.ui

import android.content.*
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.view.View
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.android.arrangement.*
import com.example.android.arrangement.presentation.app.App.Companion.scope
import com.example.android.arrangement.presentation.mvp.presenter.MainPresenter
import com.example.android.arrangement.presentation.mvp.view.MainView
import com.example.android.arrangement.presentation.service.ArrangementIntentService
import com.example.android.arrangement.presentation.service.ArrangementService
import kotlinx.android.synthetic.main.activity_main.*
import toothpick.Toothpick
import java.io.File
import java.io.FileOutputStream
import java.util.*
import javax.inject.Inject
import android.R.attr.name
import android.graphics.drawable.BitmapDrawable
import java.io.OutputStreamWriter


class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var arrangementService: ArrangementService
    private var bound: Boolean = false
    private val handler = Handler()
    private lateinit var receiver: BroadcastReceiver

    @Inject
    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = mainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, scope)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initReceiver()
        button.setOnClickListener { showArrangement() }
        buttonScreen.setOnClickListener { v -> screenShot(v) }
    }

    private fun initReceiver() {
        receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                matrix1.text = intent?.getStringExtra(PARAM_ARR)
            }
        }
        val intentFilter = IntentFilter(BROADCAST_ACTION)
        registerReceiver(receiver, intentFilter)
    }

    override fun showArrangement() {
        if (bound) {
            handler.postDelayed({
                matrix.text = arrangementService.getArrangements(FIRST_ARRANGEMENT)

            }, 1000)
            handler.postDelayed({
                matrix.text = arrangementService.getArrangements(SECOND_ARRANGEMENT)

            }, 3000)
            handler.postDelayed({
                matrix.text = arrangementService.getArrangements(THIRD_ARRANGEMENT)

            }, 5000)
        }

        val intent1 = Intent(this, ArrangementIntentService::class.java)
        intent1.putExtra(PARAM_ARR, FIRST_ARRANGEMENT)
        handler.postDelayed({ startService(intent1) }, 3000)

        val intent2 = Intent(this, ArrangementIntentService::class.java)
        intent2.putExtra(PARAM_ARR, SECOND_ARRANGEMENT)
        handler.postDelayed({ startService(intent2) }, 5000)

        val intent3 = Intent(this, ArrangementIntentService::class.java)
        intent3.putExtra(PARAM_ARR, THIRD_ARRANGEMENT)
        handler.postDelayed({ startService(intent3) }, 7000)
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

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    fun screenShot(view: View) {

        val now = Date()
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now)
       // val view = window.decorView.rootView
        view.isDrawingCacheEnabled = true
        view.buildDrawingCache()
        val b = view.drawingCache
        //   val bitmapDrawable = BitmapDrawable(resources, b)

        try {
            val sddir = File("$FOLDER_TO_SAVE/$now.jpg")
            if (!sddir.exists()) {
                sddir.mkdirs()
            }
            val fos = FileOutputStream(sddir)
            b.compress(Bitmap.CompressFormat.JPEG, 90, fos)

            fos.flush()
            fos.close()
        } catch (e: Exception) {
        }


        /*   try {
               val mPath = "$SCREEN_LOCATION/$now/screen.jpg"

               val v1 = window.decorView.rootView
               v1.isDrawingCacheEnabled = true
               val bitmap = Bitmap.createBitmap(v1.drawingCache)
               v1.isDrawingCacheEnabled = false
               val imageFile = File(mPath)
               if (!imageFile.exists()) {
                   imageFile.mkdirs()
               }
               val outputStream = FileOutputStream(imageFile)
               val quality = 100
               bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
               outputStream.flush()
               outputStream.close()
               //   openScreenshot(imageFile)
           } catch (e: Throwable) {
           }*/
    }
}
