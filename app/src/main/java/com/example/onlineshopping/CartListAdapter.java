package com.example.onlineshopping;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends BaseAdapter {

    public ArrayList<CartList> arrayListListener;
    private List<CartList> mListenerList;
    Context mContext;

    public CartListAdapter(List<CartList> mListenerList, Context context) {
        mContext = context;
        this.mListenerList = mListenerList;
        this.arrayListListener = new ArrayList<CartList>();
        this.arrayListListener.addAll(mListenerList);
    }

    public class ViewHolder {
        ImageView mItemPic;
        TextView mBrand, mName, mPrice;
    }

    @Override
    public int getCount() {
        return mListenerList.size();
    }

    @Override
    public Object getItem(int i) {
        return mListenerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CartListAdapter.ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cart_listview_layout, null);
            holder = new ViewHolder();
            holder.mItemPic = (ImageView) convertView.findViewById(R.id.productPic);
            holder.mBrand = (TextView) convertView.findViewById(R.id.productBrand);
            holder.mName = (TextView) convertView.findViewById(R.id.productName);
            holder.mPrice = (TextView) convertView.findViewById(R.id.productPrice);

            convertView.setTag(holder);
        } else {
            holder = (CartListAdapter.ViewHolder) convertView.getTag();
        }

        try {
            int image = mListenerList.get(position).getImage();
            holder.mItemPic.setImageResource(image);
            holder.mBrand.setText(mListenerList.get(position).getBrand());
            holder.mName.setText(mListenerList.get(position).getName());
            holder.mPrice.setText(mListenerList.get(position).getPrice());
        } catch (Exception ex) {}

        return convertView;
    }
}
