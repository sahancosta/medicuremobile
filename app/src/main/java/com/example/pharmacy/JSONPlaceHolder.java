package com.example.pharmacy;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JSONPlaceHolder {

    @GET("/viewbookssss")
    Call<List<Drug>> getAllDrugs();

    @POST("addClient")
    Call<ResponseBody> saveClient (@Body Client client);

    @GET("getClientLogin/{cemail}")
    Call<ClientLogin> checkClientLogin(@Path("cemail") String cemail);





}
