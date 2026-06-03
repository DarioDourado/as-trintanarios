package com.example.trintanarios;

public class GuestRecord {
    private String id;
    private String nomeCompleto;
    private String saudacao;
    private int quarto;
    private String horaChegada;
    private int acompanhantes;
    private int nPessoas;
    private String dataSaida;
    private String idioma;
    private String vipStatus;
    private String observacoes;
    private boolean isProcessed;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    
    public String getSaudacao() { return saudacao; }
    public void setSaudacao(String saudacao) { this.saudacao = saudacao; }
    
    public int getQuarto() { return quarto; }
    public void setQuarto(int quarto) { this.quarto = quarto; }
    
    public String getHoraChegada() { return horaChegada; }
    public void setHoraChegada(String horaChegada) { this.horaChegada = horaChegada; }
    
    public int getAcompanhantes() { return acompanhantes; }
    public void setAcompanhantes(int acompanhantes) { this.acompanhantes = acompanhantes; }
    
    public int getNPessoas() { return nPessoas; }
    public void setNPessoas(int nPessoas) { this.nPessoas = nPessoas; }
    
    public String getDataSaida() { return dataSaida; }
    public void setDataSaida(String dataSaida) { this.dataSaida = dataSaida; }
    
    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    
    public String getVipStatus() { return vipStatus; }
    public void setVipStatus(String vipStatus) { this.vipStatus = vipStatus; }
    
    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
    
    public boolean isProcessed() { return isProcessed; }
    public void setIsProcessed(boolean isProcessed) { this.isProcessed = isProcessed; }
}
