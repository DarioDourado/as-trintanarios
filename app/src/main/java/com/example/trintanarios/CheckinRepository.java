package com.example.trintanarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class CheckinRepository {

    private final ApiService apiService;
    private final MutableLiveData<List<GuestRecord>> pendingCheckins = new MutableLiveData<>();
    private final MutableLiveData<List<GuestRecord>> processedCheckins = new MutableLiveData<>();
    
    // Mocks locais na camada de dados
    // SELECT * FROM checkedIn WHERE isProcessed = false  →  getPendingCheckins()
    // SELECT * FROM checkedIn WHERE isProcessed = true   →  getProcessedCheckins()
    private final List<GuestRecord> mockCheckins = new ArrayList<>();

    public CheckinRepository() {
        apiService = RetrofitClient.getApiService();
        
        // Inicializar Mock Data
        GuestRecord g1 = new GuestRecord();
        g1.setId("1");
        g1.setNomeCompleto("Kim Balley");
        g1.setSaudacao("Sr.");
        g1.setQuarto(104);
        g1.setHoraChegada("14:00");
        g1.setAcompanhantes(1);
        g1.setNPessoas(2);
        g1.setDataSaida("2026-06-10");
        g1.setIdioma("PT");
        g1.setVipStatus("Ouro");
        g1.setObservacoes("Chega cedo");
        // isProcessed defaults to false

        mockCheckins.add(g1);
    }

    public LiveData<List<GuestRecord>> getPendingCheckins() {
        // --- FLUXO REAL DA API (COMENTADO) ---
        /*
        apiService.getPendingCheckins().enqueue(new Callback<List<GuestRecord>>() {
            @Override
            public void onResponse(Call<List<GuestRecord>> call, Response<List<GuestRecord>> response) {
                if (response.isSuccessful()) {
                    pendingCheckins.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<GuestRecord>> call, Throwable t) {
                // Tratar erro
            }
        });
        */
        
        // --- FLUXO MOCK --- SELECT * FROM checkedIn WHERE isProcessed = false
        List<GuestRecord> pending = new ArrayList<>();
        for (GuestRecord r : mockCheckins) {
            if (!r.isProcessed()) pending.add(r);
        }
        pendingCheckins.setValue(pending);
        return pendingCheckins;
    }

    public LiveData<List<GuestRecord>> getProcessedCheckins() {
        // --- FLUXO REAL DA API (COMENTADO) ---
        /*
        apiService.getProcessedCheckins().enqueue(new Callback<List<GuestRecord>>() { ... });
        */
        
        // --- FLUXO MOCK --- SELECT * FROM checkedIn WHERE isProcessed = true
        List<GuestRecord> processed = new ArrayList<>();
        for (GuestRecord r : mockCheckins) {
            if (r.isProcessed()) processed.add(r);
        }
        processedCheckins.setValue(processed);
        return processedCheckins;
    }

    public void updateObservation(String id, String newObs) {
        // --- FLUXO REAL DA API (COMENTADO) ---
        /*
        apiService.updateCheckinObservations(new ObservationPayload(id, newObs)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Recarregar dados chamando getPendingCheckins()
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) { }
        });
        */
        
        // --- FLUXO MOCK ---
        for (GuestRecord r : mockCheckins) {
            if (r.getId().equals(id)) {
                r.setObservacoes(newObs);
                break;
            }
        }
        getPendingCheckins();
    }

    public void processCheckin(String id) {
        // --- FLUXO REAL DA API (COMENTADO) ---
        /*
        apiService.processCheckInById(new IdPayload(id)).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Recarregar listas chamando getPendingCheckins() e getProcessedCheckins()
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) { }
        });
        */
        
        // --- FLUXO MOCK ---
        for (GuestRecord r : mockCheckins) {
            if (r.getId().equals(id)) {
                r.setIsProcessed(true);
                break;
            }
        }
        getPendingCheckins();
        getProcessedCheckins();
    }
}
