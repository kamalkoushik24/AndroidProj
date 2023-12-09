package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView text = (TextView) findViewById(R.id.RegisterText);
        text.setText("Please Register a New User");

        EditText username = (EditText) findViewById(R.id.usernameR);
        EditText pwd = (EditText) findViewById(R.id.pwd);
        EditText pwdC = (EditText) findViewById(R.id.pwdC);
        Button regbtn = (Button) findViewById(R.id.registerbtn);
        DBHelper db = new DBHelper(this);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr = username.getText().toString();
                String pass = pwd.getText().toString();
                String passC = pwdC.getText().toString();

                if (usr.equals("") || pass.equals("") || passC.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please Enter all the Specified Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    new modAsync(usr, pass, passC, RegisterActivity.this, db).execute();
                    Intent i = new Intent(RegisterActivity.this, MainActivity2.class);
                    startActivity(i);
                }
            }
        });
    }





}
class modAsync extends AsyncTask<Void, Void, String> {

    private String usr;
    private String pass;
    private String passC;
    private Context context;
    private DBHelper db;
    modAsync(String Usr, String Pass, String PassC, Context cOntext, DBHelper Db){

        usr = Usr;
        pass = Pass;
        passC = PassC;
        db = Db;
        context = cOntext;
    }


    @Override
    protected String doInBackground(Void... params) {
        Boolean usrchk = db.checkUsername(usr);
        if (usrchk == false){
            if (pass.equals(passC)){
                db.insertData(usr, pass);
                return "Registered Successfully";
            }
            else {
                return "Passwords Do not Match";
            }
        } else{
            return "User Exists Already";
        }

    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}