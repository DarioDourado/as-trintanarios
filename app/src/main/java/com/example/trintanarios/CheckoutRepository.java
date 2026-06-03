package com.example.trintanarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutRepository {

    private final ApiService apiService;
    private final MutableLiveData<List<GuestRecord>> pendingCheckouts = new MutableLiveData<>();
    private final MutableLiveData<List<GuestRecord>> processedCheckouts = new MutableLiveData<>();
    
    // Mocks locais na camada de dados
    // SELECT * FROM checkedIn WHERE isProcessed = false  →  getPendingCheckouts()
    // SELECT * FROM checkedIn WHERE isProcessed = true   →  getProcessedCheckouts()
    private final List<GuestRecord> mockCheckouts = new ArrayList<>();

    public CheckoutRepository() {
        apiService = RetrofitClient.getApiService();
        
        // Mock Data
        GuestRecord g2 = new GuestRecord();
        g2.setId("2");
        g2.setNomeCompleto("Maria Souza");
        g2.setSaudacao("Sra.");
        g2.setQuarto(205);
        g2.setHoraChegada("12:00");
        g2.setAcompanhantes(0);
        g2.setNPessoas(1);
        g2.setDataSaida("2026-06-01");
        g2.setIdioma("EN");
        g2.setVipStatus("VIP3");
        g2.setObservacoes("Precisa de táxi");
        // isProcessed defaults to false

        mockCheckouts.add(g2);
    }

    public LiveData<List<GuestRecord>> getPendingCheckouts() {
        // --- FLUXO REAL DA API (COMENTADO) ---
        /*
        apiService.getPendingCheckouts().enqueue(new Callback<List<GuestRecord>>() {
            @Override
            public void onResponse(Call<List<GuestRecord>> call, Response<List<GuestRecord>> response) {
                if (response.isSuccessful()) {
                    pendingCheckouts.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<GuestRecord>> call, Throwable t) { }
        });
        */
        
        // --- FLUXO MOCK --- SELECT * FROM checkedIn WHERE isProcessed = false
        List<GuestRecord> pending = new ArrayList<>();
        for (GuestRecord r : mockCheckouts) {
            if (!r.isProcessed()) pending.add(r);
        }
        pendingCheckouts.setValue(pending);
        return pendingCheckouts;
    }

    public LiveData<List<GuestRecord>> getProcessedCheckouts() {
        // --- FLUXO REAL DA API (COMENTADO) ---
        /*
        apiService.getProcessedCheckouts().enqueue(new Callback<List<GuestRecord>>() { ... });
        */
        
        // --- FLUXO MOCK --- SELECT * FROM checkedIn WHERE isProcessed = true
        List<GuestRecord> processed = new ArrayList<>();
        for (GuestRecord r : mockCheckouts) {
            if (r.isProcessed()) processed.add(r);
        }
        processedCheckouts.setValue(processed);
        return processedCheckouts;
    }

    public void updateObservation(String id, String newObs) {
        // --- FLUXO REAL DA API (COMENTADO) ---
        /*
        apiService.updateCheckoutObservations(new ObservationPayload(id, newObs)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Recarregar
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) { }
        });
        */
        
        // --- FLUXO MOCK ---
        for (GuestRecord r : mockCheckouts) {
            if (r.getId().equals(id)) {
                r.setObservacoes(newObs);
                break;
            }
        }
        getPendingCheckouts();
    }

    public void processCheckout(String id) {
        // --- FLUXO REAL DA API (COMENTADO) ---
        /*
        apiService.processCheckoutById(new IdPayload(id)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Recarregar
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) { }
        });
        */
        
        // --- FLUXO MOCK ---
        for (GuestRecord r : mockCheckouts) {
            if (r.getId().equals(id)) {
                r.setIsProcessed(true);
                break;
            }
        }
        getPendingCheckouts();
        getProcessedCheckouts();
    }
}
