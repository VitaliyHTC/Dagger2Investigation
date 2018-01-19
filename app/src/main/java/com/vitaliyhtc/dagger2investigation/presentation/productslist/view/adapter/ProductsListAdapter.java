package com.vitaliyhtc.dagger2investigation.presentation.productslist.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vitaliyhtc.dagger2investigation.R;
import com.vitaliyhtc.dagger2investigation.domain.model.Product;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ProductsListViewHolder> {

    private OnProductClickListener mOnProductClickListener;
    private List<Product> mProducts;

    public ProductsListAdapter() {
        mProducts = new ArrayList<>();
    }

    public void setOnProductClickListener(OnProductClickListener onProductClickListener) {
        mOnProductClickListener = onProductClickListener;
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void appendToProducts(List<Product> products) {
        int c1 = getItemCount();
        mProducts.addAll(products);
        notifyItemRangeInserted(c1, products.size());
    }

    public void appendToProducts(Product product) {
        mProducts.add(product);
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public ProductsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_product, parent, false);
        return new ProductsListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductsListViewHolder holder, int position) {
        holder.bind(mProducts.get(position));
    }

    class ProductsListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_title)
        TextView textView;
        @BindView(R.id.image_view_product_small)
        ImageView imageView;

        private Context mContext;

        ProductsListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mContext = view.getContext();
        }

        @OnClick(R.id.rl_main)
        void onClick() {
            mOnProductClickListener.onProductClick(mProducts.get(getAdapterPosition()).getId());
        }

        void bind(Product product) {
            textView.setText(product.getName());
            Picasso.with(mContext)
                    .load(product.getImageThumbUrl())
                    .placeholder(R.drawable.ic_list_item_bg)
                    .error(R.drawable.ic_broken_image)
                    .into(imageView);
        }
    }
}
