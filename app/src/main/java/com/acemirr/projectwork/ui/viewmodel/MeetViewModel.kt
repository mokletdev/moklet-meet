package com.acemirr.projectwork.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acemirr.projectwork.data.api.core.UseCaseResult
import com.acemirr.projectwork.data.api.repository.MeetRepository
import com.acemirr.projectwork.data.model.Meet
import com.acemirr.projectwork.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MeetViewModel(private val meetRepository: MeetRepository):ViewModel() {
    val listMeet = SingleLiveEvent<List<Meet>>()
    val meets = SingleLiveEvent<Meet>()
    val message = SingleLiveEvent<String>()

    val name = MutableLiveData<String>()

    fun getListMeet(){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){ meetRepository.getListMeet() }
            when(response){
                is UseCaseResult.Success -> listMeet.postValue(response.data)
                is UseCaseResult.Failed -> message.postValue(response.errorMessage)
                is UseCaseResult.Error -> message.postValue(response.exception.message)
            }
        }
    }

    fun insertMeet(meet:Meet){
        viewModelScope.launch {
            val response = withContext(Dispatchers.IO){ meetRepository.insertMeet(meet) }
            when(response){
                is UseCaseResult.Success -> meets.postValue(response.data)
                is UseCaseResult.Failed -> message.postValue(response.errorMessage)
                is UseCaseResult.Error -> message.postValue(response.exception.message)
            }
        }
    }

}