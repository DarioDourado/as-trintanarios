package com.example.trintanarios;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class CheckinViewModel extends ViewModel {

    private final CheckinRepository repository;

    public CheckinViewModel() {
        repository = new CheckinRepository();
    }

    public LiveData<List<GuestRecord>> getPendingCheckins() {
        return repository.getPendingCheckins();
    }

    public LiveData<List<GuestRecord>> getProcessedCheckins() {
        return repository.getProcessedCheckins();
    }

    public void updateObservation(String id, String newObs) {
        repository.updateObservation(id, newObs);
    }

    public void processCheckin(String id) {
        repository.processCheckin(id);
    }
}
