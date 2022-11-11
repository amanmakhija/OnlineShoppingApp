package com.example.onlineshopping;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "shopDB", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(name TEXT, email TEXT PRIMARY KEY, password TEXT)");
        db.execSQL("CREATE TABLE products(image INTEGER, brand TEXT, name TEXT, price TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop Table if exists users");
        db.execSQL("Drop Table  if exists products");
        onCreate(db);
    }

    public void addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);

        long res = 0;

        try {
            res = db.insertOrThrow("users", null, contentValues);
        } catch(SQLException e) {
            Log.e("Exception","SQLException"+ e.getMessage());
            e.printStackTrace();
        }
    }

    public Boolean checkUserEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from users where email = ?", new String[] {email});

        return cursor.getCount() > 0;
    }

    public Boolean checkUserEmailPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from users where email = ? and password = ?", new String[] {email, password});

        return 0 < cursor.getCount();
    }

    public String getUserName(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from users where email = ?", new String[] {email});

        if (cursor.moveToNext()) {
            int col = cursor.getColumnIndex("name");
            final String string = cursor.getString(col);
            return string;
        } else {
            return "FALSE";
        }
    }

    public Boolean addProduct(int image, String brand, String name, String price) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("image", image);
        values.put("brand", brand);
        values.put("name", name);
        values.put("price", price);

        long res = 0;
        try {
            res = db.insertOrThrow("products", null, values);
        } catch (SQLException e) {
            Log.e("Exception", "SQLException" + e.getMessage());
            e.printStackTrace();
        }
        db.close();

        return res != -1;
    }

    public void placeOrder() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from products");
    }

    public ArrayList<CartList> getProducts() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM products", null);

        ArrayList<CartList> products = new ArrayList<CartList>();

        if (cursor.moveToFirst()) {
            do {
                products.add(new CartList(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return products;
    }
}
