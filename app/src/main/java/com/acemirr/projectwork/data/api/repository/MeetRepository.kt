package com.acemirr.projectwork.data.api.repository

import com.acemirr.projectwork.data.api.core.UseCaseResult
import com.acemirr.projectwork.data.model.BaseResponse
import com.acemirr.projectwork.data.model.Meet

interface MeetRepository {
    suspend fun getListMeet(): UseCaseResult<List<Meet>>
    suspend fun insertMeet(data:Meet):UseCaseResult<Meet>
}