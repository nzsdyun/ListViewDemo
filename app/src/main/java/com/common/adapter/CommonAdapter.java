package com.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Generic ListView adapter, but currently only supports the single layout of the form,
 * the follow-up will increase the layout of the support. you can use just like the following:
 * <pre>
 *     <code>
 *                  mListView.setAdapter(new CommonAdapter<Person>(this, persons, R.layout.list_item) {
                        @Override
                        public void convert(ViewHolder viewHolder, Person item, int position) {
                            viewHolder.setText(R.id.name, item.getName());
                            viewHolder.setImageResource(R.id.head, item.getHeadId());
                        }
                    });
 *     </code>
 * </pre>
 *
 * @param <T>
 * @author sky
 * @version 1.0
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    /** use List cache data */
    private List<T> mData;
    /** single layout id */
    private int mLayoutId;
    private Context mContext;

    public CommonAdapter(Context context, List<T> data, int layoutId) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, mLayoutId);
        convert(viewHolder, getItem(position), position);
        return viewHolder.getConvertView();
    }

    /**
     * subclass need to rewrite it, the process of rewriting, you can define the value of the control
     * @param viewHolder @see ViewHolder
     * @param item  ListView item
     * @param position Item view position
     */
    public abstract void convert(ViewHolder viewHolder, T item, int position);

}
