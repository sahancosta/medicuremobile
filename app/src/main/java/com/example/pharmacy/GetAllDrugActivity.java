package com.example.pharmacy;

import android.app.AppComponentFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetAllDrugActivity extends AppCompatActivity {
    private static final String TAG = "GetAllDrugActivity";
    private final String BASE_URL = "http://20.7.3.51:8080";
    private DrugAdapter drugAdapter;
    private ArrayList<Drug> drugArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_drug);
        recyclerView = findViewById(R.id.recycle_drugs);
        recyclerView.setLayoutManager(new LinearLayoutManager(GetAllDrugActivity.this));
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        JSONPlaceHolder jsonPlaceHolder = retrofit.create(JSONPlaceHolder.class);
        Call<List<Drug>> listCall = jsonPlaceHolder.getAllDrugs();
        listCall.enqueue(new Callback<List<Drug>>() {
            @Override
            public void onResponse(Call<List<Drug>> call, Response<List<Drug>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(GetAllDrugActivity.this, "Error!!!"+response.code(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG,"Response error!!! :"+ response.code());
                    return;
                }


                else {
                    List<Drug> drugdetList = response.body();
                    drugAdapter = new DrugAdapter(drugdetList,GetAllDrugActivity.this);
                    recyclerView.setAdapter(drugAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Drug>> call, Throwable t) {
                Toast.makeText(GetAllDrugActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
