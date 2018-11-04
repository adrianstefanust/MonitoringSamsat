package org.kaltimprov.samkal.network;

import org.kaltimprov.samkal.model.mon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RESTHelper {

    private static Retrofit retrofit;
    private static final String API_BASE_URL = "http://proxy.bpprdku.net/";

    /**
     * Method to get the required retrofit instance
     * @return retrofit instance
     */
    private static Retrofit getRetrofitInstance(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


public void monitoring(String tanggal){
    GetPostDataService service = getRetrofitInstance().create(GetPostDataService.class);
    Call<mon> call = service.monitoring(tanggal);

}
}
