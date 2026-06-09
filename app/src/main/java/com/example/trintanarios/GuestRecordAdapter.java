package com.example.trintanarios;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class GuestRecordAdapter extends RecyclerView.Adapter<GuestRecordAdapter.ViewHolder> {

    public interface OnGuestRecordListener {
        void onProcessClicked(GuestRecord record);
        void onSaveObservation(GuestRecord record, String newObservation);
    }

    private List<GuestRecord> records = new ArrayList<>();
    private final boolean isProcessedTab;
    private final OnGuestRecordListener listener;

    public GuestRecordAdapter(boolean isProcessedTab, OnGuestRecordListener listener) {
        this.isProcessedTab = isProcessedTab;
        this.listener = listener;
    }

    public void setRecords(List<GuestRecord> records) {
        this.records = records;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guest_record, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GuestRecord record = records.get(position);
        holder.bind(record, isProcessedTab, listener);
    }

    @Override
    public int getItemCount() {
        return records != null ? records.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvRoom, tvArrivalTime, tvDepartureDate, tvPeople, tvLanguage, tvVip, tvObservationsDisplay;
        TextInputEditText etObservations;
        View tilObservations;
        ImageButton btnSaveObs;
        MaterialButton btnAction;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvRoom = itemView.findViewById(R.id.tv_room);
            tvArrivalTime = itemView.findViewById(R.id.tv_arrival_time);
            tvDepartureDate = itemView.findViewById(R.id.tv_departure_date);
            tvPeople = itemView.findViewById(R.id.tv_people);
            tvLanguage = itemView.findViewById(R.id.tv_language);
            tvVip = itemView.findViewById(R.id.tv_vip);
            tilObservations = itemView.findViewById(R.id.til_observations);
            etObservations = itemView.findViewById(R.id.et_observations);
            btnSaveObs = itemView.findViewById(R.id.btn_save_obs);
            tvObservationsDisplay = itemView.findViewById(R.id.tv_observations_display);
            btnAction = itemView.findViewById(R.id.btn_action);
        }

        void bind(GuestRecord record, boolean isProcessedTab, OnGuestRecordListener listener) {
            tvName.setText(record.getNomeCompleto() != null ? record.getNomeCompleto() : "");
            tvRoom.setText("Quarto " + record.getQuarto());
            tvArrivalTime.setText("Chegada: " + (record.getHoraChegada() != null ? record.getHoraChegada() : ""));
            tvDepartureDate.setText("Saída: " + (record.getDataSaida() != null ? record.getDataSaida() : ""));
            tvPeople.setText("Pessoas: " + record.getNPessoas() + " (" + record.getAcompanhantes() + " Acomp.)");
            tvLanguage.setText("Idioma: " + (record.getIdioma() != null ? record.getIdioma() : ""));
            tvVip.setText("VIP: " + (record.getVipStatus() != null ? record.getVipStatus() : "N/A"));

            if (isProcessedTab) {
                tilObservations.setVisibility(View.GONE);
                btnSaveObs.setVisibility(View.GONE);
                tvObservationsDisplay.setVisibility(View.VISIBLE);
                String obs = record.getObservacoes();
                tvObservationsDisplay.setText(obs != null && !obs.isEmpty() ? obs : "");
                btnAction.setText("Processado");
                btnAction.setEnabled(false);
            } else {
                tilObservations.setVisibility(View.VISIBLE);
                btnSaveObs.setVisibility(View.VISIBLE);
                tvObservationsDisplay.setVisibility(View.GONE);
                etObservations.setText(record.getObservacoes() != null ? record.getObservacoes() : "");
                btnAction.setText("Processar");
                btnAction.setEnabled(true);
                btnAction.setOnClickListener(v -> {
                    if (listener != null) listener.onProcessClicked(record);
                });
                btnSaveObs.setOnClickListener(v -> {
                    if (listener != null) {
                        listener.onSaveObservation(record, etObservations.getText() != null ? etObservations.getText().toString() : "");
                    }
                });
            }
        }
    }
}
