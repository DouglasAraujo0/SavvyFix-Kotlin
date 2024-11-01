package com.example.savvyfix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat

class ProdutoAdapter(
    private val produtos: List<Produto>,
    private val onProdutoClick: (Produto) -> Unit,
    private val onProdutoDelete: (Produto) -> Unit
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.textNomeProduto)
        val descricao: TextView = itemView.findViewById(R.id.textDescricaoProduto)
        val preco: TextView = itemView.findViewById(R.id.textPrecoProduto)
        val deleteButton: ImageView = itemView.findViewById(R.id.buttonDeleteProduto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.nome.text = produto.nome
        holder.descricao.text = produto.descricao
        holder.preco.text = NumberFormat.getCurrencyInstance().format(produto.preco) // Formatação do preço

        holder.itemView.setOnClickListener {
            onProdutoClick(produto)
        }

        holder.deleteButton.setOnClickListener {
            onProdutoDelete(produto)
        }
    }

    override fun getItemCount(): Int = produtos.size
}
