package com.example.android.arrangement.presentation.app

import android.app.Application
import com.example.android.arrangement.presentation.di.MainModule
import com.github.stephanenicolas.toothpick.smoothie.BuildConfig
import toothpick.Scope
import toothpick.Toothpick
import toothpick.configuration.Configuration
import toothpick.registries.FactoryRegistryLocator
import toothpick.registries.MemberInjectorRegistryLocator

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initToothpick()
    }

       private fun initToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        } else {
            Toothpick.setConfiguration(Configuration.forProduction().disableReflection())
            MemberInjectorRegistryLocator.setRootRegistry(com.example.android.arrangement.MemberInjectorRegistry())
            FactoryRegistryLocator.setRootRegistry(com.example.android.arrangement.FactoryRegistry())
        }
        scope = Toothpick.openScope("mainScope")
        scope.installModules(MainModule())
    }
    companion object{
        lateinit var scope: Scope
    }
}