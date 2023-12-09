package com.example.loginscreen;

import static com.example.loginscreen.R.*;
import static com.example.loginscreen.R.id.addBtn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main2);

        EditText username = (EditText) findViewById(id.emailField);
        EditText password = (EditText) findViewById(id.passwordField);
        Button Login = (Button) findViewById(id.button);
        DBHelper db = new DBHelper(this);

        FloatingActionButton addBtn = (FloatingActionButton) findViewById(id.addBtn);

        addBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity2.this, RegisterActivity.class);
                startActivity(i);
            }
        });




        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = username.getText().toString();
                String pwd = password.getText().toString();
                newAsync nasync  = new newAsync(input, pwd, MainActivity2.this, db);
                nasync.execute();
                Boolean status = nasync.getstatus();
                System.out.println(status);
                if (status){
                    Intent i = new Intent(MainActivity2.this, NewActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(MainActivity2.this,"Bad Credentials" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
class newAsync extends AsyncTask<Void, Void, String> {
    String input;
    String pwd;
    DBHelper db;
    Context context1;

    Boolean status;


    newAsync(String user, String password, Context context, DBHelper DB) {
        input = user;
        pwd = password;
        context1 = context;
        db = DB;
        status = false;
    }


    @Override
    protected String doInBackground(Void... voids) {
        Boolean result = db.validateLogin(input, pwd);
        if (!result) {
            status = false;
            return "Bad Credentials";

        } else {
            status = true;
            return "Login Successful";

        }
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println(s);
        if (s.equals("Login Successful")) {
            status = true;
        }
    }

    public Boolean getstatus() {
        return status;
    }
}
