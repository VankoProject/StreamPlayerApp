package com.kliachenko.data.main.cloud

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoRecordService {

    @GET("api/videos/")
    fun videoRecords(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = API_KEY,
    ): Call<TotalResponse>

    companion object {
        private const val QUERY_PARAM_API_KEY = "key"
        private const val API_KEY = "46155926-ad922df1f20bebf4cecfdcbf7"
    }

}