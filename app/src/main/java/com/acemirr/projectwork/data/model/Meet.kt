package com.acemirr.projectwork.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class Meet(
    @SerializedName("_id")
    var id: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("creator")
    var creator: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("start_date")
    var startDate: String? = null,
    @SerializedName("end_date")
    var endDate: String? = null,
    @SerializedName("created_at")
    var createdAt: String? = null,
    @SerializedName("updated_at")
    var updatedAt: String? = null,
) : Parcelable