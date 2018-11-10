package com.jaijith.sois.health_care_app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button _btnlogin;
    EditText _txtEmail, _txtPass;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();
        _btnlogin=(Button)findViewById(R.id.btnlogin);
        _txtEmail=(EditText)findViewById(R.id.txtEmail);
        _txtPass=(EditText)findViewById(R.id.txtPass);
        _btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = _txtEmail.getText().toString();
                String pass = _txtPass.getText().toString();
                cursor = db.rawQuery("SELECT * FROM "+ DatabaseHelper.TABLE_NAME+ " WHERE " +DatabaseHelper.COL_5+ " =? AND " +DatabaseHelper.COL_4+ "=? ", new String[]{email, pass});
                if(cursor!=null){
                    if(cursor.getCount()>0){
                        cursor.moveToNext();
                        Toast.makeText(getApplicationContext(), "login successfull", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(getApplicationContext(), "login not successfull", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}
