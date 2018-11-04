package org.kaltimprov.samkal.network;


import org.kaltimprov.samkal.model.mon;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetPostDataService {

    @GET("monitoring.php")
   Call<mon> monitoring(@Query("tanggal") String tanggal);
}
