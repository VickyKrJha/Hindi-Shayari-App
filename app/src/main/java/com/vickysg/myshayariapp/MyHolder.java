package com.vickysg.myshayariapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView img;
    TextView modelTitle;
    private ItemClickListener itemClickListener;

    MyHolder(@NonNull View itemView) {
        super(itemView);
        this.img = itemView.findViewById(R.id.modelImage);
        this.modelTitle = itemView.findViewById(R.id.modelTitle);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        this.itemClickListener.onItemClick(view, getLayoutPosition());
    }

    void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}