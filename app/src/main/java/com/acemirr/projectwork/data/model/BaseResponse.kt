package com.acemirr.projectwork.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Keep
data class BaseResponse<MODEL>(
    @SerializedName("status")
    var status: Int? = null,

    @SerializedName("success")
    var success: Boolean? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("data")
    var data: MODEL? = null,
)