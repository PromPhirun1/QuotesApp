package com.promphirun.quotesapp;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://type.fit/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context)  {

        this.context = context;
    }

    public void GetAllQuotes(QuoteResponseListener listener){
        CallQuotes callQuotes = retrofit.create(CallQuotes.class);
        Call<List<QuotesResponse>> call = callQuotes.callQuotes();
        call.enqueue(new Callback<List<QuotesResponse>>() {
            @Override
            public void onResponse(Call<List<QuotesResponse>> call, Response<List<QuotesResponse>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "Request not successful!", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<List<QuotesResponse>> call, Throwable  t) {
               listener.didError(t.getMessage());

            }
        });
    }

    private interface  CallQuotes{
        @GET("api/quotes")
        Call<List<QuotesResponse>> callQuotes();
    }
}
