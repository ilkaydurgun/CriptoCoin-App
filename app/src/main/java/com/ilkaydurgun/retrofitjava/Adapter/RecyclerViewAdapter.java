package com.ilkaydurgun.retrofitjava.Adapter;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ilkaydurgun.retrofitjava.Model.CriptoModel;
import com.ilkaydurgun.retrofitjava.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>{

    private ArrayList<CriptoModel> criptoList;

    private String[] colors={"#00b02f","#8168f2","#d2ed05","#d60804","#04c1d6","#c46500","#c40266"};

    public RecyclerViewAdapter(ArrayList<CriptoModel> criptoList) {
        this.criptoList = criptoList;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return  new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {

        holder.bind(criptoList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {

        return criptoList.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textPrice;
        public RowHolder(@NonNull View itemView) {

            super(itemView);

            textName= itemView.findViewById(R.id.textName);
            textPrice=itemView.findViewById(R.id.textPrice);
        }

        public void bind(CriptoModel criptoModel, String[] colors, Integer position){
            itemView.setBackgroundColor(Color.parseColor(colors[position % colors.length]));

            textName.setText(criptoModel.symbol);
            textPrice.setText(criptoModel.price);

        }
    }
}
