package com.example.onlineshopping;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    ListView lv;
    public ArrayList<CustomList> arr_bean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View cartView = inflater.inflate(R.layout.custom_listview_layout, container, false);
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        lv = (ListView) view.findViewById(R.id.listView);

        ImageView productPic = (ImageView) cartView.findViewById(R.id.productPic);
        TextView brand = (TextView) cartView.findViewById(R.id.productBrand);
        TextView name = (TextView) cartView.findViewById(R.id.productName);
        TextView price = (TextView) cartView.findViewById(R.id.productPrice);

        String productBrand = brand.getText().toString();
        String productName = name.getText().toString();
        String productPrice = price.getText().toString();

        int imageId = getResources().getIdentifier(String.valueOf(productPic), "drawable", getContext().getPackageName());

        arr_bean = new ArrayList<CustomList>();

        arr_bean.add(new CustomList(R.drawable.tshirt, "LEVI'S", "White t-shirt", "Rs. 1050"));
        arr_bean.add(new CustomList(R.drawable.waterbottles, "CELLO", "Water bottles - 4", "Rs. 850"));
        arr_bean.add(new CustomList(R.drawable.laptop, "RAZER", "Gaming Laptop", "Rs. 100000"));
        arr_bean.add(new CustomList(R.drawable.mouse, "RAZER", "Gaming Mouse", "Rs. 3000"));
        arr_bean.add(new CustomList(R.drawable.mechanicalkeyboard, "RAZER", "Mechanical Keyboard", "Rs. 10000"));

        lv.setAdapter(new CustomListAdapter(arr_bean, this.getContext()));

        return view;
    }
}