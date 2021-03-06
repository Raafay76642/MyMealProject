package com.example.mymealproject.DiscoverDishes;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.mymealproject.OpenRestaurant.open_restaurant;
import com.example.mymealproject.R;
import com.example.mymealproject.MenuModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<MenuModel> mFoodList;
    FoodAdapter(Context context, ArrayList<MenuModel> foodList){
        mContext = context;
        mFoodList = foodList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {

        LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
        View mView = mLayoutInflater.inflate(R.layout.rv_food_item,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(mView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        try{
            final MenuModel menuModel = mFoodList.get(i);
            viewHolder.item_name.setText(menuModel.getName());
            viewHolder.item_price.setText(menuModel.getPrice()+ " Rs");
            viewHolder.item_restName.setText(menuModel.getRestName());
            viewHolder.ratingBar.setRating(Float.parseFloat(menuModel.getRating()));
            Picasso.with(mContext).load(menuModel.getImageUrl()).fit().into(viewHolder.item_image);
            viewHolder.goToRest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,open_restaurant.class);
                    intent.putExtra("rID",menuModel.getrID());
                    mContext.startActivity(intent);
                }
            });
//            viewHolder.setItemClickListener(new ItemClickListener() {
//                @Override
//                public void onClick(View view, int i) {
//                    Intent intent = new Intent(mContext,open_restaurant.class);
//                    intent.putExtra("rID",menuModel.getrID());
//                    mContext.startActivity(intent);
//
//
//                }
//            });
        }
        catch (Exception e){
            Log.d("test", "onBindViewHolder: " + e);
        }



    }




    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView item_name,item_price,item_restName;
        ImageView item_image;
        Button goToRest;
        RatingBar ratingBar;
        private ItemClickListener itemClickListener;
        public ViewHolder( View itemView) {
            super(itemView);


            item_name = itemView.findViewById(R.id.RestName);
            item_price = itemView.findViewById(R.id.itemPrice);
            item_restName = itemView.findViewById(R.id.itemRestName);
            item_image = itemView.findViewById(R.id.itemImage);
            ratingBar = itemView.findViewById(R.id.bar);
            goToRest = itemView.findViewById(R.id.goToRest);

//            goToRest.setOnClickListener(this);

        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener= itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition());
        }
    }

}
