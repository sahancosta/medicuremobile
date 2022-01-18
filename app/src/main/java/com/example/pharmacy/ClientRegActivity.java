 package com.example.pharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import at.favre.lib.crypto.bcrypt.BCrypt;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

 public class ClientRegActivity extends AppCompatActivity {

    private EditText txtname,txtemail,txtaddress,txtpwd;
    private Button btnregister;
    private static final String TAG = "ClientRegActivity";
    private static final String BASE_URL = "http://20.7.3.51:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_reg);

        txtname = findViewById(R.id.txtName);
        txtemail = findViewById(R.id.txtEmail);
        txtaddress = findViewById(R.id.txtAddress);
        txtpwd = findViewById(R.id.txtPwd);
        btnregister = findViewById(R.id.btnRegister);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONPlaceHolder jsonPlaceHolder = retrofit.create(JSONPlaceHolder.class);


        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cname,cemail,caddress,clientpassword;
                cname =txtname.getText().toString();
                cemail = txtemail.getText().toString();
                caddress = txtaddress.getText().toString();
                clientpassword = txtpwd.getText().toString();

                if (cname.equals("")|| cemail.equals("") || caddress.equals("") || clientpassword.equals("")){
                    Toast.makeText(ClientRegActivity.this, "Please Fill The Required Fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                    String hashPassword = BCrypt.withDefaults().hashToString(12,clientpassword.toCharArray());

                    ClientLogin clientLogin = new ClientLogin(cemail,hashPassword);
                    Client client = new Client(cname,cemail,caddress,clientLogin);


                Call<ResponseBody> call = jsonPlaceHolder.saveClient(client);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (!response.isSuccessful()){
                            Toast.makeText(ClientRegActivity.this, "Registration Faliure :"+response.code(), Toast.LENGTH_SHORT).show();
                            Log.e(TAG,"Response Error:"+response.code());
                            return;

                        }
                        Toast.makeText(ClientRegActivity.this, "Successfully Registered..", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(ClientRegActivity.this, "Registration Faliure :"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG,t.getMessage());


                    }
                });


            }
        });

    }
}