package com.luxembourgaifm.fundsbox.api;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    @FormUrlEncoded
    @POST("/")
    Call<ResponseBody>  invest(
            @Field("user") String user,
            @Field("asset") String asset,
            @Field("amount") String amount,
            @Field("ccy") String ccy
    );
}
