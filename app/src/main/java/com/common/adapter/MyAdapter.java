package com.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.adapter.com.common.entity.Person;
import com.example.chenkun.listviewdemo.R;

import java.util.List;

/**
 * this class Is to demonstrate the previous practice
 * when we need each ListView, needs to be rewritten all
 * sorts of this Adapter
 *
 * @author sky
 */
public class MyAdapter extends BaseAdapter {
    /**
     * data source
     */
    private List<Person> mData;
    /**
     * layout id
     */
    private int mLayoutId;
    private LayoutInflater mLayoutInflater;

    public MyAdapter(Context context, List<Person> data, int layoutId) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(mLayoutId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.head = (ImageView) convertView.findViewById(R.id.head);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Person person = mData.get(position);
        if (person != null) {
            viewHolder.head.setImageResource(person.getHeadId());
            viewHolder.name.setText(person.getName());
        }
        return convertView;
    }

    static class ViewHolder {
        public TextView name;
        public ImageView head;
    }
}
