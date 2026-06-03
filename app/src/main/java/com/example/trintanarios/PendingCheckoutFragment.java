package com.example.trintanarios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PendingCheckoutFragment extends Fragment {
    
    private CheckoutViewModel viewModel;
    private GuestRecordAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guest_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireParentFragment()).get(CheckoutViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        adapter = new GuestRecordAdapter(false, new GuestRecordAdapter.OnGuestRecordListener() {
            @Override
            public void onProcessClicked(GuestRecord record) {
                viewModel.processCheckout(record.getId());
            }

            @Override
            public void onSaveObservation(GuestRecord record, String newObservation) {
                viewModel.updateObservation(record.getId(), newObservation);
            }
        });
        recyclerView.setAdapter(adapter);

        viewModel.getPendingCheckouts().observe(getViewLifecycleOwner(), records -> {
            adapter.setRecords(records);
        });
    }
}
