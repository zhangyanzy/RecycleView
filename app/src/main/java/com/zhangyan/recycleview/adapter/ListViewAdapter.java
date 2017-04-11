package com.zhangyan.recycleview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zhangyan.recycleview.R;
import com.zhangyan.recycleview.http.Tngou;

import java.util.Collection;
import java.util.List;

/**
 * Created by zhang_yan on 2017/4/11.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Tngou.Cook> list;

    public ListViewAdapter(Context mContext, List<Tngou.Cook> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.listview_item, viewGroup, false);
            view.setTag(new ViewHolder(view));
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        Tngou.Cook cook = list.get(position);
        holder.Title.setText(cook.getName());
        holder.Context.setText(cook.getDescription());
        Picasso.with(mContext).load("http://tnfs.tngou.net/img" + cook.getImg()).into(holder.Image);
        return view;
    }

    public void addAll(Collection<? extends Tngou.Cook> collection) {
        list.addAll(collection);
        notifyDataSetChanged();
    }

    public static class ViewHolder {

        private ImageView Image;
        private TextView Title;
        private TextView Context;

        public ViewHolder(View view) {
            Image = (ImageView) view.findViewById(R.id.image_item);
            Title = (TextView) view.findViewById(R.id.title_item);
            Context = (TextView) view.findViewById(R.id.context_item);
        }

    }
}
