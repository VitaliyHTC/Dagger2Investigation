package com.vitaliyhtc.dagger2investigation.view.adapter;

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

public class ProductsListAdapter extends RecyclerView.Adapter<ProductsListAdapter.ProductsListViewHolder> {

    private final Picasso picasso;
    private OnProductClickListener mOnProductClickListener;
    private List<Product> mProducts;

    public ProductsListAdapter(Picasso picasso) {
        this.picasso = picasso;
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
        mProducts.addAll(products);
    }

    public void appendToProducts(Product product) {
        mProducts.add(product);
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

    public class ProductsListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_title)
        public TextView textView;
        @BindView(R.id.image_view_product_small)
        public ImageView imageView;

        private Context mContext;

        ProductsListViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mContext = view.getContext();

            // TODO: 1/17/18 how many times we talked about this? you already have butterknife here
            view.setOnClickListener(v -> mOnProductClickListener.onProductClick(mProducts.get(getAdapterPosition()).getId()));
        }

        void bind(Product product) {
            textView.setText(product.getName());
            picasso.with(mContext)
                    .load(product.getImageThumbUrl())
                    .placeholder(R.drawable.ic_list_item_bg)
                    .error(R.drawable.ic_broken_image)
                    .into(imageView);
        }
    }
}
