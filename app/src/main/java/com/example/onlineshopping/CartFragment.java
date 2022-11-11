package com.example.onlineshopping;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class CartFragment extends Fragment {

    ListView lv;
    Button placeOrder;
    TextView cartEmpty;
    public ArrayList<CartList> arr_bean;
    DBHelper db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        db = new DBHelper(this.getContext());
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);
        lv = (ListView) view.findViewById(R.id.cartListView);
        placeOrder = (Button) view.findViewById(R.id.placeOrderBtn);
        cartEmpty = (TextView) view.findViewById(R.id.cartEmpty);

        String name = getArguments().getString("name");
        String email = getArguments().getString("email");

        arr_bean = new ArrayList<CartList>();

        arr_bean = db.getProducts();

        lv.setAdapter(new CartListAdapter(arr_bean, this.getContext()));

        if (arr_bean.size()>0) {
            placeOrder.setVisibility(View.VISIBLE);
        } else {
            placeOrder.setVisibility(View.GONE);
            cartEmpty.setText(R.string.cart_empty);
        }

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext().getApplicationContext(), MainActivity.class);
                intent.putExtra("username", name);
                intent.putExtra("useremail", email);
                startActivity(intent);
                db.placeOrder();
                Toast.makeText(getContext().getApplicationContext(), "Order Placed", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}