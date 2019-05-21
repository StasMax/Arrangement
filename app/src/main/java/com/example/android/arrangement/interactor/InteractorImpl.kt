package com.example.android.arrangement.interactor

import com.example.android.arrangement.data.repository.Repository
import javax.inject.Inject

class InteractorImpl @Inject constructor(private val repository: Repository) : Interactor {
    override fun getArrangement(id: Int): String {
        return repository.getArrangementById(id = id)
    }
}