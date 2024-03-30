package com.laznas.mylmi.network

import com.laznas.mylmi.model.ResponseMagazinesItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("apilmi/mylmi-app/public/api/magazines")
    fun getMagazines(): Call<ArrayList<ResponseMagazinesItem>>
}