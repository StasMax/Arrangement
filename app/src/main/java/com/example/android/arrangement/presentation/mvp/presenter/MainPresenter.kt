package com.example.android.arrangement.presentation.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.android.arrangement.interactor.Interactor
import com.example.android.arrangement.presentation.mvp.view.MainView
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(private val interactor: Interactor) : MvpPresenter<MainView>() {


}