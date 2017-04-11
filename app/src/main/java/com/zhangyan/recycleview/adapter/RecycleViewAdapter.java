package com.zhangyan.recycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangyan.recycleview.http.Fruit;
import com.zhangyan.recycleview.R;
import com.zhangyan.recycleview.http.Tngou;

import java.util.Collection;
import java.util.List;

/**
 * Created by zhang_yan on 2017/4/10.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Tngou.Cook> list;

    public RecycleViewAdapter(List<Tngou.Cook> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tngou.Cook cook = list.get(position);
        holder.fruitName.setText(cook.getName());
        holder.fruitImage.setImageResource(R.mipmap.address_book);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void addAll(Collection<? extends Tngou.Cook> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        View Clickview;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view) {
            super(view);
            Clickview = view;
            fruitImage = (ImageView) view.findViewById(R.id.image);
            fruitName = (TextView) view.findViewById(R.id.name);
        }
    }
}

