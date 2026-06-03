package com.example.trintanarios;

public class ObservationPayload {
    private String id;
    private String observacoes;

    public ObservationPayload(String id, String observacoes) {
        this.id = id;
        this.observacoes = observacoes;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}
