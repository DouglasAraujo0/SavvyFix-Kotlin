package com.example.savvyfix

import com.google.firebase.firestore.PropertyName

data class Produto(
    @get:PropertyName("id") var id: String = "",
    @get:PropertyName("nome") var nome: String,
    @get:PropertyName("descricao") var descricao: String,
    @get:PropertyName("preco") var preco: Double,
) {
    init {
        require(preco >= 0) { "O preço deve ser maior ou igual a zero." }
        require(nome.isNotBlank()) { "O nome não pode estar vazio." }
        require(descricao.isNotBlank()) { "A descrição não pode estar vazia." }
    }

    override fun toString(): String {
        return "Produto(id='$id', nome='$nome', descrição='$descricao', preço=$preco)"
    }
}
