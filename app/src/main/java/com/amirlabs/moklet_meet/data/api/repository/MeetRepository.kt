package com.amirlabs.moklet_meet.data.api.repository

import com.amirlabs.moklet_meet.data.api.core.UseCaseResult
import com.amirlabs.moklet_meet.data.model.BaseResponse
import com.amirlabs.moklet_meet.data.model.Meet

interface MeetRepository {
    suspend fun getListMeet(): UseCaseResult<List<Meet>>
    suspend fun insertMeet(data:Meet):UseCaseResult<Meet>
}