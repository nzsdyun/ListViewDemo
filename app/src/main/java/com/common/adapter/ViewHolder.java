package com.common.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * ViewHolder cache ListView covert view and item view
 * at present, only to achieve the ImageView and TextView value of the definition,
 * the follow-up can be added to their own or through the getView to achieve their own
 * @author sky
 */
public class ViewHolder {
    /** convert view */
    private View mConvertView;
    /** covert item view collection */
    private SparseArray<View> mViews;
    private Context mContext;

    public ViewHolder(Context context, ViewGroup parent, int layoutId) {
        this.mContext = context;
        this.mViews = new SparseArray<>();
        this.mConvertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder =  new ViewHolder(context, parent, layoutId);
            convertView = viewHolder.getConvertView();
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return viewHolder;
    }
    /**
     * find view by view id
     * @param id view id
     * @param <T> generic paradigm
     * @return return return to the specific view
     */
    public <T extends View> T getView(int id) {
        if (id <= 0)
            throw new NullPointerException("resource id is null");
        View view = mViews.get(id);
        //FIXME: collection not get view, search through findViewById
        if (view == null) {
            view = mConvertView.findViewById(id);
            if (view == null)
                throw new NullPointerException("view not found by" + id);
            //FIXME: save to SpareArray through ID
            mViews.put(id, view);
        }
        return (T) view;
    }

    /**
     * set TextView text content
     * @param textId TextView id
     * @param text  TextView content
     */
    public void setText(int textId, CharSequence text) {
        TextView tv = getView(textId);
        tv.setText(text);
    }

    /**
     * set TextView text content
     * @param textId TextView id
     * @param resId  TextView resource id
     */
    public void setText(int textId, int resId) {
        this.setText(textId, getString(resId));
    }

    /**
     * set ImageView background
     * @param imageId ImageView id
     * @param bitmap Bitmap photo resource
     */
    public void setImageBitmap(int imageId, Bitmap bitmap) {
        ImageView iv = getView(imageId);
        iv.setImageBitmap(bitmap);
    }

    /**
     * set ImageView background
     * @param imageId ImageView id
     * @param resId ImageView resource id
     */
    public void setImageResource(int imageId, int resId) {
        ImageView iv = getView(imageId);
        iv.setImageResource(resId);
    }

    /**
     * set ImageView background
     * @param imageId ImageView id
     * @param imageUri ImageView Uri
     */
    public void setImageURI(int imageId, Uri imageUri) {
        ImageView iv = getView(imageId);
        //TODO: it is not wise to do so, you can choose the Image-loader or other cache frame to load the image.
        iv.setImageURI(imageUri);
    }

    /**
     * get resource string
     * @param id resource id
     * @return String content
     */
    private CharSequence getString(int id) {
        return mContext.getResources().getString(id);
    }

    /**
     * get ListView convert view
     * @return View
     */
    public View getConvertView() {
        return mConvertView;
    }
}
