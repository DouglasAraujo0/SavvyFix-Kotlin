package com.example.savvyfix.api

import com.example.savvyfix.model.ViaCepResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepAPI {
    @GET("ws/{cep}/json/")
    suspend fun recuperarEndereco(@Path("cep") cep: String): Response<ViaCepResponse>
}
