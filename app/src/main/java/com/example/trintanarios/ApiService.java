package com.example.trintanarios;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface ApiService {

    // --- CHECK-IN ---
    @GET("checkins/pending")
    Call<List<GuestRecord>> getPendingCheckins();

    @GET("checkins/processed")
    Call<List<GuestRecord>> getProcessedCheckins();

    @PATCH("checkins/{id}/observations")
    Call<Void> updateCheckinObservations(@Path("id") String id, @Body ObservationPayload payload);

    @PATCH("checkins/{id}/process")
    Call<Void> processCheckin(@Path("id") String id);

    // --- CHECK-OUT ---
    @GET("checkouts/today")
    Call<List<GuestRecord>> getTodayCheckouts();

    @GET("checkouts/processed")
    Call<List<GuestRecord>> getProcessedCheckouts();

    @PATCH("checkouts/{id}/observations")
    Call<Void> updateCheckoutObservations(@Path("id") String id, @Body ObservationPayload payload);

    @PATCH("checkouts/{id}/process")
    Call<Void> processCheckout(@Path("id") String id);
}
