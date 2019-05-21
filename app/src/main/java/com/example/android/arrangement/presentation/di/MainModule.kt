package com.example.android.arrangement.presentation.di

import com.example.android.arrangement.data.repository.Repository
import com.example.android.arrangement.data.repository.RepositoryImpl
import com.example.android.arrangement.interactor.Interactor
import com.example.android.arrangement.interactor.InteractorImpl
import toothpick.config.Module

class MainModule : Module() {
    init {
        this.bind(Repository::class.java).to(RepositoryImpl::class.java).singletonInScope()
        this.bind(Interactor::class.java).to(InteractorImpl::class.java).singletonInScope()
    }
}