package com.example.sujal.pocketmed;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextView username, password;
    EditText edit1, edit2;
    Button busign, login;
    private int REQUEST_SIGNUP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextView) findViewById(R.id.user);
        password = (TextView) findViewById(R.id.password);
        edit1 = (EditText) findViewById(R.id.edit1);
        edit2 = (EditText) findViewById(R.id.edit2);
        login = (Button) findViewById(R.id.login);
        busign= (Button) findViewById(R.id.busign);

        busign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String name = intent.getStringExtra("username");
                String pword = intent.getStringExtra("password");
                if (name.equals(edit1.getText().toString())){
                    if(pword.equals(edit2.getText().toString())) {
                        Log.d("TAG", "login");
                        if (!validate()) {
                            onLoginFailed();
                            return;
                        }
                        login.setEnabled(false);
                        final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("Authenticating....");
                        progressDialog.show();

                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        onLoginSuccess();
                                        progressDialog.dismiss();
                                    }
                                }, 3000);
                        Intent i = new Intent(Login.this, MainActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), " Incorrect username or password ", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }


    private void onLoginSuccess() {
        login.setEnabled(true);
        finish();
    }

    private void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        login.setEnabled(true);
    }

    public boolean validate(){
        boolean valid = true;
        return valid;
    }
}



