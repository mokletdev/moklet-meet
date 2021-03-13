package com.acemirr.projectwork.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acemirr.projectwork.data.api.repository.MeetRepositoryImpl
import com.acemirr.projectwork.ui.viewmodel.MeetViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MeetViewModel::class.java) ->
                MeetViewModel(MeetRepositoryImpl()) as T

            else -> throw IllegalArgumentException("Unknown Class Name")
        }
    }
}