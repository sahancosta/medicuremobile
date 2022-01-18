package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import at.favre.lib.crypto.bcrypt.BCrypt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientLoginActivity extends AppCompatActivity {


    private EditText txtlemail,txtlpwd;
    private Button btnlogin;
    private static final String TAG = "ClientLoginActivity";
    private static final String BASE_URL = "http://20.7.3.51:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login2);
        txtlemail = findViewById(R.id.txtlemail);
        txtlpwd = findViewById(R.id.txtlPwd);
        btnlogin = findViewById(R.id.btnlogin);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONPlaceHolder jsonPlaceHolder = retrofit.create(JSONPlaceHolder.class);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname,pass;
                uname = txtlemail.getText().toString();
                pass = txtlpwd.getText().toString();

                if (uname.equals("") || pass.equals("")){
                    Toast.makeText(ClientLoginActivity.this, "Please Fill Both The Fields", Toast.LENGTH_SHORT).show();
                    return;

                }

                Call<ClientLogin> call = jsonPlaceHolder.checkClientLogin(uname);
                call.enqueue(new Callback<ClientLogin>() {
                    @Override
                    public void onResponse(Call<ClientLogin> call, Response<ClientLogin> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(ClientLoginActivity.this, "Invalid Username Or Password.. Please Try Again...", Toast.LENGTH_SHORT).show();
                            Log.e(TAG,"Invalid Credentials:"+response.code());
                        }
                        ClientLogin clientLogin = response.body();
                        BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(),clientLogin.getClientpassword());
                        if (result.verified == false){
                            Toast.makeText(ClientLoginActivity.this, "Invalid Username Or Password,, Plaese Try Again..", Toast.LENGTH_SHORT).show();
                            Log.e(TAG,"Invalid Password:");
                            return;
                        }
                        else if (clientLogin.getClientstatus()!=1){
                            Toast.makeText(ClientLoginActivity.this, "Account Has Been Disabled..", Toast.LENGTH_SHORT).show();
                            Log.e(TAG,"Locked Account:"+response.code());
                            return;

                        }
                        else {
                            Toast.makeText(ClientLoginActivity.this, "Valid Customer", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ClientLogin> call, Throwable t) {
                        Toast.makeText(ClientLoginActivity.this, "Error:"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG,t.getMessage());

                    }
                });
            }
        });
    }

}