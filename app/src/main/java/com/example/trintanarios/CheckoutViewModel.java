package com.example.trintanarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CheckoutViewModel extends ViewModel {

    private final CheckoutRepository repository;

    public CheckoutViewModel() {
        repository = new CheckoutRepository();
    }

    public LiveData<List<GuestRecord>> getPendingCheckouts() {
        return repository.getPendingCheckouts();
    }

    public LiveData<List<GuestRecord>> getProcessedCheckouts() {
        return repository.getProcessedCheckouts();
    }

    public void updateObservation(String id, String newObs) {
        repository.updateObservation(id, newObs);
    }

    public void processCheckout(String id) {
        repository.processCheckout(id);
    }
}
