package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<Bean> data;
    private Context context;

    public MyAdapter(List<Bean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        ViewHolder viewHolder;

//      要拿到item的布局
        if(convertview == null) {
            viewHolder = new ViewHolder();
            convertview = LayoutInflater.from(context).inflate(R.layout.listview_item, parent, false);
            viewHolder.textView = convertview.findViewById(R.id.tv);
            convertview.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertview.getTag();
        }
        //TextView textView = convertview.findViewById(R.id.tv);
        //每次findViewById会很耗时，所以会单独创建一个ViewHolder
        //textView.setText(data.get(position).getName());
        viewHolder.textView.setText(data.get(position).getName());

        return convertview;
    }

    private final class ViewHolder {
        TextView textView;
    }
}
