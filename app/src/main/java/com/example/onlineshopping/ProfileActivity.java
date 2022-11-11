package com.example.onlineshopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class ProfileActivity extends AppCompatActivity {

    ImageView back;
    LinearLayout backToHome;
    TextView username, useremail, profileUsername, profileUseremail, edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent i = getIntent();
        String name = i.getStringExtra("userName");
        String email = i.getStringExtra("userEmail");

        Objects.requireNonNull(getSupportActionBar()).hide();

        username = (TextView) findViewById(R.id.profileName);
        profileUsername = (TextView) findViewById(R.id.profileUsername);
        useremail = (TextView) findViewById(R.id.profileEmail);
        profileUseremail = (TextView) findViewById(R.id.profileUseremail);
        back = (ImageView) findViewById(R.id.backIcon);
        backToHome = (LinearLayout) findViewById(R.id.backBtn);
        edit = (TextView) findViewById(R.id.editProfile);

        username.setText("@"+name);
        profileUsername.setText(name);
        useremail.setText(email);
        profileUseremail.setText(email);

        backToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                back.putExtra("username", name);
                back.putExtra("useremail", email);
                startActivity(back);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                back.putExtra("username", name);
                back.putExtra("useremail", email);
                startActivity(back);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Profile Editing is not available currently", Toast.LENGTH_SHORT).show();
            }
        });

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.statusbar));
        }
    }
}