package com.vitaliyhtc.dagger2investigation.presentation.productslist.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.vitaliyhtc.dagger2investigation.R
import com.vitaliyhtc.dagger2investigation.domain.model.Product
import com.vitaliyhtc.dagger2investigation.utils.inflateLayout
import kotlinx.android.synthetic.main.list_item_product.view.*

class ProductsListAdapter : RecyclerView.Adapter<ProductsListAdapter.ProductsListViewHolder>() {

    private val mProducts: ArrayList<Product> = ArrayList()
    private var mOnProductClickListener: OnProductClickListener? = null

    fun setOnProductClickListener(onProductClickListener: OnProductClickListener) {
        mOnProductClickListener = onProductClickListener
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }

    fun appendToProducts(products: List<Product>) {
        val positionStart = itemCount
        mProducts.addAll(products)
        notifyItemRangeInserted(positionStart, products.size)
    }

    fun appendToProducts(product: Product) {
        mProducts.add(product)
        notifyItemInserted(itemCount - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListViewHolder {
        val v: View = inflateLayout(parent, R.layout.list_item_product)
        return ProductsListViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProductsListViewHolder, position: Int) {
        holder.bind(mProducts[position])
    }

    inner class ProductsListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val mContext: Context = view.context
        private val title = view.item_title
        private val image = view.image_view_product_small

        init {
            view.setOnClickListener { mOnProductClickListener?.onProductClick(mProducts[adapterPosition].id) }
        }

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