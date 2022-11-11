package com.example.onlineshopping;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.ColorFilter;
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
import java.util.zip.Inflater;

public class CustomListAdapter extends BaseAdapter {

    public ArrayList<CustomList> arrayListListener;
    private List<CustomList> mListenerList;
    Context mContext;
    ColorFilter color;
    DBHelper db;

    public CustomListAdapter(List<CustomList> mListenerList, Context context) {
        mContext = context;
        this.mListenerList = mListenerList;
        this.arrayListListener = new ArrayList<CustomList>();
        this.arrayListListener.addAll(mListenerList);
    }

    public class ViewHolder {
        ImageView mItemPic, mCartPic;
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
        final ViewHolder holder;
        db = new DBHelper(mContext);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.custom_listview_layout, null);
            holder = new ViewHolder();
            holder.mItemPic = (ImageView) convertView.findViewById(R.id.productPic);
            holder.mCartPic = (ImageView) convertView.findViewById(R.id.productCart);
            holder.mBrand = (TextView) convertView.findViewById(R.id.productBrand);
            holder.mName = (TextView) convertView.findViewById(R.id.productName);
            holder.mPrice = (TextView) convertView.findViewById(R.id.productPrice);

            color = holder.mCartPic.getColorFilter();

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            int image = mListenerList.get(position).getImage();
            holder.mItemPic.setImageResource(image);
            holder.mBrand.setText(mListenerList.get(position).getBrand());
            holder.mName.setText(mListenerList.get(position).getName());
            holder.mPrice.setText(mListenerList.get(position).getPrice());

            holder.mCartPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.mCartPic.getColorFilter() != color) {
                        Toast.makeText(mContext, holder.mBrand.getText()+" "+holder.mName.getText()+" already in cart", Toast.LENGTH_SHORT).show();
                    } else {
                        Boolean added = db.addProduct(image, holder.mBrand.getText().toString(), holder.mName.getText().toString(), holder.mPrice.getText().toString());
                        if (added) {
                            Toast.makeText(mContext, holder.mBrand.getText() + " " + holder.mName.getText() + " added to cart", Toast.LENGTH_SHORT).show();
                            holder.mCartPic.setColorFilter(ContextCompat.getColor(mContext, R.color.green), PorterDuff.Mode.MULTIPLY);
                        }
                        else Toast.makeText(mContext, "Unable to add " + holder.mBrand.getText() + " " + holder.mName.getText() + " to cart", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception ex) {}

        return convertView;
    }
}