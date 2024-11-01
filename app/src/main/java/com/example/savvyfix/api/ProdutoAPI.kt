package com.example.savvyfix.api

import com.example.savvyfix.Produto
import retrofit2.Response
import retrofit2.http.*

interface ProdutoAPI {
    @GET("produtos")
    suspend fun listarProdutos(): Response<List<Produto>>

    @POST("produtos")
    suspend fun criarProduto(@Body produto: Produto): Response<Produto>

    @PUT("produtos/{id}")
    suspend fun atualizarProduto(@Path("id") id: String, @Body produto: Produto): Response<Produto>

    @DELETE("produtos/{id}")
    suspend fun deleteProduto(@Path("id") produtoId: String): Response<Unit>
}
