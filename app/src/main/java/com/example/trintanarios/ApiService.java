package com.example.trintanarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    // --- CHECK-IN ---
    @GET("checkin/pending")
    Call<List<GuestRecord>> getPendingCheckins();

    @GET("checkin/processed")
    Call<List<GuestRecord>> getProcessedCheckins();

    @POST("checkin/updateObservations")
    Call<Void> updateCheckinObservations(@Body ObservationPayload payload);

    @POST("checkin/processCheckInById")
    Call<Void> processCheckInById(@Body IdPayload payload);

    // --- CHECK-OUT ---
    @GET("checkout/pending")
    Call<List<GuestRecord>> getPendingCheckouts();

    @GET("checkout/processed")
    Call<List<GuestRecord>> getProcessedCheckouts();

    @POST("checkout/updateObservations")
    Call<Void> updateCheckoutObservations(@Body ObservationPayload payload);

    @POST("checkout/processCheckoutById")
    Call<Void> processCheckoutById(@Body IdPayload payload);
}
