package com.amirlabs.moklet_meet.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amirlabs.moklet_meet.data.api.repository.MeetRepositoryImpl
import com.amirlabs.moklet_meet.ui.viewmodel.MeetViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MeetViewModel::class.java) ->
                MeetViewModel(MeetRepositoryImpl()) as T

            else -> throw IllegalArgumentException("Unknown Class Name")
        }
    }
}