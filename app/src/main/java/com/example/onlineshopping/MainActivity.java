package com.example.onlineshopping;

import static com.example.onlineshopping.R.*;
import static com.example.onlineshopping.R.id.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    TextView name, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).add(fragmentView, HomeFragment.class, null).addToBackStack(null).commit();

        setContentView(layout.activity_main);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");
        String userEmail = intent.getStringExtra("useremail");

        TextView title = findViewById(id.pageTitle);

        Objects.requireNonNull(getSupportActionBar()).hide();
        Objects.requireNonNull(title).setText(string.home);

        final DrawerLayout dl = findViewById(drawerLayout);
        NavigationView nv = findViewById(navigationView);

        View v = getLayoutInflater().inflate(layout.sidemenu_header, null);
        name = (TextView) v.findViewById(id.name);
        email = (TextView) v.findViewById(id.email);

        findViewById(menuIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(name).setText(userName);
                Objects.requireNonNull(email).setText(userEmail);
                dl.openDrawer(GravityCompat.START);
                nv.removeHeaderView(v);
                nv.addHeaderView(v);
            }
        });

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case home:
                        Objects.requireNonNull(title).setText(string.home);
                        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(fragmentView, HomeFragment.class, null).commit();
                        dl.closeDrawer(GravityCompat.START);
                        break;

                    case account:
                        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                        i.putExtra("userName", userName);
                        i.putExtra("userEmail", userEmail);
                        startActivity(i);
                        dl.closeDrawer(GravityCompat.START);
                        break;

                    case cart:
                        Bundle bundle = new Bundle();
                        bundle.putString("name", userName);
                        bundle.putString("email", userEmail);
                        CartFragment obj = new CartFragment();
                        obj.setArguments(bundle);

                        Objects.requireNonNull(title).setText(string.cart);
                        getSupportFragmentManager().beginTransaction().setReorderingAllowed(true).replace(fragmentView, CartFragment.class, bundle).commit();
                        dl.closeDrawer(GravityCompat.START);
                        break;

                    case signout:
                        dl.closeDrawer(GravityCompat.START);
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + item.getItemId());
                }
                return true;
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.teal_200));
        }
    }
}