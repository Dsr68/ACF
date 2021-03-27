package com.example.sospirangueiro.modelbean;

public class Metas extends Gastos{
    private double limite;
    private String tempo;

    public Metas(){

    }

    public Metas(String id, String descricao, double limite, String tempo){
        super(id, descricao);

        this.limite = limite;
        this.tempo = tempo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }
}
