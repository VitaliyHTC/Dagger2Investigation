package com.vitaliyhtc.dagger2investigation.presentation.productslist.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.vitaliyhtc.dagger2investigation.R
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import kotlinx.android.synthetic.main.list_item_product.view.*

class ProductsListAdapter : RecyclerView.Adapter<ProductsListAdapter.ProductsListViewHolder>() {

    private val mProducts: ArrayList<Product> = ArrayList()
    var mOnProductClickListener: OnProductClickListener? = null

    fun setOnProductClickListener(onProductClickListener: OnProductClickListener) {
        mOnProductClickListener = onProductClickListener
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }

    fun appendToProducts(products: List<Product>) {
        val c1 = itemCount
        mProducts.addAll(products)
        notifyItemRangeInserted(c1, products.size)
    }

    fun appendToProducts(product: Product) {
        mProducts.add(product)
        notifyItemInserted(itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ProductsListViewHolder {
        val v: View = LayoutInflater.from(parent?.context)
                .inflate(R.layout.list_item_product, parent, false)
        return ProductsListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductsListViewHolder, position: Int) {
        holder.bind(mProducts[position])
        holder.itemView.setOnClickListener { _ -> mOnProductClickListener?.onProductClick(mProducts[position].id) }
    }

    class ProductsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val mContext: Context = view.context
        private val title = view.item_title
        private val image = view.image_view_product_small

        fun bind(product: Product) {
            title.text = product.name
            Picasso.with(mContext)
                    .load(product.image_thumb_url)
                    .placeholder(R.drawable.ic_list_item_bg)
                    .error(R.drawable.ic_broken_image)
                    .into(image)
        }
    }

}