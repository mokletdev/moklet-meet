package com.amirlabs.moklet_meet.data.api.service

import com.amirlabs.moklet_meet.data.model.BaseResponse
import com.amirlabs.moklet_meet.data.model.Meet
import retrofit2.http.*

interface ApiService {
    @GET("meet/getAll")
    suspend fun getAllMeet(): BaseResponse<List<Meet>>

    @POST("meet/insert")
    @FormUrlEncoded
    suspend fun insertMeet(
        @Field("name") name: String,
        @Field("creator") creator: String,
        @Field("start_date") startDate: String,
        @Field("end_date") endDate: String
    ): BaseResponse<Meet>

}