package com.example.trintanarios;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckinRepository {

    private final ApiService apiService;
    private final MutableLiveData<List<GuestRecord>> pendingCheckins = new MutableLiveData<>();
    private final MutableLiveData<List<GuestRecord>> processedCheckins = new MutableLiveData<>();

    public CheckinRepository() {
        apiService = RetrofitClient.getApiService();
    }

    public LiveData<List<GuestRecord>> getPendingCheckins() {
        apiService.getPendingCheckins().enqueue(new Callback<List<GuestRecord>>() {
            @Override
            public void onResponse(Call<List<GuestRecord>> call, Response<List<GuestRecord>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    pendingCheckins.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<GuestRecord>> call, Throwable t) { }
        });
        return pendingCheckins;
    }

    public LiveData<List<GuestRecord>> getProcessedCheckins() {
        apiService.getProcessedCheckins().enqueue(new Callback<List<GuestRecord>>() {
            @Override
            public void onResponse(Call<List<GuestRecord>> call, Response<List<GuestRecord>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    processedCheckins.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<GuestRecord>> call, Throwable t) { }
        });
        return processedCheckins;
    }

    public void updateObservation(String id, String newObs) {
        apiService.updateCheckinObservations(id, new ObservationPayload(newObs)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getPendingCheckins();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) { }
        });
    }

    public void processCheckin(String id) {
        apiService.processCheckin(id).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    getPendingCheckins();
                    getProcessedCheckins();
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) { }
        });
    }
}
