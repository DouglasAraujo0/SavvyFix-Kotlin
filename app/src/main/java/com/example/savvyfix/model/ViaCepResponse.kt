package com.example.savvyfix.model

data class ViaCepResponse(
    val cep: String,
    val logradouro: String,
    val bairro: String,
    val localidade: String,  // Cidade
    val uf: String  // Estado
)
