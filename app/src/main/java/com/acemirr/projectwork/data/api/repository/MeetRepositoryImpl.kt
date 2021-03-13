package com.acemirr.projectwork.data.api.repository

import com.acemirr.projectwork.data.api.core.NetworkConfig
import com.acemirr.projectwork.data.api.core.UseCaseResult
import com.acemirr.projectwork.data.model.BaseResponse
import com.acemirr.projectwork.data.model.Meet

class MeetRepositoryImpl(): MeetRepository {
    private val network = NetworkConfig.getApi()
    override suspend fun getListMeet(): UseCaseResult<List<Meet>> {
        return try {
                val response = network.getAllMeet()
                if (response.status==200){
                    UseCaseResult.Success(response.data?: emptyList())
                }else{
                    UseCaseResult.Failed(response.message?:"")
                }
        }catch (e:Exception){
            UseCaseResult.Error(e)
        }
    }

    override suspend fun insertMeet(data: Meet): UseCaseResult<Meet> {
        return try {
            val response = network.insertMeet(data.name!!,data.creator!!,data.startDate!!,data.endDate!!)
            if (response.status==200){
                UseCaseResult.Success(response.data?:Meet())
            }else{
                UseCaseResult.Failed(response.message?:"")
            }
        }catch (e:Exception){
            UseCaseResult.Error(e)
        }
    }
}