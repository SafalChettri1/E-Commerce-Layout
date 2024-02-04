package net.com.gopal.ecommerceapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import net.com.gopal.ecommerceapp.R;
import net.com.gopal.ecommerceapp.databinding.ItemCatgeoriesBinding;
import net.com.gopal.ecommerceapp.utilities.Category;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    ArrayList<Category> categories;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_catgeories,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
Category category = categories.get(position);
holder.itemCatgeoriesBinding.itemCategoriesTextview.setText(category.getName());
        Picasso.get().load(category.getIcon()).into(holder.itemCatgeoriesBinding.imageItemCategories);

        holder.itemCatgeoriesBinding.imageItemCategories.setBackgroundColor(Color.parseColor(category.getColor()));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        ItemCatgeoriesBinding itemCatgeoriesBinding;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
             itemCatgeoriesBinding = ItemCatgeoriesBinding.bind(itemView);
        }
    }
}
