package com.canitopai.proyectointegrador


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.canitopai.proyectointegrador.databinding.ProductItemBinding
import com.canitopai.proyectointegrador.model.ProductObjectItem

class ProductAdapter(private val onProductClicked: (ProductObjectItem) -> Unit) :
    ListAdapter<ProductObjectItem, ProductAdapter.ViewHolder>(ProductItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProductItemBinding = ProductItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prod = getItem(position)

        holder.binding.tvItemName.text = prod.name
        holder.binding.tvItemPrice.text = prod.price.toString()
        holder.binding.tvCat.text = prod.category

        holder.binding.root.setOnClickListener {
            onProductClicked(prod)
        }
    }

    inner class ViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)


}

class ProductItemCallback : DiffUtil.ItemCallback<ProductObjectItem>() {
    override fun areItemsTheSame(oldItem: ProductObjectItem, newItem: ProductObjectItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ProductObjectItem,
        newItem: ProductObjectItem
    ): Boolean = oldItem.id == newItem.id

}

