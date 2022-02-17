package com.canitopai.proyectointegrador



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.canitopai.proyectointegrador.databinding.ProductItemBinding
import com.canitopai.proyectointegrador.model.Product
import com.canitopai.proyectointegrador.model.ProductInfo
import com.canitopai.proyectointegrador.model.ProductList

interface onProductListener {
    fun onClick(prod: Product)
}

class ProductAdapter(private val onUserClicked: (ProductList) -> Unit): ListAdapter<ProductList, ProductAdapter.ViewHolder>(ProductItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ProductItemBinding = ProductItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prod = getItem(position)

        holder.binding.tvItemName = prod.name
        holder.binding.tvItemPrice = prod.price

        holder.binding.root.setOnClickListener {
            onProductClicked(prod)
        }
    }

    inner class ViewHolder(val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root)


}

class UserItemCallback: DiffUtil.ItemCallback<ProductList>(){
    override fun areItemsTheSame(oldItem: ProductList, newItem: ProductList): Boolean = oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: ProductList, newItem: ProductList): Boolean = oldItem.url == newItem.url

}

