package com.example.trintanarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutRepository {

    private final ApiService apiService;
    private final MutableLiveData<List<GuestRecord>> pendingCheckouts = new MutableLiveData<>();
    private final MutableLiveData<List<GuestRecord>> processedCheckouts = new MutableLiveData<>();

    public CheckoutRepository() {
        apiService = RetrofitClient.getApiService();
    }

    public LiveData<List<GuestRecord>> getPendingCheckouts() {
        apiService.getTodayCheckouts().enqueue(new Callback<List<GuestRecord>>() {
            @Override
            public void onResponse(Call<List<GuestRecord>> call, Response<List<GuestRecord>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pendingCheckouts.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<GuestRecord>> call, Throwable t) { }
        });
        return pendingCheckouts;
    }

    public LiveData<List<GuestRecord>> getProcessedCheckouts() {
        apiService.getProcessedCheckouts().enqueue(new Callback<List<GuestRecord>>() {
            @Override
            public void onResponse(Call<List<GuestRecord>> call, Response<List<GuestRecord>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    processedCheckouts.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<GuestRecord>> call, Throwable t) { }
        });
        return processedCheckouts;
    }

    public void updateObservation(String id, String newObs) {
        apiService.updateCheckoutObservations(id, new ObservationPayload(newObs)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getPendingCheckouts();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) { }
        });
    }

    public void processCheckout(String id) {
        apiService.processCheckout(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getPendingCheckouts();
                    getProcessedCheckouts();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) { }
        });
    }
}
