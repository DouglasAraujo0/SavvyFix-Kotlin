package com.example.savvyfix

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProdutoAdapter(
    private val produtos: List<Produto>,
    private val onClick: (Produto) -> Unit,
    private val onDelete: (Produto) -> Unit
) : RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder>() {

    class ProdutoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nome: TextView = itemView.findViewById(R.id.textNomeProduto)
        val descricao: TextView = itemView.findViewById(R.id.textDescricaoProduto)
        val preco: TextView = itemView.findViewById(R.id.textPrecoProduto)
        val deleteButton: ImageView = itemView.findViewById(R.id.buttonDeleteProduto) // Botão para deletar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produto, parent, false)
        return ProdutoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        val produto = produtos[position]
        holder.nome.text = produto.nome
        holder.descricao.text = produto.descricao
        holder.preco.text = produto.preco.toString()

        // Configurar o clique para editar o produto
        holder.itemView.setOnClickListener {
            onClick(produto)
        }

        // Configurar o clique para deletar o produto
        holder.deleteButton.setOnClickListener {
            onDelete(produto)
        }
    }

    override fun getItemCount(): Int = produtos.size
}
