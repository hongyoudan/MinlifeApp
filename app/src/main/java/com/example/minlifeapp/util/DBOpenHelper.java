package com.example.minlifeapp.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBOpenHelper extends SQLiteOpenHelper {

    //声明一个AndroidSDK自带的数据库变量db
    private SQLiteDatabase db;

    //写一个这个类的构造函数，数据库设置成可写入状态
    public DBOpenHelper(Context context) {
        super(context, "db_MinLifeApp", null, 1);
        db = getReadableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS user(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "college CHAR(20)," +
                        "number CHAR(20) ," +
                        "name CHAR(10)," +
                        "gender CHAR(5)," +
                        "phone CHAR(20) ," +
                        "dormitory CHAR(10)," +
                        "password CHAR(10))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }

    /**
     * 增删改查
     * add()
     * delete()
     * update()
     * getAllData()
     */
    public void add(String college, String number, String name, String gender, String phone, String dormitory, String password) {
        db.execSQL("INSERT INTO user (college, number, name, gender, phone, dormitory, password) VALUES(?,?,?,?,?,?,?)",
                new Object[]{college, number, name, gender, phone, dormitory, password});
    }

    public void delete(String phone) {
        db.execSQL("DELETE FROM user WHERE phone = " + phone);
    }

    public void update(String college, String number, String name, String gender, String dormitory, String password, String phone) {
        db.execSQL("UPDATE user SET college = ? WHERE phone=" + phone,
                new Object[]{college});
        db.execSQL("UPDATE user SET number = ? WHERE phone=" + phone,
                new Object[]{number});
        db.execSQL("UPDATE user SET name = ? WHERE phone=" + phone,
                new Object[]{name});
        db.execSQL("UPDATE user SET gender = ? WHERE phone=" + phone,
                new Object[]{gender});
        db.execSQL("UPDATE user SET dormitory = ? WHERE phone=" + phone,
                new Object[]{dormitory});
        db.execSQL("UPDATE user SET password = ? WHERE phone=" + phone,
                new Object[]{password});
    }

    //查询出来的内容，定义了一个ArrayList类的list，使用游标Cursor，写一个while循环，让游标从表头游到表尾，把游出来的数据存放到list容器中
    public ArrayList<User> getAllData() {

        ArrayList<User> list = new ArrayList<User>();
        Cursor cursor = db.query("user", null, null, null, null, null, "number DESC");
        while (cursor.moveToNext()) {
            String college = cursor.getString(cursor.getColumnIndex("college"));
            String number = cursor.getString(cursor.getColumnIndex("number"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String gender = cursor.getString(cursor.getColumnIndex("gender"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String dormitory = cursor.getString(cursor.getColumnIndex("dormitory"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new User(college, number, name, gender, phone, dormitory, password));
        }
        return list;

    }


}
